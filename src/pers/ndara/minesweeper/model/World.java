package pers.ndara.minesweeper.model;

import pers.ndara.minesweeper.model.Bomb;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nishanth on 7/2/15.
 */
public class World
{
    private int num_rows;
    private int num_cols;
    private Tile[][] occupancy;
    public World(int num_rows, int num_cols)
    {
        this.occupancy = new Tile[num_rows][num_cols];
        this.num_cols = num_cols;
        this.num_rows = num_rows;
    }
    public int getNum_rows()
    {
        return this.num_rows;
    }

    public int getNum_cols()
    {
        return this.num_cols;
    }

    public Tile[][] getOccupancy()
    {
        return this.occupancy;
    }

    public void setNum_rows(int rows)
    {
        this.num_rows = rows;
    }

    public void setNum_cols(int cols)
    {
        this.num_cols = cols;
    }

    public boolean isEmpty(Point pos)
    {
        return (occupancy[pos.getX()][pos.getY()] == null);
    }

    public void addTile(Tile t)
    {
        Point p = t.getPos();
        if(isEmpty(p))
        {
            occupancy[p.getX()][p.getY()] = t;
        }

    }
    public static int randInt(int min, int max)
    {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public void randomizebombs(int num)
    {
        ArrayList<Bomb> bombs = new ArrayList<>();
        while (bombs.size() < num)
        {
            int x = randInt(0, num_rows-1);
            int y = randInt(0, num_cols-1);
            Point pt = new Point(x,y);
            Bomb newbomb = new Bomb(pt, "Images/Mine.jpg");
            //make a function to check if there is a duplicate bomb
            bombs.add(newbomb);
        }
        for (Bomb b: bombs)
        {
            addTile(b);
        }
    }
    public boolean withinBounds(Point pt)
    {
        return (pt.getX() < num_rows && pt.getX() >= 0
                && pt.getY() < num_cols && pt.getY() >= 0);
    }
    public ArrayList<Point> findneighbors(int posX, int posY)
    {
        ArrayList<Point> neighbors = new ArrayList<>();
        Point pt1 = new Point(posX + 1, posY);
        Point pt2 = new Point(posX - 1, posY);
        Point pt3 = new Point(posX, posY + 1);
        Point pt4 = new Point(posX, posY - 1);
        Point pt5 = new Point(posX + 1, posY + 1);
        Point pt6 = new Point(posX - 1, posY - 1);
        Point pt7 = new Point(posX + 1, posY - 1);
        Point pt8 = new Point(posX - 1, posY + 1);
        neighbors.add(pt1);
        neighbors.add(pt2);
        neighbors.add(pt3);
        neighbors.add(pt4);
        neighbors.add(pt5);
        neighbors.add(pt6);
        neighbors.add(pt7);
        neighbors.add(pt8);
        return neighbors;
    }
    public int adjacentBombs(Point position)
    {
        int posX = position.getX();
        int posY = position.getY();
        int total = 0;

        ArrayList<Point> neighbors = findneighbors(posX, posY);

        for(Point neighbor: neighbors)
        {
            if (withinBounds(neighbor))
            {
                int nx = neighbor.getX();
                int ny = neighbor.getY();
                if(occupancy[nx][ny] instanceof Bomb)
                {
                    total += 1;
                }
            }
        }
        return total;
    }
    private String assignImage(int num)
    {
        switch(num){
            case 0:
                return "Images/Tile0.png";
            case 2:
                return "Images/Tile2.jpg";
            case 3:
                return "Images/Tile3.png";
            case 4:
                return "Images/Tile4.jpg";
            case 5:
                return "Images/Tile5.png";
            case 6:
                return "Images/Tile6.png";
            case 7:
                return "Images/Tile7.png";
            case 8:
                return "Images/Tile8.png";
            default:
                return "Images/Tile1.png";
        }
    }
    public void setTiles()
    {
        for(int x = 0; x < occupancy.length; x++)
        {
            for(int y = 0; y < occupancy[x].length; y++)
            {
                if(occupancy[x][y] == null)
                {
                    Point current = new Point(x, y);
                    int adjacentBombs = adjacentBombs(current);
                    String image = assignImage(adjacentBombs);
                    Tile newtile = new Tile(current, image);
                    occupancy[x][y] = newtile;
                }
            }
        }
    }
    public void mouseclick(Tile t)
    {
        if(t instanceof Bomb)
        {
            t.openTile();
        }
        else if(!(t.getOpenImage().equals("Images/Tile0.png")))
        {
            t.openTile();
        }
        else
        {
            if (!(t.isOpen()))
            {
                t.openTile();
                ArrayList<Point> neighbors = findneighbors(t.getPos().getX(), t.getPos().getY());
                for (Point n : neighbors)
                {
                    if (withinBounds(n))
                    {
                        mouseclick(this.occupancy[n.getX()][n.getY()]);
                    }
                }
            }
        }
    }
    public void endGame(Bomb current)
    {

        for(int x = 0; x < occupancy.length; x++) {
            for (int y = 0; y < occupancy[x].length; y++)
            {
                if(occupancy[x][y] != current)
                {
                    occupancy[x][y].openTile();
                }
                else
                {
                    current.explode();
                }
            }
        }
    }
}

