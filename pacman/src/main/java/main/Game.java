/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import enums.StatusFantasma;
import fase.FasePanel;

import java.util.ArrayList;
import java.util.List;

import mapa.Mapa;
import mapa.TxtParser;
import personagem.fantasma.Fantasma;
import personagem.fantasma.FantasmaThread;
import personagem.pacman.Pacman;

/**
 *
 * @author klaus
 */
public class Game {

    private Mapa mapa;
    private List<Fantasma> fantasmas;
    private Pacman pacman;
    private List<FantasmaThread> threads;
    private FasePanel panel;

    public Game() {
        mapa = new TxtParser().criarMapa("/mapas/mapa1.txt");
        fantasmas = new ArrayList<Fantasma>();
        threads = new ArrayList<>();
    }

    public void start() {
        for (Fantasma fantasma : fantasmas) {
            threads.add(new FantasmaThread(fantasma, pacman, mapa, panel));
        }
        threads.forEach(Thread::start);
    }

    public void update() {
        fantasmas.forEach(this::checarColisoes);
        panel.repaint();
    }

    public void finish() {
        threads.forEach(Thread::interrupt);
    }

    public void checarColisoes(Fantasma fantasma) {
        if (fantasma.getPosX() == pacman.getPosX()
                && fantasma.getPosY() == pacman.getPosY()) {
            if (fantasma.getStatus() == StatusFantasma.ALVO) {
                fantasma.morrer();
            } else if (fantasma.getStatus() == StatusFantasma.PERSEGUIDOR) {
                pacman.morrer();
                finish();
            }
        }
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public List<Fantasma> getFantasmas() {
        return fantasmas;
    }

    public void setFantasmas(List<Fantasma> fantasmas) {
        this.fantasmas = fantasmas;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public List<FantasmaThread> getThreads() {
        return threads;
    }

    public void setThreads(List<FantasmaThread> threads) {
        this.threads = threads;
    }

    public FasePanel getPanel() {
        return panel;
    }

    public void setPanel(FasePanel panel) {
        this.panel = panel;
    }


}
