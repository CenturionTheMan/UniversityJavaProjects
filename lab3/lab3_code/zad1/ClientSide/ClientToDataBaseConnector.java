
package lab3_code.zad1.ClientSide;
import java.util.List;
import java.util.Scanner;

import lab3_code.zad1.DataBase;
import lab3_code.zad1.Item;

public class ClientToDataBaseConnector {
    DataBase dataBase;
    ClientBasket clientBasket;


    public ClientToDataBaseConnector(DataBase dataBase, ClientBasket clientBasket) {
        this.dataBase = dataBase;
        this.clientBasket = clientBasket;
    }

    public void PrintBasket()
    {
        System.out.println("==================================");
        System.out.println("Basket summarize price = " + clientBasket.GetSumPrice());
        System.out.println("Items:");
        clientBasket.PrintBasketInConsole();
    }

    /**
     * Saves changes
     */
    public void FinalizeOrder()
    {
        clientBasket.ClearBasket();
        dataBase.SaveDataBase(dataBase.DataBasePath);
    }

    /**
     * Removes item form basket with user input
     * @param scanner Scanner object
     * @return false if basket is empty, true otherwise
     */
    public boolean RemoveItemFromBasket(Scanner scanner)
    {
        if(clientBasket.GetBasketSize() == 0)
        {
            System.out.println("Basket is empty.");
            return false;
        }

        System.out.println("Pick item to remove (by number):");
        System.out.println(clientBasket.toString());

       Item chosen;

        while (true) {
            try {
                System.out.println("Choose item: ");
                String input = scanner.nextLine();
                input = input.replace(".", "");
                int inputInt = Integer.parseInt(input);
                chosen = clientBasket.RemoveFromBasket(inputInt - 1);
                break;

            } catch (Exception e) {
                System.out.println("Wrong item number");
            }    
        }
        dataBase.AddItemToDataBase(chosen);

        return true;
    }

    /**
     * Checks if item exists in database
     *!Include pseudo REGEX
     * @param scanner Scanner object
     * @return true if item exists, false otherwise
     */
    public boolean CheckIfItemExists(Scanner scanner)
    {
        System.out.println("Give item to check name: ");
        String tempName = scanner.nextLine();

        if(dataBase.CheckIfItemExists(tempName))
        {
            System.out.println("Item " + tempName + " exists.");
            return true;
        }
        {
            System.out.println("Item " + tempName + " does not exists.");
            return false;
        }
    }

    /**
     * Adds item to basket
     * !Include pseudo REGEX
     * @param scanner Scanner object
     * @return false if given item do not exists in data base, true otherwise
     */
    public boolean AddItemToBasket(Scanner scanner)
    {
        String name;

        System.out.println("Give item name: ");
        name = scanner.nextLine();

        List<Item> found = dataBase.GetItemsWithGivenName(name);
        
        if(found == null || found.size() == 0)
        {
            System.out.println("No items with given name in data base");
            return false;
        }

        System.out.println("Items found: ");
        for (int i = 0; i < found.size(); i++) {
            Item current = found.get(i);
            System.out.println( "[" + (i+1) + "] -> id= "+current.GetStringId() + ", name= "+current.GetName()+ ", price= " +current.GetPrice());
        }

        int selectionInt;

        while (true)
        {
            try {
                System.out.println("Select item from list by number: ");
                selectionInt = Integer.parseInt(scanner.nextLine());
                selectionInt --;
                if(selectionInt >= 0 && selectionInt < found.size()) break;
                else
                    System.out.println("Wrong selection input");
            } catch (Exception e) {
                System.out.println("Wrong selection input");
            }
        }

        clientBasket.AddToBasket(found.get(selectionInt));
        dataBase.RemoveFromDataBase(found.get(selectionInt));

        return true;
    }
}
