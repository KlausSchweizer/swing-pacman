/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Intel
 */
public class Pacman extends JPanel{
    private BufferedImage imagem;
    
    
     public Pacman() {
        setLayout(null); 
        
        try {
            BufferedImage sprite1 = ImageIO.read(new File("../../../../imagens/pacman/PACMAN-1.png"));
            BufferedImage sprite2 = ImageIO.read(new File("../../../../imagens/pacman/PACMAN-2.png"));
            BufferedImage sprite3 = ImageIO.read(new File("../../../../imagens/pacman/PACMAN-3.png"));

            JLabel labelSprite1 = new JLabel(new ImageIcon(sprite1));
            labelSprite1.setBounds(100, 60, sprite1.getWidth(), sprite1.getHeight());

            JLabel labelSprite2 = new JLabel(new ImageIcon(sprite2));
            labelSprite2.setBounds(200, 100, sprite2.getWidth(), sprite2.getHeight());
            
            JLabel labelSprite3 = new JLabel(new ImageIcon(sprite3));
            labelSprite3.setBounds(200, 100, sprite3.getWidth(), sprite3.getHeight());
            
            
            adicionarLabelSprites(labelSprite1, labelSprite2, labelSprite3);

        } catch (Exception e) {
            
        }
    }

    private void adicionarLabelSprites(JLabel labelSprite1, JLabel labelSprite2, JLabel labelSprite3) {
        add(labelSprite1);
        add(labelSprite2);
        add(labelSprite3);
    }
     
public static void main(String[] args) {
        JFrame frame = new JFrame("Gráfico com Imagem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new Pacman());
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}