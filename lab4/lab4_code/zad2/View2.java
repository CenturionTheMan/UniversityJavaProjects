package lab4_code.zad2;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View2 {

    private ViewModel2 vm;

    private JFrame frame;
    private JPanel container;
    private BarChart barChart;
    private AppTopSelectionUI appTop;

    private int height, width;

    public View2(ViewModel2 vm, int width,int height) {
        this.vm = vm;

        this.height = height;
        this.width = width;

        frame = new JFrame();
        container = new JPanel();

        Setup();  

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Zadanie 2");

        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var screenX = screenSize.getWidth();
        var screenY = screenSize.getHeight();

        frame.setLocation((int)(screenX/2-width/2), (int)(screenY/2-height/2));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void Setup()
    {
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        appTop = new AppTopSelectionUI(this,10,new Dimension(width,height * 1/10));
        
        barChart = new BarChart(10,width,height * 9/10);

        container.add(appTop);
        container.add(barChart);
        frame.add(container);        
    }

    /**
     * Adds bar to GUI bar chart
     * @param index
     * @param val
     * @param color
     */
    public void AddBarToChart(int index, Integer val, Color color)
    {
        barChart.AddBar(index, val, color);
    }

    /**
     * Removes Bar from gui bar chart
     * @param index
     */
    public void RemoveBarFromChart(int index)
    {
        barChart.RemoveBar(index);
    }
    
    /**
     * Will pass input from App JTextField x JCheckBox structures forward to viewmodel
     * @param index
     * @param text
     * @param isChecked
     * @return true if input is correct
     */
    public boolean PassInputFromAppTop(int index, String text,boolean isChecked)
    {
        return vm.OnCheckBoxPressed(index, text, isChecked);
    }

    public void ShowMessage(String message)
    {
        JOptionPane.showMessageDialog(container,message);
    }
}