package mapa.direcoes;

import enums.Direcao;

import java.util.ArrayList;
import java.util.List;

import mapa.matrizmapa.MatrizMapa;

public class VerificarDirecoes {

    public List<Direcao> verificarDirecoes(char[][] textoMapa, int i, int j) {
        List<Direcao> direcoesParede = new ArrayList<>();

        if (hasParedeEsquerda(textoMapa, i, j)) {
            direcoesParede.add(Direcao.ESQUERDA);
        }
        if (hasParedeCima(textoMapa, i, j)) {
            direcoesParede.add(Direcao.CIMA);
        }
        if (hasParedeBaixo(textoMapa, i, j)) {
            direcoesParede.add(Direcao.BAIXO);
        }
        if (hasParedeDireita(textoMapa, i, j)) {
            direcoesParede.add(Direcao.DIREITA);
        }

        return direcoesParede;
    }

    public boolean hasParedeEsquerda(char[][] textoMapa, int i, int j) {
        return j > 0 && (textoMapa[i][j - 1] == MatrizMapa.PAREDE || textoMapa[i][j - 1] == MatrizMapa.SAIDA_FANTASMA
                || textoMapa[i][j - 1] == MatrizMapa.FANTASMA);
    }

    public boolean hasParedeDireita(char[][] textoMapa, int i, int j) {
        return j + 1 < textoMapa[i].length && (textoMapa[i][j + 1] == MatrizMapa.PAREDE
                || textoMapa[i][j + 1] == MatrizMapa.SAIDA_FANTASMA || textoMapa[i][j + 1] == MatrizMapa.FANTASMA);
    }

    public boolean hasParedeCima(char[][] textoMapa, int i, int j) {
        return i > 0 && (textoMapa[i - 1][j] == MatrizMapa.PAREDE || textoMapa[i - 1][j] == MatrizMapa.SAIDA_FANTASMA
                || textoMapa[i - 1][j] == MatrizMapa.FANTASMA);
    }

    public boolean hasParedeBaixo(char[][] textoMapa, int i, int j) {
        return i + 1 < textoMapa.length && (textoMapa[i + 1][j] == MatrizMapa.PAREDE
                || textoMapa[i + 1][j] == MatrizMapa.SAIDA_FANTASMA || textoMapa[i + 1][j] == MatrizMapa.FANTASMA);
    }
}
