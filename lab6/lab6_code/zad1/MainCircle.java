package lab6_code.zad1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainCircle extends JPanel{

    private final float paddingPercent = 0.10f;
    private int paddingW, paddingH;
    private int width, height; 

    private int movingCirclesAmount, movingCircleRadius;
    private Color color;
    private double maxSpeed = 0.01f;
    private double minSpeed = 0f;
    private MovingCircle[] movingCircles = null;

    private int updatePeriod;
    private boolean isDataOk = true;
    private boolean isDrawn = false;
    private Timer timer = null;

    /**
     * CTOR
     * @param color color of gid circle
     * @param movingCirclesAmount
     * @param movingCircleRadius
     * @param updatePeriod frame rate
     */
    public MainCircle(Color color, int movingCirclesAmount, int movingCircleRadius, int updatePeriod) {
        this.color = color;
        this.movingCirclesAmount = movingCirclesAmount;
        this.movingCircleRadius = movingCircleRadius;
        this.updatePeriod = updatePeriod;
    }

    /**
     * 
     * @return movingCirclesAmount
     */
    public int GetMovingCirclesAmount()
    {
        return movingCirclesAmount;
    }

    /**
     * 
     * @return max speed of moving circles
     */
    public double GetMaxSpeed()
    {
        return maxSpeed;
    }

    /**
     * 
     * @return min speed of moving circles
     */
    public double GetMinSpeed()
    {
        return minSpeed;
    }

    /**
     * Wrapper used for stopping simulation
     */
    public void StopSimulation()
    {
        StopThreads();
        if(timer != null && timer.isRunning())
        {
            timer.stop();
            timer = null;
        }

        repaint();
    }

    /**
     * Wrappers for running simulation
     * @return true is success, false otherwise
     */
    public boolean RunSimulation()
    {
        if(!isDataOk)
        {
            return false;
        }
        if((timer != null && timer.isRunning()))
        {
            return false;
        }
        RunThreads();
        Update();
        return true;
    }

    /**
     * Update function for constant repainting
     */
    private void Update()
    {
        timer = new Timer(updatePeriod, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revalidate();
                repaint();
            }
        });
        timer.start();
    }

    /**
     * Will change speed of given moving circle
     * @param index 
     * @param speed
     * @return true if success, false otherwise
     */
    public boolean ChangeMovingCircleSpeed(int index, double speed)
    {
        if(index < 0 || index >= movingCirclesAmount)
        {
            return false;
        }
        if(speed < 0 || speed > maxSpeed)
        {
            return false;
        }
        movingCircles[index].SetSpeed(speed);
        return true;
    }

    /**
     * 
     * @param index
     * @return speed of given moving circle
     */
    public double GetMovingCircleSpeed(int index)
    {
        if(index < 0 || index >= movingCirclesAmount)
        {
            return -1;
        }
        return movingCircles[index].GetSpeed();
    }

    /**
     * 
     * @param index
     * @return color og given moving circle
     */
    public Color GetMovingCircleColor(int index)
    {
        if(index < 0 || index >= movingCirclesAmount)
        {
            return null;
        }
        return movingCircles[index].GetColor();
    }
    
    /**
     * Setups moving circles
     */
    private void SetupCircles()
    {
        Random random = new Random();
        movingCircles = new MovingCircle[movingCirclesAmount];
        for (int i = 0; i < movingCirclesAmount; i++) {
            double beginAngle;
            boolean isOk = false;
            do {
                beginAngle = random.nextDouble(0,Math.PI*2);
                isOk = MovingCircle.CheckIfNewFits(beginAngle, movingCircleRadius);
            } while (isOk == false);
            
            Color mColor = RandomColor.GetRandomNewColor();
            double speed = random.nextDouble(minSpeed, maxSpeed);
            movingCircles[i] = new MovingCircle(new Vector2(paddingW, paddingH),height/2 - paddingH,movingCircleRadius, beginAngle,speed, mColor);
        }

        for (MovingCircle movingCircle : movingCircles) {
            movingCircle.SetClosestBefore();
        }
    }

    /**
     * Run all circles or notify them
     */
    private void RunThreads()
    {
        for (int i = 0; i < movingCircles.length; i++) {
            if(movingCircles[i].getState() == State.NEW)movingCircles[i].start();
            else
            {
                movingCircles[i].SetShouldWait(false);
                synchronized(movingCircles[i])
                {
                    movingCircles[i].notify();                    
                }
            }
        }
    }

    /**
     * Sets all circles to wait
     */
    private void StopThreads()
    {
        for (int i = 0; i < movingCircles.length; i++) {
            movingCircles[i].SetShouldWait(true);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(!isDrawn)
        {
            height = this.getHeight();
            width = this.getWidth();
            paddingH = Math.round(height * paddingPercent);
            paddingW = Math.round(width * paddingPercent);
            SetupCircles();
            isDrawn = true;
        }

        g.setColor(color);
        g.drawArc(paddingW, paddingH, width - (paddingW * 2), height - paddingH*2, 0, 360);

        synchronized(movingCircles)
        {
            for (int i = 0; i < movingCircles.length; i++) {
                movingCircles[i].Draw(g);
            }
        }
    }
}
