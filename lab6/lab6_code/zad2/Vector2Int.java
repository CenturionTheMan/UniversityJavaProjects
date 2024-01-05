package lab6_code.zad2;


public class Vector2Int {
    private int x,y;

    /**
     * CTOR
     * @param x
     * @param y
     */
    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return x value
     */
    public int GetX()
    {
        return x;
    }

    /**
     * 
     * @return y value
     */
    public int GetY()
    {
        return y;
    }

    /**
     * 
     * @param vec
     * @return non squared distance between this and given vector
     */
    public int GetNonSquaredDistanceToPoint(Vector2Int vec)
    {
        return (x - vec.x)*(x - vec.x) + ( y - vec.y)*( y - vec.y);
    }

    @Override
    public String toString() {
        return "[" + x + " , " + y + "]";
    }
}
