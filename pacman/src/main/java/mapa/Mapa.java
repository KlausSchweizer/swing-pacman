/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

/**
 *
 * @author klaus
 */
public class Mapa {

    private Tile[][] mapa;
    private int tileSize;
    private char[][] textoMapa;

    public Mapa() {
        tileSize = 32;
    }

    public Tile[][] getMapa() {
        return mapa;
    }

    public void setMapa(Tile[][] mapa) {
        this.mapa = mapa;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public char[][] getTextoMapa() {
        return textoMapa;
    }

    public void setTextoMapa(char[][] textoMapa) {
        this.textoMapa = textoMapa;
    }

    
}
