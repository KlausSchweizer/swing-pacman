/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personagem.fantasma.bfs;

import mapa.matrizmapa.MatrizMapa;

/**
 *
 * @author klaus
 */
public class ConversorMapaFantasmas {

    //Faz os fantasmas ignorarem os '#' que só eles podem passar
    public char[][] converterMapaFantasma(char[][] mapa) {
        char[][] novoMapa = copiarMapa(mapa);

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] == MatrizMapa.SAIDA_FANTASMA || mapa[i][j] == MatrizMapa.FANTASMA) {

                    if (i == 0 || j == 0 || i == mapa.length - 1 || j == mapa[0].length - 1) {
                        continue;
                    }

                    boolean hasParedeBaixo = novoMapa[i + 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeCima = novoMapa[i - 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeDireita = novoMapa[i][j + 1] != MatrizMapa.LIVRE;
                    boolean hasParedeEsquerda = novoMapa[i][j - 1] != MatrizMapa.LIVRE;

                    if (!(!hasParedeBaixo && (!hasParedeCima || !hasParedeDireita || !hasParedeEsquerda))) {
                        mapa[i][j] = MatrizMapa.VAZIO;
                    }
                    if(!hasParedeDireita || !hasParedeBaixo) {
                        mapa[i][j] = MatrizMapa.SAIDA_FANTASMA;
                    }
                    if(!hasParedeEsquerda) {
                        mapa[i][j] = MatrizMapa.SAIDA_FANTASMA;
                    }
                }
            }
        }
        return mapa;
    }

    private char[][] copiarMapa(char[][] textoMapa) {
        char[][] mapa = new char[textoMapa.length][textoMapa[0].length];
        for (int i = 0; i < textoMapa.length; i++) {
            for (int j = 0; j < textoMapa[0].length; j++) {
                mapa[i][j] = textoMapa[i][j];
            }
        }
        return mapa;
    }
}
