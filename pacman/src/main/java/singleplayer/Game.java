/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleplayer;

import fase.FasePanel;
import fase.glasspanes.ContagemRegressivaPanel;
import fase.glasspanes.GameEndPanel;
import main.Direcao;
import main.Main;
import mapa.Mapa;
import mapa.Posicao;
import mapa.TxtParser;
import personagem.fantasma.Fantasma;
import personagem.fantasma.FantasmaCiano;
import personagem.fantasma.FantasmaVermelho;
import personagem.fantasma.StatusFantasma;
import personagem.pacman.Pacman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
    protected boolean podeMover;
    protected int delayTimer;
    private JLabel pontosLabel;

    public void setPontosLabel(JLabel label) {
        this.pontosLabel = label;
        this.pontosLabel.setText(pontos + "");
    }

    @Override
    public void adicionarPonto() {
        this.pontos += 10;
        setPontosLabel(this.pontosLabel);
        if (pontosLabel != null) {
            pontosLabel.setText(pontos + "");
        }
    }

    public Game() {
        fantasmas = new ArrayList<>();
        isRunning = true;
        pontos = 0;
        delayTimer = 700;
    }

    public void update(JLabel vidasLabel) {
        if (podeMover) {
            for (Fantasma fantasma : fantasmas) {
                checarColisoes(fantasma, vidasLabel);
                fantasma.setDirecao(fantasma.decidirDirecao(pacman, mapa));
                fantasma.mover(mapa);
                checarColisoes(fantasma, vidasLabel);
            }
            pacman.mover(mapa);
            panel.repaint();
        }
    }

    public void finish() {
        isRunning = false;
         pacman.setVidas(0);

        Main.getTimer().stop();
        Main.setTimer(null);

        GameEndPanel painel = new GameEndPanel(this);
        Main.getFase().setGlassPane(painel);
        painel.setVisible(true);
    }

    public void recomecar() {
        Main.setTimer(Main.getTimerReserva());
        Main.getTimer().start();
        pacman.setPosX(mapa.getSpawnPacman().getPosX());
        pacman.setPosY(mapa.getSpawnPacman().getPosY());
        pacman.setDirecao(null);
        pacman.setSpritesAtuais(pacman.getSpritesAndandoDireita());

        for (int i = 0; i < fantasmas.size(); i++) {
            Fantasma f = fantasmas.get(i);
            Posicao spawn = mapa.getSpawnsFantasma().get(i);
            f.setPosX(spawn.getPosX());
            f.setPosY(spawn.getPosY());
            f.setStatus(StatusFantasma.PERSEGUIDOR);
            f.setDirecao(Direcao.CIMA);
            f.setSpritesAtuais(f.getSpritesAndandoCima());
            f.setIndiceSprite(0);

            long agora = System.currentTimeMillis();
            f.setTempoInicializacao(agora + 3000);

            if (f instanceof FantasmaCiano) {
                for (Fantasma g : fantasmas) {
                    if (g instanceof FantasmaVermelho vermelho) {
                        ((FantasmaCiano) f).setFantasmaVermelho(vermelho);
                    }
                }
            }
        }
        isRunning = true;
        iniciarContador();
    }

    public void selecionarFase(String path) {
        mapa = new TxtParser().criarMapa(path);
        mapa.setNomeMapa(path);
        setarCoordenadas(path);
        Main.comecarFase(this);
        this.iniciarContador();

        long agora = System.currentTimeMillis();
        for (Fantasma f : fantasmas) {
            f.setTempoInicializacao(agora + 3000);
        }
        iniciarContador();
    }

    private void setarCoordenadas(String path) {
        List<Posicao> coordenadas = new ArrayList<>();
        String resourcePath = getClass().getResource("/mapas/coordenadas-padrao/coordenada-" + path.substring(7))
                .getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(resourcePath))) {
            List<String> posicoesString = br.lines().toList();

            for (String str : posicoesString) {
                int x = Integer.parseInt(str.split(",")[0]);
                int y = Integer.parseInt(str.split(",")[1]);
                coordenadas.add(new Posicao(x, y));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao abrir coordenada " + e.getMessage(), "Falha em carregamento",
                    JOptionPane.ERROR_MESSAGE);
        }
        mapa.setCoordenadas(coordenadas);
    }

    public void checarColisoes(Fantasma fantasma, JLabel vidasLabel) {
        if (fantasma.getPosX() == pacman.getPosX() && fantasma.getPosY() == pacman.getPosY()) {
            if (fantasma.getStatus() == StatusFantasma.ALVO) {
                fantasma.morrer(mapa);
                pontos += 200;
                setPontosLabel(this.pontosLabel);
            } else if (fantasma.getStatus() == StatusFantasma.PERSEGUIDOR) {
                pacman.morrer(mapa);
                vidasLabel.setText(pacman.getVidas() + "");
                try {
                    vidasLabel.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/imagens/icons/pacmanIcon.png"))));
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                if (pacman.getVidas() <= 0) {
                    finish();
                } else {
                    recomecar();
                }
            }
        }
    }

    public void iniciarContador() {
        Main.getTimer().stop();

        podeMover = false;
        final int[] contador = {3};

        ContagemRegressivaPanel contadorPanel = new ContagemRegressivaPanel();
        Main.getFase().setGlassPane(contadorPanel);
        contadorPanel.setVisible(true);

        contadorPanel.getContadorLB().setText(contador[0] + "");

        Timer contadorTimer = new Timer(delayTimer, null);
        contadorTimer.addActionListener(e -> {
            contador[0]--;
            String texto = (contador[0] > 0) ? contador[0] + "" : "GO!";
            contadorPanel.getContadorLB().setText(texto);

            if (contador[0] < 0) {
                contadorTimer.stop();
                contadorPanel.setVisible(false);
                podeMover = true;
                Main.getTimer().start();
                iniciarFantasmas();
            }
        });

        contadorTimer.start();
    }

    private void iniciarFantasmas() {
        long agora = System.currentTimeMillis();
        int delayEntreFantasmas = 2000;

        for (int i = 0; i < fantasmas.size(); i++) {
            Fantasma f = fantasmas.get(i);
            f.setTempoInicializacao(agora + i * delayEntreFantasmas);
        }
    }


    @Override
    public void usarPowerUp() {
        pontos += 50;
        fantasmas.forEach(fantasma -> {
            for(Posicao posicao : mapa.getSpawnsFantasma()) {
                if(fantasma.getPosX() == posicao.getPosX() && fantasma.getPosY() == posicao.getPosY()) {
                    fantasma.setStatus(StatusFantasma.PERSEGUIDOR);
                    return;
                }
            }
            fantasma.setStatus(StatusFantasma.ALVO);
        });

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

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}
