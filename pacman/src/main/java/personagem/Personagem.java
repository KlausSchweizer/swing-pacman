/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem;

import main.Direcao;
import mapa.Mapa;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    protected BufferedImage[] spritesAndandoCima;
    protected BufferedImage[] spritesAndandoBaixo;
    protected BufferedImage[] spritesAndandoEsquerda;
    protected BufferedImage[] spritesAndandoDireita;
    protected BufferedImage[] spritesMorte;
    protected int andandoFPS;
    protected int morteFPS;

    public Personagem(int posY, int posX) {
        this.posY = posY;
        this.posX = posX;
    }

    public abstract void draw(Graphics2D g, int tileSize, Mapa mapa, int inicioX, int inicioY);

    public abstract void morrer();

    public void mover(Mapa mapa) {
        if (direcao == Direcao.BAIXO) {
            this.posY++;
            spritesAtuais = spritesAndandoBaixo;
        } else if (direcao == Direcao.CIMA) {
            this.posY--;
            spritesAtuais = spritesAndandoCima;
        } else if (direcao == Direcao.DIREITA) {
            this.posX++;
            spritesAtuais = spritesAndandoDireita;
        } else if (direcao == Direcao.ESQUERDA) {
            this.posX--;
            spritesAtuais = spritesAndandoEsquerda;
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

    public BufferedImage[] getSpritesAndandoCima() {
        return spritesAndandoCima;
    }

    public void setSpritesAndandoCima(BufferedImage[] spritesAndandoCima) {
        this.spritesAndandoCima = spritesAndandoCima;
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
