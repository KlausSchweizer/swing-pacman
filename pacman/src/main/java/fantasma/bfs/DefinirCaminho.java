/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasma.bfs;

import enums.Direcao;
import java.util.LinkedList;
import java.util.Queue;
import mapa.Mapa;
import mapa.matrizmapa.MatrizMapa;

/**
 *
 * @author klaus
 */
public class DefinirCaminho {
    private final int CIMA = -1;
    private final int BAIXO = 1;
    private final int DIREITA = 1;
    private final int ESQUERDA = -1;
    /*
    Resumo do algoritmo:
    Começa na célula inicial,
    avança em cada "nível" de células por vez, no estilo:
        ***
        x*o Está aqui ('o'), nível 0, suponha que
    'x' seja o destino que queremos chegar
        -----------------
        **o
        xo- Os 'o' estão no nível 1, uma direção adjacente 
    ao nível 0 (já passou, marcando com '-')
        ------------------
        *o-
        X-- Agora os 'o' estao no nivel 2, e assim sucessivamente, 
    com os caminhos passados sendo marcados, agora o algoritmo chegou no 'x' 
    que queriamos chegar, pelo caminho mais curto.
    
    Basicamente esse é o algoritmo de chegar no caminho mais curto.
    Cada célula passada é adicionada em uma Queue(fila), e marcado como "true" 
    num array de booleans dos caminhos percorridos.
    
    Agora, para definir apenas qual a melhor direção para andar no
    nível 1 (objetivo das funções a seguir), para cada célula percorrida, 
    teremos que armazenar num HashMap<Celula, Celula>, onde cada célula percorrida
    tem uma "célula-pai", a célula que levou para chegar lá. Ou por como atributo
    essa ideia parece melhor, mas dá no mesmo.
    
    Quando chegar ao destino, deverá percorrer o caminho inverso, passando por 
    cada célula-pai até chegar àquela que tem por célula-pai a célula inicial.
     */
    protected Direcao decidirDirecao(int posYInicial, int posXInicial, int posYFinal, int posXFinal, Mapa mapa) {
        char[][] textoMapa = mapa.getTextoMapa();

        if (textoMapa[posXInicial][posYInicial] == MatrizMapa.PAREDE
                || textoMapa[posXInicial][posYInicial] == MatrizMapa.SAIDA_FANTASMA
                || textoMapa[posXFinal][posYFinal] == MatrizMapa.PAREDE
                || textoMapa[posXFinal][posYFinal] == MatrizMapa.SAIDA_FANTASMA) {
            return null;
        }

        boolean[][] posVisitadas = new boolean[textoMapa.length][textoMapa[0].length];
        posVisitadas[posXInicial][posYInicial] = true;

        Queue<CelulaBFS> proximasPosicoes = new LinkedList<>();
        proximasPosicoes.add(new CelulaBFS(posXInicial, posYInicial, null));
        

        while (!posVisitadas[posXFinal][posYFinal]) {
            
        }
        return null;
    }
}
