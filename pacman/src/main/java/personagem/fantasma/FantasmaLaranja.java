/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma;

import enums.Direcao;
import java.awt.Graphics2D;
import mapa.Mapa;
import personagem.pacman.Pacman;

/**
 *
 * @author Intel
 */
public class FantasmaLaranja extends Fantasma {

    public FantasmaLaranja(int posY, int posX) {
        super(posY, posX);
    }

    @Override
    protected Direcao decidirDirecao(Pacman pacman, Mapa mapa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void draw(Graphics2D g, int tileSize, Mapa mapa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
