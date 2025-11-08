package multiplayer;

import main.Main;
import main.TipoUsuario;
import multiplayer.cliente.ClienteSocket;
import multiplayer.network.Response;
import multiplayer.panels.lobby.LobbyPanel;
import multiplayer.server.Server;
import personagem.Personagem;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;
import singleplayer.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MultiplayerGame extends Game {
    private ClienteSocket clienteSocket;
    private static TipoUsuario tipoUsuario;

    @Override
    public void update() {
        try {

            Response responseSocket = clienteSocket.getUltimoResponse();
            if (responseSocket != null) {
                this.mapa = responseSocket.getMapa();
                List<Personagem> personagens = new ArrayList<>(responseSocket.getPersonagems());
                for (Personagem personagem : personagens) {
                    if (personagem instanceof Pacman) {
                        this.pacman = (Pacman) personagem;
                    } else if (personagem instanceof Fantasma) {
                        this.fantasmas.add((Fantasma) personagem);
                    }
                }
            }
            if (tipoUsuario == TipoUsuario.SERVIDOR &&
                    System.currentTimeMillis() - Server.getInstance().getUltimoBroadCast() >= 100) {
                Response responseEnvio = new Response();
                responseEnvio.setMapa(this.mapa);

                List<Personagem> listaPersonagems = new ArrayList<>();
                listaPersonagems.add(this.pacman);
                listaPersonagems.addAll(this.fantasmas);
                responseEnvio.setPersonagems(listaPersonagems);

                Server.getInstance().broadcast(responseEnvio);

                Server.getInstance().setUltimoBroadCast(System.currentTimeMillis());
            }
            for (Fantasma fantasma : fantasmas) {
                fantasma.setDirecao(fantasma.decidirDirecao(pacman, mapa));
                fantasma.mover(mapa);
                checarColisoes(fantasma);
            }
            pacman.mover(mapa);
            panel.repaint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void finish() {
        isRunning = false;
        SwingUtilities.invokeLater(() -> {
            Main.configurarPanel(new LobbyPanel(mapa, fantasmas, pacman));
        });
    }

    public static TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public static void setTipoUsuario(TipoUsuario tipoUsuario) {
        MultiplayerGame.tipoUsuario = tipoUsuario;
    }
}
