package multiplayer.cliente;

import itens.Item;
import itens.Ponto;
import itens.PowerUp;
import main.Direcao;
import multiplayer.fantasmas.MultiplayerFantasmaCiano;
import multiplayer.fantasmas.MultiplayerFantasmaLaranja;
import multiplayer.fantasmas.MultiplayerFantasmaRosa;
import multiplayer.fantasmas.MultiplayerFantasmaVermelho;
import multiplayer.network.Request;
import multiplayer.network.Response;
import multiplayer.server.Server;
import personagem.Personagem;
import personagem.fantasma.Fantasma;
import personagem.fantasma.StatusFantasma;
import personagem.pacman.Pacman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteHandler extends Thread {
    private final Socket socket;
    private Cliente cliente;
    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            cliente = solicitarCliente();
            Server.getInstance().getClientes().add(cliente);

            while (socket.isConnected() && !socket.isClosed()) {

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void processarRequest(Object obj) {
        Request request = (Request) obj;
        cliente.getPersonagem().setDirecao(request.getDirecao());
    }

    public Cliente solicitarCliente() throws IOException, ClassNotFoundException {
        return null;
    }

    public void enviar(Response response) throws IOException {
        enviarMapa(response);
        enviarItens(response);

        for (Personagem personagem : response.getPersonagems()) {
            if (personagem != null) {
                enviarTipoPersonagem(personagem);

                if (personagem instanceof Fantasma) {
                    enviarStatusFantasma((Fantasma) personagem);
                }

                enviarDirecao(personagem);
                enviarPosicao(personagem);
            }
        }
    }

    private void enviarMapa(Response response) throws IOException {
        out.writeChars(response.getMapa().getNomeMapa());
    }

    private void enviarPosicao(Personagem personagem) throws IOException {
        out.write(personagem.getPosY());
        out.write(personagem.getPosX());
    }

    private void enviarDirecao(Personagem personagem) throws IOException {
        final int CIMA = 0;
        final int BAIXO = 1;
        final int DIREITA = 2;
        final int ESQUERDA = 3;

        if (personagem.getDirecao() == Direcao.CIMA) {
            out.write(CIMA);
        } else if (personagem.getDirecao() == Direcao.BAIXO) {
            out.write(BAIXO);
        } else if (personagem.getDirecao() == Direcao.ESQUERDA) {
            out.write(ESQUERDA);
        } else if (personagem.getDirecao() == Direcao.DIREITA) {
            out.write(DIREITA);
        }
    }

    private void enviarStatusFantasma(Fantasma personagem) throws IOException {
        final int PERSEGUIDOR = 0;
        final int ALVO = 1;

        Fantasma fantasma = personagem;
        if (fantasma.getStatus() == StatusFantasma.PERSEGUIDOR) {
            out.write(PERSEGUIDOR);
        } else if (fantasma.getStatus() == StatusFantasma.ALVO) {
            out.write(ALVO);
        }
    }

    private void enviarTipoPersonagem(Personagem personagem) throws IOException {
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

    private void enviarItens(Response response) throws IOException {
        for (Item item : response.getItems()) {
            final int VAZIO = 0;
            final int PONTO = 1;
            final int POWERUP = 2;

            if (item == null) {
                out.write(VAZIO);
            } else if (item instanceof Ponto) {
                out.write(PONTO);
            } else if (item instanceof PowerUp) {
                out.write(POWERUP);
            }
        }
    }
}
