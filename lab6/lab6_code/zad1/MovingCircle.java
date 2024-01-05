package lab6_code.zad1;

import java.awt.*;
import java.util.ArrayList;


public class MovingCircle extends Thread  {
    private Vector2 currentPosition;
    private double speed;
    private double currentAngle;
    private int radius;
    private MovingCircle closestBefore;
    private Color color;

    private static int mapRadius;
    private static Vector2 mapTopLeftCorner;
    private static ArrayList<MovingCircle> allCircles = new ArrayList<>();

    private boolean shouldWait = false;

    /**
     * CTOR
     * @param mapTopLeftCorner
     * @param mapRadius
     * @param radius
     * @param beginAngle
     * @param speed
     * @param color
     */
    public MovingCircle(Vector2 mapTopLeftCorner, int mapRadius, int radius, Double beginAngle, double speed, Color color) {        
        currentAngle = beginAngle;
        MovingCircle.mapTopLeftCorner = mapTopLeftCorner;
        this.color = color;
        MovingCircle.mapRadius = mapRadius;
        this.speed = speed;
        this.closestBefore = null;
        this.radius = radius;
        currentPosition = GetGlobalPosition(CircleMath.GetPositionOnUnitCircle(beginAngle));

        allCircles.add(this);
    }

    /**
     * Draws this component
     * @param g
     */
    public void Draw(Graphics g)
    {
        g.setColor(color);

        int x = currentPosition.getIntX() - radius;
        int y = currentPosition.getIntY() - radius;
        int wH = (int)Math.round(radius*2);
        g.fillOval(x,y,wH,wH);

        g.setColor(Color.black);
        g.drawArc(x, y, wH, wH, 0, 360);
    }

    /**
     * 
     * @return current circle angle
     */
    public double GetCurrentAngle()
    {
        return currentAngle;
    }

    /**
     * 
     * @return circle color
     */
    public Color GetColor()
    {
        return color;
    }

    /**
     * 
     * @return circle radius
     */
    public double GetRadius()
    {
        return radius;
    }

    /**
     * 
     * @return current position
     */
    public Vector2 GetCurrentPosition()
    {
        return currentPosition;
    }

    /**
     * Set speed
     */
    public void SetSpeed(double speed)
    {
        this.speed = speed;
    }

    /**
     * 
     * @return speed
     */
    public double GetSpeed()
    {
        return speed;
    }

    /**
     * Set shouldWait, boolean used for calling wait for this thread
     * @param b
     */
    public void SetShouldWait(boolean b)
    {
        shouldWait = b;
    }

    /**
     * Converts local position to global (in order to match big circle cords)
     * @param localUnitPosition local position (on unit circle)
     * @return global position
     */
    private static Vector2 GetGlobalPosition(Vector2 localUnitPosition)
    {
        return localUnitPosition.MultiplyByConst(mapRadius).AddVector(mapTopLeftCorner).AddVector(mapRadius,mapRadius);
    }

    /**
     * Changes this circle position if do not collide with closest previous circle
     * @param change amount of position change (angle)
     * @return true if position was changed, false otherwise
     */
    public synchronized boolean ChangePosition(double change)
    {
        Vector2 position = GetGlobalPosition(CircleMath.GetPositionOnUnitCircle(currentAngle + change));
        
        if(CheckIfCollideOnPos(position, 1))
        {
            return false;
        }


        currentAngle += change;
        currentPosition = position;
        return true;
    }

    /**
     * 
     * @param otherCircle other circle
     * @param position
     * @param radius
     * @param threshold
     * @return true if given circle would collide if circle with given parameters would exists with custom parameters, false otherwise
     */
    private static boolean CheckIfCollideOnPos(MovingCircle otherCircle,Vector2 position, int radius ,double threshold)
    {
        if(otherCircle != null && position.GetDistanceToPositionSqrt(otherCircle.GetCurrentPosition()) - (radius + otherCircle.GetRadius()) < -threshold) return true;
        else return false;
    }

    /**
     * 
     * @param position
     * @param threshold error zone (how much can two circe pass through each other)
     * @return true if collides with moving circle before this one  on given position (closestBefore param), false otherwise
     */
    private synchronized boolean CheckIfCollideOnPos(Vector2 position, double threshold)
    {
        if(closestBefore != null && position.GetDistanceToPositionSqrt(closestBefore.GetCurrentPosition()) - (this.radius + closestBefore.GetRadius()) < -threshold) return true;
        else return false;
    }

    /**
     * Setups closest moving circle which is before this one (saves it to closestBefore param)
     * @return false if this circle collides with closestBefore, true otherwise
     */
    public boolean SetClosestBefore()
    {
        MovingCircle closestBigger = null;
        MovingCircle smallest = null;

        double angleDiff = Double.MAX_VALUE;
        
        for (MovingCircle movingCircle : allCircles) {
            if(movingCircle == this) continue;

            if(movingCircle.GetCurrentAngle() > this.currentAngle && angleDiff > movingCircle.GetCurrentAngle() - this.currentAngle)
            {
                closestBigger = movingCircle;
                angleDiff = movingCircle.GetCurrentAngle() - this.currentAngle;
            }

            if(smallest == null) smallest = movingCircle;

            if(smallest.GetCurrentAngle() > movingCircle.GetCurrentAngle())
            {
                smallest = movingCircle;
            }
        }
        if(closestBigger == null) closestBigger = smallest;

        closestBefore = closestBigger;

        if(CheckIfCollideOnPos(currentPosition,0))
        {
            System.out.println("Circles collide");
            return false;
        }

        return true;
    }


    /**
     * Will try to update this moving circle position each frame
     */
    @Override
    public void run() {
        while (true) {

            while(shouldWait)
            {
                synchronized(MovingCircle.this)
                {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }

            ChangePosition(speed);

            try {
                Thread.sleep(0,1);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * 
     * @param angle
     * @param radius
     * @return true if circle on given angle and with given radius would not collide with any existing one, false otherwise
     */
    public static boolean CheckIfNewFits(double angle, int radius)
    {
        if(allCircles.size() == 0) return true;

        var pos = GetGlobalPosition(CircleMath.GetPositionOnUnitCircle(angle));

        for (MovingCircle movingCircle : allCircles) {
            if(CheckIfCollideOnPos(movingCircle,pos,radius,1))
            {
                return false;
            }
        }

        return true;
    }
}
