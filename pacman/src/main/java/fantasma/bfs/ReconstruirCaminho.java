/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasma.bfs;

import enums.Direcao;

/**
 *
 * @author klaus
 */
public class ReconstruirCaminho {
    public Direcao reconstruirDirecaoCaminho(CelulaBFS celulaInicio, CelulaBFS proximaCelula) {
        int diferencaHorizontal = celulaInicio.getPosX() - proximaCelula.getPosX();
        int diferencaVertical = celulaInicio.getPosY() - proximaCelula.getPosY();

        if (diferencaVertical != 0) {
            if (diferencaVertical == ExplorarCaminho.CIMA) {
                return Direcao.CIMA;
            } else if (diferencaVertical == ExplorarCaminho.BAIXO) {
                return Direcao.BAIXO;
            }
        } else if (diferencaHorizontal != 0) {
            if (diferencaHorizontal == ExplorarCaminho.ESQUERDA) {
                return Direcao.ESQUERDA;
            } else if (diferencaHorizontal == ExplorarCaminho.DIREITA) {
                return Direcao.DIREITA;
            }
        }
        return null;
    }
}
