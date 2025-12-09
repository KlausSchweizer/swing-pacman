/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;

import java.awt.image.BufferedImage;
import java.io.IOException;
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
            criarSpritesCima();
            criarSpritesBaixo();
            criarSpritesDireita();
            criarSpritesEsquerda();

            spritesAtuais = spritesAndandoCima;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void criarSpritesEsquerda() throws IOException {
        BufferedImage spriteEsquerda1 = ImageIO.read(getClass().getResource("/imagens"
                + "/fantasmas/FantasmaVermelho/esquerda/VERMELHO-ESQUERDA1.png"));
        BufferedImage spriteEsquerda2 = ImageIO.read(getClass().getResource("/imagens"
                + "/fantasmas/FantasmaVermelho/esquerda/VERMELHO-ESQUERDA2.png"));
        BufferedImage spriteEsquerda3 = ImageIO.read(getClass().getResource("/imagens"
                + "/fantasmas/FantasmaVermelho/esquerda/VERMELHO-ESQUERDA3.png"));
        spritesAndandoEsquerda = new BufferedImage[]{spriteEsquerda1};
    }

    private void criarSpritesDireita() throws IOException {
        BufferedImage spriteDireita1 = ImageIO.read(getClass().getResource("/imagens/fantasmas/fantasmaVermelho/direita/VERMELHO-DIRETA1.png"));
        spritesAndandoDireita = new BufferedImage[]{spriteDireita1};
    }

    private void criarSpritesBaixo() throws IOException {
        BufferedImage spriteBaixo1 = ImageIO.read(getClass().getResource("/imagens"
                + "/fantasmas/fantasmaVermelho/baixo/VERMELHO-BAIXO1.png"));
        spritesAndandoBaixo = new BufferedImage[]{spriteBaixo1};
    }

    private void criarSpritesCima() throws IOException {
        BufferedImage spriteCima1 = ImageIO.read(getClass().getResource("/imagens"
                + "/fantasmas/fantasmaVermelho/cima/VERMELHO-CIMA1.png"));
        spritesAndandoCima = new BufferedImage[]{spriteCima1};
    }

    @Override
    public Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        return explorador.decidirDirecao(this.posY, this.posX, pacman.getPosY(),
                pacman.getPosX(), mapa);
    }
}
