package lab3_code.zad1;
public class Item
    {
        private int id;
        private String name;
        private float price;

        public String GetStringId(){
            return String.format("%04d",id);
        }
        public int GetIntId(){return id;}

        public String GetName(){return name;}

        public float GetPrice(){return price;}

        public Item(String id, String name, String price) throws Exception {
            this.id = ConvertIdStringToInt(id);
            this.name = name;
            this.price = ConvertStringToFloat(price);
        }

        public Item(int id, String name, String price) throws Exception {
            this.id = id;
            this.name = name;
            this.price = ConvertStringToFloat(price);
        }

        private float ConvertStringToFloat(String val) throws Exception
        {
            try {
                return Float.parseFloat(val);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                throw e;
            }
        }

        private int ConvertIdStringToInt(String code) throws Exception
        {
            try {
                return Integer.parseInt(code);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                throw e;
            }
        }

        /**
         * Goes through char array until it find char other than '*'
         * @param beginIndex begin search index
         * @param arr arr to search in
         * @return index of element, -1 if there in no found
         */
        private int FindFirstNoStartChar(int beginIndex, char[] arr)
        {
            for (int k = beginIndex; k < arr.length; k++) {
                if(arr[k] != '*') return k;
            }

            return -1;
        }

        /**
         * Implements pseudo regex action, where:
         *  -> '*' means 0 or any appearance of any character
         *  -> '?' replace any character
         * @param input pseudo regex input
         * @return true if input matches Item name, false otherwise
         */
        boolean PseudoREGEXCompare(String input)
        {
            if(input.contains("*") && input.contains("?"))
            {
                char[] inputChars = input.toCharArray();
                char[] thisName = GetName().toCharArray();

                int adder = 0;
                for (int i = 0; i < inputChars.length; i++) {
                    char in = inputChars[i];

                    if(adder + i >= thisName.length) return false;
                    int j = i + adder;

                    char th = thisName[j];

                    if(th == in || in == '?')
                    {
                        continue;
                    }
                    if(in == '*')
                    {
                        if(inputChars.length == i +1) continue;
                        else
                        {
                            int next = FindFirstNoStartChar(i + 1, inputChars);
                            char nextChar = inputChars[next];
                            
                            if(nextChar == '?')
                            {
                                adder = next - j - 1;
                                continue;
                            }
                            else
                            {
                                int nextThis = GetName().indexOf(nextChar, j);
                                
                                if(nextThis == -1) return false;

                                adder = nextThis - j - 1;
                                continue;
                            }
                        }
                    }
                    else if(th != in && in != '?')
                    {
                        return false;
                    }
                    
                }
                return true;

            }
            else if(input.contains("*"))
            {
                if(input.charAt(0) != '*')
                {
                    for (int i = 0; i < input.length(); i++) {
                        if(input.charAt(i) == '*') break;
                        if(input.charAt(i) != GetName().charAt(i)) return false;
                    }
                }

                if(input.charAt(input.length()-1) != '*')
                {
                    for (int i = 0; i < input.length(); i++) {
                        if(input.charAt(input.length() -1 - i) == '*') break;
                        if(input.charAt(input.length() -1 - i) != GetName().charAt(GetName().length() -1 - i)) return false;
                    }
                }

                var keys = input.split("\\*");

                var prevPos = 0;
                int prevLength = 0;

                for (String key : keys) {
                    
                    int pos = GetName().indexOf(key,prevPos + prevLength);
                    
                    if(pos == -1) return false;

                    prevPos = pos;
                    prevLength = key.length();
                }

                return true;
            }
            else if (input.contains("?"))
            {
                return CompareStringsWithSkippingQuestionMark(input,GetName());
            }
            else
            {
                return input.contentEquals(GetName());
            }
        }

        /**
         * Compares two strings but treats '?' sing as any char
         * @param one 
         * @param two
         * @return true is strings matches, false otherwise
         */
        private boolean CompareStringsWithSkippingQuestionMark(String one, String two)
        {
            if(one.length() != two.length()) return false;
            for (int i = 0; i < one.length(); i++) {
                if(one.charAt(i) == '?' || two.charAt(i) == '?') continue;
                if(one.charAt(i) != two.charAt(i)) return false;
            }

            return true;
        }

        
        public boolean CompareByName(String name)
        {
            return PseudoREGEXCompare(name);
        }

        @Override
        public String toString() {
            return GetStringId() + ";" + GetName() + ";" +GetPrice();
        }
    }