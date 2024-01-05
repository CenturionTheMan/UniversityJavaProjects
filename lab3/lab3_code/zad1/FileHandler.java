package lab3_code.zad1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class FileHandler {

    /**
     * Reads 'Item' elements from file of csv format, will try to create new file if there in no found in given path or file format error occurs.
     * @param path path for file
     * @return List of items from csv file
     * @throws Exception
     */
    public static List<Item> GetItemsFromCsv(String path)
    {
        var result = new ArrayList<Item>();
        Scanner scanner = null;
        
        try 
        {
            File file = new File(path);
            scanner = new Scanner(file);

            if(scanner.hasNextLine()) scanner.nextLine(); //Head skip
        } 
        catch (FileNotFoundException e) {
            System.out.println("There is no data base in given path, creating new one...");
            SaveItemsInCsvFile(path, null);
            return new ArrayList<Item>();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            var words = line.split(";");
            try {
                result.add(new Item(words[0], words[1], words[2]));
            } 
            catch (Exception e) {
                System.out.println("Data base file format error. New data base will be created...");
                SaveItemsInCsvFile(path, null);
                scanner.close();
                return new ArrayList<Item>();
            }
        }
        scanner.close();    
        
        return result;          
    }

    /**
     * Saves Items data in csv file
     * @param path path for file
     * @param items items to save
     */
    public static void SaveItemsInCsvFile(String path, List<Item> items)
    {
        FileWriter writer = null;
        File file = null;
        if(items == null) items = new ArrayList<Item>();

        try {
            file = new File(path);
           
            if(file.exists()) file.delete();

            file.createNewFile();

            writer = new FileWriter(path);
            writer.write("Id;Name;Price\n");

            for (var item : items) {
                writer.write(item.toString() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving csv file. Probably given file path is incorrect. \nPROGRESS IS NOT SAVED!");
            e.printStackTrace();
        }
    }
}
