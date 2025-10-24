/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import fase.FaseJF;
import fase.FasePanel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import personagem.Personagem;
import personagem.fantasma.Fantasma;
import personagem.fantasma.FantasmaThread;
import personagem.fantasma.FantasmaVermelho;
import personagem.pacman.Pacman;

/**
 *
 * @author klaus
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        FaseJF fase = new FaseJF();
        
        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new FantasmaVermelho(6, 10));
        personagens.add(new Pacman(10, 5));
        
        FasePanel painelFase = new FasePanel(game.getMapa(), personagens);
        
        fase.add(painelFase);
        fase.setContentPane(painelFase);
        fase.pack();
        fase.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fase.setVisible(true);
        
        Thread thread = new FantasmaThread((Fantasma) personagens.get(0), 
                (Pacman) personagens.get(1), game.getMapa(), painelFase);
        
        thread.start();
    }
}
