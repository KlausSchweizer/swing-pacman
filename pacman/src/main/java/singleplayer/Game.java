/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleplayer;

import fase.GameEndPanel;
import mapa.Posicao;
import personagem.fantasma.StatusFantasma;
import fase.FasePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import mapa.Mapa;
import mapa.TxtParser;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;

import javax.swing.*;

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

        GameEndPanel painel = new GameEndPanel();

        Main.getFase().setGlassPane(painel);
        painel.setVisible(true);
    }

    public void selecionarFase(String path) {
        mapa = new TxtParser().criarMapa(path);
        mapa.setNomeMapa(path);
        setarCoordenadas(path);
        Main.comecarFase(this);
    }

    private void setarCoordenadas(String path) {
        List<Posicao> coordenadas = new ArrayList<>();
        String resourcePath = getClass().getResource("/mapas/coordenadas-padrao/coordenada-" + path.substring(7)).getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(resourcePath))) {
            List<String> posicoesString = br.lines().toList();

            for(String str : posicoesString) {
                int x = Integer.parseInt(str.split(",")[0]);
                int y = Integer.parseInt(str.split(",")[1]);
                coordenadas.add(new Posicao(x, y));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao abrir coordenada " + e.getMessage(),
                    "Falha em carregamento", JOptionPane.ERROR_MESSAGE);
        }
        mapa.setCoordenadas(coordenadas);
    }

    public void checarColisoes(Fantasma fantasma) {
        if (fantasma.getPosX() == pacman.getPosX() && fantasma.getPosY() == pacman.getPosY()) {
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
