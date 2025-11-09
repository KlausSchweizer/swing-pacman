package multiplayer.network;

import itens.Item;
import mapa.Mapa;
import personagem.Personagem;

import java.util.List;

public class Response {
    private Mapa mapa;
    private List<Item> items;
    private List<Personagem> personagems;

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Personagem> getPersonagems() {
        return personagems;
    }

    public void setPersonagems(List<Personagem> personagems) {
        this.personagems = personagems;
    }
}
