package multiplayer.cliente;

import personagem.Personagem;

import java.net.Socket;

public class Cliente {
    private int id;
    private String nome;
    private final Socket socket;
    private Personagem personagem;

    public Cliente(Socket socket) {
        this.socket = socket;
    }
}
