package lab6_code.zad2;

import java.util.ArrayList;
import java.awt.Color;

public class LeafCell {
    private SnailThread snail = null;
    private float foodAmount;

    private Vector2Int mapPosition;
    private ArrayList<LeafCell> neighbors;

    /**
     * CTOR
     * @param mapPosition
     * @param foodAmount
     */
    public LeafCell(Vector2Int mapPosition, float foodAmount) {

        if(foodAmount > 10) foodAmount =10;
        if(foodAmount < 0) foodAmount =0;
            
        this.mapPosition = mapPosition;
        this.foodAmount = foodAmount;
        this.snail = null;
    }

    /**
     * 
     * @return Color of green scale which depends on food amount in this cell
     */
    public Color GetColorByFoodAmount()
    {
        int rVal = Interpolate(12, 255, 1 - foodAmount/(float)10);
        int gVal = Interpolate(107, 255, 1 - foodAmount/(float)10);
        int bVal = Interpolate(21, 255, 1 - foodAmount/(float)10);
        return new Color(rVal,gVal,bVal);
    }

    /**
     * @param min
     * @param max
     * @param percent
     * @return lineal value between min and max depending on percent param:
     */
    private int Interpolate(int min, int max, float percent)
    {
        return Math.round(min + percent * (max - min));
    }

    /**
     * 
     * @return mapPosition param
     */
    public Vector2Int GetMapPosition()
    {
        return mapPosition;
    }

    /**
     * @return neighbors param
     */
    public ArrayList<LeafCell> GetNeighbors()
    {
        return neighbors;
    }

    /**
     * Sets neighbors param
     * @param neigh
     */
    public void SetNeighbors(ArrayList<LeafCell> neigh)
    {
        neighbors = neigh;
    }

    /**
     * 
     * @return foodAmount param
     */
    public float GetFoodAmount()
    {
        return foodAmount;
    }
    
    /**
     * Will change foodAmount param by given amount in 0,10 bounds
     * @param amount amount to change
     * @return true if value was changed, false otherwise
     */
    public synchronized boolean ChangeFoodAmount(float amount)
    {
        if(amount < 0 && foodAmount <= 0) return false;
        if(amount > 0 && foodAmount >= 10) return false;
        
        foodAmount += amount;

        if(foodAmount > 10) foodAmount = 10;
        if(foodAmount < 0) foodAmount = 0;
        return true;
    }

    /**
     * 
     * @return true if there is snail on given cell, false otherwise
     */
    public boolean IsSnail()
    {
        if(snail == null) return false;
        return true;
    }

    /**
     * Will set snail param
     * @param snail to set
     * @return true if success, false if there is already snail 
     */
    public boolean SetSnail(SnailThread snail)
    {
        if(this.snail != null) return false;
        this.snail = snail;
        snail.SetCurrentCell(this);
        return true;
    }

    /**
     * Will remove snail form cell
     * @return removed snail
     */
    public SnailThread RemoveSnail()
    {
        var temp = snail;
        temp.SetCurrentCell(null);
        snail = null;
        return temp;
    }

}
