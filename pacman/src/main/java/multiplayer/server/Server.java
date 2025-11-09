package multiplayer.server;

import multiplayer.cliente.Cliente;
import multiplayer.cliente.ClienteHandler;
import multiplayer.network.Response;

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
    private long ultimoBroadCast;
    public static Server instance;

    private Server() throws IOException {
        port = escolherPorta();
        clientes = new ArrayList<>();
        clienteHandlers = new ArrayList<>();
        this.serverSocket = new ServerSocket(port);
        isLobby = true;
        maximoJogadores = 5;
        configureServer();
    }

    public static Server getInstance() throws IOException {
        if(instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public int escolherPorta() {
        int temporaryPort = 5000;
        while (true) {
            try (ServerSocket temporaryServerSocket = new ServerSocket(temporaryPort)) {
                return temporaryPort;
            } catch (IOException e) {
                temporaryPort++;
            }
        }
    }

    private void configureServer() throws IOException {
        //nao tenho certeza se farei algo aqui
    }

    public void startServer() {
        Cliente clienteLocal = new Cliente();
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

    public void broadcast(Response response) throws IOException {
        for (ClienteHandler handler : clienteHandlers) {
            handler.enviar(response);
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

    public long getUltimoBroadCast() {
        return ultimoBroadCast;
    }

    public void setUltimoBroadCast(long ultimoBroadCast) {
        this.ultimoBroadCast = ultimoBroadCast;
    }
}
