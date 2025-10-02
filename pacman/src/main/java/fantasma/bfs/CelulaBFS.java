/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasma.bfs;

/**
 *
 * @author klaus
 */
public class CelulaBFS {

    private int posX;
    private int posY;
    private CelulaBFS celulaPai;

    public CelulaBFS(int posX, int posY, CelulaBFS celulaPai) {
        this.posX = posX;
        this.posY = posY;
        this.celulaPai = celulaPai;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public CelulaBFS getCelulaPai() {
        return celulaPai;
    }

    public void setCelulaPai(CelulaBFS celulaPai) {
        this.celulaPai = celulaPai;
    }

}
