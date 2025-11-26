/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;
import mapa.Mapa;
import mapa.Posicao;
import mapa.matrizmapa.MatrizMapa;
import personagem.Personagem;
import personagem.fantasma.bfs.ExplorarCaminho;
import personagem.pacman.Pacman;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    protected long ultimaMudanca;
    protected ExplorarCaminho explorador;
    protected Posicao alvo;
    public static final int VERMELHO = 0;
    public static final int ROSA = 1;
    public static final int AZUL = 2;
    public static final int LARANJA = 3;

    public Fantasma(int posY, int posX) {
        super(posY, posX);

        this.direcao = Direcao.CIMA;
        this.status = StatusFantasma.PERSEGUIDOR;
        this.explorador = new ExplorarCaminho();
        this.ultimaMudanca = System.currentTimeMillis();
    }

    public abstract Direcao decidirDirecao(Pacman pacman, Mapa mapa);

    @Override
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa, int inicioX, int inicioY) {
        BufferedImage imagemAtual = spritesAtuais[0];
        g2d.drawImage(imagemAtual, posX * tileSize + inicioX, posY * tileSize + inicioY, tileSize, tileSize, null);
    }

    public void morrer() {

    }

    public Posicao contornar(Posicao alvo, Mapa mapa) {
        char[][] mapaTexto = mapa.getTextoMapa();
        int linhas = mapa.getTextoMapa().length;
        int colunas = mapa.getTextoMapa()[0].length;

        if (alvo.getPosX() < 0) {
            alvo.setPosX(0);
        } else if (alvo.getPosX() >= colunas) {
            alvo.setPosX(colunas - 1);
        }
        if (alvo.getPosY() < 0) {
            alvo.setPosY(0);
        } else if (alvo.getPosY() >= linhas) {
            alvo.setPosY(linhas - 1);
        }

        char tileAlvo = mapa.getTextoMapa()[alvo.getPosY()][alvo.getPosX()];

        if (tileAlvo == MatrizMapa.LIVRE || tileAlvo == MatrizMapa.PACMAN) {
            return alvo;
        }

        Queue<Posicao> fila = new LinkedList<>();
        boolean[][] visitados = new boolean[linhas][colunas];

        fila.add(alvo);
        visitados[alvo.getPosY()][alvo.getPosX()] = true;

        while (!fila.isEmpty()) {
            Posicao atual = fila.poll();
            char tile = mapaTexto[atual.getPosY()][atual.getPosX()];
            if (tile == MatrizMapa.LIVRE || tile == MatrizMapa.PACMAN) {
                return atual;
            }

            List<Posicao> possibilidades = new ArrayList<>();
            possibilidades.add(new Posicao(atual.getPosX(), atual.getPosY() + 1));
            possibilidades.add(new Posicao(atual.getPosX(), atual.getPosY() - 1));
            possibilidades.add(new Posicao(atual.getPosX() + 1, atual.getPosY()));
            possibilidades.add(new Posicao(atual.getPosX() - 1, atual.getPosY()));

            for (Posicao possibilidade : possibilidades) {
                int x = possibilidade.getPosX();
                int y = possibilidade.getPosY();
                if (x >= 0 && x < colunas && y >= 0 && y < linhas && !visitados[y][x]) {
                    fila.add(possibilidade);
                    visitados[y][x] = true;
                }
            }
        }

        return alvo;
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
