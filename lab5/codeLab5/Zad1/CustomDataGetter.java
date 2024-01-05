package codeLab5.Zad1;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomDataGetter extends JPanel {

    private int width,height;
    private JTextField a1,a2,a3,b1,b2,b3,c0,c1,c2,c3;

    public CustomDataGetter(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width,height));
        Setup();
    }

    /**
     * 
     * @return Input from JTextField's for as unknown
     */
    public String[] GetThreeAs()
    {
        return new String[]{a1.getText(),a2.getText(),a3.getText()};
    }

    /**
     * 
     * @return Input from JTextField's for bs unknown
     */
    public String[] GetThreeBs()
    {
        return new String[]{b1.getText(),b2.getText(),b3.getText()};
    }

    /**
     * 
     * @return Input from JTextField's for cs unknown
     */
    public String[] GetFourCs()
    {
        return new String[]{c0.getText(),c1.getText(),c2.getText(),c3.getText()};
    }

    /** 
     * Will setup Gui part for numbers input
    */
    private void Setup()
    {
        final int contentWidth = width - 10;

        final int thisWidth = contentWidth/4;
        final int thisWidthLast = contentWidth/6;
        final int labelWith = contentWidth/17;

        final int thisHeight = height/3 - (int)(height*0.03f);



        this.add(LabelText("a1:", labelWith, thisHeight));
        a1 = CreateTextFieldForInt(thisWidth,thisHeight); this.add(a1);

        this.add(LabelText("a2:", labelWith, thisHeight));
        a2 = CreateTextFieldForInt(thisWidth,thisHeight); this.add(a2);

        this.add(LabelText("a3:", labelWith, thisHeight));
        a3 = CreateTextFieldForInt(thisWidth,thisHeight); this.add(a3);

        this.add(LabelText("b1:", labelWith, thisHeight));
        b1 = CreateTextFieldForInt(thisWidth,thisHeight); this.add(b1);
        
        this.add(LabelText("b2:", labelWith, thisHeight));
        b2 = CreateTextFieldForInt(thisWidth,thisHeight); this.add(b2);
        
        this.add(LabelText("b3:", labelWith, thisHeight));
        b3 = CreateTextFieldForInt(thisWidth,thisHeight); this.add(b3);
        
        this.add(LabelText("c0:", labelWith, thisHeight));
        c0 = CreateTextFieldForInt(thisWidthLast,thisHeight); this.add(c0);
        
        this.add(LabelText("c1:", labelWith, thisHeight));
        c1 = CreateTextFieldForInt(thisWidthLast,thisHeight); this.add(c1);

        this.add(LabelText("c2:", labelWith, thisHeight));
        c2 = CreateTextFieldForInt(thisWidthLast,thisHeight); this.add(c2);
        
        this.add(LabelText("c3:", labelWith, thisHeight));
        c3 = CreateTextFieldForInt(thisWidthLast,thisHeight); this.add(c3);
    }

    /**
     * 
     * @param str text
     * @param width width
     * @param height height
     * @return Formated JLabel
     */
    private JLabel LabelText(String str, int width, int height)
    {
        var label = new JLabel(str);
        label.setPreferredSize(new Dimension(width,height));
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }

    /**
     * 
     * @param width
     * @param height
     * @return dormated JTextField
     */
    private JTextField CreateTextFieldForInt(int width, int height)
    {        
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(width,height));
        field.setHorizontalAlignment(JTextField.CENTER);

        return field;
    }

}
