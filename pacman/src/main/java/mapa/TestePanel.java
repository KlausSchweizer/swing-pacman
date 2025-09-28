/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mapa.Mapa
 *  mapa.Tile
 *  mapa.TxtParser
 */
package mapa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import mapa.Mapa;
import mapa.Tile;
import mapa.TxtParser;

public class TestePanel
extends JPanel {
    private Mapa mapa = new TxtParser().criarMapa("mapas/mapa.txt");

    public TestePanel() {
        this.initComponents();
        this.setPreferredSize(new Dimension(400, 300));
    }

    private void initComponents() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = 32;
        Tile[][] tiles = this.mapa.getMapa();
        for (int linha = 0; linha < tiles.length; ++linha) {
            for (int coluna = 0; coluna < tiles[linha].length; ++coluna) {
                Tile tile = tiles[linha][coluna];
                if (tile != null && tile.getImage() != null) {
                    g.drawImage(tile.getImage(), coluna * tileSize, linha * tileSize, tileSize, tileSize, null);
                    continue;
                }
                g.setColor(Color.BLACK);
                g.fillRect(coluna * tileSize, linha * tileSize, tileSize, tileSize);
            }
        }
    }
}
