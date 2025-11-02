package multiplayer.server;

import multiplayer.cliente.Cliente;
import multiplayer.cliente.ClienteHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final ServerSocket serverSocket;
    private List<Cliente> clientes;
    private List<ClienteHandler> clienteHandlers;
    private int port;
    private int maximoJogadores;
    private boolean isLobby;
    public static Server instance;

    private Server(int port) throws IOException {
        this.port = port;
        clientes = new ArrayList<>();
        clienteHandlers = new ArrayList<>();
        this.serverSocket = new ServerSocket(port);
        isLobby = true;
        configureServer();
    }

    public static Server getInstance() throws IOException {
        if (instance == null) {
            throw new IOException("Use o getInstance(int port), não há servidor criado");
        }
        return instance;
    }

    public static Server getInstance(int port) throws IOException {
        if (instance == null) {
            instance = new Server(port);
        }
        return instance;
    }

    private void configureServer() throws IOException {
        //nao tenho certeza se farei algo aqui
    }

    public void startServer() {
        Cliente clienteLocal = new Cliente();
        clienteLocal.setId(0);
        clientes.add(clienteLocal);

        Thread threadConexoes = new Thread(() -> {
            try {
                while (!serverSocket.isClosed() && maximoJogadores >= clientes.size() && isLobby) {
                    Socket socket = serverSocket.accept();
                    ClienteHandler handler = new ClienteHandler(socket);
                    clienteHandlers.add(handler);
                    handler.start();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });

        threadConexoes.start();

    }

    public void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void broadcast(Object mensagem) throws IOException {
        for (ClienteHandler handler : clienteHandlers) {
            handler.enviarObjeto(mensagem);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaximoJogadores() {
        return maximoJogadores;
    }

    public void setMaximoJogadores(int maximoJogadores) {
        this.maximoJogadores = maximoJogadores;
    }

    public List<ClienteHandler> getClienteHandlers() {
        return clienteHandlers;
    }

    public void setClienteHandlers(List<ClienteHandler> clienteHandlers) {
        this.clienteHandlers = clienteHandlers;
    }

    public boolean isLobby() {
        return isLobby;
    }

    public void setLobby(boolean lobby) {
        isLobby = lobby;
    }
}
