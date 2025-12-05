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
import java.awt.image.BufferedImage;

/**
 *
 * @author Intel
 */
public class FantasmaRosa extends Fantasma {

    public FantasmaRosa(int posY, int posX) {
        super(posY, posX);

        try {
            BufferedImage sprite1 = ImageIO.read(getClass().getResource("/imagens/fantasmas/FantasmaRosa/FantasmaRosaCima.png"));

            spritesAndando = new BufferedImage[]{sprite1};
            spritesAtuais = new BufferedImage[]{sprite1};
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        if (System.currentTimeMillis() - tempoInicializacao > 2000) {
            Direcao direcaoPacman = pacman.getDirecao();
            Posicao alvo;
            if (direcaoPacman == Direcao.ESQUERDA) {
                alvo = new Posicao(pacman.getPosX() - 4, pacman.getPosY());
            } else if (direcaoPacman == Direcao.DIREITA) {
                alvo = new Posicao(pacman.getPosX() + 4, pacman.getPosY());
            } else if (direcaoPacman == Direcao.CIMA) {
                alvo = new Posicao(pacman.getPosX(), pacman.getPosY() - 4);
            } else {
                alvo = new Posicao(pacman.getPosX(), pacman.getPosY() + 4);
            }
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
            return explorador.decidirDirecao(posY, posX, contorno.getPosY(), contorno.getPosX(), mapa);
        }
        return null;
    }

}
