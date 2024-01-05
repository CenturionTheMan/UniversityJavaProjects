package lab4_code.zad1;

import java.math.BigInteger;


public class ViewModel1 {
    private Model1 model;
    private View1 view;

    public void SetModel(Model1 model)
    {
        this.model = model;
    }

    public void SetView(View1 view)
    {
        this.view = view;
    }

    /**
     * Gets input form JTextField and return it as int
     * @return
     */
    private Integer GetNumberFromInput()
    {
        BigInteger input = null;
        try {
            input = new BigInteger(view.textField.getText());
        } catch (Exception e) {
            input = BigInteger.valueOf(-1);
        }

        if(input.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 )
        {
            view.ShowMessageDialog("Given input is to big!");
            input = BigInteger.valueOf(-1);
        }
        else if(input.intValue() < 0)
        {
            view.ShowMessageDialog("Given input must be a positive integer!");
        }

        view.textField.setText("");
        return input.intValue();
    }

    /**
     * Adds input form JTextField to List
     */
    public void AddTextFieldInput()
    {
        Integer res = GetNumberFromInput();
        var color = model.GetColor();

        if(res < 0) return;

        model.AddNumber(res,color);

        view.UpdateNumbersList(model.GetUsedNumbersAsArray(), model.GetUsedColorsAsArray());

        view.chart.AddNumber(res, color);

        view.UpdateGui();
    }

    /**
     * Removes number in given position from list
     * @param index
     */
    public void RemoveNumberFromList(int index)
    {
        if(index == -1) return;

        model.RemoveNumberByIndex(index);

        view.UpdateNumbersList(model.GetUsedNumbersAsArray(), model.GetUsedColorsAsArray());

        view.chart.RemoveNumberByIndex(index);

        view.UpdateGui();
    }

    /**
     * Replaces number in given position with input in JTextField
     * @param index
     */
    public void EditNumberInList(int index)
    {
        if(index == -1) return;

        Integer number = GetNumberFromInput();
        if(number < 0) return;

        model.EditNumberByIndex(index, number);
        
        view.UpdateNumbersList(model.GetUsedNumbersAsArray(), model.GetUsedColorsAsArray());

        view.chart.EditNumberByIndex(index, number);

        view.UpdateGui();
    }
}
