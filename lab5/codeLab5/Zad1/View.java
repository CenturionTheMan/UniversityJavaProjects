package codeLab5.Zad1;

import java.awt.Dimension;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View {
    private JFrame frame = new JFrame();
    private JPanel container = new JPanel();
    public CustomDataGetter dataGui;
    private JTextField signField;
    private JTextArea textArea;

    private int windowWidth;
    private int windowHeight;

    private ViewModel vm;

    public View(int windowWidth, int windowHeight) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;

        SetupApp();
        

        RunAppGui();
    }

    /**
     * Setups viewModel reference
     * @param vm ViewModel
     */
    public void SetViewModel(ViewModel vm)
    {
        this.vm = vm;
    }
    
    /**
     * Gui setup
     */
    private void SetupApp()
    {
        frame.setPreferredSize(new Dimension(windowWidth,windowHeight));
        

        container = new JPanel();
        container.setPreferredSize(new Dimension(windowWidth,windowHeight));
        frame.add(container);

        dataGui = new CustomDataGetter(windowWidth - 80,(int)(windowHeight/2));
        container.add(dataGui);

        JLabel signLabel = new JLabel("SIGN:");
        signLabel.setHorizontalAlignment(JLabel.RIGHT);
        signLabel.setPreferredSize(new Dimension(windowWidth/7,windowHeight/8));
        container.add(signLabel);

        signField = new JTextField();
        signField.setPreferredSize(new Dimension(windowWidth/5,windowHeight/8));
        signField.setHorizontalAlignment(JTextField.CENTER);
        container.add(signField);

        JButton solveButton = new JButton("SOLVE");
        solveButton.setPreferredSize(new Dimension(windowWidth/3,windowHeight/8));
        solveButton.addActionListener(e->{
            if(vm == null)
            {
                System.out.println("ViewModel is not assigned");
                return;
            }

            vm.UserSolveInput();
        });
        container.add(solveButton);

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(windowWidth * 8/10,windowHeight/5));
        textArea.setEditable(false);
        container.add(textArea);
    }

    /**
     * Tets text of JTextArea (result of calculations)
     * @param toSet
     */
    public void SetTextArea(String toSet)
    {
        textArea.setText(toSet);
    }

    /**
     * 
     * @return text in JTextField for operation sign
     */
    public String GetSignInput()
    {
        return signField.getText();
    }

    public void ShowErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(container, message);
    }

    /**
     * Finalize app setup and run app
     */
    private void RunAppGui()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (dim.getWidth()/2 - windowWidth / 2);
        int y = (int) (dim.getHeight()/2 - windowHeight / 2);
        frame.setLocation(x, y);

        frame.setTitle("Zadanie 1");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
