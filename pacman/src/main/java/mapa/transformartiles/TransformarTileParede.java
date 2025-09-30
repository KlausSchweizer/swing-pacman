/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa.transformartiles;

import enums.Direcao;
import java.io.IOException;
import java.util.List;
import mapa.Tile;
import mapa.imagens.ImagensTile;

/**
 *
 * @author Intel
 */
public class TransformarTileParede {

    public Tile executar(List<Direcao> direcoesParede, int i, int j) {
        boolean hasLivreBaixo = !direcoesParede.contains(Direcao.BAIXO);
        boolean hasLivreCima = !direcoesParede.contains(Direcao.CIMA);
        boolean hasLivreDireita = !direcoesParede.contains(Direcao.DIREITA);
        boolean hasLivreEsquerda = !direcoesParede.contains(Direcao.ESQUERDA);

        if (hasLivreBaixo && hasLivreCima && hasLivreDireita && hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.fechado, direcoesParede, null);
        } else if (hasLivreBaixo && hasLivreCima && hasLivreDireita) {
            return new Tile(i, j, ImagensTile.esquerdaAberta, direcoesParede, null);
        } else if (hasLivreBaixo && hasLivreCima && hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.direitaAberta, direcoesParede, null);
        } else if (hasLivreBaixo && hasLivreDireita && hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.cimaAberta, direcoesParede, null);
        } else if (hasLivreCima && hasLivreDireita && hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.baixoAberta, direcoesParede, null);
        } else if (hasLivreBaixo && hasLivreCima) {
            return new Tile(i, j, ImagensTile.ladosAbertos, direcoesParede, null);
        } else if (hasLivreBaixo && hasLivreDireita) {
            return new Tile(i, j, ImagensTile.esquerdaCimaAbertas, direcoesParede, null);
        } else if (hasLivreBaixo && hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.direitaCimaAbertas, direcoesParede, null);
        } else if (hasLivreCima && hasLivreDireita) {
            return new Tile(i, j, ImagensTile.esquerdaBaixoAbertas, direcoesParede, null);
        } else if (hasLivreCima && hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.direitaBaixoAbertas, direcoesParede, null);
        } else if (hasLivreDireita && hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.alturasAbertas, direcoesParede, null);
        } else if (hasLivreBaixo) {
            return new Tile(i, j, ImagensTile.baixoFechada, direcoesParede, null);
        } else if (hasLivreCima) {
            return new Tile(i, j, ImagensTile.cimaFechada, direcoesParede, null);
        } else if (hasLivreDireita) {
            return new Tile(i, j, ImagensTile.direitaFechada, direcoesParede, null);
        } else if (hasLivreEsquerda) {
            return new Tile(i, j, ImagensTile.esquerdaFechada, direcoesParede, null);
        }
        return new Tile(i, j, ImagensTile.aberto, direcoesParede, null);
    }
}
