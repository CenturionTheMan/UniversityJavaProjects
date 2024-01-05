package lab6_code.zad2;

public class Program {

    /**
     * ENTRY POINT
     * @param args
     * args[0] = width (w)
     * args[1] = height (h)
     * args[2] = snails amount (n)
     */
    public static void main(String[] args) {
        int w,h,n;

        if(args.length != 3)
        {
            System.out.println("Wrong arguments amount, should be 3 and was " + args.length);
            return;
        }

        try {
            w = Integer.parseInt(args[0]);
            if(w < 1) throw new Exception();
        } catch (Exception e) {
            System.out.println(" Argument number 0 (args[0]) have to be an integer and must be grater than 0");
            return;
        }

        try {
            h = Integer.parseInt(args[1]);
            if(h < 1) throw new Exception();
        } catch (Exception e) {
            System.out.println(" Argument number 1 (args[1]) have to be an integer and must be grater than 0");
            return;
        }

        try {
            n = Integer.parseInt(args[2]);
            if(n  > h*w) throw new Exception();
        } catch (Exception e) {
            System.out.println(" Argument number 2 (args[2]) have to be an integer and must be lesser or equal to w * h (args[0] * args[1])");
            return;
        }

        int snailsAmount = n;
        Vector2Int mapSize = new Vector2Int(w, h);


        LeafThread leafThread = new LeafThread(snailsAmount,mapSize);
        leafThread.start();

        new MainView(800,800,leafThread);
    }
}
