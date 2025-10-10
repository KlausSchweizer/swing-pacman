/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasma.bfs;

import mapa.matrizmapa.MatrizMapa;

/**
 *
 * @author klaus
 */
public class ConversorMapaFantasmas {

    //Faz os fantasmas ignorarem os '#' que só eles podem passar
    public char[][] converterMapaFantasma(char[][] textoMapa) {
        for (int i = 0; i < textoMapa.length; i++) {
            for (int j = 0; j < textoMapa[0].length; j++) {
                if (textoMapa[i][j] == MatrizMapa.SAIDA_FANTASMA) {

                    boolean hasParedeBaixo = textoMapa[i + 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeCima = textoMapa[i - 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeDireita = textoMapa[i][j + 1] != MatrizMapa.LIVRE;
                    boolean hasParedeEsquerda = textoMapa[i][j - 1] != MatrizMapa.LIVRE;
                    if (hasParedeBaixo && hasParedeDireita && hasParedeEsquerda) {
                        if (!hasParedeCima || !(hasParedeDireita || hasParedeEsquerda)) {
                            textoMapa[i][j] = MatrizMapa.LIVRE;
                        }
                    }
                }
            }
        }
        return textoMapa;
    }
}
