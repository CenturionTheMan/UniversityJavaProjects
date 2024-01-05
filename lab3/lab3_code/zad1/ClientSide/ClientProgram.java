package lab3_code.zad1.ClientSide;

import java.util.Scanner;

import lab3_code.zad1.DataBase;


public class ClientProgram {
    
    static final String DataBaseFilePath = "lab3_code\\zad1\\DataBase.csv";
    
    public static void main(String[] args) {
    
        DataBase dataBase = new DataBase(DataBaseFilePath);
        ClientBasket basket = new ClientBasket();
        var connector = new ClientToDataBaseConnector(dataBase, basket);
        
        App(connector);

        dataBase.SaveDataBase(DataBaseFilePath);
    }

    static void App(ClientToDataBaseConnector connector)
    {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        do {
            
            System.out.println("--Choose action--");
            System.out.println("0 - Exit app");
            System.out.println("1 - Check if item exists");
            System.out.println("2 - Add item to basket");
            System.out.println("3 - Remove item from basket");
            System.out.println("4 - Print basket with sum price");
            System.out.println("5 - Finalize order");

            

            input = scanner.nextLine();

            switch (input) {
                case "1":
                connector.CheckIfItemExists(scanner);
                    break;
                
                case "2":
                connector.AddItemToBasket(scanner);
                    break;
                    
                case "3":
                connector.RemoveItemFromBasket(scanner);
                    break;

                case "4":
                connector.PrintBasket();
                    break;

                case "5":
                connector.FinalizeOrder();
                    break;

                default:
                if(!input.contentEquals("0")) System.out.println("Unknown input");    
                    break;
            }
            System.out.println();

        } while (!input.contentEquals("0"));
        scanner.close();
        
    }




}

