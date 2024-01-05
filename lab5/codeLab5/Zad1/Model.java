package codeLab5.Zad1;


public class Model {

    /**
     * Solves equation from exercise
     * @param a a1,a2,a3
     * @param b b1, b2, b3
     * @param c c0, c1, c2, c3
     * @param sign operation sign
     * @return result 
     */
    public String_Double SolveEquation(String[] a, String[] b, String[] c, String sign)
    {
        CustomNumber A = CalculateTensSum(a,"a");
        CustomNumber B = CalculateTensSum(b,"b");
        CustomNumber C = CalculateTensSum(c,"c");

        MathOperationType operation = MathOperationType.ParseOperationType(sign);

        if(!A.getName().contentEquals(""))
        {
            MathOperationType reactSign = switch(operation)
            {
                case ADD -> MathOperationType.SUBTRACT;
                case SUBTRACT -> MathOperationType.ADD;
                case MULTIPLY -> MathOperationType.DIVIDE;
                default -> MathOperationType.NONE;
            };

            var temp = HandleTailMathOperation(C,B,reactSign);
            var val = (temp - A.tail)/A.toDivide;
            return new String_Double(A.getName(),val);
        }
        else if(!B.getName().contentEquals(""))
        {
            MathOperationType reactSign = switch(operation)
            {
                case ADD -> MathOperationType.SUBTRACT;
                case SUBTRACT -> MathOperationType.SUBTRACT;
                case MULTIPLY -> MathOperationType.DIVIDE;
                default -> MathOperationType.NONE;
            };

            var temp = HandleTailMathOperation(C,A,reactSign);

            if(operation == MathOperationType.SUBTRACT) temp = -1 * temp;

            var val = (temp - B.tail)/B.toDivide;
            return new String_Double(B.getName(),val);
        }
        else if(!C.getName().contentEquals(""))
        {
            var temp = HandleTailMathOperation(A,B,operation);
            var val = (temp - C.tail)/C.toDivide;
            return new String_Double(C.getName(),val);
        }

        return null;
    }

    /**
     * 
     * @param one
     * @param two
     * @param sign 
     * @return result of math operation determined by sign 
     */
    private Double HandleTailMathOperation(CustomNumber one, CustomNumber two, MathOperationType sign)
    {
        Double res = null;
        switch (sign) {
            case ADD:
                res = (double)(one.tail + two.tail);
                break;
            case SUBTRACT:
                res = (double)(one.tail - two.tail);
                break;
            case MULTIPLY:
                res = (double)(one.tail * two.tail);
                break;
            case DIVIDE:
                res = (double)one.tail/(double)two.tail;
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        return res;
    }

    /**
     * Function calculates equation of type: a0 * 10^3 + a1 * 10^2 + a2 * 10^1 + a3 * 10^0
     * @param numsText strings for a0,a1,a2,a3...
     * @param letter letter of unknown (a,b,c)
     * @return if all as are known => CustomNumber with name ="" and toDivide =0, if one of them is missing => name = numsText[missingIndex], toDivide = 10^{numsText.length - missingIndex - 1} 
     */
    private CustomNumber CalculateTensSum(String[] numsText, String letter)
    {
        if(CheckIfInt(numsText))
        {
            var nums = ParseMultipleInt(numsText);
            Integer sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i] * (int)(Math.pow(10, nums.length - i - 1));
            }
            return new CustomNumber("",sum,0);
        }

        int tail = 0;
        int toDivide = 0;
        String name = "";
        for (int i = 0; i < numsText.length; i++) {
            if(CheckIfInt(numsText[i]))
            {
                tail += ParseToInt(numsText[i]) * (int)(Math.pow(10, numsText.length - i - 1));
            }
            else
            {
                toDivide = (int)(Math.pow(10, numsText.length - i - 1));
                name = numsText[i];
            }
        }
        return new CustomNumber(name, tail, toDivide);
    }

    /**
     * 
     * @param toParse
     * @return string parsed to Integer or null if can not do it
     */
    private Integer ParseToInt(String toParse)
    {
        try {
            return Integer.parseInt(toParse);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @param toParse
     * @return strings parsed to Integers or null if can not do it
     */
    private Integer[] ParseMultipleInt(String[] toParse)
    {
        Integer[] num = new Integer[toParse.length];
        try {
            for (int i = 0; i < toParse.length; i++) {
                num[i] = Integer.parseInt(toParse[i]);
            }
        } catch (Exception e) {
            return null;
        }
        return num;
    }

    /**
     * 
     * @param text
     * @return true if text can be parsed to int, false otherwise
     */
    public boolean CheckIfInt(String text)
    {
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 
     * @param texts
     * @return true if all strings can be parsed to integers, false otherwise
     */
    boolean CheckIfInt(String[] texts)
    {
        for (int i = 0; i < texts.length; i++) {
            if(CheckIfInt(texts[i]) == false) return false;
        }
        return true;
    }

    private class CustomNumber
    {
        public float toDivide = 0;
        public int tail = 0;
        String name;

        public CustomNumber(String name, int tail, int toDivide) {
            this.name = name;
            this.tail = tail;
            this.toDivide = toDivide;
        }

        public String getName()
        {
            return name;
        }
    }

    enum MathOperationType
    {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        NONE;

        /**
         * 
         * @param toParse
         * @return MathOperationType from parsed string
         */
        public static MathOperationType ParseOperationType(String toParse)
        {
            switch (toParse) {
                case "+":
                    return MathOperationType.ADD;
                case "-":
                    return MathOperationType.SUBTRACT;
                case "*":
                    return MathOperationType.MULTIPLY;
                case "/":
                    return MathOperationType.DIVIDE;
                default:
                    return MathOperationType.NONE;
            }
        }
    }
}
