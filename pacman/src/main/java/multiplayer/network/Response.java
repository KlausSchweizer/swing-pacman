package multiplayer.network;

import mapa.Mapa;
import personagem.Personagem;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private Mapa mapa;
    private List<Personagem> personagems;

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public List<Personagem> getPersonagems() {
        return personagems;
    }

    public void setPersonagems(List<Personagem> personagems) {
        this.personagems = personagems;
    }
}
