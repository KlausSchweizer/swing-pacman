/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.pacman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Direcao;
import itens.Ponto;
import itens.PowerUp;
import singleplayer.EventosGame;
import mapa.Mapa;
import personagem.Personagem;

/**
 *
 * @author Intel
 */
public class Pacman extends Personagem {
    private EventosGame eventos;

    public Pacman(int posY, int posX) {
        super(posY, posX);
        try {
            BufferedImage sprite1 = ImageIO.read(getClass().getResource("/imagens"
                    + "/pacman/PACMAN-1.png"));

            spritesAndando = new BufferedImage[]{sprite1};
            spritesAtuais = new BufferedImage[]{sprite1};
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void morrer() {

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
        } else if (direcao == Direcao.CIMA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.CIMA)) {
                this.posY--;
                if (this.posY < 0) {
                    this.posY = maximoY;
                }
            }
        } else if (direcao == Direcao.DIREITA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.DIREITA)) {
                this.posX++;
                if (this.posX > maximoX) {
                    this.posX = 0;
                }
            }
        } else if (direcao == Direcao.ESQUERDA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.ESQUERDA)) {
                this.posX--;
                if (this.posX < 0) {
                    this.posX = maximoX;
                }
            }
        }

        if (mapa.getMapa()[posY][posX].getItem() instanceof Ponto && eventos != null) {
            eventos.adicionarPonto();
        } else if (mapa.getMapa()[posY][posX].getItem() instanceof PowerUp && eventos != null) {
            eventos.usarPowerUp();
        }
        mapa.getMapa()[posY][posX].setItem(null);
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa) {
        BufferedImage imagemAtual = spritesAtuais[0];
        g2d.drawImage(imagemAtual, posX * tileSize, posY * tileSize, tileSize, tileSize, null);
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
}
