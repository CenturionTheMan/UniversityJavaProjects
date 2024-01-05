package lab6_code.zad2;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame{
    
    JPanel container;
    LeafView leafView;

    int width, height;

    /**
     * CTOR
     * @param width
     * @param height
     * @param leafThread
     */
    public MainView(int width, int height, LeafThread leafThread) {
        super();

        this.height = height;
        this.width = width;
        
        container = new JPanel();
        container.setBackground(Color.darkGray);

        leafView = new LeafView(leafThread,1000/30);
        leafView.setPreferredSize(new Dimension(width,height));

        Setup();

        container.add(leafView);
        
        this.pack();
        this.setVisible(true);
    }

    /**
     * Will setup frame
     */
    private void Setup()
    {
        this.setContentPane(container);
        this.setPreferredSize(new Dimension(width,height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Zadanie 2"); 
    }
}
