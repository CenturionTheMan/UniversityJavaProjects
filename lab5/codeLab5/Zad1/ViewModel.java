package codeLab5.Zad1;

public class ViewModel {
    
    private Model model;
    private View view;
    
    public ViewModel(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Uses input from View class, passes it to Model for calculations, and prints result using View class
     */
    public void UserSolveInput()
    {
        var a = view.dataGui.GetThreeAs();
        var b = view.dataGui.GetThreeBs();
        var c = view.dataGui.GetFourCs();
        var sign = view.GetSignInput();

        int missingAmount = 0;

        for (String str : a) {
            if(str.contentEquals(""))
            {
                view.ShowErrorMessage("All fields need to be assigned");
                return;
            }

            if(!model.CheckIfInt(str))
            {
                if(str.contains(" "))
                {
                    view.ShowErrorMessage("Unknown part of equation can not contain spaces");
                    return;
                }

                missingAmount ++;
            }
        }

        for (String str : b) {
            if(str.contentEquals(""))
            {
                view.ShowErrorMessage("All fields need to be assigned");
                return;
            }

            if(!model.CheckIfInt(str))
            {
                if(str.contains(" "))
                {
                    view.ShowErrorMessage("Unknown part of equation can not contain spaces");
                    return;
                }

                missingAmount ++;
            }
        }

        for (String str : c) {
            if(str.contentEquals(""))
            {
                view.ShowErrorMessage("All fields need to be assigned");
                return;
            }

            if(!model.CheckIfInt(str))
            {
                if(str.contains(" "))
                {
                    view.ShowErrorMessage("Unknown part of equation can not contain spaces");
                    return;
                }

                missingAmount ++;
            }
        }

        if(missingAmount == 0 || missingAmount > 1)
        {
            view.ShowErrorMessage("There have to be exactly one unknown");
            return;
        }

        if(!(sign.contentEquals("+") || sign.contentEquals("-") || sign.contentEquals("*")))
        {
            view.ShowErrorMessage("Sign input can contain only - or + or *");
            return;
        }

        var result = model.SolveEquation(a,b,c,sign);

        view.SetTextArea(" A [" + sign + "] B = C\n A = [" + a[0] + "] *10^2 + [" + a[1] + "] *10 + [" + a[2] + "]\n B = [" + b[0] + "] *10^2 + [" + b[1] + "] *10 + [" + b[2] + "]\n C = [" + c[0] + "] *10^3 + [" + c[1] + "] *10^2 + [" + c[2] + "] *10 + [" + c[3] + "]\n " + result);

        System.out.println(result);
    }
}
