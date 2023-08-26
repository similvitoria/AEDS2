//TP01Q13 - Ciframento de Cesar
//@Author: Vitoria Simil Araujo

class Ciframento {
    private static boolean isEnd(String input) {
        return (input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    //method to execute
    public static String execute(String input) {
        //@value: 3 = key 
        return getCipher(3, 0, input);
    }

    //get cipher
    private static String getCipher(int key,int index, String input) {
        //data
        String cipher = "";

        if ( index < input.length() ) {
            cipher += (char) (input.charAt(index) + key);
            return cipher + getCipher(key, index + 1, input);
        }
        else {
            return cipher;
        }
    }

    public static void main(String[] args) {
        //data
        String input = "";
        
        //read the input archive
        input = MyIO.readLine();
        
        while( !isEnd(input) ) {
          MyIO.println(execute(input));
          input = MyIO.readLine();
        }
    }
}