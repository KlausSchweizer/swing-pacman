package multiplayer.panels.lobby;

import fase.FasePanel;
import mapa.Mapa;
import personagem.Personagem;
import personagem.fantasma.Fantasma;
import personagem.pacman.Pacman;

import java.awt.*;
import java.util.List;

public class LobbyPanel extends FasePanel {
    public LobbyPanel(Mapa mapa, List<Fantasma> fantasmas, Pacman pacman) {
        super(mapa, fantasmas, pacman);
    }

    public LobbyPanel(Mapa mapa, List<Fantasma> fantasmas, Pacman pacman, Personagem personagem) {
        super(mapa, fantasmas, pacman, personagem);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
