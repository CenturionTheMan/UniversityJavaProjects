package lab4_code.zad1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;



import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class View1 {

    /**
     * GENERAL GUI GENERATING
     */


    public JTextField textField;
    private JList<JListItem> list;
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JPanel container;
    private JFrame frame;
    public PiChart chart;

    private int appXSize; 
    private int appYSize;
    private int textFieldFontSize;
    private int listFontSize;
    private int buttonsXSize;
    private int buttonsYSize;

    private ViewModel1 viewModel;

    public View1(ViewModel1 viewModel ,int appXSize, int appYSize, int textFieldFontSize, int listFontSize, int buttonsXSize, int buttonsYSize) {
        this.viewModel = viewModel;
        this.appXSize = appXSize;
        this.textFieldFontSize = textFieldFontSize;
        this.appYSize = appYSize;
        this.listFontSize = listFontSize;
        this.buttonsXSize = buttonsXSize;
        this.buttonsYSize = buttonsYSize;
        
        SetupGui();
    }

    
    /**
     * Updates Gui
     */
    public void UpdateGui()
    {
        container.revalidate();
        container.repaint();
    }

    /**
     * Show message on screen
     * @param message
     */
    public void ShowMessageDialog(String message)
    {
        JOptionPane.showMessageDialog(container, message);
    }

    /**
     * Updates number in JList (gui)
     * @param numbers
     */
    public void UpdateNumbersList(Integer[] numbers, Color[] colors)
    {
        if(numbers.length != colors.length) return;

        JListItem[] items = new JListItem[numbers.length];

        for (int i = 0; i < items.length; i++) {
            items[i] = new JListItem(numbers[i], colors[i]);
        }

        list.setListData(items);

        UpdateGui();
    }

    private void SetupGui()
    {
        frame = SetupFrame();

        container = new JPanel();
        frame.add(container);

        var leftSide = CreateLeftSideOfGui();
        chart = new PiChart((int)(appXSize*0.75),appYSize );
        container.add(leftSide);
        container.add(chart);


        //run gui
        RunGui(frame);
    }

    private JFrame SetupFrame()
    {
        JFrame frame = new JFrame();

        frame = new JFrame("Zadanie 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var screenX = screenSize.getWidth();
        var screenY = screenSize.getHeight();

        frame.setLocation((int)(screenX/2-appXSize/2), (int)(screenY/2-appYSize/2));

        return frame;
    }

    private JPanel CreateLeftSideOfGui()
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(appXSize/4,appYSize)); //size
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); //layout top -> bottom

        textField = new JTextField();
        textField.setFont(new Font("",0,textFieldFontSize));
        textField.setPreferredSize(new Dimension(appXSize/2,appYSize/5));
        textField.setSize(new Dimension(appXSize/2,appYSize/5));
        var buttonsHolder = SetupButtonsHolder();

        list = new JList<JListItem>();
        list.setFont(new Font("",0,listFontSize));
        list.setCellRenderer(new CustomJListRenderer(appXSize/8 - 6,40));

        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(11);
        JScrollPane listViewPort = new JScrollPane(list);
        listViewPort.setPreferredSize(new Dimension(appXSize/4,appYSize));

        panel.add(textField);
        panel.add(buttonsHolder);
        panel.add(listViewPort);
        

        return panel;
    }

    private JPanel SetupButtonsHolder()
    {
        JPanel buttonsHolder = new JPanel();
        buttonsHolder.setLayout(new BoxLayout(buttonsHolder, BoxLayout.X_AXIS));

        addButton = CreateButton(buttonsXSize,buttonsYSize,"ADD");
        addButton.addActionListener(e -> {
            viewModel.AddTextFieldInput();
        });

        editButton = CreateButton(buttonsXSize,buttonsYSize,"EDIT");
        editButton.addActionListener(e ->{
            viewModel.EditNumberInList(list.getSelectedIndex());
        });

        removeButton = CreateButton(buttonsXSize,buttonsYSize,"REMOVE");
        removeButton.addActionListener(e -> {
            viewModel.RemoveNumberFromList(list.getSelectedIndex());
        });

        buttonsHolder.add(addButton);
        buttonsHolder.add(editButton);
        buttonsHolder.add(removeButton);


        return buttonsHolder;
    }

    private JButton CreateButton(int xSize, int ySize, String name)
    {
        JButton button = new JButton();
        button.setText(name);
        return button;
    }


    private void RunGui(JFrame frame)
    {
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}
