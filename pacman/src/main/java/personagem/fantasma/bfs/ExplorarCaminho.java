package personagem.fantasma.bfs;

import main.Direcao;

import java.util.*;

import mapa.Mapa;
import mapa.matrizmapa.MatrizMapa;

/**
 *
 * @author klaus
 */
public class ExplorarCaminho {

    public static final int CIMA = -1;
    public static final int BAIXO = 1;
    public static final int DIREITA = 1;
    public static final int ESQUERDA = -1;

    private char[][] textoMapa;
    private Queue<CelulaBFS> proximasPosicoes;
    boolean[][] posVisitadas;
    private LinkedList<CelulaBFS> caminhoOrdenado;


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
    public Direcao decidirDirecao(int posYInicial, int posXInicial,
            int posYFinal, int posXFinal, Mapa mapa) {
        ConversorMapaFantasmas conversorMapa = new ConversorMapaFantasmas();
        this.textoMapa = conversorMapa.converterMapaFantasma(mapa.getTextoMapa());

        CelulaBFS celulaInicio = new CelulaBFS(posYInicial, posXInicial, null);
        CelulaBFS destino = new CelulaBFS(posYFinal, posXFinal, null);

        proximasPosicoes = new LinkedList<>();
        posVisitadas = new boolean[textoMapa.length][textoMapa[0].length];

        if (!isCelulaValida(celulaInicio) || !isCelulaValida(destino)) {
            return null;
        }

        proximasPosicoes.add(celulaInicio);
        posVisitadas[posYInicial][posXInicial] = true;

        while (!proximasPosicoes.isEmpty()) {
            CelulaBFS celula = proximasPosicoes.remove();

            if (celula.getPosX() == posXFinal && celula.getPosY() == posYFinal) {
                destino = celula;
                break;
            }
            explorarVizinhos(celula);
        }
        caminhoOrdenado = ordenarCaminho(destino, celulaInicio);

        if(caminhoOrdenado.size() > 1) {
            CelulaBFS proximaCelula = caminhoOrdenado.get(1);

            ReconstruirCaminho reconstrutor = new ReconstruirCaminho();
            return reconstrutor.reconstruirDirecaoCaminho(celulaInicio, proximaCelula);
        }
        return null;
    }

    public boolean isCelulaValida(CelulaBFS celula) {
        int posX = celula.getPosX();
        int posY = celula.getPosY();

        if (posY < 0 || posX < 0 || posY >= textoMapa.length || posX >= textoMapa[0].length) {
            return false;
        }

        if(posVisitadas != null) {
            if (posVisitadas[posY][posX]) {
                return false;
            }
        }

        return !(textoMapa[posY][posX] == MatrizMapa.SAIDA_FANTASMA
                || textoMapa[posY][posX] == MatrizMapa.PAREDE);
    }

    private List<CelulaBFS> definirCelulasVizinhas(CelulaBFS celula) {
        CelulaBFS celulaAbaixo = new CelulaBFS(celula.getPosY() + BAIXO, celula.getPosX(), celula);
        CelulaBFS celulaAcima = new CelulaBFS(celula.getPosY() + CIMA, celula.getPosX(), celula);
        CelulaBFS celulaDireita = new CelulaBFS(celula.getPosY(), celula.getPosX() + DIREITA, celula);
        CelulaBFS celulaEsquerda = new CelulaBFS(celula.getPosY(), celula.getPosX() + ESQUERDA, celula);

        List<CelulaBFS> celulasVizinhas = new ArrayList<>();
        celulasVizinhas.add(celulaAbaixo);
        celulasVizinhas.add(celulaAcima);
        celulasVizinhas.add(celulaDireita);
        celulasVizinhas.add(celulaEsquerda);

        return celulasVizinhas;
    }

    private void explorarVizinhos(CelulaBFS celula) {
        for (CelulaBFS vizinho : definirCelulasVizinhas(celula)) {
            int y = vizinho.getPosY();
            int x = vizinho.getPosX();

            if (y < 0 || y >= textoMapa.length || x < 0 || x >= textoMapa[0].length) {
                continue;
            }

            if (!posVisitadas[y][x] && textoMapa[y][x] != MatrizMapa.PAREDE && textoMapa[y][x] != MatrizMapa.SAIDA_FANTASMA) {
                posVisitadas[y][x] = true;
                proximasPosicoes.add(new CelulaBFS(y, x, celula));
            }
        }
    }

    private LinkedList<CelulaBFS> ordenarCaminho(CelulaBFS destino, CelulaBFS inicio) {
        LinkedList<CelulaBFS> caminho = new LinkedList<>();
        caminho.add(destino);

        CelulaBFS celula = destino;
        while (celula.getCelulaPai() != null
                && !celula.equals(inicio)) {
            celula = celula.getCelulaPai();
            caminho.add(celula);
        }

        Collections.reverse(caminho);
        return caminho;
    }

    public char[][] getTextoMapa() {
        return textoMapa;
    }

    public void setTextoMapa(char[][] textoMapa) {
        this.textoMapa = textoMapa;
    }
}
