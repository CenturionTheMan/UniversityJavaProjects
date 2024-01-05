package lab6_code.zad1;

public class CircleMath {
    
    /**
     * 
     * @param x
     * @return sin value of given x
     */
    private static double SinEq(double x)
    {
        return Math.sin(x);
    }

    /**
     * 
     * @param x
     * @return cosine value of given x
     */
    private static double CosEq(double x)
    {
        return Math.cos(x);
    }

    /**
     * 
     * @param rad angle
     * @return point on unit circle determined by given angle, for example angle=0 will return (0,1)
     */
    public static Vector2 GetPositionOnUnitCircle(double angle)
    {
        double x = SinEq(angle);
        double y = CosEq(angle);
        Vector2 result = new Vector2(x, y);
        return result;
    }

}
