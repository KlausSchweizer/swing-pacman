/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import mapa.Mapa;
import personagem.pacman.Pacman;

/**
 *
 * @author Intel
 */
public class FantasmaVermelho extends Fantasma {

    public FantasmaVermelho(int posY, int posX) {
        super(posY, posX);

        try {
            BufferedImage spriteCima = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaVermelho/FantasmaVermelhoCima.png"));

            BufferedImage spriteBaixo = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaVermelho/FantasmaVermelhoBaixo.png"));

            BufferedImage spriteDireita = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaVermelho/FantasmaVermelhoDireita.png"));

            BufferedImage spriteEsquerda = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaVermelho/FantasmaVermelhoEsquerda.png"));

            spritesAndandoCima = new BufferedImage[]{spriteCima};
            spritesAndandoBaixo = new BufferedImage[]{spriteBaixo};
            spritesAndandoDireita = new BufferedImage[]{spriteDireita};
            spritesAndandoEsquerda = new BufferedImage[]{spriteEsquerda};
            spritesAtuais = spritesAndandoCima;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        return explorador.decidirDirecao(this.posY, this.posX, pacman.getPosY(),
                pacman.getPosX(), mapa);
    }
}
