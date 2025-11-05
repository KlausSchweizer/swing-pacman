package multiplayer;

import main.Main;
import multiplayer.panels.lobby.LobbyPanel;
import singleplayer.Game;

public class MultiplayerGame extends Game {

    @Override
    public void finish() {
        isRunning = false;
        Main.configurarPanel(Main.getFase(), new LobbyPanel(mapa, fantasmas, pacman));
    }
}
