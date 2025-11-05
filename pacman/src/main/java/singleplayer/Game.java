/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleplayer;

import personagem.fantasma.StatusFantasma;
import fase.FasePanel;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import mapa.Mapa;
import mapa.TxtParser;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;

/**
 *
 * @author klaus
 */
public class Game implements EventosGame {

    protected Mapa mapa;
    protected List<Fantasma> fantasmas;
    protected Pacman pacman;
    protected FasePanel panel;
    protected int pontos;
    protected boolean isRunning;

    public Game() {
        fantasmas = new ArrayList<>();
        isRunning = true;
        pontos = 0;
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

    public void selecionarFase(String path) {
        mapa = new TxtParser().criarMapa(path);
        Main.comecarFase(this, Main.getFase());
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

    @Override
    public void adicionarPonto() {
        this.pontos++;
    }

    @Override
    public void usarPowerUp() {
        fantasmas.forEach(fantasma -> fantasma.setStatus(StatusFantasma.ALVO));
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
