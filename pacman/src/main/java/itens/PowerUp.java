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
        }
        
        numeroFrames = images.size();
    }
    
    private void adicionarSprites() throws IOException {
        images.add(ImageIO.read(getClass().getResourceAsStream("/imagens/powerup/powerup00.png")));
    }
}
