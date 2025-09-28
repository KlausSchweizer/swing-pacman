/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itens;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author klaus
 */
public class Ponto extends Item {

    public Ponto() {
        super();
        try {
            adicionarSprites();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        numeroFrames = images.size();
    }

    private void adicionarSprites() throws IOException {
        images.add(ImageIO.read(new File("imagens/ponto/ponto0.png")));
        images.add(ImageIO.read(new File("imagens/ponto/ponto1.png")));
        images.add(ImageIO.read(new File("imagens/ponto/ponto2.png")));
        images.add(ImageIO.read(new File("imagens/ponto/ponto3.png")));
    }
}
