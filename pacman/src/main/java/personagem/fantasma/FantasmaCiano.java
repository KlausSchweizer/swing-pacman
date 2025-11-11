/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;
import mapa.Mapa;
import mapa.Posicao;
import personagem.pacman.Pacman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Intel
 */
public class FantasmaCiano extends Fantasma {
    private FantasmaRosa fantasmaRosa;

    public FantasmaCiano(int posY, int posX) {
        super(posY, posX);

        try {
            BufferedImage sprite1 = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaCiano/FantasmaCianoCima.png"));

            spritesAndando = new BufferedImage[]{sprite1};
            spritesAtuais = new BufferedImage[]{sprite1};
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        int posX = (pacman.getPosX() + fantasmaRosa.getPosX()) / 2;
        int posY = (pacman.getPosY() + fantasmaRosa.getPosY()) / 2;
        Posicao alvo = new Posicao(posX, posY);

        if (alvo.getPosX() < 0) {
            alvo.setPosX(0);
        } else if (alvo.getPosX() >= mapa.getTextoMapa()[0].length) {
            alvo.setPosX(mapa.getTextoMapa()[0].length - 1);
        }

        if (alvo.getPosY() < 0) {
            alvo.setPosY(0);
        } else if (alvo.getPosY() >= mapa.getTextoMapa().length) {
            alvo.setPosY(mapa.getTextoMapa().length - 1);
        }
        Posicao contorno = contornar(alvo, mapa);
        return explorador.decidirDirecao(this.posY, this.posX, contorno.getPosY(), contorno.getPosX(), mapa);
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa) {
        BufferedImage imagemAtual = spritesAtuais[0];
        g2d.drawImage(imagemAtual, posX * tileSize, posY * tileSize, tileSize, tileSize, null);
    }

    public void setFantasmaRosa(FantasmaRosa fantasmaRosa) {
        this.fantasmaRosa = fantasmaRosa;
    }
}
