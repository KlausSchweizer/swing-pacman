/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import mapa.Mapa;
import mapa.TxtParser;

/**
 *
 * @author klaus
 */
public class Game {

    private Mapa mapa;
    
    public Game() {
        mapa = new TxtParser().criarMapa("/mapas/mapa2.txt");
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
    
    
    
}
