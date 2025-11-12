/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import mapa.Mapa;
import mapa.Posicao;
import personagem.pacman.Pacman;

import javax.imageio.ImageIO;

/**
 *
 * @author Intel
 */
public class FantasmaLaranja extends Fantasma {
    private boolean podeMudarAlvo;

    public FantasmaLaranja(int posY, int posX) {
        super(posY, posX);

        try {
            BufferedImage sprite1 = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaLaranja/FantasmLaranjaBaixo.png"));

            spritesAndando = new BufferedImage[]{sprite1};
            spritesAtuais = new BufferedImage[]{sprite1};
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        Random rand = new Random();
        if (mapa.getCoordenadas().isEmpty()) {
            return null;
        }

        alvo = mapa.getCoordenadas().get(rand.nextInt(4));

        status = StatusFantasma.PERSEGUIDOR;
//        if (System.currentTimeMillis() - ultimaMudanca < 30000) {
//            if (status == StatusFantasma.ALVO) {
//                status = StatusFantasma.PERSEGUIDOR;
//            } else if (status == StatusFantasma.PERSEGUIDOR) {
//                status = StatusFantasma.ALVO;
//            }
//        } else {
//            ultimaMudanca = System.currentTimeMillis();
//        }

        podeMudarAlvo = false;

        if (status == StatusFantasma.PERSEGUIDOR) {
            if (posX == alvo.getPosX() && posY == alvo.getPosY()) {
                podeMudarAlvo = true;
            }
            if (((Math.abs(posX - pacman.getPosX()) + Math.abs(posY - pacman.getPosY()) / 2) < 7 && podeMudarAlvo)) {
                int posicao = rand.nextInt(mapa.getCoordenadas().size() - 1);
                alvo = mapa.getCoordenadas().get(posicao);
                podeMudarAlvo = false;
            } else if(((Math.abs(posX - pacman.getPosX()) + Math.abs(posY - pacman.getPosY()) / 2) > 15)){
                alvo = new Posicao(pacman.getPosX(), pacman.getPosY());
            }
        } else {
            if (podeMudarAlvo) {
                int posicao = rand.nextInt(mapa.getCoordenadas().size() - 1);
                alvo = mapa.getCoordenadas().get(posicao);
                podeMudarAlvo = false;
            }
        }


        return explorador.decidirDirecao(this.posY, this.posX, alvo.getPosY(),
                alvo.getPosX(), mapa);
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa) {
        BufferedImage imagemAtual = spritesAtuais[0];
        g2d.drawImage(imagemAtual, posX * tileSize, posY * tileSize, tileSize, tileSize, null);
    }
}
