/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import mapa.matrizmapa.MatrizMapa;

/**
 *
 * @author klaus
 */
public class TxtParser {

    private MatrizMapa matrizParaMapa;
    private char[][] textoMapa;

    public Mapa criarMapa(String filePath) {
        try {
            this.matrizParaMapa = new MatrizMapa(filePath);
            int quantidadeLinhas = matrizParaMapa.getNumeroLinhas();
            int quantidadeColunas = matrizParaMapa.getNumeroColunas();

            textoMapa = converterMatriz(quantidadeLinhas, quantidadeColunas, filePath);
            Mapa mapa = matrizParaMapa.converterMatrizMapa(textoMapa);
            mapa.setTextoMapa(textoMapa);
            return mapa;
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
    
    public char[][] converterMatriz(int quantidadeLinhas, int quantidadeColunas, String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            if (quantidadeLinhas > 0 && quantidadeColunas > 0) {
                char[][] textoMapa = new char[quantidadeLinhas][quantidadeColunas];
                String line = "";
                for (int i = 0; line != null; i++) {
                    line = br.readLine();
                    if (line != null) {
                        textoMapa[i] = line.toCharArray();
                    }
                }
                return textoMapa;
            }
            return null;
        }
    }

}
