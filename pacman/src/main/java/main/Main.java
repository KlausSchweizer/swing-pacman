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
import personagem.fantasma.FantasmaAmarelo;
import personagem.fantasma.FantasmaLaranja;
import personagem.fantasma.FantasmaRosa;
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
        personagens.add(new Pacman(10, 4));
        personagens.add(new FantasmaRosa(5, 10));
        personagens.add(new FantasmaLaranja(4, 10));
        personagens.add(new FantasmaAmarelo(3, 10));
        
        FasePanel painelFase = new FasePanel(game.getMapa(), personagens);
        
        fase.add(painelFase);
        fase.setContentPane(painelFase);
        fase.pack();
        
        fase.setVisible(true);
        
        Thread threadVermelho = setarThreadFantasmaVermelho(personagens, game, painelFase);
        Thread threadRosa = setarThreadFantasmaRosa(personagens, game, painelFase);
        Thread threadLaranja = setarThreadFantasmaLaranja(personagens, game, painelFase);
        Thread threadAmarelo = setarThreadFantasmaAmarelo(personagens, game, painelFase);
        
        
        
        startThreads(threadRosa, threadVermelho, threadLaranja, threadAmarelo);
    }

    private static Thread setarThreadFantasmaLaranja(List<Personagem> personagens, Game game, FasePanel painelFase) {
        Thread threadLaranja = new FantasmaThread((Fantasma) personagens.get(3),
                (Pacman) personagens.get(1), game.getMapa(), painelFase);
        return threadLaranja;
    }

    private static Thread setarThreadFantasmaRosa(List<Personagem> personagens, Game game, FasePanel painelFase) {
        Thread threadRosa = new FantasmaThread((Fantasma) personagens.get(2),
                (Pacman) personagens.get(1), game.getMapa(), painelFase);
        return threadRosa;
    }

    private static Thread setarThreadFantasmaVermelho(List<Personagem> personagens, Game game, FasePanel painelFase) {
        Thread threadVermelho = new FantasmaThread((Fantasma) personagens.get(0),
                (Pacman) personagens.get(1), game.getMapa(), painelFase);
        return threadVermelho;
    }
    private static Thread setarThreadFantasmaAmarelo(List<Personagem> personagens, Game game, FasePanel painelFase) {
        Thread threadAmarelo = new FantasmaThread((Fantasma) personagens.get(4),
                (Pacman) personagens.get(1), game.getMapa(), painelFase);
        return threadAmarelo;
    }

    private static void startThreads(Thread threadRosa, Thread threadVermelho, Thread threadLaranja, Thread threadAmarelo) {
        threadRosa.start();
        threadVermelho.start();
        threadLaranja.start();
        threadAmarelo.start();
    }
}
