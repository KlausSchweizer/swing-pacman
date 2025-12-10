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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    protected BufferedImage[] spritesAlvoCima;
    protected BufferedImage[] spritesAlvoBaixo;
    protected BufferedImage[] spritesAlvoDireita;
    protected BufferedImage[] spritesAlvoEsquerda;
    protected int medoFPS;
    protected ExplorarCaminho explorador;
    protected Posicao alvo;
    public static final int VERMELHO = 0;
    public static final int ROSA = 1;
    public static final int AZUL = 2;
    public static final int LARANJA = 3;
    protected long tempoInicializacao;

    public Fantasma(int posY, int posX) {
        super(posY, posX);
        indiceSprite = 0;

        this.direcao = Direcao.CIMA;
        this.status = StatusFantasma.PERSEGUIDOR;
        this.explorador = new ExplorarCaminho();
        tempoInicializacao = System.currentTimeMillis();
    }

    public abstract Direcao decidirDirecao(Pacman pacman, Mapa mapa);

    @Override
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa, int inicioX, int inicioY) {
        BufferedImage imagemAtual = spritesAtuais[indiceSprite];
        g2d.drawImage(imagemAtual, posX * tileSize + inicioX, posY * tileSize + inicioY, tileSize, tileSize, null);
    }

    @Override
    public void mover(Mapa mapa) {
        if (status == StatusFantasma.PERSEGUIDOR) {
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
        } else if (status == StatusFantasma.ALVO) {
            if (direcao == Direcao.CIMA) {
                this.posY--;
                spritesAtuais = spritesAlvoCima;
            } else if (direcao == Direcao.BAIXO) {
                this.posY++;
                spritesAtuais = spritesAlvoBaixo;
            } else if (direcao == Direcao.ESQUERDA) {
                this.posX++;
                spritesAtuais = spritesAlvoEsquerda;
            } else {
                this.posX--;
                spritesAtuais = spritesAlvoDireita;
            }
            indiceSprite++;
            if (indiceSprite >= spritesAtuais.length) {
                indiceSprite = 0;
            }
        }
    }

    public void carregarSprites() {
        try {
            BufferedImage spriteCima1 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/cima/FANTASMAMEDO-CIMA1.png"));
            BufferedImage spriteCima2 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/cima/FANTASMAMEDO-CIMA2.png"));
            BufferedImage spriteCima3 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/cima/FANTASMAMEDO-CIMA3.png"));
            spritesAlvoCima = new BufferedImage[] {spriteCima1, spriteCima2, spriteCima3};

            BufferedImage spriteBaixo1 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/baixo/FANTASMAMEDO-BAIXO1.png"));
            BufferedImage spriteBaixo2 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/baixo/FANTASMAMEDO-BAIXO2.png"));
            BufferedImage spriteBaixo3 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/baixo/FANTASMAMEDO-BAIXO3.png"));
            spritesAlvoBaixo = new BufferedImage[] {spriteBaixo1, spriteBaixo2, spriteBaixo3};

            BufferedImage spriteDireita1 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/direita/FANTASMAMEDO-DIREITA1.png"));
            BufferedImage spriteDireita2 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/direita/FANTASMAMEDO-DIREITA2.png"));
            BufferedImage spriteDireita3 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/direita/FANTASMAMEDO-DIREITA3.png"));
            spritesAlvoDireita = new BufferedImage[] {spriteDireita1, spriteDireita2, spriteDireita3};

            BufferedImage spriteEsquerda1 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/esquerda/FANTASMAMEDO-ESQUERDA1.png"));
            BufferedImage spriteEsquerda2 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/esquerda/FANTASMAMEDO-ESQUERDA2.png"));
            BufferedImage spriteEsquerda3 = ImageIO.read(getClass().getResourceAsStream("/imagens/fantasmas/FantasmaMedo/esquerda/FANTASMAMEDO-ESQUERDA3.png"));
            spritesAlvoEsquerda = new BufferedImage[] {spriteEsquerda1, spriteEsquerda2, spriteEsquerda3};

        } catch(IOException e) {
            throw new RuntimeException();
        }
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
            if(direcao == Direcao.CIMA) {
                spritesAtuais = spritesAlvoCima;
            } else if (direcao == Direcao.BAIXO) {
                spritesAtuais = spritesAlvoBaixo;
            } else if(direcao == Direcao.ESQUERDA) {
                spritesAtuais = spritesAlvoEsquerda;
            } else {
                spritesAtuais = spritesAlvoDireita;
            }
            status = StatusFantasma.ALVO;
        } else {
            spritesAtuais = spritesAndandoCima;
            status = StatusFantasma.PERSEGUIDOR;
        }
    }

    public StatusFantasma getStatus() {
        return status;
    }

    public void setStatus(StatusFantasma status) {
        this.status = status;
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

    public void setTempoInicializacao(long tempoInicializacao) {
        this.tempoInicializacao = tempoInicializacao;
    }
}
