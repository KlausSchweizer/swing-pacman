/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.criarmapa.contar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Intel
 */
public class ContarLinhasColunas {
    public int contarLinhas(String filePath) throws IOException {
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

    public int contarColunas(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line = br.readLine();
            if (line != null) {
                return line.toCharArray().length;
            }
            return -1;
        }
}
}
