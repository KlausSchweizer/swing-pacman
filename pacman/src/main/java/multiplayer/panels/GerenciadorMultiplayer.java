package multiplayer.panels;

import itens.Item;
import main.Main;
import mapa.Tile;
import multiplayer.MultiplayerGame;
import multiplayer.cliente.Cliente;
import multiplayer.cliente.ClienteSocket;
import multiplayer.network.Response;
import multiplayer.panels.lobby.Lobby;
import multiplayer.server.Server;
import personagem.Personagem;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorMultiplayer {
    private Lobby lobby;
    private MultiplayerGame game;

    public void criarSala(String nome) throws IOException {
        Main.criarSala();
        Server.getInstance().setLobby(true);

        InetAddress ip = Server.getInstance().getServerSocket().getInetAddress();
        int porta = Server.getInstance().getPort();

        lobby = new Lobby();
        game = lobby;
        lobby.setClienteSocket(new ClienteSocket(ip, porta));

        Cliente cliente = setarCliente(nome);

        lobby.getClienteSocket().enviarCliente(cliente);
        lobby.setMeuPersonagem(cliente.getPersonagem());

        if (lobby.getMeuPersonagem() instanceof Pacman) {
            lobby.setPacman((Pacman) lobby.getMeuPersonagem());
        } else if (lobby.getMeuPersonagem() instanceof Fantasma) {
            lobby.getFantasmas().add((Fantasma) lobby.getMeuPersonagem());
        }
        Response temporarioResponse = definirTemporarioResponse();
        lobby.getClienteSocket().setUltimoResponse(temporarioResponse);

        lobby.start();
    }

    public void entrarSala(String nome, InetAddress ip, int porta) throws IOException {
        lobby = new Lobby();
        ClienteSocket clienteSocket = new ClienteSocket(ip, porta);
        lobby.setClienteSocket(clienteSocket);

        Cliente cliente = setarCliente(nome);
        lobby.getClienteSocket().enviarCliente(cliente);
        lobby.setMeuPersonagem(cliente.getPersonagem());

        game = lobby;
        lobby.start();
    }

    private Response definirTemporarioResponse() {
        Response temporarioResponse = new Response();
        temporarioResponse.setMapa(lobby.getMapa());
        List<Personagem> personagems = new ArrayList<>();
        if (lobby.getPacman() != null) {
            personagems.add(lobby.getPacman());
        }
        personagems.addAll(lobby.getFantasmas());
        List<Item> itens = new ArrayList<>();
        for (Tile[] tiles : lobby.getMapa().getMapa()) {
            for (Tile tile : tiles) {
                itens.add(tile.getItem());
            }
        }
        temporarioResponse.setItems(itens);
        temporarioResponse.setPersonagems(personagems);
        return temporarioResponse;
    }

    private Cliente setarCliente(String nome) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setPersonagem(lobby.escolherPersonagem());
        return cliente;
    }
}
