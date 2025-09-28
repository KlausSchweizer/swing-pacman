/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itens;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author klaus
 */
public abstract class Item {
    protected List<BufferedImage> images;
    protected int numeroFrameAtual;
    protected int numeroFrames;

    public Item() {
        images = new ArrayList<>();
        numeroFrameAtual = 0;
    }

    public List<BufferedImage> getImages() {
        return images;
    }

    public void setImages(List<BufferedImage> images) {
        this.images = images;
    }

    public int getNumeroFrameAtual() {
        return numeroFrameAtual;
    }

    public void setNumeroFrameAtual(int numeroFrameAtual) {
        this.numeroFrameAtual = numeroFrameAtual;
    }

    public int getNumeroFrames() {
        return numeroFrames;
    }

    public void setNumeroFrames(int numeroFrames) {
        this.numeroFrames = numeroFrames;
    }

    
    
}
