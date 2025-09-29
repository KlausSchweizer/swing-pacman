package mapa.criarmapa.direcoes;

import enums.Direcao;
import java.util.ArrayList;
import java.util.List;

public class VerificarDirecoes {

    private TemParede temParede;
    public VerificarDirecoes(){
    temParede = new TemParede();
    }
    public List<Direcao> executar(char[][] textoMapa, int i, int j) {
        List<Direcao> direcoesParede = new ArrayList<>();

        if (temParede.hasParedeEsquerda(textoMapa, i, j)) {
            direcoesParede.add(Direcao.ESQUERDA);
        }
        if (temParede.hasParedeCima(textoMapa, i, j)) {
            direcoesParede.add(Direcao.CIMA);
        }
        if (temParede.hasParedeBaixo(textoMapa, i, j)) {
            direcoesParede.add(Direcao.BAIXO);
        }
        if (temParede.hasParedeDireita(textoMapa, i, j)) {
            direcoesParede.add(Direcao.DIREITA);
        }

        return direcoesParede;
    }
}