/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import fase.FaseJF;
import fase.FasePanel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import fase.SeletorFases;
import mapa.Posicao;
import personagem.fantasma.*;
import personagem.pacman.Pacman;

/**
 *
 * @author klaus
 */
public class Main {
    private static FaseJF fase;

    public static void main(String[] args) {
        try {
            Game game = new Game();
            fase = new FaseJF();
            SeletorFases seletor = new SeletorFases(game);
            fase.add(seletor);
            fase.setContentPane(seletor);
            fase.pack();
            fase.setExtendedState(JFrame.MAXIMIZED_BOTH);
            fase.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void comecarFase(Game game, FaseJF fase) {
        configurarFantasmas(game);
        configurarPacman(game);
        configurarPainelFase(game);
        configurarFase(fase, game.getPanel());

        int intervalo = 200;
        Timer timer = new Timer(intervalo, e ->  {
            if(game.isRunning()) {
                game.update();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    private static void configurarPainelFase(Game game) {
        FasePanel painelFase = new FasePanel(game.getMapa(), game.getFantasmas(), game.getPacman());
        game.setPanel(painelFase);
    }

    private static void configurarPacman(Game game) {
        Posicao spawnPacman = game.getMapa().getSpawnPacman();
        Pacman pacman = new Pacman(spawnPacman.getPosY(), spawnPacman.getPosX());
        game.setPacman(pacman);
    }

    private static void configurarFantasmas(Game game) {
        List<Fantasma> fantasmas = new ArrayList<>();
        List<Posicao> spawnFantasmas = game.getMapa().getSpawnsFantasma();

        for (int i = 0; i < spawnFantasmas.size(); i++) {
            Posicao posicao = spawnFantasmas.get(i);

            if (i == Fantasma.VERMELHO) {
                fantasmas.add(new FantasmaVermelho(posicao.getPosY(), posicao.getPosX()));
            } else if (i == Fantasma.ROSA) {
                fantasmas.add(new FantasmaRosa(posicao.getPosY(), posicao.getPosX()));
            } else if (i == Fantasma.AZUL) {
                fantasmas.add(new FantasmaAzul(posicao.getPosY(), posicao.getPosX()));
            } else if (i == Fantasma.LARANJA) {
                fantasmas.add(new FantasmaLaranja(posicao.getPosY(), posicao.getPosX()));
            }
        }
        game.setFantasmas(fantasmas);
    }

    private static void configurarFase(FaseJF fase, FasePanel painelFase) {
        fase.getContentPane().removeAll();
        fase.setContentPane(painelFase);
        fase.revalidate();
        fase.repaint();
        painelFase.setFocusable(true);
        painelFase.requestFocusInWindow();

    }

    public static FaseJF getFase() {
        return fase;
    }
}
