package lab6_code.zad1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;;

public class RandomColor {
    static ArrayList<Color> colors = new ArrayList<>();

    private static Random random = new Random();

    /**
     * 
     * @return random color which was not already generated 
     */
    public static Color GetRandomNewColor()
    {
        int min = 50;
        int max = 200;
        Color temp = null;
        do {
            temp = new Color(random.nextInt(min,max),random.nextInt(min,max),random.nextInt(min,max));
        } while (colors.contains(temp));
        colors.add(temp);
        return temp;
    }
}
