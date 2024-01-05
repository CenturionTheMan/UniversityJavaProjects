package lab3_code.zad2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProgramZad2 {

    enum Names{
        Monika,
        Ala,
        Tomasz,
        Jakub,
        Michal,
        Mateusz,
        Jonasz,
        Malgorzata
    }

    /**
     * Entry point
     * @param args
     */
    public static void main(String[] args) {

        if(args.length != 3) //main args check
            throw new IllegalArgumentException("Exactly 3 parameters required !");

        String[] allNames = Arrays.toString(Names.values()).replaceAll("^.|.$", "").split(", "); //Get array of strings (Names) from enum 

        // Get names from files which matches Names enum
        String[][] namesFormFiles = new String[args.length][];
        namesFormFiles[0] = FilterStringFromFileWithStringArray(args[0],allNames);
        namesFormFiles[1] = FilterStringFromFileWithStringArray(args[1],allNames);
        namesFormFiles[2] = FilterStringFromFileWithStringArray(args[2],allNames);
        //


        ArrayList<Litera> letter = GetLettersArray(namesFormFiles); //Get List of Letter 


        letter.forEach(l -> System.out.println(l.toString())); //Print result
    }

    /**
     * Goes through String from given file, splits it into words and filters with given String array
     * @param filePath path for file to get String from
     * @param namesToMatch array of Strings which need to be found in given file
     * @return Words from file which matches 'namesToMatch' array
     */
    static String[] FilterStringFromFileWithStringArray(String filePath, String[] namesToMatch)
    {
        return Arrays.stream(GetWordsFromFile(filePath)).filter(w -> CheckIfStringIsName(w, namesToMatch)).toArray(size -> new String[size]);
    }

    /**
     * Check if string matches given array of strings
     * @param word word to check
     * @param names array of keys which word i supposed to match
     * @return true if string are match, false otherwise
     */
    static boolean CheckIfStringIsName(String word, String[] names)
    {
        return Arrays.stream(names).anyMatch(n -> n.contentEquals(word));
    }

    /**
     * 
     * @param namesFromFiles double array of [file number][names strings in given file]
     * @return ArrayList of Litera class
     */
    static ArrayList<Litera> GetLettersArray(String[][] namesFromFiles)
    {
        final int maxSize = 26; //max size of list
        final int aCharNumber = 97; //char code of letter 'a'

        ArrayList<Litera> letters = new ArrayList<Litera>(maxSize);
 
        for (int i = 0; i < maxSize; i++) {
            char letterSign = (char)(aCharNumber + i);

            var namesForLetter = GetNamesByFirstLetter(letterSign, namesFromFiles);

            var letter = new Litera(letterSign,namesForLetter);
            letter.getNames().sort(new SortImieByName());
            letters.add(letter);
        }

        return letters;
    }

    /**
     * Method for each file (in 'namesFromFiles' array) goes through each string, check if first char is equal to letter, and then counts its occurrences in given file.
     * @param letter letter for given Imie class
     * @param namesFromFiles double array of [file number][names strings in given file]
     * @return List of Imie class
     */
    static List<Imie> GetNamesByFirstLetter(char letter, String[][] namesFromFiles)
    {
        LinkedList<Imie> names = new LinkedList<Imie>();

        for (int fileNum = 0; fileNum < namesFromFiles.length; fileNum++) {
            String[] namesInFile = namesFromFiles[fileNum];

            for (String nameInFile : namesInFile) {
                
                if(!CheckIfWordBeginsWithLetter(nameInFile,letter)) continue; //check if first letter matches

                int res = FindIndexOfName(nameInFile, names); //finds index in array of given word

                // adds occurrences
                if(res == -1)
                {
                    int[] occurrences = new int[3];
                    occurrences[fileNum] = 1;
                    names.add(new Imie(nameInFile, occurrences));
                }
                else
                {
                    names.get(res).AddOccurrences(fileNum);
                }
                //
            }

        }

        return names;
    }

    /**
     * 
     * @param name String to find
     * @param names List of strings to search in
     * @return index of given Imie element, -1 if there if no such
     */
    static int FindIndexOfName(String name, LinkedList<Imie> names)
    {
        LinkedList<Imie> toWork = new LinkedList<Imie>(names);

        int count = 0;
        while (toWork.size() > 0) {
            Imie im = toWork.pop();
            if(im.CompareName(name))
            {
                return count;
            }
            count++;
        }

        return -1;

    }

    /**
     * 
     * @param word word which first char need to be check
     * @param letter char to match
     * @return true if match, false otherwise
     */
    static boolean CheckIfWordBeginsWithLetter(String word, char letter)
    {
        String holder = String.valueOf(letter).toLowerCase();
        char[] temp = holder.toCharArray();

        if(word.toLowerCase().charAt(0) == temp[0]) return true;
        else
            return false;
    }

    /**
     * 
     * @param filePath path for file
     * @return array of words in file
     */
    static String[] GetWordsFromFile(String filePath){
        try {
            var path = Path.of(filePath);
            String content = Files.readString(path);
            content = content.replace("/[^A-Za-z ]/g","").trim();
            return content.split(" ");
        } catch (Exception e) {
            throw new IllegalArgumentException("Path: " + filePath + " does not exists.");
        }
    }

    

}
