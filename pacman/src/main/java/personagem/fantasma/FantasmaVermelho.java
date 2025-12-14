/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;
import mapa.Mapa;
import personagem.pacman.Pacman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author Intel
 */
public class FantasmaVermelho extends Fantasma {

    public FantasmaVermelho(int posY, int posX) {
        super(posY, posX);

        carregarSprites();
    }

    @Override
    public void carregarSprites() {
        super.carregarSprites();
        try {
            BufferedImage spriteEsquerda1 = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaVermelho/esquerda/VERMELHO-ESQUERDA1.png"));
            spritesAndandoEsquerda = new BufferedImage[]{spriteEsquerda1};

            BufferedImage spriteDireita1 = ImageIO.read(getClass().getResource("/imagens/fantasmas/fantasmaVermelho/direita/VERMELHO-DIRETA1.png"));
            spritesAndandoDireita = new BufferedImage[]{spriteDireita1};

            BufferedImage spriteBaixo1 = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/fantasmaVermelho/baixo/VERMELHO-BAIXO1.png"));
            spritesAndandoBaixo = new BufferedImage[]{spriteBaixo1};

            BufferedImage spriteCima1 = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/fantasmaVermelho/cima/VERMELHO-CIMA1.png"));
            spritesAndandoCima = new BufferedImage[]{spriteCima1};

            spritesAtuais = spritesAndandoCima;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        if (status == StatusFantasma.ALVO) {
            return alvoFugindo(mapa);
        }
        return explorador.decidirDirecao(this.posY, this.posX, pacman.getPosY(),
                pacman.getPosX(), mapa);
    }
}
