/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import enums.Direcao;
import itens.Ponto;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author klaus
 */
public class TxtParser {

    private static final int INDICE_COLUNAS = 0;
    private BufferedImage ladosAbertos;
    private BufferedImage fechado;
    private BufferedImage aberto;
    private BufferedImage esquerdaAberta;
    private BufferedImage direitaAberta;
    private BufferedImage alturasAbertas;
    private BufferedImage direitaFechada;
    private BufferedImage esquerdaFechada;
    private BufferedImage cimaFechada;
    private BufferedImage baixoFechada;
    private BufferedImage direitaCimaAbertas;
    private BufferedImage esquerdaCimaAbertas;
    private BufferedImage esquerdaBaixoAbertas;
    private BufferedImage direitaBaixoAbertas;
    private BufferedImage saidaFantasmas;
    private BufferedImage baixoAberta;
    private BufferedImage cimaAberta;

    public Mapa criarMapa(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            lerImagens();
            int quantidadeLinhas = contarLinhas(filePath);
            int quantidadeColunas = contarColunas(filePath);

            char[][] textoMapa = converterMatriz(quantidadeLinhas, quantidadeColunas, br);
            return matrizParaMapa(textoMapa);
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

    private char[][] converterMatriz(int quantidadeLinhas, int quantidadeColunas, BufferedReader br)
            throws IOException {
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

    private void lerImagens() throws IOException {
        File image = new File("imagens/tiles/aberto.png");
        aberto = ImageIO.read(image);

        image = new File("imagens/tiles/fechado.png");
        fechado = ImageIO.read(image);

        image = new File("imagens/tiles/alturas-abertas.png");
        alturasAbertas = ImageIO.read(image);

        image = new File("imagens/tiles/baixo-fechada.png");
        baixoFechada = ImageIO.read(image);

        image = new File("imagens/tiles/cima-fechada.png");
        cimaFechada = ImageIO.read(image);

        image = new File("imagens/tiles/direita-aberta.png");
        direitaAberta = ImageIO.read(image);

        image = new File("imagens/tiles/direita-baixo-abertas.png");
        direitaBaixoAbertas = ImageIO.read(image);

        image = new File("imagens/tiles/direita-cima-abertas.png");
        direitaCimaAbertas = ImageIO.read(image);

        image = new File("imagens/tiles/direita-fechada.png");
        direitaFechada = ImageIO.read(image);

        image = new File("imagens/tiles/esquerda-aberta.png");
        esquerdaAberta = ImageIO.read(image);

        image = new File("imagens/tiles/esquerda-baixo-abertas.png");
        esquerdaBaixoAbertas = ImageIO.read(image);

        image = new File("imagens/tiles/esquerda-cima-abertas.png");
        esquerdaCimaAbertas = ImageIO.read(image);

        image = new File("imagens/tiles/esquerda-fechada.png");
        esquerdaFechada = ImageIO.read(image);

        image = new File("imagens/tiles/lados-abertos.png");
        ladosAbertos = ImageIO.read(image);

        image = new File("imagens/tiles/saida-fantasmas.png");
        saidaFantasmas = ImageIO.read(image);

        image = new File("imagens/tiles/cima-aberta.png");
        cimaAberta = ImageIO.read(image);

        image = new File("imagens/tiles/baixo-aberta.png");
        baixoAberta = ImageIO.read(image);
    }

    private Mapa matrizParaMapa(char[][] textoMapa) {
        Mapa mapa = new Mapa();
        Tile[][] matrizTile = new Tile[textoMapa.length][textoMapa[INDICE_COLUNAS].length];

        for (int i = 0; i < textoMapa.length; i++) {
            for (int j = 0; j < textoMapa[i].length; j++) {
                List<Direcao> direcoesParede = new ArrayList<>();
                verificarDirecoes(direcoesParede, textoMapa, i, j);
                if (textoMapa[i][j] == 'o') {
                    matrizTile[i][j] = transformarTileLivre(direcoesParede, i, j);
                } else if (textoMapa[i][j] == '$') {
                    matrizTile[i][j] = transformarTileParede(direcoesParede, i, j);
                } else if (textoMapa[i][j] == '#') {
                    matrizTile[i][j] = transformarTileFantasmas(direcoesParede, i, j);
                } else if (textoMapa[i][j] == '^' || textoMapa[i][j] == 'v'
                        || textoMapa[i][j] == '>' || textoMapa[i][j] == '<') {
                    matrizTile[i][j] = transformarTileSaidas(direcoesParede, i, j);
                }
            }
        }

        mapa.setMapa(matrizTile);
        return mapa;
    }

    private void verificarDirecoes(List<Direcao> direcoesParede, char[][] textoMapa, int i, int j) {
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
    }

    private boolean hasParedeEsquerda(char[][] textoMapa, int i, int j) {
        return j > 0 && (textoMapa[i][j - 1] == '$' || textoMapa[i][j - 1] == '#');
    }

    private boolean hasParedeDireita(char[][] textoMapa, int i, int j) {
        return j + 1 < textoMapa[i].length && (textoMapa[i][j + 1] == '$' || textoMapa[i][j + 1] == '#');
    }

    private boolean hasParedeCima(char[][] textoMapa, int i, int j) {
        return i > 0 && (textoMapa[i - 1][j] == '$' || textoMapa[i - 1][j] == '#');
    }

    private boolean hasParedeBaixo(char[][] textoMapa, int i, int j) {
        return i + 1 < textoMapa.length && (textoMapa[i + 1][j] == '$' || textoMapa[i + 1][j] == '#');
    }

    private Tile transformarTileLivre(List<Direcao> direcoesParede, int i, int j) {
        for (Direcao direcao : direcoesParede) {
            boolean hasBaixo = direcao == Direcao.BAIXO;
            boolean hasCima = direcao == Direcao.CIMA;
            boolean hasDireita = direcao == Direcao.DIREITA;
            boolean hasEsquerda = direcao == Direcao.ESQUERDA;

            if (hasBaixo && hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, fechado, direcoesParede, new Ponto());
            } else if (hasBaixo && hasCima && hasDireita) {
                return new Tile(i, j, esquerdaAberta, direcoesParede, new Ponto());
            } else if (hasBaixo && hasCima && hasEsquerda) {
                return new Tile(i, j, direitaAberta, direcoesParede, new Ponto());
            } else if (hasBaixo && hasDireita && hasEsquerda) {
                return new Tile(i, j, cimaAberta, direcoesParede, new Ponto());
            } else if (hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, baixoAberta, direcoesParede, new Ponto());
            } else if (hasBaixo && hasCima) {
                return new Tile(i, j, ladosAbertos, direcoesParede, new Ponto());
            } else if (hasBaixo && hasDireita) {
                return new Tile(i, j, esquerdaCimaAbertas, direcoesParede, new Ponto());
            } else if (hasBaixo && hasEsquerda) {
                return new Tile(i, j, direitaCimaAbertas, direcoesParede, new Ponto());
            } else if (hasCima && hasDireita) {
                return new Tile(i, j, esquerdaBaixoAbertas, direcoesParede, new Ponto());
            } else if (hasCima && hasEsquerda) {
                return new Tile(i, j, direitaBaixoAbertas, direcoesParede, new Ponto());
            } else if (hasDireita && hasEsquerda) {
                return new Tile(i, j, alturasAbertas, direcoesParede, new Ponto());
            } else if (hasBaixo) {
                return new Tile(i, j, baixoFechada, direcoesParede, new Ponto());
            } else if (hasCima) {
                return new Tile(i, j, cimaFechada, direcoesParede, new Ponto());
            } else if (hasDireita) {
                return new Tile(i, j, direitaFechada, direcoesParede, new Ponto());
            } else if (hasEsquerda) {
                return new Tile(i, j, esquerdaAberta, direcoesParede, new Ponto());
            } else {
                return new Tile(i, j, aberto, direcoesParede, new Ponto());
            }
        }
        return null;
    }

    private Tile transformarTileParede(List<Direcao> direcoesParede, int i, int j) {
        for (Direcao direcao : direcoesParede) {
            boolean hasBaixo = direcao == Direcao.BAIXO;
            boolean hasCima = direcao == Direcao.CIMA;
            boolean hasDireita = direcao == Direcao.DIREITA;
            boolean hasEsquerda = direcao == Direcao.ESQUERDA;

            if (hasBaixo && hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, fechado, direcoesParede, null);
            } else if (hasBaixo && hasCima && hasDireita) {
                return new Tile(i, j, esquerdaAberta, direcoesParede, null);
            } else if (hasBaixo && hasCima && hasEsquerda) {
                return new Tile(i, j, direitaAberta, direcoesParede, null);
            } else if (hasBaixo && hasDireita && hasEsquerda) {
                return new Tile(i, j, cimaAberta, direcoesParede, null);
            } else if (hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, baixoAberta, direcoesParede, null);
            } else if (hasBaixo && hasCima) {
                return new Tile(i, j, ladosAbertos, direcoesParede, null);
            } else if (hasBaixo && hasDireita) {
                return new Tile(i, j, esquerdaCimaAbertas, direcoesParede, null);
            } else if (hasBaixo && hasEsquerda) {
                return new Tile(i, j, direitaCimaAbertas, direcoesParede, null);
            } else if (hasCima && hasDireita) {
                return new Tile(i, j, esquerdaBaixoAbertas, direcoesParede, null);
            } else if (hasCima && hasEsquerda) {
                return new Tile(i, j, direitaBaixoAbertas, direcoesParede, null);
            } else if (hasDireita && hasEsquerda) {
                return new Tile(i, j, alturasAbertas, direcoesParede, null);
            } else if (hasBaixo) {
                return new Tile(i, j, baixoFechada, direcoesParede, null);
            } else if (hasCima) {
                return new Tile(i, j, cimaFechada, direcoesParede, null);
            } else if (hasDireita) {
                return new Tile(i, j, direitaFechada, direcoesParede, null);
            } else if (hasEsquerda) {
                return new Tile(i, j, esquerdaAberta, direcoesParede, null);
            }
        }
        return null;
    }

    private Tile transformarTileFantasmas(List<Direcao> direcoesParede, int i, int j) {
        for (Direcao direcao : direcoesParede) {
            boolean hasBaixo = direcao == Direcao.BAIXO;
            boolean hasCima = direcao == Direcao.CIMA;
            boolean hasDireita = direcao == Direcao.DIREITA;
            boolean hasEsquerda = direcao == Direcao.ESQUERDA;

            if (hasBaixo && hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, fechado, direcoesParede, null);
            } else if (hasBaixo && hasCima && hasDireita) {
                return new Tile(i, j, esquerdaAberta, direcoesParede, null);
            } else if (hasBaixo && hasCima && hasEsquerda) {
                return new Tile(i, j, direitaAberta, direcoesParede, null);
            } else if (hasBaixo && hasDireita && hasEsquerda) {
                return new Tile(i, j, saidaFantasmas, direcoesParede, null);
            } else if (hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, baixoAberta, direcoesParede, null);
            } else if (hasBaixo && hasCima) {
                return new Tile(i, j, ladosAbertos, direcoesParede, null);
            } else if (hasBaixo && hasDireita) {
                return new Tile(i, j, esquerdaCimaAbertas, direcoesParede, null);
            } else if (hasBaixo && hasEsquerda) {
                return new Tile(i, j, direitaCimaAbertas, direcoesParede, null);
            } else if (hasCima && hasDireita) {
                return new Tile(i, j, esquerdaBaixoAbertas, direcoesParede, null);
            } else if (hasCima && hasEsquerda) {
                return new Tile(i, j, direitaBaixoAbertas, direcoesParede, null);
            } else if (hasDireita && hasEsquerda) {
                return new Tile(i, j, alturasAbertas, direcoesParede, null);
            } else if (hasBaixo) {
                return new Tile(i, j, baixoFechada, direcoesParede, null);
            } else if (hasCima) {
                return new Tile(i, j, cimaFechada, direcoesParede, null);
            } else if (hasDireita) {
                return new Tile(i, j, direitaFechada, direcoesParede, null);
            } else if (hasEsquerda) {
                return new Tile(i, j, esquerdaFechada, direcoesParede, null);
            }
        }
        return null;
    }

    private Tile transformarTileSaidas(List<Direcao> direcoesParede, int i, int j) {
        for (Direcao direcao : direcoesParede) {
            boolean hasBaixo = direcao == Direcao.BAIXO;
            boolean hasCima = direcao == Direcao.CIMA;
            boolean hasDireita = direcao == Direcao.DIREITA;
            boolean hasEsquerda = direcao == Direcao.ESQUERDA;

            if (hasBaixo && hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, fechado, direcoesParede, new Ponto());
            } else if (hasBaixo && hasCima && hasDireita) {
                return new Tile(i, j, esquerdaAberta, direcoesParede, new Ponto());
            } else if (hasBaixo && hasCima && hasEsquerda) {
                return new Tile(i, j, direitaAberta, direcoesParede, new Ponto());
            } else if (hasBaixo && hasDireita && hasEsquerda) {
                return new Tile(i, j, cimaAberta, direcoesParede, new Ponto());
            } else if (hasCima && hasDireita && hasEsquerda) {
                return new Tile(i, j, baixoAberta, direcoesParede, new Ponto());
            } else if (hasBaixo && hasCima) {
                return new Tile(i, j, ladosAbertos, direcoesParede, new Ponto());
            } else if (hasBaixo && hasDireita) {
                return new Tile(i, j, esquerdaCimaAbertas, direcoesParede, new Ponto());
            } else if (hasBaixo && hasEsquerda) {
                return new Tile(i, j, direitaCimaAbertas, direcoesParede, new Ponto());
            } else if (hasCima && hasDireita) {
                return new Tile(i, j, esquerdaBaixoAbertas, direcoesParede, new Ponto());
            } else if (hasCima && hasEsquerda) {
                return new Tile(i, j, direitaBaixoAbertas, direcoesParede, new Ponto());
            } else if (hasDireita && hasEsquerda) {
                return new Tile(i, j, alturasAbertas, direcoesParede, new Ponto());
            } else if (hasBaixo) {
                return new Tile(i, j, baixoFechada, direcoesParede, new Ponto());
            } else if (hasCima) {
                return new Tile(i, j, cimaFechada, direcoesParede, new Ponto());
            } else if (hasDireita) {
                return new Tile(i, j, direitaFechada, direcoesParede, new Ponto());
            } else if (hasEsquerda) {
                return new Tile(i, j, esquerdaAberta, direcoesParede, new Ponto());
            } else {
                return new Tile(i, j, aberto, direcoesParede, new Ponto());
            }
        }
        return null;
    }
}
