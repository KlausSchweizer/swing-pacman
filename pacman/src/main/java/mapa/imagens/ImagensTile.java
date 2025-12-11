package mapa.imagens;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagensTile {

    public static BufferedImage ladosAbertos;
    public static BufferedImage fechado;
    public static BufferedImage aberto;
    public static BufferedImage esquerdaAberta;
    public static BufferedImage direitaAberta;
    public static BufferedImage alturasAbertas;
    public static BufferedImage direitaFechada;
    public static BufferedImage esquerdaFechada;
    public static BufferedImage cimaFechada;
    public static BufferedImage baixoFechada;
    public static BufferedImage direitaCimaAbertas;
    public static BufferedImage esquerdaCimaAbertas;
    public static BufferedImage esquerdaBaixoAbertas;
    public static BufferedImage direitaBaixoAbertas;
    public static BufferedImage saidaFantasmas;
    public static BufferedImage baixoAberta;
    public static BufferedImage cimaAberta;

    public ImagensTile() {
        try {
            lerImagens();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerImagens() throws IOException {
        aberto = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/aberto.png"));

        fechado = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/fechado.png"));

        alturasAbertas = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/alturas-abertas.png"));

        baixoFechada = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/baixo-fechada.png"));

        cimaFechada = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/cima-fechada.png"));

        direitaAberta = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/direita-aberta.png"));

        direitaBaixoAbertas = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/direita-baixo-abertas.png"));

        direitaCimaAbertas = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/direita-cima-abertas.png"));

        direitaFechada = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/direita-fechada.png"));

        esquerdaAberta = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/esquerda-aberta.png"));

        esquerdaBaixoAbertas = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/esquerda-baixo-abertas.png"));

        esquerdaCimaAbertas = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/esquerda-cima-abertas.png"));

        esquerdaFechada = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/esquerda-fechada.png"));

        ladosAbertos = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/lados-abertos.png"));

        saidaFantasmas = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/saida-fantasma.png"));

        cimaAberta = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/cima-aberta.png"));

        baixoAberta = ImageIO.read(getClass().getResourceAsStream("/imagens/tiles/baixo-aberta.png"));
    }
}
