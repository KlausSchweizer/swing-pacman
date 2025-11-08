/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import main.Direcao;
import itens.Item;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author klaus
 */
public class Tile implements Serializable {
    private int posX;
    private int posY;
    private BufferedImage image;
    private List<Direcao> direcoesBloqueadas;
    private Item item;

    public Tile(int posX, int posY, BufferedImage image, List<Direcao> direcoesBloqueadas, Item item) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
        this.direcoesBloqueadas = direcoesBloqueadas;
        this.item = item;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public List<Direcao> getDirecoesBloqueadas() {
        return direcoesBloqueadas;
    }

    public void setDirecoesBloqueadas(List<Direcao> direcoesBloqueadas) {
        this.direcoesBloqueadas = direcoesBloqueadas;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    
}
