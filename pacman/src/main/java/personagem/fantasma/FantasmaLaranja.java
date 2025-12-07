/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;
import mapa.Mapa;
import mapa.Posicao;
import personagem.fantasma.bfs.CelulaBFS;
import personagem.fantasma.bfs.ExplorarCaminho;
import personagem.pacman.Pacman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Intel
 */
public class FantasmaLaranja extends Fantasma {
    private boolean podeMudarAlvo;

    public FantasmaLaranja(int posY, int posX) {
        super(posY, posX);

        try {
            BufferedImage spriteCima = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaLaranja/FantasmaLaranjaCima.png"));

            BufferedImage spriteBaixo = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaLaranja/FantasmaLaranjaBaixo.png"));

            BufferedImage spriteDireita = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaLaranja/FantasmaLaranjaDireita.png"));

            BufferedImage spriteEsquerda = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaLaranja/FantasmaLaranjaEsquerda.png"));

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
        if (System.currentTimeMillis() - tempoInicializacao > 6000) {
            Random rand = new Random();

            if (mapa.getCoordenadas().isEmpty()) {
                return null;
            }

            int posX = this.posX;
            int posY = this.posY;
            int distancia = Math.abs(posX - pacman.getPosX()) + Math.abs(posY - pacman.getPosY());

            if (alvo == null || (posX == alvo.getPosX() && posY == alvo.getPosY())) {
                if (distancia < 10) {
                    Posicao novoAlvo;
                    ExplorarCaminho explorador = new ExplorarCaminho();
                    explorador.setTextoMapa(mapa.getTextoMapa());
                    do {
                        novoAlvo = mapa.getCoordenadas().get(rand.nextInt(mapa.getCoordenadas().size()));
                    } while (!explorador.isCelulaValida(new CelulaBFS(novoAlvo.getPosY(), novoAlvo.getPosX(), null)));
                    alvo = novoAlvo;
                } else {
                    alvo = new Posicao(pacman.getPosX(), pacman.getPosY());
                }
            }

            if (alvo == null) {
                alvo = new Posicao(posY, posX);
            }

            return explorador.decidirDirecao(this.posY, this.posX, alvo.getPosY(), alvo.getPosX(), mapa);
        }
        return null;
    }
}
