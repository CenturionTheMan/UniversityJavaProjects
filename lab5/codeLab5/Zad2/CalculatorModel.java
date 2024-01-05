package codeLab5.Zad2;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CalculatorModel {
    private StringBuilder userDisplayText; //working text


    public CalculatorModel() {
        ResetAll();
    }

    /**
     * Resets model class
     */
    public void ResetAll()
    {
        userDisplayText = new StringBuilder();
    }

    /**
     * 
     * @return string composed of user inputs
     */
    public String GetUserText()
    {
        return userDisplayText.toString();
    }

    /**
     * Adds letter at last position to working text
     * @param letter
     * @return working text
     */
    public String AddLetterToDisplay(char letter)
    {
        userDisplayText.append(letter);
        return userDisplayText.toString();
    }

    /**
     * Adds + sign to working text
     * @return working text
     */
    public String AddLettersOperation()
    {
        userDisplayText.append(" + ");
        return userDisplayText.toString();
    }

    /**
     * Adds - sign to working text
     * @return working text
     */
    public String SubtractLettersOperation()
    {
        userDisplayText.append(" - ");
        return userDisplayText.toString();
    }

    /**
     * Adds / sign to working text
     * @return working text
     */
    public String DivideLettersOperation()
    {
        userDisplayText.append(" / ");
        return userDisplayText.toString();
    }

    /**
     * 
     * @param main
     * @param toRemove
     * @return string {main} with removed {toRemove} substrings 
     */
    private String Subtract(String main, String toRemove)
    {
        return main.replace(toRemove, "");
    }

    /**
     * 
     * @param main
     * @param toAdd
     * @return string {main} with added {toAdd}
     */
    private String Add(String main, String toAdd)
    {
        return main + toAdd;
    }

    /**
     * 
     * @param main
     * @param toDivide
     * @return The biggest string which is present in both {main} and {toDivide}
     */
    private String Divide(String main, String toDivide)
    {
        Set<Character> h1 = new LinkedHashSet<Character>();
        Set<Character> h2 = new LinkedHashSet<Character>();
        for(int i = 0; i < main.length(); i++)                                            
        {
            h1.add(main.charAt(i));
        }
        for(int i = 0; i < toDivide.length(); i++)
        {
            h2.add(toDivide.charAt(i));
        }
        h1.retainAll(h2);
        String res = h1.stream().map(c -> String.valueOf(c)).collect(Collectors.joining());
        return res;
    }

    /**
     * Calculates result of working text and appends it to itself with = sign
     * @return Working text
     */
    public String ResultLettersOperation()
    {
        String worker = userDisplayText.toString();

        if(worker == "") return "";

        var keys = worker.split(" ");

        String main = keys[0];
        for (int i = 1; i < keys.length; i += 2) {
            switch (keys[i]) {
                case "+":
                    main = Add(main, keys[i+1]);
                    break;
            
                case "-":
                    main = Subtract(main, keys[i+1]);
                    break;

                case "/":
                    main = Divide(main, keys[i+1]);
                    break;

                default:
                    System.out.println("ERROR");
                    break;
            }
        }

        if(main.contentEquals("") || main == null) main = "_"; 

        userDisplayText.append(" = " + main);
        return userDisplayText.toString();
    }

    /**
     * Removes last non space char from working text
     * @return true if currently chars at last position in working text is not mathematical sign, false otherwise
     */
    public boolean RemoveLastMeaningChar()
    {
        if(userDisplayText.length() == 0) return false;
        
        if(userDisplayText.charAt(userDisplayText.length()-1) == ' ')
        {
            userDisplayText.delete(userDisplayText.length()-3, userDisplayText.length());
            return true;
        }
        else
        {
            userDisplayText.deleteCharAt(userDisplayText.length()-1);
            
            if(userDisplayText.length() > 0 && userDisplayText.charAt(userDisplayText.length()-1) != ' ')
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Simply removes char ta last position from working text
     * @return working text
     */
    public String RemoveCharAtLastFromUserText()
    {
        if(userDisplayText.length() > 0)
        {
            userDisplayText.deleteCharAt(userDisplayText.length()-1);         
        }

        return userDisplayText.toString();
    }

    /**
     * Removes all chars from working text
     * @return working text
     */
    public String RemoveAllCharsFromUserText()
    {
        ResetAll();
        return userDisplayText.toString();
    }

    enum CurrentOperation
    {
        NONE,
        ADD,
        SUBTRACT,
        DIVIDE
    }
}
