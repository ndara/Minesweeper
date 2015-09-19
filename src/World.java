import java.lang.reflect.Array;
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
    public World(int num_rows, int num_cols, Tile[][] occupancy)
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
        if (occupancy[pos.getX()][pos.getY()] != null)
        {
            return false;
        }
        else
        {
            return true;
        }
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
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
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
            Bomb newbomb = new Bomb(pt);
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
    public Tile assignNumber(int num, Point pos)
    {
       Tile tile;
       if(num == 1)
       {
           tile = new Tile1(pos);
       }
       else if(num == 2)
       {
           tile = new Tile2(pos);
       }
       else if(num == 3)
       {
           tile = new Tile3(pos);
       }
       else if(num == 4)
       {
           tile = new Tile4(pos);
       }
       else if(num == 5)
       {
           tile = new Tile5(pos);
       }
       else if(num == 6)
       {
           tile = new Tile6(pos);
       }
       else if(num == 7)
       {
           tile = new Tile7(pos);
       }
       else if(num == 8)
       {
           tile = new Tile8(pos);
       }
       else
       {
           tile = new OpenTile(pos);
       }
       return tile;
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
                    //System.out.println("x is " + current.getX());
                    //System.out.println("y is " + current.getY());
                    int adjacentBombs = adjacentBombs(current);
                    //System.out.println(adjacentBombs);
                    Tile newtile = assignNumber(adjacentBombs, current);
                    occupancy[x][y] = newtile;
                }
            }
        }
    }
    public void mouseclick(Tile t)
    {
        if(!(t instanceof OpenTile || t instanceof Bomb))
            //make bomb opening into another function or stuff it in main class
        {
            t.openTile();
        }
        //SKIP OPENED TILES
        else if (!(t.getOpen()))
        {
            t.openTile();
            ArrayList<Point> neighbors = findneighbors(t.getPos().getX(), t.getPos().getY());
            for (Point n: neighbors)
            {
                if(withinBounds(n))
                {
                    System.out.println("x is " + n.getX() + " y is " + n.getY());
                    mouseclick(this.occupancy[n.getX()][n.getY()]);
                }
            }
        }
    }
}
