package multiplayer.panels.lobby;

import fase.FasePanel;
import mapa.Mapa;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;

import java.util.List;

public class LobbyPanel extends FasePanel {
    public LobbyPanel(Mapa mapa, List<Fantasma> fantasmas, Pacman pacman) {
        super(mapa, fantasmas, pacman);
    }
}
