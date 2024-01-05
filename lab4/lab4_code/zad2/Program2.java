package lab4_code.zad2;

public class Program2 {

    static final int APP_HEIGHT = 700;
    static final int APP_WIDTH = 600;

    public static void main(String[] args) {
        ViewModel2 vm = new ViewModel2();
        View2 view = new View2(vm,APP_WIDTH,APP_HEIGHT);
        
        vm.setView(view);
    }
}
