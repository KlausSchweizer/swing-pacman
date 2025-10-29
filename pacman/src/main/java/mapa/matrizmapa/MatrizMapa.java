/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.matrizmapa;

import enums.Direcao;
import itens.PowerUp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import mapa.Mapa;
import mapa.Posicao;
import mapa.Tile;
import mapa.direcoes.VerificarDirecoes;
import mapa.imagens.ImagensTile;
import mapa.transformartiles.TransformarTileFantasma;
import mapa.transformartiles.TransformarTileParede;
import mapa.transformartiles.TransformarTileSaida;
import mapa.transformartiles.TransformarTilelivre;

/**
 *
 * @author Intel
 */
public class MatrizMapa {

    public static final char PAREDE = '$';
    public static final char SAIDA_FANTASMA = '#';
    public static final char POWERUP = 'O';
    public static final char LIVRE = 'o';
    public static final char SAIDA_CIMA = '^';
    public static final char SAIDA_BAIXO = 'v';
    public static final char SAIDA_ESQUERDA = '<';
    public static final char SAIDA_DIREITA = '>';
    public static final char FANTASMA = 'G';
    public static final char PACMAN = 'C';

    private static final int INDICE_COLUNAS = 0;

    private int numeroColunas;
    private int numeroLinhas;

    private TransformarTileFantasma transformarTileFantasma;
    private TransformarTileParede transformarTileParede;
    private TransformarTilelivre transformarTileLivre;
    private TransformarTileSaida transformarTileSaida;
    private VerificarDirecoes verificarDirecoes;
    private ImagensTile imagens;

    public MatrizMapa(String filePath) throws IOException {
        verificarDirecoes = new VerificarDirecoes();
        numeroColunas = contarColunas(filePath);
        numeroLinhas = contarLinhas(filePath);

        transformarTileFantasma = new TransformarTileFantasma();
        transformarTileLivre = new TransformarTilelivre();
        transformarTileParede = new TransformarTileParede();
        transformarTileSaida = new TransformarTileSaida();
        imagens = new ImagensTile();
    }

    private int contarLinhas(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(filePath)))) {

            String line = "";

            for (int i = 0; line != null; i++) {
                line = br.readLine();
                if (line == null) {
                    return i;
                }
            }
            return -1;
        }
    }

    private int contarColunas(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)))) {
            String line = br.readLine();
            if (line != null) {
                return line.toCharArray().length;
            }
            return -1;
        }
    }

    public Mapa converterMatrizMapa(char[][] textoMapa) {
        Mapa mapa = new Mapa();
        Tile[][] matrizTile = new Tile[textoMapa.length][textoMapa[INDICE_COLUNAS].length];

        for (int i = 0; i < textoMapa.length; i++) {
            for (int j = 0; j < textoMapa[i].length; j++) {
                List<Direcao> direcoesParede = verificarDirecoes.verificarDirecoes(textoMapa, i, j);
                verificarDirecoes.verificarDirecoes(textoMapa, i, j);
                if (textoMapa[i][j] == LIVRE) {
                    matrizTile[i][j] = transformarTileLivre.executar(direcoesParede, i, j);
                } else if (textoMapa[i][j] == POWERUP) {
                    matrizTile[i][j] = transformarTileLivre.executar(direcoesParede, i, j);
                    matrizTile[i][j].setItem(new PowerUp());
                } else if (textoMapa[i][j] == PAREDE) {
                    matrizTile[i][j] = transformarTileParede.executar(direcoesParede, i, j);
                } else if (textoMapa[i][j] == SAIDA_FANTASMA) {
                    matrizTile[i][j] = transformarTileFantasma.executar(direcoesParede, i, j);
                } else if (textoMapa[i][j] == SAIDA_CIMA || textoMapa[i][j] == SAIDA_BAIXO
                        || textoMapa[i][j] == SAIDA_ESQUERDA || textoMapa[i][j] == SAIDA_DIREITA) {
                    matrizTile[i][j] = transformarTileSaida.executar(direcoesParede, i, j);
                } else if (textoMapa[i][j] == FANTASMA) {
                    matrizTile[i][j] = transformarTileFantasma.executar(direcoesParede, i, j);
                    mapa.adicionarSpawnFantasma(new Posicao(i, j));
                } else if (textoMapa[i][j] == PACMAN) {
                    matrizTile[i][j] = transformarTileLivre.executar(direcoesParede, i, j);
                    mapa.setSpawnPacman(new Posicao(i, j));
                }
            }
        }

        mapa.setMapa(matrizTile);
        return mapa;
    }

    public int getNumeroColunas() {
        return numeroColunas;
    }

    public int getNumeroLinhas() {
        return numeroLinhas;
    }
}
