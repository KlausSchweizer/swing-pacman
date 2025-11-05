package multiplayer.network;

import mapa.Mapa;
import personagem.Personagem;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private Mapa mapa;
    private List<Personagem> personagems;
}
