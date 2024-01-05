package lab6_code.zad1;

public class Vector2 {


    private Double x,y;

    /**
     * CTOR
     * @param x
     * @param y
     */
    public Vector2(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * CTOR
     * @param x
     * @param y
     */
    public Vector2(int x, int y) {
        this.x = Double.valueOf(x);
        this.y = Double.valueOf(y);
    }

    /**
     * @return x value
     */
    public Double getX() { return x; }
    /**
     * @return y value
     */
    public Double getY() { return y; }

    /**
     * @return x value rounded to int
     */
    public Integer getIntX() { return (int)Math.round(x); }

    /**
     * @return y value rounded to int
     */
    public Integer getIntY() { return (int)Math.round(y); }

    /**
     * Will add x to x value of vector and y to y val of vector
     * @param x
     * @param y
     * @return new vector
     */
    public Vector2 AddVector(int x, int y)
    {
        return AddVector(new Vector2(x, y));
    }

    /**
     * 
     * @param vec param vector
     * @return new vector which x and y equals to sum of this vector and param vector
     */
    public Vector2 AddVector(Vector2 vec)
    {
        x += vec.getX();
        y += vec.getY();
        return this;
    }

    /**
     * 
     * @param multiply
     * @return vector multiplied by value
     */
    public Vector2 MultiplyByConst(int multiply)
    {
        x *= multiply;
        y *= multiply;
        return this;
    }

    /**
     * 
     * @param vec param vector
     * @return new vector which x and y equals to subtraction of this vector and param vector
     */
    public Vector2 SubtractVector(Vector2 vec)
    {
        x -= vec.getX();
        y -= vec.getY();
        return this;
    }

    /**
     * 
     * @return length of vector
     */
    public double GetLength()
    {
        return GetDistanceToPositionSqrt(new Vector2(0d, 0d));
    }

    /**
     * 
     * @param vec
     * @return distance between this vector and vector given as param
     */
    public double GetDistanceToPositionSqrt(Vector2 vec)
    {
        return Math.sqrt( GetDistanceToPosition(vec) );
    }

    /**
     * 
     * @param vec
     * @return not squared distance between this vector and vector given as param
     */
    public double GetDistanceToPosition(Vector2 vec)
    {
        return Math.pow( x - vec.x , 2) + Math.pow( y - vec.y , 2);
    }

    /**
     * 
     * @return Vector which x and y are equal to 0
     */
    public static Vector2 GetZero()
    {
        return new Vector2(0, 0);
    }

    @Override
    public String toString() {
        return "[ " + x + " | " + y + " ]";
    }

}
