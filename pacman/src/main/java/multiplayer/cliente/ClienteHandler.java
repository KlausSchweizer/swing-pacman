package multiplayer.cliente;

import multiplayer.network.Request;
import multiplayer.server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteHandler extends Thread {
    private Cliente cliente;
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            cliente = solicitarCliente();
            Server.getInstance().getClientes().add(cliente);

            while (socket.isConnected() && !socket.isClosed()) {
                Object obj = in.readObject();

                if(obj instanceof Request) {
                    processarRequest(obj);
                }
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
        cliente = (Cliente) in.readObject();
        return cliente;
    }

    public void enviarObjeto(Object mensagem) throws IOException {
        out.writeObject(mensagem);
    }
}
