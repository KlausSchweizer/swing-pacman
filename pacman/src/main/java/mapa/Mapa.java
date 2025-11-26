/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import itens.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author klaus
 */
public class Mapa {

    private final List<Posicao> spawnsFantasma;
    private Tile[][] mapa;
    private char[][] textoMapa;
    private Posicao spawnPacman;
    private String nomeMapa;
    private List<Posicao> coordenadas;

    public Mapa() {
        spawnsFantasma = new ArrayList<>();
    }

    public void draw(Graphics2D g2d, int tileSize, int inicioX, int inicioY) {
        for (int linha = 0; linha < mapa.length; ++linha) {
            for (int coluna = 0; coluna < mapa[linha].length; ++coluna) {
                Tile tile = mapa[linha][coluna];
                if (tile != null && tile.getImage() != null) {
                    g2d.drawImage(tile.getImage(), coluna * tileSize + inicioX, linha * tileSize + inicioY, tileSize, tileSize, null);

                    Item item = mapa[linha][coluna].getItem();
                    if (item != null && item.getImages().getFirst() != null) {
                        g2d.drawImage(item.getImages().getFirst(), coluna * tileSize + inicioX, linha * tileSize + inicioY, tileSize, tileSize, null);
                    }
                    continue;
                }

                g2d.setColor(Color.BLACK);
                g2d.fillRect(coluna * tileSize, linha * tileSize, tileSize, tileSize);
            }
        }
    }

    public Tile[][] getMapa() {
        return mapa;
    }

    public void setMapa(Tile[][] mapa) {
        this.mapa = mapa;
    }

    public char[][] getTextoMapa() {
        return textoMapa;
    }

    public void setTextoMapa(char[][] textoMapa) {
        this.textoMapa = textoMapa;
    }

    public Posicao getSpawnPacman() {
        return spawnPacman;
    }

    public void setSpawnPacman(Posicao spawnPacman) {
        this.spawnPacman = spawnPacman;
    }

    public List<Posicao> getSpawnsFantasma() {
        return spawnsFantasma;
    }

    public String getNomeMapa() {
        return nomeMapa;
    }

    public void setNomeMapa(String nomeMapa) {
        this.nomeMapa = nomeMapa;
    }

    public void adicionarSpawnFantasma(Posicao posicao) {
        this.spawnsFantasma.add(posicao);
    }

    public List<Posicao> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Posicao> coordenadas) {
        this.coordenadas = coordenadas;
    }
}
