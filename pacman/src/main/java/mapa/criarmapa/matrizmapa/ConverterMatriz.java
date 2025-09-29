/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.criarmapa.matrizmapa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import mapa.criarmapa.contar.ContarLinhasColunas;

/**
 *
 * @author Intel
 */
public class ConverterMatriz {
    private static int quantidadeLinhas;
    private static int quantidadeColunas;
    private static BufferedReader br;
    public static void main(String[] args) throws IOException {
        String filePath = "mapas/mapa.txt";
        ContarLinhasColunas contar = new ContarLinhasColunas();
        int numeroColunas = contar.contarColunas(filePath);
        int numeroLinhas = contar.contarLinhas(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            ConverterMatriz converter = new ConverterMatriz();
            char[][] mapa = converter.executar(quantidadeLinhas, quantidadeColunas, br);
            
            // Aqui você pode usar o mapa lido, por exemplo:
            for (char[] linha : mapa) {
                System.out.println(new String(linha));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    
       new ConverterMatriz().executar(quantidadeLinhas, quantidadeColunas, br);
    }
    public char[][] executar(int quantidadeLinhas, int quantidadeColunas, BufferedReader br)throws IOException {
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
