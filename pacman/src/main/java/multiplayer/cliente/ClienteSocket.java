package multiplayer.cliente;

import itens.Item;
import itens.Ponto;
import itens.PowerUp;
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
import personagem.fantasma.Fantasma;
import personagem.fantasma.StatusFantasma;
import personagem.pacman.Pacman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClienteSocket {

    private final Socket socket;
    private final DataOutputStream out;
    private final DataInputStream in;
    private volatile Response ultimoResponse;
    private final Object sincronizador;

    public ClienteSocket(InetAddress ip, int porta) throws IOException {
        socket = new Socket(ip, porta);
        socket.setKeepAlive(true);
        socket.setSoTimeout(0);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        sincronizador = new Object();
        lerDados();
    }

    public void enviarRequest(Request request) throws IOException {
        final int CIMA = 0;
        final int BAIXO = 1;
        final int DIREITA = 2;
        final int ESQUERDA = 3;

        Direcao direcao = request.getDirecao();

        synchronized (sincronizador) {
            if (direcao == Direcao.CIMA) {
                out.writeInt(CIMA);
            } else if (direcao == Direcao.BAIXO) {
                out.writeInt(BAIXO);
            } else if (direcao == Direcao.ESQUERDA) {
                out.writeInt(ESQUERDA);
            } else if (direcao == Direcao.DIREITA) {
                out.writeInt(DIREITA);
            }
            out.flush();
        }
    }

    public void enviarCliente(Cliente cliente) throws IOException {
        synchronized (sincronizador) {
            out.writeUTF(cliente.getNome());
            out.flush();

            Personagem personagem = cliente.getPersonagem();

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
    }


    public void lerDados() {
        new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    ultimoResponse = receber();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }).start();
    }

    public Response receber() throws IOException {
        Response response = new Response();

        String stringMapa = in.readUTF();
        Mapa mapa = new TxtParser().criarMapa(stringMapa);

        List<Item> itens = new LinkedList<>();

        for (int i = 0; i < mapa.getMapa().length; i++) {
            for (int j = 0; j < mapa.getMapa()[0].length; j++) {
                int item = in.readInt();
                final int VAZIO = 0;
                final int PONTO = 1;
                final int POWERUP = 2;

                if (item == VAZIO) {
                    itens.add(null);
                } else if (item == PONTO) {
                    itens.add(new Ponto());
                } else if (item == POWERUP) {
                    itens.add(new PowerUp());
                }
                mapa.getMapa()[i][j].setItem(itens.getLast());
            }
        }

        int quantidadePersonagens = in.readInt();
        List<Personagem> personagens = new LinkedList<>();

        for(int i = 0; i < quantidadePersonagens; i++) {
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
                personagem =  new MultiplayerFantasmaRosa(posY, posX);
            } else if (tipoPersonagem == CIANO) {
                personagem = new MultiplayerFantasmaCiano(posY, posX);
            } else if (tipoPersonagem == LARANJA) {
                personagem = new  MultiplayerFantasmaLaranja(posY, posX);
            } else {
                personagem = new Pacman(posY, posX);
            }

            int numeroDirecao =  in.readInt();
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

            if(personagem instanceof Fantasma) {
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

            personagens.add(personagem);
        }
        response.setItems(itens);
        response.setMapa(mapa);
        response.setPersonagems(personagens);

        return response;
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
