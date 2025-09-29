/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import mapa.criarmapa.LerImagens;
import mapa.criarmapa.contar.ContarLinhasColunas;
import mapa.criarmapa.matrizmapa.ConverterMatriz;
import mapa.criarmapa.matrizmapa.MatrizParaMapa;

/**
 *
 * @author klaus
 */
public class TxtParser {
    private LerImagens lerImagens;
    private ConverterMatriz converterMatriz;
    private MatrizParaMapa matrizParaMapa;
    private ContarLinhasColunas contar;
    private int i;
    private int j;
    private char[][] textoMapa;
    
    public TxtParser()throws IOException{
    contar = new ContarLinhasColunas();
    i = 0;
    j = 0;
    lerImagens = new LerImagens(i, j);
    converterMatriz = new ConverterMatriz();
    matrizParaMapa = new MatrizParaMapa();
    }
    
    public Mapa criarMapa(String filePath) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath))) ) {
            lerImagens.executar();
            int quantidadeLinhas = contar.contarLinhas(filePath);
            int quantidadeColunas = contar.contarColunas(filePath);

            textoMapa = converterMatriz.executar(quantidadeLinhas, quantidadeColunas, br);
            return matrizParaMapa.executar(textoMapa);
        } catch (IOException e) {
            File file = new File("mapas/mapa.txt");
            System.out.println("Caminho absoluto: " + file.getAbsolutePath());
            System.out.println("Existe? " + file.exists());
            System.out.println("Pode ler? " + file.canRead());

            System.err
                    .println("Arquivo: " + new File(filePath).getAbsolutePath() + " não encontrado!" + e.getMessage());
        }
        return null;
    }

    
    }
