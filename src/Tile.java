import processing.core.PImage;
import java.util.List;

/**
 * Created by Nishanth on 6/30/15.
 */
public class Tile
{
    private Point pos;
    private boolean open;

    public Tile(Point pos, boolean open)
    {
        this.pos = pos;
        this.open = false;
    }
    public Point getPos()
    {
        return this.pos;
    }
    public boolean getOpen()
    {
        return this.open;
    }
    public void openTile()
    {
        this.open = true;
    }
}
