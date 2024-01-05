package lab6_code.zad1;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

public class View extends JFrame {
    
    private int width, height;

    MainCircle mainCircle;
    JPanel container;

    /**
     * CTOR
     * @param width
     * @param height
     * @param circlesAmount how many circles will be in simulation
     */
    public View(int width, int height, int circlesAmount) {
        this.width = width;
        this.height = height;

        container = new JPanel();
        mainCircle = new MainCircle(new Color(0,126,167),circlesAmount,50,1000/120);
        mainCircle.setPreferredSize(new Dimension(width,Math.round(height * (0.84f))));
        mainCircle.setBackground(new Color(154,209,212));
        container.add(mainCircle);

        JButton playStop = new JButton("PLAY / STOP");
        playStop.setFont(new FontUIResource("",0,20));
        playStop.setPreferredSize(new Dimension(Math.round(width * 0.40f), Math.round(height * (0.085f))));
        playStop.addActionListener(e ->{
            OnRunSimulation();
        });
        container.add(playStop);

        JButton settingsButton = new JButton("EDIT SPEED");
        settingsButton.setFont(new FontUIResource("",0,20));
        settingsButton.setPreferredSize(new Dimension(Math.round(width * 0.40f), Math.round(height * (0.085f))));
        settingsButton.addActionListener(e ->{
            OnChangeSettings();
        });
        container.add(settingsButton);
        
        Setup();   
    }

    /**
     * Basic frame setup
     */
    private void Setup()
    {
        this.setPreferredSize(new Dimension(width,height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Zadanie 1"); 
        this.setContentPane(container);
        this.pack();
        this.setVisible(true);     
    }

    /**
     * Run / Stop simulation button press action
     */
    public void OnRunSimulation()
    {
        if(mainCircle != null)
        {
            if(!mainCircle.RunSimulation())
            {
                mainCircle.StopSimulation();
            }
        }
    }

    /**
     * Change speed settings button press action
     */
    public void OnChangeSettings()
    {
        SettingsView.ShowSettingsView(this, mainCircle);
    }
}
