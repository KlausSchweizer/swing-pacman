package multiplayer.network;

import main.Direcao;

import java.io.Serializable;

public class Request implements Serializable {
    private int idCliente;
    private Direcao direcao;
}
