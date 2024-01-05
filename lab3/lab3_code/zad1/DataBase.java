package lab3_code.zad1;
import java.util.List;

public class DataBase {

    public final String DataBasePath;

    private List<Item> items;
    private int freeId;

    public DataBase(String dataBaseFilePath) {
        DataBasePath = dataBaseFilePath;
        this.items = FileHandler.GetItemsFromCsv(dataBaseFilePath);

        freeId = (items.size() != 0)? items.get(items.size()-1).GetIntId() + 1 : 0;
    }

    /**
     * !Include pseudo REGEX
     * @param nameToComp Name to search
     * @return List of items which matches given name
     */
    public List<Item> GetItemsWithGivenName(String nameToComp)
    {
        return items.stream().filter(ite -> ite.CompareByName(nameToComp)).toList();
    }

    /**
     * !Include pseudo REGEX
     * @param itemName item to check
     * @return true if exists, false otherwise
     */
    public boolean CheckIfItemExists(String itemName)
    {
        return items.stream().anyMatch(ite -> ite.CompareByName(itemName));
    }

    /**
     * 
     * @param item item to remove
     * @return true if success, false otherwise
     */
    public boolean RemoveFromDataBase(Item item)
    {
        return items.remove(item);
    }

    /**
     * 
     * @param name Name of item
     * @return true if success, false otherwise
     */
    // public boolean RemoveByName(String name)
    // {
    //     for (int i = items.size() - 1; i >= 0; i--) {
    //         if(items.get(i).CompareByName(name))
    //         {
    //             items.remove(i);
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    /**
     * 
     * @param item item to add
     * @return true if success, false otherwise
     */
    public boolean AddItemToDataBase(Item item)
    {
        String price = String.valueOf(item.GetPrice());
        return AddItemToDataBase(item.GetName(),price);
    }

    /**
     * 
     * @param name name of product
     * @param price price of product in string format
     * @return true if success, false otherwise
     */
    public boolean AddItemToDataBase(String name, String price)
    {
        try {
            Item item = new Item(freeId, name, price);
            items.add(item);
            freeId++;
            return true;    
        } catch (Exception e) {
            System.out.println("An error occurred. Given values formats does not match program requirements.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 
     * @param dataBaseFilePath path for file
     */
    public void SaveDataBase(String dataBaseFilePath){
        FileHandler.SaveItemsInCsvFile(dataBaseFilePath, items);
    }

    @Override
    public String toString() {
        String result = "";        
        
        for (int i = 0; i < items.size(); i++) {
            Item current = items.get(i);
            result += (i+1) +". id= "+current.GetStringId() + ", name= "+current.GetName()+ ", price= " +current.GetPrice();
        }

        return result;
    }
}
