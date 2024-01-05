package lab3_code.zad1.AdminSide;

import java.util.Scanner;

import lab3_code.zad1.DataBase;
import lab3_code.zad1.Item;

public class AdminProgram 
{

    static final String DataBaseFilePath = "lab3_code\\zad1\\DataBase.csv";

    public static void main(String[] args) {

        DataBase dataBase = new DataBase(DataBaseFilePath);
        Scanner scanner = new Scanner(System.in);

        String input = "";
        do {
            
            System.out.println("--Choose action--");
            System.out.println("0 - save and exit app");
            System.out.println("1 - add item");
            System.out.println("2 - remove item");

            input = scanner.nextLine();

            if(input.contentEquals("1"))
            {
                AddItem(scanner,dataBase);
            }
            else if(input.contentEquals("2"))
            {
                if(RemoveItem(scanner, dataBase)) continue;
            }
            else if(!input.contentEquals("0")){
                System.out.println("Unknown input");
            }

            System.out.println();

        } while (!input.contentEquals("0"));
        scanner.close();
        dataBase.SaveDataBase(DataBaseFilePath);
    }

    /**
     * Removes item from data base with user input
     * !Include pseudo REGEX
     * @param scanner scanner object
     * @param dataBase Database object
     * @return true if user cancel removing, false otherwise
     */
    static boolean RemoveItem(Scanner scanner,DataBase dataBase)
    {
        String name;
        System.out.println("Give item name: ");
        name = scanner.nextLine();

        var found = dataBase.GetItemsWithGivenName(name);
        for (int i = 0; i < found.size(); i++) {
            Item current = found.get(i);
            System.out.println( "[" + (i+1) + "] -> id= "+current.GetStringId() + ", name= "+current.GetName()+ ", price= " +current.GetPrice());
        }

        int selectionInt;
        while (true)
        {
            try {
                System.out.println("Select item from list by number or 0 to cancel action: ");
                selectionInt = Integer.parseInt(scanner.nextLine());
                selectionInt --;

                if(selectionInt == -1) break;

                if(selectionInt >= 0 && selectionInt < found.size()) break;
                else
                    System.out.println("Wrong selection input");
            } catch (Exception e) {
                System.out.println("Wrong selection input");
            }
        }

        if(selectionInt == -1) return true;

        dataBase.RemoveFromDataBase(found.get(selectionInt));
        return false;
    }

    /**
     * Adds item to data base with user input
     * @param scanner scanner object
     * @param dataBase Database object
     */
    static void AddItem(Scanner scanner,DataBase dataBase)
    {
        String name,price;

        System.out.println("Give item name: ");
        name = scanner.nextLine();

        System.out.println("Give item price: ");
        price = scanner.nextLine();

        dataBase.AddItemToDataBase(name, price);
        System.out.println("Item added.\n");
    }
}    
