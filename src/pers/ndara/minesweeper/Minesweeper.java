/**
 * Created by Nishanth on 6/30/15.
 */
package pers.ndara.minesweeper;

import pers.ndara.minesweeper.model.Bomb;
import pers.ndara.minesweeper.model.Tile;
import pers.ndara.minesweeper.model.World;
import processing.core.*;

public class Minesweeper extends PApplet
{
    private static final int TILE_LENGTH = 16;
    private static final int NUM_ROWS = 30;
    private static final int NUM_COLS = 16;
    private static final int NUM_BOMBS = 50;
    private static final int SCREEN_WIDTH = NUM_ROWS * TILE_LENGTH;
    private static final int SCREEN_HEIGHT = NUM_COLS * TILE_LENGTH;

    private World world;

    public void setup()
    {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);

        world = new World(NUM_ROWS, NUM_COLS);
        world.randomizebombs(NUM_BOMBS);
        world.setTiles();
    }

    public void draw()
    {
        Tile[][] occupancy = world.getOccupancy();
        for (int i = 0; i < occupancy.length; i++)
        {
            for (int j = 0; j < occupancy[i].length; j++)
            {
                Tile cur_tile = occupancy[i][j];
                String cur_image = cur_tile.getImage();
                int x = cur_tile.getPos().getX();
                int y = cur_tile.getPos().getY();
                image(loadImage(cur_image), x * TILE_LENGTH, y * TILE_LENGTH);
            }
        }
    }
    public void mousePressed()
    {
        int roundX = mouseX/TILE_LENGTH;
        int roundY = mouseY/TILE_LENGTH;
        Tile[][] occupancy = world.getOccupancy();
        Tile current = occupancy[roundX][roundY];

        world.mouseclick(current);
        if(current instanceof Bomb)
        {
            Bomb cur = (Bomb) current;
            world.endGame(cur);
        }
    }
    public static void main(String[] args)
    {
        PApplet.main("pers.ndara.minesweeper.Minesweeper");
    }
}
