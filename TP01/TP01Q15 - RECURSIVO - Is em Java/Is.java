//TP01Q15 - is em java iterativo
//@Author: Vitoria Simil Araujo

class Is {
    private static boolean isEnd(String input) {
        return (input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    private static boolean isVowel(char c) {
        //data
        int vowels[] = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};

        for (  int i = 0; i < 10; i++ ) {
            if ( vowels[i] == (int) c ) return true;
        }

        return false;
    }

    //verify if a string is all composed by vowels
    private static String isVowel(int index, String input) {
        if ( index < input.length() ) {
            char c = input.charAt(index);

            if ( (isVowel(c)) ) {
                return isVowel(index + 1, input);
            }
            else {
                return "NAO ";
            }
        }
        else {
            return "SIM ";
        }
    }

    //verify if a String is all composed by consonants
    private static String isConsonant(int index, String input) {
        if ( index < input.length() ) {
            int c = (int)input.charAt(index);

            if ( (c >= 65 && c <= 90) && ( c >= 97 && c <= 121 ) && (isVowel(input.charAt(index)))) {
                return isConsonant(index + 1 , input);
            }
            else {
                return "NAO ";
            }
        }
        else {
            return "SIM ";
        }
    }

    //verify if a string is all composed by digits
    private static String isDigit(int index, String input) {
        if ( index < input.length() ) {
            int c = (int)input.charAt(index);

            if ( c >= 48 && c <= 57 ) {
                return isDigit(index + 1 , input);
            }
            else {
                return "NAO ";
            }
        }
        else {
            return "SIM ";
        }
    }

    //verify if a string is a real number  
    private static int isDouble(int index, String input) {  
        int separators = 0;

        if ( index < input.length() ) {
            int c = (int)input.charAt(index);
            char character = input.charAt(index);

            if ( c >= 48 && c <= 57 ) {
                return separators + isDouble(index + 1 , input);
            }
            else if ( character == ',' || character == '.') {
                separators++;
                return separators + isDouble(index + 1 , input);
            }
            else {
                return 2;
            }
        }
        else {
            return separators;
        }
    }

    //method to execute
    public static String execute(String input) {
        //data
        String answer = "";
        int separators = 0;
        
        answer += isVowel(0, input);
        answer += isConsonant(0, input);
        answer += isDigit(0, input);
        separators = isDouble(0, input);
        answer += (separators <= 1 ) ? "SIM" : "NAO";
        
        return answer;
    }

    public static void main(String[] args) {
        //data
        String input = "";

        //read the input archive
        input = MyIO.readLine();

        while ( !isEnd(input) ) {
            System.out.println(execute(input));
            input = MyIO.readLine();
        }
    }
}