/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import fase.FaseJF;
import fase.FasePanel;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import fase.MenuPanel;
import fase.SeletorFases;
import mapa.Mapa;
import mapa.Posicao;
import multiplayer.MultiplayerGame;
import multiplayer.panels.FormMultiplayerPanel;
import multiplayer.panels.lobby.Lobby;
import multiplayer.server.Server;
import personagem.fantasma.*;
import personagem.pacman.Pacman;
import singleplayer.Game;

/**
 *
 * @author klaus
 */
public class Main {
    private static FaseJF fase;

    public static void main(String[] args) {
        fase = new FaseJF();
        MenuPanel menuPanel = new MenuPanel();
        fase.add(menuPanel);
        fase.setContentPane(menuPanel);
        fase.pack();
        fase.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fase.setVisible(true);
    }

    public static void singlePlayer() {
        MultiplayerGame.setTipoUsuario(TipoUsuario.SINGLEPLAYER);
        try {
            Game game = new Game();
            SeletorFases seletor = new SeletorFases(game);
            configurarPanel(seletor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void multiplayer() {
        FormMultiplayerPanel panel = new FormMultiplayerPanel();
        configurarPanel(panel);
    }

    public static void timerMultiplayer(Game game) {
        int intervalo = 200;
        Timer timer = new Timer(intervalo, e -> {
            if (game.isRunning()) {
                game.update();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    public static void criarSala() {
        MultiplayerGame.setTipoUsuario(TipoUsuario.SERVIDOR);
        try {
            Server server = Server.getInstance();
            server.startServer();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(fase, "Erro ao criar servidor", "Erro ao selecionar fase", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public static void entrarSala(int port, InetAddress ip) {
        MultiplayerGame.setTipoUsuario(TipoUsuario.CLIENTE);
    }

    public static void comecarFase(Game game) {
        configurarFantasmas(game);
        configurarPacman(game);
        configurarPainelFase(game);
        configurarPanel(game.getPanel());

        int intervalo = 200;
        Timer timer = new Timer(intervalo, e -> {
            if (game.isRunning()) {
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
        if(spawnPacman != null) {
            Pacman pacman = new Pacman(spawnPacman.getPosY(), spawnPacman.getPosX());
            pacman.setEventos(game);
            game.setPacman(pacman);
        }
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
                fantasmas.add(new FantasmaCiano(posicao.getPosY(), posicao.getPosX()));
            } else if (i == Fantasma.LARANJA) {
                fantasmas.add(new FantasmaLaranja(posicao.getPosY(), posicao.getPosX()));
            }
        }
        game.setFantasmas(fantasmas);
    }

    public static void configurarPanel(JPanel painelFase) {
        SwingUtilities.invokeLater(() -> {
            fase.getContentPane().removeAll();
            fase.setContentPane(painelFase);
            fase.revalidate();
            fase.repaint();
            painelFase.setFocusable(true);
            painelFase.requestFocusInWindow();
        });
    }

    public static FaseJF getFase() {
        return fase;
    }
}
