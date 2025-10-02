/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasma;

import enums.Direcao;
import enums.StatusFantasma;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author klaus
 */
public abstract class Fantasma {

    protected int posX;
    protected int posY;
    protected int velocidade;
    protected Direcao direcao;
    protected StatusFantasma status;
    protected List<BufferedImage> spritesAtuais;
    protected List<BufferedImage> spritesAndando;
    protected List<BufferedImage> spritesAlvo;
    protected List<BufferedImage> spritesTransformando;
    protected List<BufferedImage> spritesMorte;
    protected int andandoFPS;
    protected int medoFPS;
    protected int transformandoFPS;
    protected int morteFPS;

    public Fantasma() {
        this.direcao = Direcao.CIMA;
        this.status = StatusFantasma.PERSEGUIDOR;
    }

    protected abstract Direcao decidirDirecao();

    public void mover() {
        this.direcao = decidirDirecao();
        if (direcao == Direcao.BAIXO) {
            posY += velocidade;
        } else if (direcao == Direcao.CIMA) {
            posY -= velocidade;
        } else if (direcao == Direcao.DIREITA) {
            posX += velocidade;
        } else {
            posX -= velocidade;
        }
    }

    public void morrer() {

    }

    public void transformar() {
        if (status == StatusFantasma.PERSEGUIDOR) {
            spritesAtuais = spritesAlvo;
            status = StatusFantasma.ALVO;
        } else {
            spritesAtuais = spritesAndando;
            status = StatusFantasma.PERSEGUIDOR;
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

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public StatusFantasma getStatus() {
        return status;
    }

    public void setStatus(StatusFantasma status) {
        this.status = status;
    }

    public List<BufferedImage> getSpritesAtuais() {
        return spritesAtuais;
    }

    public void setSpritesAtuais(List<BufferedImage> spritesAtuais) {
        this.spritesAtuais = spritesAtuais;
    }

    public List<BufferedImage> getSpritesAndando() {
        return spritesAndando;
    }

    public void setSpritesAndando(List<BufferedImage> spritesAndando) {
        this.spritesAndando = spritesAndando;
    }

    public List<BufferedImage> getSpritesAlvo() {
        return spritesAlvo;
    }

    public void setSpritesAlvo(List<BufferedImage> spritesAlvo) {
        this.spritesAlvo = spritesAlvo;
    }

    public List<BufferedImage> getSpritesTransformando() {
        return spritesTransformando;
    }

    public void setSpritesTransformando(List<BufferedImage> spritesTransformando) {
        this.spritesTransformando = spritesTransformando;
    }

    public List<BufferedImage> getSpritesMorte() {
        return spritesMorte;
    }

    public void setSpritesMorte(List<BufferedImage> spritesMorte) {
        this.spritesMorte = spritesMorte;
    }

    public int getAndandoFPS() {
        return andandoFPS;
    }

    public void setAndandoFPS(int andandoFPS) {
        this.andandoFPS = andandoFPS;
    }

    public int getMedoFPS() {
        return medoFPS;
    }

    public void setMedoFPS(int medoFPS) {
        this.medoFPS = medoFPS;
    }

    public int getTransformandoFPS() {
        return transformandoFPS;
    }

    public void setTransformandoFPS(int transformandoFPS) {
        this.transformandoFPS = transformandoFPS;
    }

    public int getMorteFPS() {
        return morteFPS;
    }

    public void setMorteFPS(int morteFPS) {
        this.morteFPS = morteFPS;
    }

}
