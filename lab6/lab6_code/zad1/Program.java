package lab6_code.zad1;


public class Program {

    /**
     * Main
     * @param args
     * args[0] = circles amount
     */
    public static void main(String[] args) {

        if(args.length != 1)
        {
            System.out.println("Wrong params amount, should be 1 and was " + args.length);
            return;
        }

        int n = 0;

        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Given param must be parsable to int");
            return;
        }

        if(n < 0 || n > 10)
        {
            System.out.println("Given param must be positive and smaller than 11");
            return;
        }


        new View(600,700, n);
    }
}
