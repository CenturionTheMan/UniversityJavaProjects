package lab4_code.zad1;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;


public class Model1 {
    private ArrayList<Integer> numbers = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();

    /**
     * Add number to list
     * @param number num to add
     */
    public void AddNumber(int number, Color color)
    {
        numbers.add(number);
        colors.add(color);
    }

    /**
     * Replace number of given index
     * @param index index
     * @param number new number
     */
    public void EditNumberByIndex(int index, Integer number)
    {
        if(index >= numbers.size() || index < 0) return;

        numbers.remove(index);
        Color col = colors.remove(index);

        numbers.add(index, number);
        colors.add(index, col);
    }

    /**
     * Remove number at given index
     * @param index
     */
    public void RemoveNumberByIndex(int index)
    {
        if(index >= numbers.size() || index < 0) return;

        numbers.remove(index);
        colors.remove(index);
    }

    /**
     * 
     * @return All numbers in map as array
     */
    public Integer[] GetUsedNumbersAsArray()
    {
        return numbers.toArray(size -> new Integer[size]);
    }

    /**
     * 
     * @return All colors in map as array
     */
    public Color[] GetUsedColorsAsArray()
    {
        return colors.toArray(size -> new Color[size]);
    }

    /**
     *
     * @return Random color which was not already used
     */
    public Color GetColor()
    {
        final int maxNum = 255;

        Random rand = new Random();
        Color col = null;
        boolean rep = false;
        do {

            int r = rand.nextInt(maxNum);
            int g = rand.nextInt(maxNum);
            int b = rand.nextInt(maxNum);

            rep = ((maxNum*3 -r-g-b) < 10)? true:false;
                
            col = new Color(r,g,b);
        } while (colors.contains(col) || rep);

        return col;
    }
}
