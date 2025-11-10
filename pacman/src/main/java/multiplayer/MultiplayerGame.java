package multiplayer;

import itens.Item;
import main.Main;
import main.TipoUsuario;
import mapa.Tile;
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
                System.out.println(System.currentTimeMillis());
                for (Fantasma fantasma : fantasmas) {
                    fantasma.mover(mapa);
                    checarColisoes(fantasma);
                }
                if (pacman != null) {
                    pacman.mover(mapa);
                }
            }

            panel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarResponseServidor() throws IOException {
        Response responseEnvio = new Response();
        responseEnvio.setMapa(this.mapa);
        List<Item> itens = new ArrayList<>();
        for (Tile[] tiles : mapa.getMapa()) {
            for (Tile tile : tiles) {
                itens.add(tile.getItem());
            }
        }
        responseEnvio.setItems(itens);

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

            this.fantasmas.clear();

            for (Personagem personagem : personagens) {
                if (personagem instanceof Pacman) {
                    this.pacman = (Pacman) personagem;
                } else if (personagem instanceof Fantasma) {
                    System.out.println("Recebido Fantasma: " + personagem);
                    this.fantasmas.add((Fantasma) personagem);
                }
            }
        }
    }


    private void enviarRequest() {
        try {
            Request request = new Request();
            request.setDirecao(meuPersonagem.getDirecao());
            clienteSocket.enviarRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
