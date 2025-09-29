/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.criarmapa;

import mapa.criarmapa.imagemetile.ImagemTile;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Intel
 */
public class LerImagens extends ImagemTile{
    public LerImagens(int i, int j) throws IOException{
    super(i, j);
    }
    public static void main(String[] args) throws IOException {
        int i = 0;
        int j = 0;
        new LerImagens(i, j).executar();
    }
    
    public void executar()throws IOException {
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

        image = new File("imagens/tiles/saida-fantasma.png");
        saidaFantasmas = ImageIO.read(image);

        image = new File("imagens/tiles/cima-aberta.png");
        cimaAberta = ImageIO.read(image);

        image = new File("imagens/tiles/baixo-aberta.png");
        baixoAberta = ImageIO.read(image);
    }
}
