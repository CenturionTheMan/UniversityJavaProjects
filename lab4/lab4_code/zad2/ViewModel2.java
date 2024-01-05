package lab4_code.zad2;

import java.awt.Color;
import java.math.BigInteger;

public class ViewModel2 {
    
    View2 view = null;

    public void setView(View2 v)
    {
        view = v;
    }

    /**
     * Connects user input with bar chart
     * @param index
     * @param text
     * @param isSelected
     * @return true if input is correct, false otherwise
     */
    public boolean OnCheckBoxPressed(int index, String text, boolean isSelected)
    {
        if(view == null) return false;

        BigInteger val;
        try {
            val = new BigInteger(text);
        } catch (Exception e) {
            view.ShowMessage("Input must be an integer");
            return false;
        }

        if(val.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0)
        {
            view.ShowMessage("Given input is to big");
            return false;
        }
        
        if(val.intValue() < 0)
        {
            view.ShowMessage("Input must be of positive value");
            return false;
        }

        if(isSelected)
        {
            Color col = switch (index){
                case 0 -> new Color(102, 255, 140);
                case 1 -> new Color(102, 255, 179);
                case 2 -> new Color(102, 255, 217);
                case 3 -> new Color(102,255,255);
                case 4 -> new Color(102,217,255);
                case 5 -> new Color(102,179,255);
                case 6 -> new Color(102, 140, 255);
                case 7 -> new Color(102, 102, 255);
                case 8 -> new Color(140, 102, 255);
                case 9 -> new Color(179, 102, 255);
                default -> throw new IllegalArgumentException("Unexpected value: " + index);
            };
            
            view.AddBarToChart(index, val.intValue(), col);
            return true;
        }
        else
        {
            view.RemoveBarFromChart(index);
            return true;
        }
    }
}
