//TP01Q06 - is em java
//@author: Vitória Símil Araújo

public class Is {
    private static boolean isFim(String input) {
        return (input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    private static boolean isVowel(char character) {
        //data
        int vowels[] = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};

        for (  int i = 0; i < 10; i++ ) {
            if ( vowels[i] == (int) character ) 
                return true;
        }

        return false;
    }

    //verify if a string is all composed by vowels
    private static String isVowel(String entrie) {
        for ( char c : entrie.toCharArray() )
            if ( !isVowel(c) )
                return "NAO";

        return "SIM";
    }

    private static boolean isLetter(char character) {
        //data
        int code = (int) character;
        return (( code >= 65 && code <= 90 ) || ( code >= 97 && code <= 121 ));
    }

    //verify if a String is all composed by consonants
    private static String isConsonant(String entrie) {
        for ( char c : entrie.toCharArray() )
            if ( !isLetter(c) || (isVowel(c)))
                return "NAO";

        return "SIM";
    }

    private static boolean isDigit(char character) {
        //data
        int code = (int)character;
        return ( code >= 48 && code <= 57);
    }

    //verify if a string is all composed by digits
    private static String isDigit(String entrie) {
        for ( char c : entrie.toCharArray() )
            if ( !isDigit(c) )
                return "NAO";

        return "SIM";
    }

    //verify if a string is a real number  
    private static String isDouble(String input) {
        //data
        int separators = 0;

        for ( char c : input.toCharArray() ) {
            if ( !isDigit(c)) {
                if ( c == ',' || c == '.' ) separators++;
                else return "NAO";
            }
        }

        return ( separators == 0 || separators == 1 ) ? "SIM" : "NAO";
    }

    public static void execute(String entrie) {
        //data
        String x1 = "", x2 = "", x3 = "", x4 = "";

        x1 = isVowel(entrie);
        x2 = isConsonant(entrie);
        x3 = isDigit(entrie);
        x4 = isDouble(entrie);

        System.out.println(x1 + " " + x2 + " " + x3 + " " + x4);

    }

    public static void main(String[] args) {
         //data
        String entrie = "";  

        //read entries archive
        while(!isFim(entrie = MyIO.readLine())) {
            execute(entrie);
        }
    }
}
