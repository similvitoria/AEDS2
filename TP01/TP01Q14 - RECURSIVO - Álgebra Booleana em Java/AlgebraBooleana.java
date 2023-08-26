//TP01Q14 - Algebra Booleana Recursivo
//@Author: Vitoria Simil Araujo

class AlgebraBooleana {
    //method to verify if a string equals 0
    private static boolean isEnd(String input) {
        return (input.charAt(0) == '0');
    }

    //method to remove white spaces from a string
    private static String removeSpaces(String equation) {
        return equation.replace(" ", "");
    }

    //method to perform a boolean "and" operation
    private static String and(String equation) {
        for ( char c : equation.toCharArray() ) {
            if ( c == '0' ) return "0";
        }

        return "1";
    }

    //method to perform a boolean "or" operation
    private static String or(String equation) {
        for ( char c : equation.toCharArray() ) {
            if ( c == '1' ) return "1";
        }

        return "0";
    }

    //method to perform a boolean "not" operation
    private static String not(String equation) {
        return (equation.charAt(4) == '0') ? "1" : "0";
    }

    //method to execute
    public static String execute(String equation) {
        return calculate(equation.length() - 1, equation);
    }

    //method to execute
    private static String calculate(int index, String equation) {
        if ( index > 0 ) {
            String placeHolder = "";
            int position = 0;

            while ( equation.charAt(index) != '(' ) {
                index--;
            }

            position = ( equation.charAt(index - 1) == 'r' ) ? index - 2 : index - 3;
            
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
            
            return calculate(equation.length() - 1, equation);
        }
        else {
            return equation;
        }
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
            System.out.println(execute(equation));

            //read new line
            input = MyIO.readLine();
        }
    }
}