package codeLab5.Zad2;

public class Program {
    public static void main(String[] args) {
        var calculatorModel = new CalculatorModel();
        var view = new View(750,800);
        new ViewModel(calculatorModel,view);        
    }
}
