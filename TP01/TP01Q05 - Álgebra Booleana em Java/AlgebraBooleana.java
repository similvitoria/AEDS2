//TP01Q05 - Algebra Booleana em java
//@Author: Vitoria Simil Araujo

public class AlgebraBooleana {
    //method to verify if a string equals 0
    public static boolean isEnd(String input) {
        return (input.charAt(0) == '0');
    }
    
    public static String removeSpaces(String equation) {
        return equation.replace(" ", "");
    }

    //method to perform a boolean "and" operation
    public static String and(String equation) {
        for ( char c : equation.toCharArray() ) {
            if ( c == '0' ) return "0";
        }

        return "1";
    }

    //method to perform a boolean "or" operation
    public static String or(String equation) {
        for ( char c : equation.toCharArray() ) {
            if ( c == '1' ) return "1";
        }

        return "0";
    }

    //method to perform a boolean "not" operation
    public static String not(String equation) {
        return (equation.charAt(4) == '0') ? "1" : "0";
    }

    //method to execute
    public static void execute(String equation) {
        //data
        int size = equation.length() - 1;
        int position = 0;
        String placeHolder = ""; //holds a string to be replaced on the original equation

        for ( int i = size; i >= 0; i-- ) {
            
            while ( equation.charAt(i) != '(' ) {
                i--;
            }

            position = ( equation.charAt(i - 1) == 'r' ) ? i - 2 : i - 3;
            
            while ( equation.charAt(position) != ')' ) {
                placeHolder += equation.charAt(position);
                position++;    
            }

            placeHolder += ')';

            if ( placeHolder.charAt(0) == 'n') {
                equation = equation.replace(placeHolder, not(placeHolder));
            }
            else if ( placeHolder.charAt(0) == 'a') {
                equation = equation.replace(placeHolder, and(placeHolder));
            }
            else {
                equation = equation.replace(placeHolder, or(placeHolder));
            }
            
            placeHolder = "";
            size = equation.length() - 1;
            i = size;
        }

        System.out.println(equation);
    }
    
    public static void main(String[] args) {
        //data
        String input = "";
        String equation = "";

        //read the input archive
        input = MyIO.readLine();

        while ( !isEnd(input) ) {
            //get a new equation without spaces
            input = removeSpaces(input);

            //get the number of variables
            int n = ( input.charAt(0) == '2' ) ? 2 : 3;

            //assign value to variables
            input = input.replace('A', input.charAt(1));
            input = input.replace('B', input.charAt(2));

            if ( n == 3 ) 
                input = input.replace('C', input.charAt(3));
            
            //separate the equation from the variable part
            equation = input.substring(n + 1, input.length());
            execute(equation);

            //read new line
            input = MyIO.readLine();
        }
    }
}