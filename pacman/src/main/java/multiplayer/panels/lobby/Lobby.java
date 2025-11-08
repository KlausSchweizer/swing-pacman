package multiplayer.panels.lobby;

import main.Main;
import multiplayer.MultiplayerGame;
import multiplayer.fantasmas.MultiplayerFantasmaCiano;
import multiplayer.fantasmas.MultiplayerFantasmaLaranja;
import multiplayer.fantasmas.MultiplayerFantasmaRosa;
import multiplayer.fantasmas.MultiplayerFantasmaVermelho;
import personagem.Personagem;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lobby extends MultiplayerGame {
    private List<Personagem> proximosPersonagens;

    public Lobby() {
        super();
        configurarProximosPersonagens();
    }

    public Personagem escolherPersonagem() {
        Random rand = new Random();
        int indexPersonagem = rand.nextInt(proximosPersonagens.size());
        Personagem retorno = proximosPersonagens.get(indexPersonagem);
        proximosPersonagens.remove(indexPersonagem);
        return retorno;
    }

    private void configurarProximosPersonagens() {
        proximosPersonagens = new ArrayList<>();
        int spawnY = 10;
        int spawnX = 10;
        proximosPersonagens.add(new Pacman(spawnY, spawnX));
        proximosPersonagens.add(new MultiplayerFantasmaVermelho(spawnY, spawnX));
        proximosPersonagens.add(new MultiplayerFantasmaRosa(spawnY, spawnX));
        proximosPersonagens.add(new MultiplayerFantasmaCiano(spawnY, spawnX));
        proximosPersonagens.add(new MultiplayerFantasmaLaranja(spawnY, spawnX));
    }

    public void start() {
        this.selecionarFase("/mapas/lobby.txt");
        LobbyPanel panel = new LobbyPanel(this.mapa, this.fantasmas, this.pacman);
        Main.configurarPanel(panel);
    }

    @Override
    public void checarColisoes(Fantasma fantasma) {
        //sem colisões ou mortes no lobby
    }
}
