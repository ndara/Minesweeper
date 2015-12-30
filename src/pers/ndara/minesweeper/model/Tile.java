package pers.ndara.minesweeper.model;

import pers.ndara.minesweeper.model.Point;

/**
 * Created by Nishanth on 6/30/15.
 */
public class Tile
{
    private Point pos;
    private boolean open;

    protected String closeImage = "Images/Tile.jpg";
    protected String openImage;
    public Tile(Point pos, String openImage)
    {
        this.pos = pos;
        this.open = false;
        this.openImage = openImage;
    }


    public Point getPos()
    {
        return this.pos;
    }

    public boolean isOpen()
    {
        return this.open;
    }

    public void openTile()
    {
        this.open = true;
    }

    public String getImage()
    {
        return isOpen() ? openImage : closeImage;
    }

    public String getOpenImage()
    {
        return openImage;
    }
}
