/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasma.bfs;

import enums.Direcao;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        char[][] textoMapa = converterMapaFantasma(mapa.getTextoMapa());

        CelulaBFS celulaInicio = new CelulaBFS(posXInicial, posYInicial, null);
        CelulaBFS destino = new CelulaBFS(posXFinal, posYFinal, null);

        boolean[][] posVisitadas = new boolean[textoMapa.length][textoMapa[0].length];
        Queue<CelulaBFS> proximasPosicoes = new LinkedList<>();

        if (!isCelulaValida(textoMapa, posVisitadas, celulaInicio)
                || !isCelulaValida(textoMapa, posVisitadas, destino)) {
            return null;
        }

        proximasPosicoes.add(celulaInicio);
        posVisitadas[posXInicial][posYInicial] = true;

        while (!proximasPosicoes.isEmpty()) {
            CelulaBFS celula = proximasPosicoes.remove();

            if (celula.getPosX() == posXFinal && celula.getPosY() == posYFinal) {
                destino.setCelulaPai(celula);
                break;
            }
            explorarVizinhos(celula, posVisitadas, textoMapa, proximasPosicoes);
        }
        LinkedList<CelulaBFS> caminhoOrdenado = ordenarCaminho(destino, celulaInicio);
        CelulaBFS proximaCelula = caminhoOrdenado.getLast();

        return reconstruirDirecaoCaminho(celulaInicio, proximaCelula);
    }

    private Direcao reconstruirDirecaoCaminho(CelulaBFS celulaInicio, CelulaBFS proximaCelula) {
        int diferencaHorizontal = celulaInicio.getPosX() - proximaCelula.getPosX();
        int diferencaVertical = celulaInicio.getPosY() - proximaCelula.getPosY();

        if (diferencaVertical != 0) {
            if (diferencaVertical == CIMA) {
                return Direcao.CIMA;
            } else if (diferencaVertical == BAIXO) {
                return Direcao.BAIXO;
            }
        } else if (diferencaHorizontal != 0) {
            if (diferencaHorizontal == ESQUERDA) {
                return Direcao.ESQUERDA;
            } else if (diferencaHorizontal == DIREITA) {
                return Direcao.DIREITA;
            }
        }
        return null;
    }

    private LinkedList<CelulaBFS> ordenarCaminho(CelulaBFS destino, CelulaBFS inicio) {
        LinkedList<CelulaBFS> caminho = new LinkedList<>();
        caminho.add(destino);

        CelulaBFS celula = null;
        while (!celula.getCelulaPai().equals(inicio)) {
            celula = caminho.getLast().getCelulaPai();
            caminho.add(celula);
        }

        return caminho;
    }

    private void explorarVizinhos(CelulaBFS celula, boolean[][] posVisitadas,
            char[][] textoMapa, Queue<CelulaBFS> proximasPosicoes) {
        List<CelulaBFS> celulasVizinhas = definirCelulasVizinhas(celula);

        for (CelulaBFS c : celulasVizinhas) {
            if (isCelulaValida(textoMapa, posVisitadas, c)) {
                proximasPosicoes.add(c);
                posVisitadas[c.getPosX()][c.getPosY()] = true;
            }
        }
    }

    private List<CelulaBFS> definirCelulasVizinhas(CelulaBFS celula) {
        CelulaBFS celulaAbaixo = new CelulaBFS(celula.getPosX(), celula.getPosY() + BAIXO, celula);
        CelulaBFS celulaAcima = new CelulaBFS(celula.getPosX(), celula.getPosY() + CIMA, celula);
        CelulaBFS celulaDireita = new CelulaBFS(celula.getPosX() + DIREITA, celula.getPosY(), celula);
        CelulaBFS celulaEsquerda = new CelulaBFS(celula.getPosX() + ESQUERDA, celula.getPosY(), celula);

        List<CelulaBFS> celulasVizinhas = new ArrayList<>();
        celulasVizinhas.add(celulaAbaixo);
        celulasVizinhas.add(celulaAcima);
        celulasVizinhas.add(celulaDireita);
        celulasVizinhas.add(celulaEsquerda);

        return celulasVizinhas;
    }

    private boolean isCelulaValida(char[][] textoMapa, boolean[][] posVisitadas, CelulaBFS celula) {
        int posX = celula.getPosX();
        int posY = celula.getPosY();

        if (posX < 0 || posY < 0 || posX >= textoMapa.length || posY >= textoMapa[0].length) {
            return false;
        }

        if (posVisitadas[posX][posY]) {
            return false;
        }

        return !(textoMapa[posX][posY] == MatrizMapa.SAIDA_FANTASMA
                || textoMapa[posX][posY] == MatrizMapa.PAREDE);
    }

    //Faz os fantasmas ignorarem os '#' que só eles podem passar
    public char[][] converterMapaFantasma(char[][] textoMapa) {
        for (int i = 0; i < textoMapa.length; i++) {
            for (int j = 0; j < textoMapa[0].length; j++) {
                if (textoMapa[i][j] == MatrizMapa.SAIDA_FANTASMA) {

                    boolean hasParedeBaixo = textoMapa[i + 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeCima = textoMapa[i - 1][j] != MatrizMapa.LIVRE;
                    boolean hasParedeDireita = textoMapa[i][j + 1] != MatrizMapa.LIVRE;
                    boolean hasParedeEsquerda = textoMapa[i][j - 1] != MatrizMapa.LIVRE;
                    if (hasParedeBaixo && hasParedeDireita && hasParedeEsquerda) {
                        if (!hasParedeCima || !(hasParedeDireita || hasParedeEsquerda)) {
                            textoMapa[i][j] = MatrizMapa.LIVRE;
                        }
                    }
                }
            }
        }
        return textoMapa;
    }
}
