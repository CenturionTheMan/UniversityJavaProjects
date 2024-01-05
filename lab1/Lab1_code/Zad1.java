package Lab1_code;
import java.math.BigInteger;

/**
 * Zad1
 */

public class Zad1 {

    static final int MaxNVal = 1000;

    // Entry point
    public static void main(String[] args) {
        Entry(args);
    }

    // Handles input and printing
    static void Entry(String[] args) {
        
        if(args.length != 1)
        {
            System.out.println("Linia wywolania programu musi mieć 1 argument a ma " + args.length);
            return;
        }

        System.out.println("Odczytane n z lini wywolania programu to: " + args[0]);

        int num;
        if (args[0].matches("[0-9]+")) 
        {
            num = Integer.parseInt(args[0]);
            
            if(num > 3)
            {
                if(num > MaxNVal) System.out.println("Uwaga! Program przetestowany do liczby n = " + MaxNVal);
                System.out.println("Dla danego n liczba liczb pierwszych wynosi: " + Liczba(num));
                return;
            }
        }

        System.out.println("Argument lini wywolania programu musi byc liczba calkowita wieksza od 3");

        
    }

    // Calculating number of primenumbers
    static int Liczba(int n) {
        int holder = 0;
        for (int i = 3; i <= n; i++) {
            BigInteger silniaIMinus = Silnia(i - 2);
            BigInteger iVal = BigInteger.valueOf(i);

            BigInteger secondPart = silniaIMinus.divide(iVal);

            holder += silniaIMinus.subtract(iVal.multiply(secondPart)).intValue();
        }

        return holder - 1;
    }

    // Calculating factorial
    static BigInteger Silnia(int x) {
        if (x == 0)
            return BigInteger.valueOf(1);

        BigInteger holder = BigInteger.ONE;

        for (int i = x; i > 0; i--) {
            holder = holder.multiply(BigInteger.valueOf(i));
        }
        return holder;
    }
}