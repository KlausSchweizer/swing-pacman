/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma.bfs;

/**
 *
 * @author klaus
 */
public class CelulaBFS {

    private int posX;
    private int posY;
    private CelulaBFS celulaPai;

    public CelulaBFS(int posY, int posX, CelulaBFS celulaPai) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CelulaBFS other = (CelulaBFS) obj;
        return this.posX == other.posX && this.posY == other.posY;
    }

    @Override
    public int hashCode() {
        return 31 * posX + posY;
    }
}
