package multiplayer.cliente;

import multiplayer.network.Request;
import multiplayer.server.Server;

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
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

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
        
    }

    public Cliente solicitarCliente() throws IOException, ClassNotFoundException {
        return (Cliente) in.readObject();
    }

    public void enviarObjeto(Object mensagem) throws IOException {
        out.writeObject(mensagem);
    }
}
