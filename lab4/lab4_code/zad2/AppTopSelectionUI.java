package lab4_code.zad2;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;


public class AppTopSelectionUI extends JPanel {
    
    final float percentHeightOfTextField = 0.5f;
    int choiceAmount;
    View2 view;

    /**
     * CTOR
     * @param view
     * @param choiceAmount
     * @param size
     */
    public AppTopSelectionUI(View2 view, int choiceAmount, Dimension size) {
        this.choiceAmount = choiceAmount;
        this.view = view;

        this.setPreferredSize(size);

        int prefWidth =(choiceAmount == 0)? 0 : (int)(this.getPreferredSize().width/(choiceAmount +1));
        for (int i = 0; i < choiceAmount; i++) {
            this.add(CreatePiece(prefWidth, i));
        }
    }

    /**
     * Creates JTextField X JCheckBox structure
     * @param width
     * @param index
     * @return
     */
    private JPanel CreatePiece(int width, int index)
    {
        JPanel res = new JPanel();
        res.setLayout(new BoxLayout(res, BoxLayout.PAGE_AXIS));
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(width,(int)(getPreferredSize().height * percentHeightOfTextField)));
        textField.setFont(new Font("",0,16));

        JCheckBox box = new JCheckBox();
        box.addActionListener(e ->{
            boolean actionRes = view.PassInputFromAppTop(index, textField.getText() ,box.isSelected());

            if(box.isSelected())
            {
                if(!actionRes)
                {
                    box.setSelected(false);
                }
                else
                    textField.setEnabled(false);
            }
            else
            {
                textField.setEnabled(true);
            }

            
        });        
        box.setAlignmentX(0.5f);

        res.add(textField);
        res.add(box);

        return res;
    }


}
