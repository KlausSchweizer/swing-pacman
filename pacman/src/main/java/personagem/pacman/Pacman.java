/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.pacman;

import itens.Ponto;
import itens.PowerUp;
import main.Direcao;
import mapa.Mapa;
import personagem.Personagem;
import singleplayer.EventosGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Intel
 */
public class Pacman extends Personagem {
    private EventosGame eventos;
    private int vidas;

    public Pacman(int posY, int posX) {
        super(posY, posX);
        indiceSprite = 0;
        vidas = 3;
        try {
            criarSpritesCima();
            criarSpritesBaixo();
            criarSpritesDireita();
            criarSpritesEsquerda();

            spritesAtuais = spritesAndandoEsquerda;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void criarSpritesEsquerda() throws IOException {
        BufferedImage spriteEsquerda1 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/esquerda/PACMAN-ESQUERDA1.png"));
        BufferedImage spriteEsquerda2 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/esquerda/PACMAN-ESQUERDA2.png"));
        BufferedImage spriteEsquerda3 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/esquerda/PACMAN-ESQUERDA3.png"));
        BufferedImage spriteEsquerda4 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/esquerda/PACMAN-ESQUERDA4.png"));
        spritesAndandoEsquerda = new BufferedImage[]{spriteEsquerda1, spriteEsquerda2, spriteEsquerda3, spriteEsquerda4};
    }

    private void criarSpritesDireita() throws IOException {
        BufferedImage spriteDireita1 = ImageIO.read(getClass().getResource("/imagens/pacman/direita/PACMAN-DIREITA1.png"));
        BufferedImage spriteDireita2 = ImageIO.read(getClass().getResource("/imagens/pacman/direita/PACMAN-DIREITA2.png"));
        BufferedImage spriteDireita3 = ImageIO.read(getClass().getResource("/imagens/pacman/direita/PACMAN-DIREITA3.png"));
        BufferedImage spriteDireita4 = ImageIO.read(getClass().getResource("/imagens/pacman/direita/PACMAN-DIREITA4.png"));
        spritesAndandoDireita = new BufferedImage[]{spriteDireita1, spriteDireita2, spriteDireita3, spriteDireita4};
    }

    private void criarSpritesBaixo() throws IOException {
        BufferedImage spriteBaixo1 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/baixo/PACMAN-BAIXO1.png"));
        BufferedImage spriteBaixo2 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/baixo/PACMAN-BAIXO2.png"));
        BufferedImage spriteBaixo3 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/baixo/PACMAN-BAIXO3.png"));
        BufferedImage spriteBaixo4 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/baixo/PACMAN-BAIXO4.png"));
        spritesAndandoBaixo = new BufferedImage[]{spriteBaixo1, spriteBaixo2, spriteBaixo3, spriteBaixo4};
    }

    private void criarSpritesCima() throws IOException {
        BufferedImage spriteCima1 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/cima/PACMAN-CIMA1.png"));
        BufferedImage spriteCima2 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/cima/PACMAN-CIMA2.png"));
        BufferedImage spriteCima3 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/cima/PACMAN-CIMA3.png"));
        BufferedImage spriteCima4 = ImageIO.read(getClass().getResource("/imagens"
                + "/pacman/cima/PACMAN-CIMA4.png"));
        spritesAndandoCima = new BufferedImage[]{spriteCima1, spriteCima2, spriteCima3, spriteCima4};
    }

    @Override
    public void morrer() {
        vidas--;
    }

    @Override
    public void mover(Mapa mapa) {
        int maximoY = mapa.getMapa().length - 1;
        int maximoX = mapa.getMapa()[0].length - 1;

        if (direcao == Direcao.BAIXO) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.BAIXO)) {
                this.posY++;
                if (this.posY > maximoY) {
                    this.posY = 0;
                }
            }
            spritesAtuais = spritesAndandoBaixo;
        } else if (direcao == Direcao.CIMA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.CIMA)) {
                this.posY--;
                if (this.posY < 0) {
                    this.posY = maximoY;
                }
            }
            spritesAtuais = spritesAndandoCima;
        } else if (direcao == Direcao.DIREITA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.DIREITA)) {
                this.posX++;
                if (this.posX > maximoX) {
                    this.posX = 0;
                }
            }
            spritesAtuais = spritesAndandoDireita;
        } else if (direcao == Direcao.ESQUERDA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.ESQUERDA)) {
                this.posX--;
                if (this.posX < 0) {
                    this.posX = maximoX;
                }
            }
            spritesAtuais = spritesAndandoEsquerda;
        }

        if (mapa.getMapa()[posY][posX].getItem() instanceof Ponto && eventos != null) {
            eventos.adicionarPonto();
        } else if (mapa.getMapa()[posY][posX].getItem() instanceof PowerUp && eventos != null) {
            eventos.usarPowerUp();
        }
        mapa.getMapa()[posY][posX].setItem(null);

        indiceSprite++;
        if (indiceSprite >= spritesAtuais.length) {
            indiceSprite = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa, int inicioX, int inicioY) {
        BufferedImage imagemAtual = spritesAtuais[indiceSprite];
        g2d.drawImage(imagemAtual, posX * tileSize + inicioX, posY * tileSize + inicioY, tileSize, tileSize, null);
    }

    public void setDirecao(Direcao direcao, Mapa mapa) {
        if (direcao != null) {
            if (this.direcao == null) {
                this.direcao = direcao;
            } else if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(direcao)) {
                this.direcao = direcao;
            }
        }
    }

    public EventosGame getEventos() {
        return eventos;
    }

    public void setEventos(EventosGame eventos) {
        this.eventos = eventos;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }


}
