/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import fase.FaseJF;
import fase.FasePanel;
import fase.MenuPanel;
import fase.SeletorFases;
import mapa.Posicao;
import personagem.fantasma.*;
import personagem.pacman.Pacman;
import singleplayer.Game;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author klaus
 */
public class Main {
	private static FaseJF fase;
	private static Timer timer;
	private static Timer timerReserva;
	private static JLabel vidasLabel;

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
		try {
			Game game = new Game();
			SeletorFases seletor = new SeletorFases(game);
			configurarPanel(seletor);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void comecarFase(Game game) {
		configurarFantasmas(game);
		configurarPacman(game);
		configurarPainelFase(game);
		configurarPanel(game.getPanel());
		fase.setFullScreen(true);

		if (timer != null) {
			timer.stop();
		}
		int intervalo = 200;
		timer = new Timer(intervalo, e -> {
			if (game.isRunning()) {
				game.update(vidasLabel);
			}
		});
		timer.start();
		timerReserva = timer;

		if (vidasLabel == null) {
			vidasLabel = new JLabel("Vidas: " + game.getPacman().getVidas());
			vidasLabel.setForeground(Color.CYAN);
			vidasLabel.setFont(new Font("Arial", Font.BOLD, 36));
			vidasLabel.setBounds(20, 20, 200, 50);
			game.getPanel().setLayout(null);
			game.getPanel().add(vidasLabel);
			game.getPanel().repaint();
		}
		game.getPanel().setLayout(null);
		game.getPanel().add(vidasLabel);
		game.getPanel().repaint();
		JLabel pontosLabel = new JLabel("Pontos: 0");
		pontosLabel.setForeground(Color.CYAN);
		pontosLabel.setFont(new Font("Arial", Font.BOLD, 36));
		pontosLabel.setBounds(20, 80, 500, 50);
		game.setPontosLabel(pontosLabel);
		game.getPanel().add(pontosLabel);
	}

	private static void configurarPainelFase(Game game) {
		FasePanel painelFase = new FasePanel(game.getMapa(), game.getFantasmas(), game.getPacman());
		game.setPanel(painelFase);
	}

	private static void configurarPacman(Game game) {
		Posicao spawnPacman = game.getMapa().getSpawnPacman();
		if (spawnPacman != null) {
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
				FantasmaCiano ciano = (FantasmaCiano) fantasmas.getLast();
				FantasmaVermelho vermelho = null;
				for (Fantasma fantasma : fantasmas) {
					if (fantasma instanceof FantasmaVermelho) {
						vermelho = (FantasmaVermelho) fantasma;
					}
				}
				ciano.setFantasmaVermelho(vermelho);
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
			painelFase.setFocusable(true);
			painelFase.requestFocusInWindow();
		});
	}

	public static FaseJF getFase() {
		return fase;
	}

	public static Timer getTimer() {
		return timer;
	}

	public static void setTimer(Timer timer) {
		Main.timer = timer;
	}

	public static Timer getTimerReserva() {
		return timerReserva;
	} 
}
