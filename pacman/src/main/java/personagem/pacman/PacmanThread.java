/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.pacman;

import enums.Direcao;
import fase.FasePanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapa.Mapa;
import personagem.pacman.Pacman;

/**
 *
 * @author klaus
 */
public class PacmanThread extends Thread {

    private Pacman pacman;
    private Mapa mapa;
    private FasePanel painelAtual;

    public PacmanThread(Pacman pacman, Mapa mapa, FasePanel painelAtual) {
        this.pacman = pacman;
        this.mapa = mapa;
        this.painelAtual = painelAtual;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(200);
                pacman.mover(mapa);

                javax.swing.SwingUtilities.invokeLater(() -> {
                    FasePanel panel = painelAtual;
                    panel.repaint();
                });

            } catch (InterruptedException ex) {
                Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
