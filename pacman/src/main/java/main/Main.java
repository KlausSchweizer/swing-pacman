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

import mapa.Posicao;
import personagem.Personagem;
import personagem.fantasma.*;
import personagem.pacman.Pacman;
import personagem.pacman.PacmanThread;

/**
 *
 * @author klaus
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        FaseJF fase = new FaseJF();

        List<Personagem> personagens = new ArrayList<>();
        List<Posicao> spawnFantasmas = game.getMapa().getSpawnsFantasma();
        Posicao spawnPacman = game.getMapa().getSpawnPacman();

        for (int i = 0; i < spawnFantasmas.size(); i++) {
            Posicao posicao = spawnFantasmas.get(i);

            if (i == Fantasma.VERMELHO) {
                personagens.add(new FantasmaVermelho(posicao.getPosY(), posicao.getPosX()));
            } else if (i == Fantasma.ROSA) {
                personagens.add(new FantasmaRosa(posicao.getPosY(), posicao.getPosX()));
            } else if (i == Fantasma.AZUL) {
                personagens.add(new FantasmaAzul(posicao.getPosY(), posicao.getPosX()));
            } else if (i == Fantasma.LARANJA) {
                personagens.add(new FantasmaLaranja(posicao.getPosY(), posicao.getPosX()));
            }
            game.getFantasmas().add((Fantasma) personagens.get(i));
        }

        personagens.add(new Pacman(spawnPacman.getPosY(), spawnPacman.getPosX()));
        game.setPacman((Pacman) personagens.getLast());

        FasePanel painelFase = new FasePanel(game.getMapa(), personagens);
        game.setPanel(painelFase);
        configurarFase(fase, painelFase);

        List<Thread> threads = new ArrayList<>();
        for (Fantasma fantasma : game.getFantasmas()) {
            Thread thread = new FantasmaThread(fantasma,
                    game.getPacman(), game.getMapa(), painelFase);
            threads.add(thread);
            thread.start();
        }
        Thread thread = new PacmanThread(game.getPacman(), game.getMapa(), painelFase);
        threads.add(thread);
        thread.start();

        game.start();
        while (true) {
            game.update();
        }
    }

    private static void configurarFase(FaseJF fase, FasePanel painelFase) {
        fase.add(painelFase);
        fase.setContentPane(painelFase);
        fase.pack();
        fase.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fase.setVisible(true);
        fase.setVisible(true);
        painelFase.setFocusable(true);
        painelFase.requestFocusInWindow();
    }
}
