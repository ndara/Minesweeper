package pers.ndara.minesweeper.model;

/**
 * Created by Nishanth on 6/30/15.
 */
public class Bomb extends Tile
{
    private String exploded = "Images/MineExploded.png";
    private boolean isExploded;

    public Bomb(Point pos, String openImage)
    {
        super(pos, openImage);
        boolean isExploded = false;
    }

    public void explode()
    {
        isExploded = true;
    }

    public String getImage()
    {
        if(isExploded)
            return exploded;
        else if(isOpen())
            return openImage;
        else
            return closeImage;
    }
}
