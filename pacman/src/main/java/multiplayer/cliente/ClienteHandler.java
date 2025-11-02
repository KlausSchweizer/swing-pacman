package multiplayer.cliente;

import multiplayer.network.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteHandler extends Thread {
    private Cliente cliente;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    public ClienteHandler(Cliente cliente, Socket socket) {
        this.cliente = cliente;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                Request request = (Request) in.readObject();

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarObjeto(Object mensagem) throws IOException {
        out.writeObject(mensagem);
    }
}
