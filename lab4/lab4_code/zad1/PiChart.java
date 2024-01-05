package lab4_code.zad1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;


import javax.swing.JPanel;

public class PiChart extends JPanel {

    private final float fullCircle = 360;

    private Integer sum = 0;

    private int width, height;

    private ArrayList<NumXCol> numbers = new ArrayList<NumXCol>();

    public PiChart(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width,height));
    }

    /**
     * Adds number to chart with given color
     * @param number
     * @param color
     */
    public void AddNumber(Integer number, Color color)
    {
        numbers.add(new NumXCol(number,color));
        sum += number;

        this.revalidate();
        this.repaint();
    }

    /**
     * Edits number on given index
     * @param index
     * @param number
     * @return
     */
    public boolean EditNumberByIndex(int index, Integer number)
    {
        if(index >= numbers.size() || index < 0) return false;

        var removed = numbers.remove(index);
        sum -= removed.number;

        if(numbers.size() == 0) sum =0;

        numbers.add(index, new NumXCol(number,removed.color));
        sum += number;

        this.revalidate();
        this.repaint();

        return true;
    }

    /**
     * Removes number on given index
     * @param index
     * @return
     */
    public boolean RemoveNumberByIndex(int index)
    {
        if(index >= numbers.size() || index < 0) return false;

        var item = numbers.remove(index);
        sum -= item.number;

        if(numbers.size() == 0) sum =0;

        this.revalidate();
        this.repaint();

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        g.clearRect(0, 0, width, height);

        g.setColor(new Color(238,238,238));
        g.fillRect(0, 0, width, height);

        int beginAngle = 0;

        for (NumXCol numXCol : numbers) {
            float percent = numXCol.number/(float)sum;
            int add = (int)(fullCircle*percent);
            beginAngle += add;
        }

        int diff = (int)(fullCircle) - Math.round(beginAngle);

        beginAngle = 0;

        for (NumXCol numXCol : numbers) {
            float percent = numXCol.number/(float)sum;

            int add =(int)(fullCircle*percent);
            if(diff > 0)
            {
                add +=1;
                diff-=1;
            }

            Color toPaintCol = numXCol.color;

            g.setColor(toPaintCol);
            g.fillArc(0,0,width,height,beginAngle,add);

            beginAngle += add;
        }
    }


    private class NumXCol {
        public Integer number;
        public Color color;

        public NumXCol(Integer number, Color color)
        {
            this.color = color;
            this.number = number;
        }
    }
}
