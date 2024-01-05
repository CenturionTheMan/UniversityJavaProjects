package codeLab5.Zad1;

public class String_Double {
    public String name;
    public Double val;

    public String_Double(String name, Double val) {
        this.name = name;
        this.val = val;
    }

    @Override
    public String toString() {
        return name + " = " + val;
    }
}
