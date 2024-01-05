package lab3_code.zad2;

import java.util.ArrayList;
import java.util.List;

public class Litera {


    private char letter;
    private List<Imie> names = new ArrayList<Imie>();

    public char getLetter() { return letter; }
    public List<Imie> getNames() { return names; }

    public Litera(char letter, List<Imie> names) {
        this.letter = letter;
        this.names = names;
    }

    @Override
    public String toString() {
        String toRet = "Letter: " + letter + "\n";
        for (Imie imie : names) {
            toRet += "[\n" + imie.toString() + "]";
        }
        return toRet;
    }
}
