package codeLab5.Zad2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;

import codeLab5.Zad2.ViewModel.OperationType;

public class View extends JFrame{

    private final Color BUTTON_TEXT_COLOR = new Color(48, 71, 94);
    private final Color BUTTON_OPERATION_COLOR = new Color(15, 52, 96);
    private final Color BUTTON_OPERATION_TEXT_MANIPULATION_COLOR = new Color(15, 52, 96);
    private final Color BUTTON_OPERATION_RESULT_COLOR = new Color(240, 84, 84);
    private final Color BACKGROUND_COLOR = new Color(34, 40, 49);
    private final Color TEXT_COLOR = new Color(221, 221, 221);

    private JPanel container = new JPanel();
    private JTextField display = new JTextField();
    private ArrayList<CustomButton> charButtons = new ArrayList<>();

    private final int width, height;

    private ViewModel viewModel = null;

    /**
     * CTOR => some gui setup
     * @param width
     * @param height
     */
    public View(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.setPreferredSize(new Dimension(width,height));
        this.setResizable(false);
        this.setTitle("WORD CALCULATOR");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (dim.getWidth()/2 - width / 2);
        int y = (int) (dim.getHeight()/2 - width / 2);
        this.setLocation(x, y);

        container.setBackground(BACKGROUND_COLOR);
        this.add(container);

        display.setPreferredSize(new Dimension(width * 9/10,height/6));
        display.setEditable(false);
        display.setFont(new FontUIResource("",FontUIResource.PLAIN,display.getPreferredSize().height * 1/3));
        display.setBackground(BACKGROUND_COLOR);
        display.setForeground(TEXT_COLOR);
        display.setMargin(new InsetsUIResource(5, 5, 5, 5));
        container.add(display);

        SetButtons();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Assigns ViewModel reference
     * @param viewModel
     */
    public void SetupViewModel(ViewModel viewModel)
    {
        this.viewModel = viewModel;
    }

    /**
     * Setup buttons
     */
    private void SetButtons()
    {
        CreateNewOperationButton(OperationType.REMOVE_ONE,"CE",BUTTON_OPERATION_TEXT_MANIPULATION_COLOR);
        CreateNewOperationButton(OperationType.REMOVE_ALL,"C",BUTTON_OPERATION_TEXT_MANIPULATION_COLOR);
        CreateNewOperationButton(OperationType.LETTER_SIZE_CHANGE,"D/M",BUTTON_OPERATION_TEXT_MANIPULATION_COLOR);


        CreateNewOperationButton(OperationType.ADD,"+",BUTTON_OPERATION_COLOR);

        CreateNewCustomButton(new char[]{'a','b','c'});
        CreateNewCustomButton(new char[]{'d','e','f'});
        CreateNewCustomButton(new char[]{'g','h','i'});

        CreateNewOperationButton(OperationType.SUBTRACT,"-",BUTTON_OPERATION_COLOR);

        CreateNewCustomButton(new char[]{'j','k','l'});
        CreateNewCustomButton(new char[]{'m','n','o'});
        CreateNewCustomButton(new char[]{'p','g','r'});

        CreateNewOperationButton(OperationType.DIVIDE,"/",BUTTON_OPERATION_COLOR);

        CreateNewCustomButton(new char[]{'s','t','u','v'});
        CreateNewCustomButton(new char[]{'w','x','y','z'});

        var resButton =CreateNewOperationButton(OperationType.EQUAL,"=",BUTTON_OPERATION_RESULT_COLOR);
        resButton.setPreferredSize(new Dimension(resButton.getPreferredSize().width*2,resButton.getPreferredSize().height));
    }

    /**
     * Creates new buton for math operations and adds it to container
     * @param operation 
     * @param buttonText
     * @param bgColor
     * @return this new button
     */
    public JButton CreateNewOperationButton(OperationType operation, String buttonText, Color bgColor)
    {
        final int buttonWidth = width * 3/14;
        final int buttonHeight = height * 2/11;
        CustomButton button = new CustomButton(null);
        button.addActionListener(e->{
            button.GetLetter();
            viewModel.UserNewOperationInput(operation);
        });
        button.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        button.setText(buttonText);
        button.setForeground(TEXT_COLOR);
        button.setBackground(bgColor);
        button.setFont(new FontUIResource("",FontUIResource.PLAIN, buttonHeight/4));
        container.add(button);

        return button;
    }

    /**
     * Creates new buton for char input and adds it to container and charButtons - List
     * @param chars chars for given button
     */
    public void CreateNewCustomButton(char[] chars)
    {
        final int buttonWidth = width * 3/14;
        final int buttonHeight = height * 2/11;
        CustomButton button = new CustomButton(chars);
        button.addActionListener(e->{
            viewModel.UserNewCharInput(button.GetLetter(), !button.IsNewButton());
        });
        button.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        button.setText(String.valueOf(chars));
        button.setFont(new FontUIResource("",FontUIResource.PLAIN, buttonHeight/4));
        button.setForeground(TEXT_COLOR);
        button.setBackground(BUTTON_TEXT_COLOR);
        container.add(button);
        charButtons.add(button);
    }

    /**
     * changes letter size on buttons
     * @param isBig
     */
    public void ChangeCharButtonsDisplayLetterSize(boolean isBig)
    {
        if(isBig)
        {
            charButtons.forEach(b -> b.setText(b.getText().toUpperCase()));
        }
        else
        {
            charButtons.forEach(b -> b.setText(b.getText().toLowerCase()));
        }

        container.revalidate();
        container.repaint();
    }

    /**
     * Shows error message in new window
     * @param message
     */
    public void ShowErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(container,message);
    }

    /**
     * Sets text displayed in app
     * @param text
     */
    public void SetDisplayText(String text)
    {
        display.setText(text);
    }
}
