package multiplayer.cliente;

import main.Direcao;
import mapa.Mapa;
import mapa.TxtParser;
import multiplayer.fantasmas.MultiplayerFantasmaCiano;
import multiplayer.fantasmas.MultiplayerFantasmaLaranja;
import multiplayer.fantasmas.MultiplayerFantasmaRosa;
import multiplayer.fantasmas.MultiplayerFantasmaVermelho;
import multiplayer.network.Request;
import multiplayer.network.Response;
import personagem.Personagem;
import personagem.pacman.Pacman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteSocket {

    private final Socket socket;
    private final DataOutputStream out;
    private final DataInputStream in;
    private volatile Response ultimoResponse;

    public ClienteSocket(InetAddress ip, int porta) throws IOException {
        socket = new Socket(ip, porta);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        lerDados();
    }

    public void enviarRequest(Request request) throws IOException {
        final int CIMA = 0;
        final int BAIXO = 1;
        final int DIREITA = 2;
        final int ESQUERDA = 3;

        Direcao direcao = request.getDirecao();

        if (direcao == Direcao.CIMA) {
            out.write(CIMA);
        } else if (direcao == Direcao.BAIXO) {
            out.write(BAIXO);
        } else if (direcao == Direcao.ESQUERDA) {
            out.write(ESQUERDA);
        } else if (direcao == Direcao.DIREITA) {
            out.write(DIREITA);
        }
    }

    public void enviarCliente(Cliente cliente) throws IOException {
        out.writeChars(cliente.getNome());

        Personagem personagem = cliente.getPersonagem();

        final int PACMAN = 0;
        final int VERMELHO = 1;
        final int ROSA = 2;
        final int CIANO = 3;
        final int LARANJA = 4;

        if (personagem instanceof Pacman) {
            out.write(PACMAN);
        } else if (personagem instanceof MultiplayerFantasmaVermelho) {
            out.write(VERMELHO);
        } else if (personagem instanceof MultiplayerFantasmaRosa) {
            out.write(ROSA);
        } else if (personagem instanceof MultiplayerFantasmaCiano) {
            out.write(CIANO);
        } else if (personagem instanceof MultiplayerFantasmaLaranja) {
            out.write(LARANJA);
        }
    }


    public void lerDados() {
        new Thread(() -> {
            while (!socket.isClosed()) {
//                ultimoResponse = receber();
            }
        }).start();
    }

    public Response receber() {
        //todo receber
        return null;
    }

    public void fechar() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response getUltimoResponse() {
        return ultimoResponse;
    }

    public void setUltimoResponse(Response ultimoResponse) {
        this.ultimoResponse = ultimoResponse;
    }
}
