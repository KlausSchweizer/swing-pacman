/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itens;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author klaus
 */
public class PowerUp extends Item {

    public PowerUp() {
        super();
        try {
            adicionarSprites();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("itens.PowerUp.<init>()");
        }
        
        numeroFrames = images.size();
    }
    
    private void adicionarSprites() throws IOException {
        images.add(ImageIO.read(new File("imagens/powerup/powerup00.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup01.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup02.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup03.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup04.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup05.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup06.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup07.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup08.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup09.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup10.png")));
        images.add(ImageIO.read(new File("imagens/powerup/powerup11.png")));        
    }
}
