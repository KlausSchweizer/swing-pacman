package multiplayer.cliente;

import multiplayer.network.Request;
import multiplayer.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteSocket {

    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private volatile Response ultimoResponse;

    public ClienteSocket(String ip, int porta) throws IOException {
        socket = new Socket(ip, porta);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        lerDados();
    }

    public void enviar(Request request) {
        try {
            out.writeObject(request);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerDados() {
        new Thread(() -> {
            while (!socket.isClosed()) {
                ultimoResponse = receber();
            }
        }).start();
    }

    public Response receber() {
        try {
            return (Response) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
