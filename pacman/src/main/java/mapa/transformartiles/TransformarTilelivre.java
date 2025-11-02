/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.transformartiles;

import main.Direcao;
import itens.Ponto;
import java.util.List;
import mapa.Tile;
import mapa.imagens.ImagensTile;

/**
 *
 * @author Intel
 */
public class TransformarTilelivre {

    public Tile executar(List<Direcao> direcoesParede, int i, int j) {
        boolean hasParedeBaixo = direcoesParede.contains(Direcao.BAIXO);
        boolean hasParedeCima = direcoesParede.contains(Direcao.CIMA);
        boolean hasParedeDireita = direcoesParede.contains(Direcao.DIREITA);
        boolean hasParedeEsquerda = direcoesParede.contains(Direcao.ESQUERDA);
        
        if (hasParedeBaixo && hasParedeCima && hasParedeDireita && hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.fechado, direcoesParede, new Ponto());
        } else if (hasParedeBaixo && hasParedeCima && hasParedeDireita) {
            return new Tile(i, j, ImagensTile.esquerdaAberta, direcoesParede, new Ponto());
        } else if (hasParedeBaixo && hasParedeCima && hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.direitaAberta, direcoesParede, new Ponto());
        } else if (hasParedeBaixo && hasParedeDireita && hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.cimaAberta, direcoesParede, new Ponto());
        } else if (hasParedeCima && hasParedeDireita && hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.baixoAberta, direcoesParede, new Ponto());
        } else if (hasParedeBaixo && hasParedeCima) {
//            if (textoMapa[i + 1][j] != '#') {
                return new Tile(i, j, ImagensTile.ladosAbertos, direcoesParede, new Ponto());
//            } else {
//                return new Tile(i, j, ImagensTile.cimaFechada, direcoesParede, new Ponto());
//            }
        } else if (hasParedeBaixo && hasParedeDireita) {
            return new Tile(i, j, ImagensTile.esquerdaCimaAbertas, direcoesParede, new Ponto());
        } else if (hasParedeBaixo && hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.direitaCimaAbertas, direcoesParede, new Ponto());
        } else if (hasParedeCima && hasParedeDireita) {
            return new Tile(i, j, ImagensTile.esquerdaBaixoAbertas, direcoesParede, new Ponto());
        } else if (hasParedeCima && hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.direitaBaixoAbertas, direcoesParede, new Ponto());
        } else if (hasParedeDireita && hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.alturasAbertas, direcoesParede, new Ponto());
        } else if (hasParedeBaixo) {
//            if (textoMapa[i + 1][j] != '#') {
                return new Tile(i, j, ImagensTile.baixoFechada, direcoesParede, new Ponto());
//            } else {
//                return new Tile(i, j, ImagensTile.aberto, direcoesParede, new Ponto());
//            }
        } else if (hasParedeCima) {
            return new Tile(i, j, ImagensTile.cimaFechada, direcoesParede, new Ponto());
        } else if (hasParedeDireita) {
            return new Tile(i, j, ImagensTile.direitaFechada, direcoesParede, new Ponto());
        } else if (hasParedeEsquerda) {
            return new Tile(i, j, ImagensTile.esquerdaFechada, direcoesParede, new Ponto());
        } else {
            return new Tile(i, j, ImagensTile.aberto, direcoesParede, new Ponto());
        }
    }
}
