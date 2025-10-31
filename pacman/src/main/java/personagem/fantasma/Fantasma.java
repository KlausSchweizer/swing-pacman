/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import enums.Direcao;
import enums.StatusFantasma;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mapa.Mapa;
import personagem.Personagem;
import personagem.fantasma.bfs.ExplorarCaminho;
import personagem.pacman.Pacman;

/**
 *
 * @author klaus
 */
public abstract class Fantasma extends Personagem {

    protected StatusFantasma status;
    protected BufferedImage[] spritesAlvo;
    protected BufferedImage[] spritesTransformando;
    protected int medoFPS;
    protected int transformandoFPS;
    protected ExplorarCaminho explorador;
    public static final int VERMELHO = 0;
    public static final int ROSA = 1;
    public static final int AZUL = 2;
    public static final int LARANJA = 3;

    public Fantasma(int posY, int posX) {
        super(posY, posX);

        this.direcao = Direcao.CIMA;
        this.status = StatusFantasma.PERSEGUIDOR;
        this.explorador = new ExplorarCaminho();
    }

    protected abstract Direcao decidirDirecao(Pacman pacman, Mapa mapa);

    public abstract void draw(Graphics2D g, int tileSize, Mapa mapa);

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

    public StatusFantasma getStatus() {
        return status;
    }

    public void setStatus(StatusFantasma status) {
        this.status = status;
    }

    public BufferedImage[] getSpritesAlvo() {
        return spritesAlvo;
    }

    public void setSpritesAlvo(BufferedImage[] spritesAlvo) {
        this.spritesAlvo = spritesAlvo;
    }

    public BufferedImage[] getSpritesTransformando() {
        return spritesTransformando;
    }

    public void setSpritesTransformando(BufferedImage[] spritesTransformando) {
        this.spritesTransformando = spritesTransformando;
    }

    public ExplorarCaminho getExplorador() {
        return explorador;
    }

    public void setExplorador(ExplorarCaminho explorador) {
        this.explorador = explorador;
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

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}
