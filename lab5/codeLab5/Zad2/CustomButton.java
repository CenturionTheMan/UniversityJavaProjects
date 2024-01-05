package codeLab5.Zad2;


import javax.swing.JButton;

public class CustomButton extends JButton{
    
    private static CustomButton current = null;
    private char[] letters;
    private int currentChar = 0;
    private static boolean isNewButton = true;

    public CustomButton(char[] letters) {
        super();
        this.letters = letters;
    }

    /**
     * 
     * @return true if this button was not the last pressed
     */
    public boolean IsNewButton()
    {
        return isNewButton;
    }

    /**
     * 
     * @return Currently correct letter based of letters array and number of presses
     */
    public char GetLetter()
    {
        if(current != this)
        {
            isNewButton = true;
            currentChar = 0;
            current = this;
        }
        else
        {
            isNewButton = false;
        }

        if(letters == null || letters.length < 1) return '!';

        if(currentChar >= letters.length) currentChar = 0;

        return letters[currentChar++];
    }
}
