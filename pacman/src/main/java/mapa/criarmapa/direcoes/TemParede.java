/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.criarmapa.direcoes;

/**
 *
 * @author Intel
 */
public class TemParede {
     public boolean hasParedeEsquerda(char[][] textoMapa, int i, int j) {
        return j > 0 && (textoMapa[i][j - 1] == '$' || textoMapa[i][j - 1] == '#');
    } public boolean hasParedeDireita(char[][] textoMapa, int i, int j) {
        return j + 1 < textoMapa[i].length && (textoMapa[i][j + 1] == '$' || textoMapa[i][j + 1] == '#');
    }

    public boolean hasParedeCima(char[][] textoMapa, int i, int j) {
        return i > 0 && (textoMapa[i - 1][j] == '$' || textoMapa[i - 1][j] == '#');
    }

    public boolean hasParedeBaixo(char[][] textoMapa, int i, int j) {
        return i + 1 < textoMapa.length && (textoMapa[i + 1][j] == '$' || textoMapa[i + 1][j] == '#');
    }
}
