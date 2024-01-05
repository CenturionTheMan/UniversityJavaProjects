package lab3_code.zad1.ClientSide;

import java.util.ArrayList;
import java.util.List;

import lab3_code.zad1.Item;

public class ClientBasket {
    
    private List<Item> basket = new ArrayList<Item>();

    public int GetBasketSize() {return basket.size();}

    /**
     * 
     * @return summarize price of items in the basket
     */
    public float GetSumPrice()
    {
        float sum = 0;

        for (Item item : basket) {
            sum += item.GetPrice();   
        }
        return sum;
    }

    /**
     * Clears basket
     */
    public void ClearBasket()
    {
        basket.clear();
    }

    /**
     * 
     * @param item item to add
     */
    public void AddToBasket(Item item)
    {
        basket.add(item);
    }

    /**
     * Removes item form basket by index in list
     * @param index index to remove
     * @return removed item
     */
    public Item RemoveFromBasket(int index)
    {
        return basket.remove(index);
    }

    
    public void PrintBasketInConsole()
    {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String result = "";        
        
        for (int i = 0; i < basket.size(); i++) {
            Item current = basket.get(i);
            result += (i+1) +". id= "+current.GetStringId() + ", name= "+current.GetName()+ ", price= " +current.GetPrice() + "\n";
        }

        return result;
    }

}
