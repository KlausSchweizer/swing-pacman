package multiplayer;

import main.Main;
import main.TipoUsuario;
import multiplayer.cliente.ClienteSocket;
import multiplayer.network.Request;
import multiplayer.network.Response;
import multiplayer.panels.lobby.LobbyPanel;
import multiplayer.server.Server;
import personagem.Personagem;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;
import singleplayer.Game;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiplayerGame extends Game {
    private static TipoUsuario tipoUsuario;
    private ClienteSocket clienteSocket;
    private Personagem meuPersonagem;

    public static TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public static void setTipoUsuario(TipoUsuario tipoUsuario) {
        MultiplayerGame.tipoUsuario = tipoUsuario;
    }

    @Override
    public void update() {
        try {
            enviarRequest();
            tratarResponse();

            if (tipoUsuario == TipoUsuario.SERVIDOR && System.currentTimeMillis() - Server.getInstance().getUltimoBroadCast() >= 100) {
                enviarResponseServidor();
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

    private void enviarResponseServidor() throws IOException {
        Response responseEnvio = new Response();
        responseEnvio.setMapa(this.mapa);

        List<Personagem> listaPersonagems = new ArrayList<>();
        listaPersonagems.add(this.pacman);
        listaPersonagems.addAll(this.fantasmas);
        responseEnvio.setPersonagems(listaPersonagems);

        Server.getInstance().broadcast(responseEnvio);

        Server.getInstance().setUltimoBroadCast(System.currentTimeMillis());
    }

    private void tratarResponse() {
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
    }

    private void enviarRequest() {
        Request request = new Request();
        request.setDirecao(meuPersonagem.getDirecao());
        clienteSocket.enviar(request);
    }

    @Override
    public void finish() {
        isRunning = false;
        SwingUtilities.invokeLater(() -> {
            Main.configurarPanel(new LobbyPanel(mapa, fantasmas, pacman));
        });
    }

    public ClienteSocket getClienteSocket() {
        return clienteSocket;
    }

    public void setClienteSocket(ClienteSocket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    public Personagem getMeuPersonagem() {
        return meuPersonagem;
    }

    public void setMeuPersonagem(Personagem meuPersonagem) {
        this.meuPersonagem = meuPersonagem;
    }
}
