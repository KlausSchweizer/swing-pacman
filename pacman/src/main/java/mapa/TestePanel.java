/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mapa.Mapa
 *  mapa.Tile
 *  mapa.TxtParser
 */
package mapa;

import itens.Item;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class TestePanel
        extends JPanel {

    private final Mapa mapa = new TxtParser().criarMapa("mapas/mapa.txt");

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
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        int tileSize = 32;
        Tile[][] tiles = this.mapa.getMapa();
        for (int linha = 0; linha < tiles.length; ++linha) {
            for (int coluna = 0; coluna < tiles[linha].length; ++coluna) {
                Tile tile = tiles[linha][coluna];
                if (tile != null && tile.getImage() != null) {
                    g2d.drawImage(tile.getImage(), coluna * tileSize, linha * tileSize, tileSize, tileSize, null);

                    Item item = tiles[linha][coluna].getItem();
                    if (item != null && item.getImages().getFirst() != null) {
                        g2d.drawImage(item.getImages().getFirst(), coluna * tileSize, linha * tileSize, tileSize, tileSize, null);
                    }
                    continue;
                }

                g2d.setColor(Color.BLACK);
                g2d.fillRect(coluna * tileSize, linha * tileSize, tileSize, tileSize);
            }
        }
    }
}
