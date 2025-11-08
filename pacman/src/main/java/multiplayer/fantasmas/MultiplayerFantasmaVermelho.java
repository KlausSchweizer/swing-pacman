package multiplayer.fantasmas;

import main.Direcao;
import mapa.Mapa;
import personagem.fantasma.FantasmaVermelho;

public class MultiplayerFantasmaVermelho extends FantasmaVermelho {
    public MultiplayerFantasmaVermelho(int posY, int posX) {
        super(posY, posX);
    }

    @Override
    public void mover(Mapa mapa) {
        int maximoY = mapa.getMapa().length - 1;
        int maximoX = mapa.getMapa()[0].length - 1;

        if (direcao == Direcao.BAIXO) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.BAIXO)) {
                this.posY++;
                if (this.posY > maximoY) {
                    this.posY = 0;
                }
            }
        } else if (direcao == Direcao.CIMA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.CIMA)) {
                this.posY--;
                if (this.posY < 0) {
                    this.posY = maximoY;
                }
            }
        } else if (direcao == Direcao.DIREITA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.DIREITA)) {
                this.posX++;
                if (this.posX > maximoX) {
                    this.posX = 0;
                }
            }
        } else if (direcao == Direcao.ESQUERDA) {
            if (!mapa.getMapa()[posY][posX].getDirecoesBloqueadas().contains(Direcao.ESQUERDA)) {
                this.posX--;
                if (this.posX < 0) {
                    this.posX = maximoX;
                }
            }
        }

        mapa.getMapa()[posY][posX].setItem(null);
    }
}
