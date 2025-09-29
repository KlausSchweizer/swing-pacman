/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.criarmapa.transformartile;

import enums.Direcao;
import itens.Ponto;
import java.io.IOException;
import java.util.List;
import mapa.Tile;
import mapa.criarmapa.imagemetile.ImagemTile;

/**
 *
 * @author Intel
 */
public class TransformarTileSaida extends ImagemTile{
    
    public TransformarTileSaida(int i , int j){
        super(i, j);
        
    }
    public static void main(String[] args) throws IOException{
    int i = 0;
    int j = 0;
    TransformarTileSaida transformarTileSaida = new TransformarTileSaida(i, j);
    List<Direcao> direcoesParede = transformarTileSaida.verificarDirecoes.executar(ImagemTile.textoMapa, i, j);
    
    new TransformarTileSaida(i, j).executar(direcoesParede , i, j);
    }
    public Tile executar(List<Direcao> direcoesParede, int i, int j){
        
    boolean hasBaixo = direcoesParede.contains(Direcao.BAIXO);
    boolean hasCima = direcoesParede.contains(Direcao.CIMA);
    boolean hasDireita = direcoesParede.contains(Direcao.DIREITA);
    boolean hasEsquerda = direcoesParede.contains(Direcao.ESQUERDA);
    System.out.println("hasBaixo=" + hasBaixo + " hasCima=" + hasCima +
    " hasDireita=" + hasDireita + " hasEsquerda=" + hasEsquerda);
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
            return new Tile(i, j, esquerdaFechada, direcoesParede, new Ponto());
        } else {
            return new Tile(i, j, aberto, direcoesParede, new Ponto());
        }
    }
}
