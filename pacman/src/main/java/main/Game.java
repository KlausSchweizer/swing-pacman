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
import personagem.pacman.Pacman;

/**
 *
 * @author klaus
 */
public class Game {

    private Mapa mapa;
    private List<Fantasma> fantasmas;
    private Pacman pacman;
    private FasePanel panel;
    private boolean isRunning;

    public Game() {
        mapa = new TxtParser().criarMapa("/mapas/mapa1.txt");
        fantasmas = new ArrayList<>();
        isRunning = true;
    }

    public void update() {
        for (Fantasma fantasma : fantasmas) {
            fantasma.setDirecao(fantasma.decidirDirecao(pacman, mapa));
            fantasma.mover(mapa);
            checarColisoes(fantasma);
        }
        pacman.mover(mapa);
        panel.repaint();
    }

    public void finish() {
        isRunning = false;
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

    public FasePanel getPanel() {
        return panel;
    }

    public void setPanel(FasePanel panel) {
        this.panel = panel;
    }

    public boolean isRunning() {
        return isRunning;
    }

}
