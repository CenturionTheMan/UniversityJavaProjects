package lab2_code;


public class Program {

    //data for randomizing cars data base
    final static float MinPrice = 2000;
    final static float MaxPrice = 10000;
    final static int ProductionDateMin = 1900;
    final static int ProductionDateMax = 2018;

    public static void main(String[] args) throws Exception {
        
        if(args.length != 2)
        {
            System.out.println(" Zla liczba argumentow wejsciowych, powinny byc 2 a bylo " + args.length);
            return;
        }

        System.out.println(" Argumenty odczytane z lini wywołania programu to:\n arg0: " + args[0] + "\n arg1: " + args[1]);
        
        String carsAmount =  args[0];
        String outputType = args[1];

        int carsAmountInt = 0;
        try {
            carsAmountInt = Integer.parseInt(carsAmount);
            if(carsAmountInt < 0) throw new Exception();
        } 
        catch (Exception e) {
            System.out.println( " arg0 musi byc nieujemna liczba calkowita");
            return;
        }

        outputType = outputType.toLowerCase();
        if(!outputType.contentEquals("w") && !outputType.contentEquals("r") )
        {
            System.out.println(" arg1 musi byc rowny \"w\" lub \"r\" a byl " + args[1]);
            return;
        }

        CarsHandler carsHandler = new CarsHandler(carsAmountInt, MinPrice, MaxPrice, ProductionDateMin, ProductionDateMax);

        // prints all cars in data base
        System.out.println(" All cars:");
        carsHandler.PrintAllCars();


        InputReader easyReader = new InputReader();

        // get querried data
        var result = carsHandler.GetQuerryCars(easyReader.GetQuerryType(), easyReader.GetYear());

        //choose output type
        System.out.println(" Results:");
        if (outputType.contentEquals("w")) {
            throw new CustomException(result);
        } else {
            carsHandler.PrintCarsList(result);
        }
    }

}
