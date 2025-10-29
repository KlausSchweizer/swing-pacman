/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

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
public class FantasmaThread extends Thread {

    private Fantasma fantasma;
    private Pacman pacman;
    private Mapa mapa;

    public FantasmaThread(Fantasma fantasma, Pacman pacman, Mapa mapa) {
        this.fantasma = fantasma;
        this.pacman = pacman;
        this.mapa = mapa;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                Direcao direcao = fantasma.decidirDirecao(pacman, mapa);
                System.out.println("Direção escolhida: " + direcao);
                fantasma.mover(direcao);
            } catch (InterruptedException ex) {
                Logger.getLogger(FantasmaThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
