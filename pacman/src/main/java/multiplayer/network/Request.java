package multiplayer.network;

import main.Direcao;

import java.io.Serializable;

public class Request implements Serializable {
    private int idCliente;
    private Direcao direcao;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}
