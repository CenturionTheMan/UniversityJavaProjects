package lab4_code.zad1;


public class Program1 {

    //Setup params
    private static final int textFieldFontSize = 40;
    private static final int listFontSize = 20;
    private static final int appXSize = 800;
    private static final int appYSize = 590;
    private static final int buttonsXSize = 200;
    private static final int buttonsYSize = 200;

    public static void main(String[] args) {
        Model1 model;
        ViewModel1 vm;
        View1 view;

        vm = new ViewModel1();
        model = new Model1();
        view = new View1(vm, appXSize, appYSize, textFieldFontSize, listFontSize, buttonsXSize, buttonsYSize);
        vm.SetModel(model);
        vm.SetView(view);
    }
}
