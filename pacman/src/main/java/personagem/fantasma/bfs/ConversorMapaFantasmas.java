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
    public char[][] converterMapaFantasma(char[][] textoMapa) {
        char[][] mapa = copiarMapa(textoMapa);

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] == MatrizMapa.SAIDA_FANTASMA) {

                    boolean hasParedeBaixo = mapa[i + 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeCima = mapa[i - 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeDireita = mapa[i][j + 1] != MatrizMapa.LIVRE;
                    boolean hasParedeEsquerda = mapa[i][j - 1] != MatrizMapa.LIVRE;
                    if (hasParedeBaixo && hasParedeDireita && hasParedeEsquerda) {
                        if (!hasParedeCima || !(hasParedeDireita || hasParedeEsquerda)) {
                            mapa[i][j] = MatrizMapa.LIVRE;
                        }
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
