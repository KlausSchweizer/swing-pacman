/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem;

import enums.Direcao;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.util.List;
import mapa.Mapa;

/**
 *
 * @author klaus
 */
public abstract class Personagem {

    protected int posX;
    protected int posY;
    protected int velocidade;
    protected Direcao direcao;
    protected BufferedImage[] spritesAtuais;
    protected BufferedImage[] spritesAndando;
    protected BufferedImage[] spritesMorte;
    protected int andandoFPS;
    protected int morteFPS;

    public Personagem(int posY, int posX) {
        this.posY = posY;
        this.posX = posX;
    }

    public abstract void draw(Graphics2D g, int tileSize, Mapa mapa);
    public abstract void morrer();
    
    public void mover(Direcao direcao) {
        if (direcao == Direcao.BAIXO) {
            this.posY++;
        } else if (direcao == Direcao.CIMA) {
            this.posY--;
        } else if (direcao == Direcao.DIREITA) {
            this.posX++;
        } else if (direcao == Direcao.ESQUERDA) {
            this.posX--;
        }
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

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public BufferedImage[] getSpritesAtuais() {
        return spritesAtuais;
    }

    public void setSpritesAtuais(BufferedImage[] spritesAtuais) {
        this.spritesAtuais = spritesAtuais;
    }

    public BufferedImage[] getSpritesAndando() {
        return spritesAndando;
    }

    public void setSpritesAndando(BufferedImage[] spritesAndando) {
        this.spritesAndando = spritesAndando;
    }

    public BufferedImage[] getSpritesMorte() {
        return spritesMorte;
    }

    public void setSpritesMorte(BufferedImage[] spritesMorte) {
        this.spritesMorte = spritesMorte;
    }

    public int getAndandoFPS() {
        return andandoFPS;
    }

    public void setAndandoFPS(int andandoFPS) {
        this.andandoFPS = andandoFPS;
    }

    public int getMorteFPS() {
        return morteFPS;
    }

    public void setMorteFPS(int morteFPS) {
        this.morteFPS = morteFPS;
    }

}
