package lab3_code.zad2;

import java.util.Comparator;

public class SortImieByName implements Comparator<Imie> {

    @Override
    public int compare(Imie o1, Imie o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
