package multiplayer.panels.lobby;

import personagem.fantasma.Fantasma;
import singleplayer.Game;

public class Lobby extends Game {

    public Lobby() {
        super();
    }

    public void start() {
        this.selecionarFase("/mapas/lobby.txt");
    }

    @Override
    public void checarColisoes(Fantasma fantasma) {}
}
