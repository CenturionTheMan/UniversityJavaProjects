package codeLab5.Zad2;


public class ViewModel {
    
    private CalculatorModel model;
    private View view;

    private boolean isCharInput = false;
    private boolean letterIsBigSize = false;

    public ViewModel(CalculatorModel model, View view) {
        this.model = model;
        this.view = view;

        view.SetupViewModel(this);
    }

    /**
     * Gets input from chars buttons
     * @param c which char is given
     * @param replace if true will replace char on last position, if false will add new one 
     */
    public void UserNewCharInput(char c, boolean replace)
    {
        if(letterIsBigSize) c -= 32;

        if(replace)
        {
            model.RemoveCharAtLastFromUserText();
        }
        model.AddLetterToDisplay(c);

        view.SetDisplayText(model.GetUserText());
        isCharInput = true;
    }

    /**
     * Gets input from math operations buttons 
     * @param operationType
     */
    public void UserNewOperationInput(OperationType operationType)
    {
        boolean hasThrown = false;
        switch (operationType) {
            case ADD:
                if(CheckIfMathOperationIsValid()) model.AddLettersOperation();
                isCharInput = false;
            break;
        
            case SUBTRACT:
                if(CheckIfMathOperationIsValid()) model.SubtractLettersOperation();
                isCharInput = false;
            break;

            case DIVIDE:
                if(CheckIfMathOperationIsValid()) model.DivideLettersOperation();
                isCharInput = false;
            break;

            case EQUAL:
                if(CheckIfMathOperationIsValid()) model.ResultLettersOperation();
                else
                {
                    hasThrown = true;
                }
                isCharInput = false;
            break;

            case LETTER_SIZE_CHANGE:
                letterIsBigSize = !letterIsBigSize;
                view.ChangeCharButtonsDisplayLetterSize(letterIsBigSize);
            break;

            case REMOVE_ALL:
                model.RemoveAllCharsFromUserText();
                isCharInput = false;
            break;

            case REMOVE_ONE:
                isCharInput = model.RemoveLastMeaningChar();
            break;
        }

        view.SetDisplayText(model.GetUserText());
        if(operationType == OperationType.EQUAL && !hasThrown) model.ResetAll();
    }

    /**
     * 
     * @return whether user is allowed to use math operation
     */
    private boolean CheckIfMathOperationIsValid()
    {
        if(!isCharInput) 
        {
            view.ShowErrorMessage("Can not use math operation without any char input!");
            return false;
        }
        return true;
    }


    public enum OperationType
    {
        ADD,
        SUBTRACT,
        DIVIDE,
        EQUAL,
        LETTER_SIZE_CHANGE,
        REMOVE_ONE,
        REMOVE_ALL
    }
}
