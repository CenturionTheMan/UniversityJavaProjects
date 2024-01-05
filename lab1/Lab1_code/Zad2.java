package Lab1_code;

import java.util.List;
import java.math.BigInteger;
import java.util.ArrayList;

public class Zad2 {

    static final int CustomMaxSize = 1000; // Max size of list
    static final boolean IsZeroNatural = true; // Defines whether 0 should be account as natural number

    // Entry point
    public static void main(String[] args) {
        HandleAction(args);
    }

    // Handles input and output printing
    static void HandleAction(String[] args) {

        if(args.length != 1)
        {
            System.out.println("Linia wywołania programu musi mieć 1 argument a ma " + args.length);
            return;
        }

        System.out.println("Odczytane n_0 z lini wywolania programu to: " + args[0]);

        if(!args[0].matches("[0-9]+"))
        {
            System.out.println("n_0 musi byc liczba naturalna");
            return;
        }

        int nZero = Integer.parseInt(args[0]);
        
        if (!IsZeroNatural && nZero == 0) 
        {
            System.out.println("n_0 musi byc liczba naturalna a byl: " + nZero + " zmien wejscie programu albo zmienc wartosc pola \"IsZeroNatural\"");
        } 
        else 
        {
            var res = numbers(nZero);

            for (int i = 0; i < res.size() - 1; i++) {
                String pre = (res.get(i) % 2 == 0) ? "parzysta" : "nieparzysta";
                System.out.println("n_" + i + ": " + res.get(i) + ", " + pre + ", " + "n_" + (i + 1) + ": "+ res.get(i + 1));
            }
        }
    }

    // Calculates sequence of numbers
    static List<Long> numbers(int nZero) {
        List<Long> res = new ArrayList<Long>();

        long item = nZero;
        res.add(item);

        do {
            item = Kolejna(item);

            if (item == -1)
                break;

            res.add(item);
        } while (res.size() < CustomMaxSize + 1 && item != 1);

        return res;
    }

    // Calculates next value basing on previous one
    static long Kolejna(long n) {

        BigInteger check = BigInteger.valueOf(n);

        if (check.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0)
            return -1;
        else
            return (n % 2 == 0) ? n / 2 : n * 3 + 1;
    }
}
