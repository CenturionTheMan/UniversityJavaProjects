package lab6_code.zad2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.Timer;

public class LeafView extends JPanel {

    private final boolean SHOW_FOOD_AMOUNT_NUMBER = true; // if true will show amount of food in each cell

    LeafThread leafThread;
    int guiUpdatePeriod;
    Timer timer;

    /**
     * CTOR
     * @param leafThread
     * @param guiUpdatePeriod time between gui updates ticks
     */
    public LeafView(LeafThread leafThread, int guiUpdatePeriod) {
        this.leafThread = leafThread;
        this.guiUpdatePeriod = guiUpdatePeriod;
        timer = null;
        this.setBackground(Color.darkGray);

        Update();
    }

    /**
     * Update function for constant repainting
     */
    private void Update()
    {
        timer = new Timer(guiUpdatePeriod, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revalidate();
                repaint();
            }
        });
        timer.start();
    }
    
    /**
     * DRAWING
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = Math.round(this.getWidth()*0.9f);
        int height = Math.round(this.getHeight()*0.9f);


        int rightLeftPadding = (getWidth()-width)/2;

        int xAmount = leafThread.GetMapSize().GetX();
        int yAmount = leafThread.GetMapSize().GetY();

        int padding = 3;
        int cellWidth = Math.round(width/xAmount);
        int cellHeight = Math.round(height/yAmount);

        for (var cell : leafThread.GetMapCells()) {
            int x = cell.GetMapPosition().GetX();
            int y = cell.GetMapPosition().GetY();

            g.setColor(cell.GetColorByFoodAmount());
            g.fillRect(rightLeftPadding + x * cellWidth, y *cellHeight,cellWidth -padding,cellHeight-padding);        

            if(SHOW_FOOD_AMOUNT_NUMBER)
            {
                DecimalFormat dm = new DecimalFormat("00.00");
                var temp = cell.GetFoodAmount();
                g.setColor(Color.black);
                g.drawString(dm.format(temp), rightLeftPadding + x * cellWidth, y *cellHeight + cellHeight/2);
            }

            if(cell.IsSnail())
            {
                g.setColor(Color.RED);
                int smaller =(cellWidth < cellHeight)? cellWidth:cellHeight;
                int snailWidth = Math.round( smaller * 0.5f);
                int snailHeigh = Math.round( smaller * 0.5f);

                g.fillOval(rightLeftPadding + x * cellWidth + cellWidth/2 - snailWidth/2, y * cellHeight + cellHeight/2 - snailHeigh/2,snailWidth,snailHeigh);
            }
        }
    }
}
