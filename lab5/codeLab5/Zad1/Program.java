package codeLab5.Zad1;


/**
 * Program1
 */
public class Program {

    static final int APP_WIDTH = 400;
    static final int APP_HEIGHT = 400;

    public static void main(String[] args) {
        View view = new View(APP_WIDTH,APP_HEIGHT);
        Model model = new Model();
        ViewModel vm = new ViewModel(model, view);
        view.SetViewModel(vm);
    }

}