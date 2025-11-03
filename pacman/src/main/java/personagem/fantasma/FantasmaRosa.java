/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import main.Direcao;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import mapa.Mapa;
import personagem.pacman.Pacman;

/**
 *
 * @author Intel
 */
public class FantasmaRosa extends Fantasma {

    public FantasmaRosa(int posY, int posX) {
        super(posY, posX);

        try {
              BufferedImage sprite1 = ImageIO.read(getClass().getResource("/imagens/fantasmas/FantasmaRosa/FantasmRosaCima.png"));

            spritesAndando = new BufferedImage[]{sprite1};
            spritesAtuais = new BufferedImage[]{sprite1};
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        return explorador.decidirDirecao(this.posY, this.posX, pacman.getPosY(),
                pacman.getPosX(), mapa);
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa) {
        BufferedImage imagemAtual = spritesAtuais[0];
        g2d.drawImage(imagemAtual, posX * tileSize, posY * tileSize, tileSize, tileSize, null);
    }

}
