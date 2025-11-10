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
    private final Object sincronizador;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
        sincronizador = new Object();
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            cliente = solicitarCliente();
            Server.getInstance().getClientes().add(cliente);

            while (socket.isConnected() && !socket.isClosed()) {
                setarDirecao();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void lerCliente() throws IOException {
        String nomeCliente = in.readUTF();
        final int PACMAN = 0;
        final int VERMELHO = 1;
        final int ROSA = 2;
        final int CIANO = 3;
        final int LARANJA = 4;

        Personagem personagem;
        int tipoPersonagem = in.readInt();
        int posY = in.readInt();
        int posX = in.readInt();

        if (tipoPersonagem == PACMAN) {
            personagem = new Pacman(posY, posX);
        } else if (tipoPersonagem == VERMELHO) {
            personagem = new MultiplayerFantasmaVermelho(posY, posX);
        } else if (tipoPersonagem == ROSA) {
            personagem = new MultiplayerFantasmaRosa(posY, posX);
        } else if (tipoPersonagem == CIANO) {
            personagem = new MultiplayerFantasmaCiano(posY, posX);
        } else if (tipoPersonagem == LARANJA) {
            personagem = new MultiplayerFantasmaLaranja(posY, posX);
        } else {
            personagem = new Pacman(posY, posX);
        }

        int numeroDirecao = in.readInt();
        Direcao direcao;

        final int CIMA = 0;
        final int BAIXO = 1;
        final int DIREITA = 2;
        final int ESQUERDA = 3;

        if (numeroDirecao == CIMA) {
            direcao = Direcao.CIMA;
        } else if (numeroDirecao == BAIXO) {
            direcao = Direcao.BAIXO;
        } else if (numeroDirecao == ESQUERDA) {
            direcao = Direcao.ESQUERDA;
        } else if (numeroDirecao == DIREITA) {
            direcao = Direcao.DIREITA;
        } else {
            direcao = Direcao.CIMA;
        }

        personagem.setDirecao(direcao);

        if (personagem instanceof Fantasma) {
            final int PERSEGUIDOR = 0;
            final int ALVO = 1;

            Fantasma fantasma = (Fantasma) personagem;
            int fantasmaStatus = in.readInt();
            StatusFantasma status;

            if (fantasmaStatus == PERSEGUIDOR) {
                status = StatusFantasma.PERSEGUIDOR;
            } else if (fantasmaStatus == ALVO) {
                status = StatusFantasma.ALVO;
            } else {
                status = StatusFantasma.PERSEGUIDOR;
            }

            fantasma.setStatus(status);
        }

        cliente = new Cliente();
        cliente.setPersonagem(personagem);
        cliente.setPersonagem(personagem);
    }

    private void setarDirecao() throws IOException {
        int numeroDirecao = in.readInt();
        Direcao direcao;

        final int CIMA = 0;
        final int BAIXO = 1;
        final int DIREITA = 2;
        final int ESQUERDA = 3;

        if (numeroDirecao == CIMA) {
            direcao = Direcao.CIMA;
        } else if (numeroDirecao == BAIXO) {
            direcao = Direcao.BAIXO;
        } else if (numeroDirecao == DIREITA) {
            direcao = Direcao.DIREITA;
        } else {
            direcao = Direcao.ESQUERDA;
        }
        cliente.getPersonagem().setDirecao(direcao);
    }

    private void processarRequest(Object obj) {
        Request request = (Request) obj;
        cliente.getPersonagem().setDirecao(request.getDirecao());
    }

    public Cliente solicitarCliente() throws IOException, ClassNotFoundException {
        Cliente cliente = new Cliente();
        String nome = in.readUTF();
        Personagem personagem;
        int tipoPersonagem = in.readInt();

        final int PACMAN = 0;
        final int VERMELHO = 1;
        final int ROSA = 2;
        final int CIANO = 3;
        final int LARANJA = 4;

        if (tipoPersonagem == PACMAN) {
            personagem = new Pacman(10, 10);
        } else if (tipoPersonagem == VERMELHO) {
            personagem = new MultiplayerFantasmaVermelho(10, 10);
        } else if (tipoPersonagem == ROSA) {
            personagem = new MultiplayerFantasmaRosa(10, 10);
        } else if (tipoPersonagem == CIANO) {
            personagem = new MultiplayerFantasmaCiano(10, 10);
        } else if (tipoPersonagem == LARANJA) {
            personagem = new MultiplayerFantasmaLaranja(10, 10);
        } else {
            personagem = new Pacman(10, 10);
        }

        cliente.setPersonagem(personagem);
        cliente.setNome(nome);
        return cliente;
    }

    public void enviar(Response response) throws IOException {
        synchronized (sincronizador) {
            enviarMapa(response);
            enviarItens(response);

            out.writeInt(response.getPersonagems().size());
            out.flush();

            for (Personagem personagem : response.getPersonagems()) {
                if (personagem != null) {
                    enviarTipoPersonagem(personagem);
                    enviarPosicao(personagem);
                    enviarDirecao(personagem);

                    if (personagem instanceof Fantasma) {
                        enviarStatusFantasma((Fantasma) personagem);
                    }

                }
            }
        }
    }

    private void enviarMapa(Response response) throws IOException {
        out.writeUTF(response.getMapa().getNomeMapa());
        out.flush();
    }

    private void enviarPosicao(Personagem personagem) throws IOException {
        out.writeInt(personagem.getPosY());
        out.flush();
        out.writeInt(personagem.getPosX());
        out.flush();
    }

    private void enviarDirecao(Personagem personagem) throws IOException {
        final int CIMA = 0;
        final int BAIXO = 1;
        final int DIREITA = 2;
        final int ESQUERDA = 3;

        if (personagem.getDirecao() == Direcao.CIMA) {
            out.writeInt(CIMA);
        } else if (personagem.getDirecao() == Direcao.BAIXO) {
            out.writeInt(BAIXO);
        } else if (personagem.getDirecao() == Direcao.ESQUERDA) {
            out.writeInt(ESQUERDA);
        } else if (personagem.getDirecao() == Direcao.DIREITA) {
            out.writeInt(DIREITA);
        }
        out.flush();
    }

    private void enviarStatusFantasma(Fantasma personagem) throws IOException {
        final int PERSEGUIDOR = 0;
        final int ALVO = 1;

        Fantasma fantasma = personagem;
        if (fantasma.getStatus() == StatusFantasma.PERSEGUIDOR) {
            out.writeInt(PERSEGUIDOR);
        } else if (fantasma.getStatus() == StatusFantasma.ALVO) {
            out.writeInt(ALVO);
        }
        out.flush();
    }

    private void enviarTipoPersonagem(Personagem personagem) throws IOException {
        final int PACMAN = 0;
        final int VERMELHO = 1;
        final int ROSA = 2;
        final int CIANO = 3;
        final int LARANJA = 4;

        if (personagem instanceof Pacman) {
            out.writeInt(PACMAN);
        } else if (personagem instanceof MultiplayerFantasmaVermelho) {
            out.writeInt(VERMELHO);
        } else if (personagem instanceof MultiplayerFantasmaRosa) {
            out.writeInt(ROSA);
        } else if (personagem instanceof MultiplayerFantasmaCiano) {
            out.writeInt(CIANO);
        } else if (personagem instanceof MultiplayerFantasmaLaranja) {
            out.writeInt(LARANJA);
        }
        out.flush();
    }

    private void enviarItens(Response response) throws IOException {
        for (Item item : response.getItems()) {
            final int VAZIO = 0;
            final int PONTO = 1;
            final int POWERUP = 2;

            if (item == null) {
                out.writeInt(VAZIO);
            } else if (item instanceof Ponto) {
                out.writeInt(PONTO);
            } else if (item instanceof PowerUp) {
                out.writeInt(POWERUP);
            }
            out.flush();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
