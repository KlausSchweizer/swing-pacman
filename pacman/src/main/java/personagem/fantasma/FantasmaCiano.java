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

/**
 *
 * @author Intel
 */
public class FantasmaCiano extends Fantasma {
    private FantasmaVermelho fantasmaVermelho;

    public FantasmaCiano(int posY, int posX) {
        super(posY, posX);

        carregarSprites();
    }

    @Override
    public void carregarSprites() {
        super.carregarSprites();
        try {
            BufferedImage spriteCima = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaCiano/FantasmaCianoCima.png"));

            BufferedImage spriteBaixo = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaCiano/FantasmaCianoBaixo.png"));

            BufferedImage spriteDireita = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaCiano/FantasmaCianoDireita.png"));

            BufferedImage spriteEsquerda = ImageIO.read(getClass().getResource("/imagens"
                    + "/fantasmas/FantasmaCiano/FantasmaCianoEsquerdo.png"));

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
        explorador = new ExplorarCaminho();
        explorador.setTextoMapa(mapa.getTextoMapa());

        if (System.currentTimeMillis() - tempoInicializacao > 4000) {
            if(status == StatusFantasma.ALVO) {
                return alvoFugindo(mapa);
            }
            int posX = pacman.getPosX();
            posX = pacman.getDirecao() == Direcao.ESQUERDA ? posX - 2 : posX;
            posX = pacman.getDirecao() == Direcao.DIREITA ? posX + 2 : posX;

            int posY = pacman.getPosY();
            posY = pacman.getDirecao() == Direcao.CIMA ? posY - 2 : posY;
            posY = pacman.getDirecao() == Direcao.BAIXO ? posY + 2 : posY;

            int vetorX = posX - fantasmaVermelho.getPosX();
            int vetorY = posY - fantasmaVermelho.getPosY();

            int alvoX = fantasmaVermelho.getPosX() + vetorX;
            int alvoY = fantasmaVermelho.getPosY() + vetorY;

            alvo = new Posicao(Math.max(0, Math.min(alvoX, mapa.getTextoMapa()[0].length - 1)),
                    Math.max(0, Math.min(alvoY, mapa.getTextoMapa().length - 1)));

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
        return null;
    }

    public void setFantasmaVermelho(FantasmaVermelho fantasmaVermelho) {
        this.fantasmaVermelho = fantasmaVermelho;
    }
}
