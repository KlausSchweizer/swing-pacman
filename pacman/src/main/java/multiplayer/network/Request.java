package multiplayer.network;

import main.Direcao;

import java.io.Serializable;

public class Request implements Serializable {
    private Direcao direcao;

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}
