package mapa.criarmapa.imagemetile;

import enums.Direcao;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import mapa.criarmapa.contar.ContarLinhasColunas;
import mapa.criarmapa.direcoes.VerificarDirecoes;
import mapa.criarmapa.matrizmapa.ConverterMatriz;

public abstract class ImagemTile {
    protected static BufferedImage ladosAbertos;
    protected static BufferedImage fechado;
    protected static BufferedImage aberto;
    protected static BufferedImage esquerdaAberta;
    protected static BufferedImage direitaAberta;
    protected static BufferedImage alturasAbertas;
    protected static BufferedImage direitaFechada;
    protected static BufferedImage esquerdaFechada;
    protected static BufferedImage cimaFechada;
    protected static BufferedImage baixoFechada;
    protected static BufferedImage direitaCimaAbertas;
    protected static BufferedImage esquerdaCimaAbertas;
    protected static BufferedImage esquerdaBaixoAbertas;
    protected static BufferedImage direitaBaixoAbertas;
    protected static BufferedImage saidaFantasmas;
    protected static BufferedImage baixoAberta;
    protected static BufferedImage cimaAberta;

    protected List<Direcao> direcoesParede;
    protected int i;
    protected int j;
    protected static char[][] textoMapa;
    protected boolean hasBaixo;
    protected boolean hasCima;
    protected boolean hasDireita;
    protected boolean hasEsquerda;
    
    protected ConverterMatriz converterMatriz;
    protected int numeroLinhas;
    protected int numeroColunas;
    protected ContarLinhasColunas contar;
    protected String filePath;
    protected BufferedReader br;
    protected VerificarDirecoes verificarDirecoes;
            
    public ImagemTile(int i, int j) {
    try {
        contar = new ContarLinhasColunas();
        verificarDirecoes = new VerificarDirecoes();
        converterMatriz = new ConverterMatriz();
        filePath = "mapas/mapa.txt";
        br = new BufferedReader(new FileReader(new File(filePath)));
        numeroColunas = contar.contarColunas(filePath);
        numeroLinhas = contar.contarLinhas(filePath);
        textoMapa = converterMatriz.executar(numeroLinhas, numeroColunas, br);

        this.i = i;
        this.j = j;
        this.direcoesParede = calcularDirecoes(textoMapa, i, j);
    } catch(IOException e) {
        e.printStackTrace();
    }
}
protected List<Direcao> calcularDirecoes(char[][] mapa, int linha, int coluna) {
    VerificarDirecoes verificarDirecoes = new VerificarDirecoes();
        
    return verificarDirecoes.executar(mapa, linha, coluna);
}
}