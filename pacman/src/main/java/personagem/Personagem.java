/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem;

import main.Direcao;
import mapa.Mapa;
import mapa.matrizmapa.MatrizMapa;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author klaus
 */
public abstract class Personagem {

    protected int posX;
    protected int posY;
    protected Float pixelX;
    protected Float pixelY;
    protected int velocidade;
    protected Direcao direcao;
    protected BufferedImage[] spritesAtuais;
    protected BufferedImage[] spritesAndando;
    protected BufferedImage[] spritesMorte;
    protected int morteFPS;

    public Personagem(int posY, int posX) {
        this.posY = posY;
        this.posX = posX;
    }

    public void initPixels(int tileSize) {
        this.pixelX = (float) (posX * tileSize);
        this.pixelY = (float) (posY * tileSize);
    }

    public void draw(Graphics2D g2d, int tileSize, Mapa mapa, int inicioX, int inicioY) {
        if (pixelX == null || pixelY == null) {
            return;
        }

        int drawX = inicioX + Math.round(pixelX);
        int drawY = inicioY + Math.round(pixelY);

        g2d.drawImage(spritesAtuais[0], drawX, drawY, tileSize, tileSize, null);
    }

    ;

    public abstract void morrer();

    public void mover(Mapa mapa, int tileSize) {
        if (pixelX == null || pixelY == null) {
            initPixels(tileSize);
        }

        float antigoPixelX = pixelX;
        float antigoPixelY = pixelY;

        if (direcao == Direcao.BAIXO) {
            pixelY += velocidade;
        } else if (direcao == Direcao.CIMA) {
            pixelY -= velocidade;
        } else if (direcao == Direcao.DIREITA) {
            pixelX += velocidade;
        } else if (direcao == Direcao.ESQUERDA) {
            pixelX -= velocidade;
        }

        int novoTileX = (int) ((pixelX + tileSize / 2f) / tileSize);
        int novoTileY = (int) ((pixelY + tileSize / 2f) / tileSize);
        if (novoTileX != posX || novoTileY != posY) {
            if (!(mapa.getTextoMapa()[novoTileY][novoTileX] == MatrizMapa.LIVRE ||
                    mapa.getTextoMapa()[novoTileY][novoTileX] == MatrizMapa.VAZIO ||
                    mapa.getTextoMapa()[novoTileY][novoTileX] == MatrizMapa.PACMAN ||
                    mapa.getTextoMapa()[novoTileY][novoTileX] == MatrizMapa.SAIDA_FANTASMA)) {
                pixelX = antigoPixelX;
                pixelY = antigoPixelY;
                return;
            }

            posX = novoTileX;
            posY = novoTileY;
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

    public int getMorteFPS() {
        return morteFPS;
    }

    public void setMorteFPS(int morteFPS) {
        this.morteFPS = morteFPS;
    }

    public Float getPixelX() {
        return pixelX;
    }

    public void setPixelX(Float pixelX) {
        this.pixelX = pixelX;
    }

    public Float getPixelY() {
        return pixelY;
    }

    public void setPixelY(Float pixelY) {
        this.pixelY = pixelY;
    }
}
