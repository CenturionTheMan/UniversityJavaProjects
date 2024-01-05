package lab3_code.zad2;

public class Imie {
    private String name;
    private int[] occurrences;

    public String getName() { return name; }
    public int[] getOccurrences() {return occurrences; }

    public Imie(String name, int[]occurrences ) {
        this.occurrences = occurrences;
        this.name = name;
    }

    public void AddOccurrences(int fileNum)
    {
        occurrences[fileNum] += 1;   
    }

    public boolean CompareName(String nameToComp)
    {
        if(nameToComp.contentEquals(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @Override
    public String toString()
    {
        String toRet = "Imie: " + name + "\n";

        for (int i =0; i < occurrences.length;i++) {
            toRet += "  file number: " + i + " || => " + occurrences[i] + "\n";
        }

        return toRet;
    }
}
