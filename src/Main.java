/**
 * Created by Nishanth on 6/30/15.
 */

import processing.core.*;

import java.awt.geom.Ellipse2D;

public class Main extends PApplet
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

        Tile[][] occ = new Tile[NUM_ROWS][NUM_COLS];
        world = new World(NUM_ROWS, NUM_COLS, occ);
        world.randomizebombs(NUM_BOMBS);
        world.setTiles();
    }

    public void draw()
    {
        Tile[][] occupancy = world.getOccupancy();
        for(int i = 0; i < occupancy.length; i++) {
            for (int j = 0; j < occupancy[i].length; j++) {
                Tile cur_tile = occupancy[i][j];
                if (cur_tile instanceof Bomb && cur_tile.getOpen()) {
                    //end the game
                }
            }
        }

        for(int x = 0; x < occupancy.length; x++) {
            for (int y = 0; y < occupancy[x].length; y++) {
                Tile cur_tile = occupancy[x][y];
                if (cur_tile instanceof Bomb) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Mine.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                        //also try just ending the game here
                    }
                } else if (cur_tile instanceof Tile1) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile1.png"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else if (cur_tile instanceof Tile2) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile2.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else if (cur_tile instanceof Tile3) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile3.png"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else if (cur_tile instanceof Tile4) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile4.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else if (cur_tile instanceof Tile5) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile5.png"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else if (cur_tile instanceof Tile6) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile6.png"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else if (cur_tile instanceof Tile7) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile7.png"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else if (cur_tile instanceof Tile8) {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile8.png"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                } else {
                    if (!cur_tile.getOpen()) {
                        image(loadImage("Images/Tile.jpg"), x * TILE_LENGTH, y * TILE_LENGTH);
                    } else {
                        image(loadImage("Images/Tile0.png"), x * TILE_LENGTH, y * TILE_LENGTH);
                    }
                }
            }
        }
    }
    public void mousePressed()
    {
        int roundX = mouseX/TILE_LENGTH;
        int roundY = mouseY/TILE_LENGTH;
        Tile[][] occupancy = world.getOccupancy();
        Tile current = occupancy[roundX][roundY];
        //current.openTile();

        /*
        if (current instanceof Bomb)
        {

        }
        */

        world.mouseclick(current);
    }
    public static void main(String[] args)
    {
        PApplet.main("Main");
    }
}
