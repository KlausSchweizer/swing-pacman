/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.pacman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import mapa.Mapa;
import personagem.Personagem;

/**
 *
 * @author Intel
 */
public class Pacman extends Personagem {

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
    public void draw(Graphics2D g2d, int tileSize, Mapa mapa) {
        BufferedImage imagemAtual = spritesAtuais[0];
        g2d.drawImage(imagemAtual, posX * tileSize, posY * tileSize, tileSize, tileSize, null);
    }

}
