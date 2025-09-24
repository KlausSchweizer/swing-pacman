/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author klaus
 */
public class TxtParser {

    public Mapa criarMapa(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            int quantidadeLinhas = contarLinhas(filePath);
            int quantidadeColunas = contarColunas(filePath);

            char[][] textoMapa = converterMatriz(quantidadeLinhas, quantidadeColunas, br);
        } catch (IOException e) {
            System.err.println("Arquivo: " + filePath + " não encontrado!");
        }
        return null;
    }

    private int contarLinhas(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {

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
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line = br.readLine();
            if (line != null) {
                return line.toCharArray().length;
            }
            return -1;
        }
    }

    private char[][] converterMatriz(int quantidadeLinhas, int quantidadeColunas, BufferedReader br) throws IOException {
        if (quantidadeLinhas > 0 && quantidadeColunas > 0) {
            char[][] textoMapa = new char[quantidadeLinhas][quantidadeColunas];
            String line = "";
            for (int i = 0; line != null; i++) {
                line = br.readLine();
                textoMapa[i] = line.toCharArray();
            }
            return textoMapa;
        }
        return null;
    }
}


