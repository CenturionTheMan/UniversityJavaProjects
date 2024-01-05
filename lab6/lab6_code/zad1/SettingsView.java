package lab6_code.zad1;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SettingsView extends JDialog {
    
    private double maxSpeed;
    private double minSpeed;
    private int scale = 10000;
    MainCircle mCircle;

    private int width = 500;
    private int height = 500;

    private static boolean isOpen = false;

    /**
     * CTOR
     * @param parent
     * @param mCircle
     */
    public SettingsView(Window parent, MainCircle mCircle) {
        super(parent);

        this.mCircle = mCircle;
        maxSpeed = mCircle.GetMaxSpeed();
        minSpeed = mCircle.GetMinSpeed();

        this.setTitle("Speed settings");
        this.setDefaultCloseOperation(2);
        this.setSize(new Dimension(width,height));
        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));;
        JScrollPane scroll = new JScrollPane(panel);

        for (int i = 0; i < mCircle.GetMovingCirclesAmount(); i++) {
            var slider = CreateSlider(i);
            panel.add(slider);
            if(i != mCircle.GetMovingCirclesAmount() -1)
            {
                JPanel space = new JPanel();
                space.setPreferredSize(new Dimension(Math.round(width * 0.8f),5));
                panel.add(space);
            }
        }

        this.setContentPane(scroll);
        this.setVisible(true);
    }

    /**
     * Crates slider for given moving circle speed param
     * @param index of moving circle in array (int MainCircle)
     * @return object if success, null otherwise
     */
    private JSlider CreateSlider(int index)
    {
        int sliderWidth = Math.round(width * 0.8f);
        int sliderHeight = Math.round( (height*0.91f/mCircle.GetMovingCirclesAmount()));
        sliderHeight = (sliderHeight < height/7)? height/7 : sliderHeight;

        JSlider temp = new JSlider(JSlider.HORIZONTAL, GetScaledDoubleToInt(minSpeed), GetScaledDoubleToInt(maxSpeed), GetScaledDoubleToInt(mCircle.GetMovingCircleSpeed(index)));
        temp.setMajorTickSpacing(10);
        temp.setMinorTickSpacing(1);
        temp.setPaintTicks(true);
        temp.setPreferredSize(new Dimension(sliderWidth,sliderHeight));
        temp.setPaintLabels(true);
        temp.addChangeListener(l ->{
            mCircle.ChangeMovingCircleSpeed(index, GetScaledIntToDouble(temp.getValue()));
        });
        temp.setBackground(mCircle.GetMovingCircleColor(index));
        return temp;
    }

    /**
     * Scales int to double for slider
     * @param val
     * @return
     */
    private double GetScaledIntToDouble(int val)
    {
        double temp = val;
        return temp/scale;
    }

    /**
     * Scales double to int for slider
     * @param val
     * @return
     */
    private int GetScaledDoubleToInt(double val)
    {
        val *= scale;
        return (int)Math.round(val);
    }

    /**
     * Creates speed setting view and makes sure that only one is opened at time
     * @param parent
     * @param mCircle
     * @return object
     */
    public static SettingsView ShowSettingsView(Window parent, MainCircle mCircle)
    {
        if(isOpen) return null;
        isOpen = true;
        SettingsView sv = new SettingsView(parent,mCircle);

        sv.addWindowListener(new WindowListener(){

            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                
            }

            @Override
            public void windowClosed(WindowEvent e) {
                isOpen = false;
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }
            
        });

        return sv;
    }
}
