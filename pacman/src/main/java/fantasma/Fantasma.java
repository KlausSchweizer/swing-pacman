/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasma;

import enums.Direcao;
import enums.StatusFantasma;

/**
 *
 * @author klaus
 */
public abstract class Fantasma {
    protected int posX;
    protected int posY;
    protected Direcao direcao;
    protected StatusFantasma status;
    
    public Fantasma() {
        this.direcao = Direcao.CIMA;
        this.status = StatusFantasma.PERSEGUIDOR;
    }
    
    public abstract Direcao mover();
    
    public void morrer() {
        
    }
}
