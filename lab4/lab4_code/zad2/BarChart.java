package lab4_code.zad2;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public class BarChart extends JPanel {

    private final int constPadding = 4;
    private final Color baseColor = new Color(238,238,238);
    private int currentBarsAmount = 0;
    private Bar[] bars;

    private int width;
    private int height;

    public BarChart(int maxBarsAmount, int width, int height) {
        bars = new Bar[maxBarsAmount];
        this.height = height;
        this.width = width;

        this.setPreferredSize(new Dimension(width,height));
        this.setSize(new Dimension(width, height));
    }

    /**
     * Ads bar to chart
     * @param index
     * @param val
     * @param color
     * @return
     */
    public boolean AddBar(int index,Integer val, Color color)
    {
        if(index >= bars.length) return false;
        bars[index] = new Bar(val,color);
        currentBarsAmount++;
        this.repaint();
        this.revalidate();
        return true;
    }

    /**
     * Removes bar from chart
     * @param index
     * @return
     */
    public boolean RemoveBar(int index)
    {
        if(index >= bars.length ||  bars[index] == null) return false;
        bars[index] = null;
        currentBarsAmount--;
        this.repaint();
        this.revalidate();
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0,0,width,height);

        g.setColor(baseColor);
        g.fillRect(0, 0, width, height);
        float maxBarVal = Float.MIN_VALUE;

        for (int i = 0; i < bars.length; i++) {
            if(bars[i] == null) continue;
            maxBarVal = Math.max(maxBarVal, bars[i].val);
        }

        int barWidth =(currentBarsAmount == 0)? 0 : width/currentBarsAmount - constPadding;
        int xPos = constPadding/2;

        final int maxFontSize = 30;
        int strWidth = 0;

        for (Bar bar : bars) {
            if(bar == null) continue;

            int barHeight = (int)((height - constPadding) * (bar.val/maxBarVal));

            g.setColor(bar.color);
            g.fillRect(xPos, height - barHeight, barWidth, barHeight);

            
            for (int i = maxFontSize; i > 0; i--) {
                Font font = new Font("",Font.PLAIN,i);
                strWidth = g.getFontMetrics(font).stringWidth(bar.val.toString());
                if(strWidth < barWidth - constPadding*2){
                    g.setFont(font);
                    break;
                }
            }

            g.setColor(Color.black);
            g.drawString(bar.val.toString(), (int)(xPos + barWidth/2 - strWidth/2), height - (barHeight * 1/2));

            xPos += (barWidth + constPadding);
        }
    }


    private class Bar {
        public Color color;
        public Integer val;

        public Bar(Integer val, Color color) {
            this.val = val;
            this.color = color;
        }
    }
}
