/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.criarmapa.transformartile;

import enums.Direcao;
import java.io.IOException;
import java.util.List;
import mapa.Tile;
import mapa.criarmapa.imagemetile.ImagemTile;

/**
 *
 * @author Intel
 */
public class TransformarTileParede extends ImagemTile{
    
    public TransformarTileParede(int i, int j){
        super(i, j);
        
    }
    public static void main(String[] args) throws IOException{
    int i = 0;
    int j = 0;
    TransformarTileParede transformarTileParede = new TransformarTileParede(i, j);
    List<Direcao> direcoesParede = transformarTileParede.verificarDirecoes.executar(ImagemTile.textoMapa, i, j);
    
    new TransformarTileParede(i, j).executar(direcoesParede , i, j);
    }
    public Tile executar(List<Direcao> direcoesParede, int i, int j){
        
    boolean hasBaixo = direcoesParede.contains(Direcao.BAIXO);
    boolean hasCima = direcoesParede.contains(Direcao.CIMA);
    boolean hasDireita = direcoesParede.contains(Direcao.DIREITA);
    boolean hasEsquerda = direcoesParede.contains(Direcao.ESQUERDA);
        System.out.println("hasBaixo=" + hasBaixo + " hasCima=" + hasCima +
    " hasDireita=" + hasDireita + " hasEsquerda=" + hasEsquerda);
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
            return new Tile(i, j, esquerdaFechada, direcoesParede, null);
        }
        return new Tile(i, j, aberto, direcoesParede, null);
    }   
}
