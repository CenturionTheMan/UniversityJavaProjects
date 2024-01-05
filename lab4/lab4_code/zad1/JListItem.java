package lab4_code.zad1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JListItem extends JPanel{

    public Integer ItemNumber;
    public Color ItemColor;

    private int width, height;

    private final Color BG_COL = Color.white;
    private boolean isSelected = false;
    
    /**
     * CTOR
     * @param number
     * @param color
     */
    public JListItem(Integer number, Color color) {
        ItemNumber = number;
        ItemColor = color;
    }

    /**
     * Sets size for GUI object
     * @param width
     * @param height
     */
    public void SetSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));
    }

    /**
     * Changes selected status
     * @param isSelected
     */
    public void ChangeIsSelected(boolean isSelected)
    {
        this.isSelected = isSelected;
    }

    @Override
    public void paint(Graphics g) {

        final int PAD_10 = 10;
        final int PAD_3 = 3;

        if(isSelected)
        {
            g.setColor(ItemColor);
            g.fillRect(PAD_3,PAD_3,width - PAD_3*2,height - PAD_3*2);
        }
        else
        {
            g.setColor(ItemColor);
            g.fillRect(PAD_3,PAD_3,width - PAD_3*2,height - PAD_3*2);
            g.setColor(BG_COL);
            g.fillRect(PAD_10*2, PAD_3, width - PAD_10*3, height- PAD_3);
        }

        int stringWidth = this.getFontMetrics(this.getFont()).stringWidth(ItemNumber.toString());
        int componentWidth = width - PAD_10*3;
        double widthRatio = (double)componentWidth / (double)stringWidth;
        int newFontSize = (int)(this.getFont().getSize() * widthRatio);
        int fontSizeToUse = Math.min(newFontSize - PAD_3, height - PAD_3*2);

        g.setFont(new Font(this.getFont().getName(), Font.PLAIN, fontSizeToUse));
        g.setColor(Color.black);
        g.drawString(ItemNumber.toString(),width/4,height * 5/6 - (height - 6 - fontSizeToUse)*3/4);
    }
}
