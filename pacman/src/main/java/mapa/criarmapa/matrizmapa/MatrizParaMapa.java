/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.criarmapa.matrizmapa;

import enums.Direcao;
import itens.PowerUp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mapa.Mapa;
import mapa.Tile;
import mapa.criarmapa.contar.ContarLinhasColunas;
import mapa.criarmapa.direcoes.VerificarDirecoes;
import mapa.criarmapa.transformartile.TransformarTileFantasma;
import mapa.criarmapa.transformartile.TransformarTileParede;
import mapa.criarmapa.transformartile.TransformarTileSaida;
import mapa.criarmapa.transformartile.TransformarTilelivre;

/**
 *
 * @author Intel
 */
public class MatrizParaMapa {
    private static final int INDICE_COLUNAS = 0;
    private TransformarTileFantasma transformarTileFantasma;
    private TransformarTileParede transformarTileParede;
    private TransformarTilelivre transformarTileLivre;
    private TransformarTileSaida transformarTileSaida;
    private VerificarDirecoes verificarDirecoes;
    private static char[][] textoMapa;

    private List<Direcao> direcoesParede;
    private ConverterMatriz converterMatriz;
    private int numeroLinhas;
    private int numeroColunas;
    private ContarLinhasColunas contar;
    private String filePath;
    private BufferedReader br;
    private int i;
    private int j;
    
    public static void main(String[] args) {
        new MatrizParaMapa().executar(textoMapa);
    }
    
    public MatrizParaMapa(){
    transformarTileFantasma = new TransformarTileFantasma(i, j);
    transformarTileParede = new TransformarTileParede(i, j);
    transformarTileLivre = new TransformarTilelivre(i, j);
    transformarTileSaida = new TransformarTileSaida(i, j);
    verificarDirecoes = new VerificarDirecoes();
   try{
    contar = new ContarLinhasColunas();
    verificarDirecoes = new VerificarDirecoes();
    converterMatriz = new ConverterMatriz();
    filePath = "mapas/mapa.txt";
    br = new BufferedReader(new FileReader(new File(filePath)));
    numeroColunas = contar.contarColunas(filePath);
    numeroLinhas = contar.contarLinhas(filePath);
    textoMapa = converterMatriz.executar(numeroLinhas, numeroColunas, br);
    direcoesParede = verificarDirecoes.executar(textoMapa, i, j);
    i =0;
    j =0;
    
    }catch(IOException e){
    
    }
    }
    
    public Mapa executar(char[][] textoMapa){
        Mapa mapa = new Mapa();
        Tile[][] matrizTile = new Tile[textoMapa.length][textoMapa[INDICE_COLUNAS].length];

        for (int i = 0; i < textoMapa.length; i++) {
            for (int j = 0; j < textoMapa[i].length; j++) {
                List<Direcao> direcoesParede = verificarDirecoes.executar(textoMapa, i, j);
                verificarDirecoes.executar(textoMapa, i, j);
                if (textoMapa[i][j] == 'o') {
                    matrizTile[i][j] = transformarTileLivre.executar(direcoesParede, i, j);
                } else if (textoMapa[i][j] == 'O')  {
                    matrizTile[i][j] = transformarTileLivre.executar(direcoesParede, i, j);
                    matrizTile[i][j].setItem(new PowerUp());
                } else if (textoMapa[i][j] == '$') {
                    matrizTile[i][j] = transformarTileParede.executar(direcoesParede, i, j);
                } else if (textoMapa[i][j] == '#') {
                    matrizTile[i][j] = transformarTileFantasma.executar(direcoesParede, i, j);
                } else if (textoMapa[i][j] == '^' || textoMapa[i][j] == 'v'
                        || textoMapa[i][j] == '>' || textoMapa[i][j] == '<') {
                    matrizTile[i][j] = transformarTileSaida.executar(direcoesParede, i, j);
                }
            }
        }

        mapa.setMapa(matrizTile);
        return mapa;
    }
}
