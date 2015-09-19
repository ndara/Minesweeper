import processing.core.PApplet;
import processing.core.PImage;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nishanth on 7/2/15.
 */
public class Imagestore extends PApplet
{
    private List<PImage> images = new LinkedList<>();
    public Imagestore(List<PImage> images)
    {
        this.images = images;
        images.add(loadImage("Images/Tile0.jpg"));
        images.add(loadImage("Images/Tile1.jpg"));
        images.add(loadImage("Images/Tile2.jpg"));
        images.add(loadImage("Images/Tile3.jpg"));
        images.add(loadImage("Images/Tile4.jpg"));
        images.add(loadImage("Images/Tile5.jpg"));
        images.add(loadImage("Images/Tile6.jpg"));
        images.add(loadImage("Images/Tile7.jpg"));
        images.add(loadImage("Images/Tile8.jpg"));
        images.add(loadImage("Images/Tile9.jpg"));
        images.add(loadImage("Images/Tile.jpg"));
        images.add(loadImage("Images/Mine.jpg"));
    }
    private List<PImage> getImages()
    {
        return this.images;
    }
    private PImage getOpenTile()
    {
        return images.get(0);
    }
    private PImage getTile1()
    {
        return images.get(1);
    }
    private PImage getTile2()
    {
        return images.get(2);
    }
    private PImage getTile3()
    {
        return images.get(3);
    }
    private PImage getTile4()
    {
        return images.get(4);
    }
    private PImage getTile5()
    {
        return images.get(5);
    }
    private PImage getTile6()
    {
        return images.get(6);
    }
    private PImage getTile7()
    {
        return images.get(7);
    }
    private PImage getTile8()
    {
        return images.get(8);
    }
    private PImage getTile()
    {
        return images.get(9);
    }
    private PImage getBomb()
    {
        return images.get(10);
    }

}
