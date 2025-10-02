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
