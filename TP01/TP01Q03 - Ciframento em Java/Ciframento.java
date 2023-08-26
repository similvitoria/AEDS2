//TP01Q03 - Ciframento de Cesar em java
//@author: Vitória Símil Araújo

public class Ciframento {
    private static boolean isFim(String input) {
        return (input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    public static String execute(String entrie) {
        //call private function sending 3 as key
        return execute(entrie, 3);
    }

    private static String execute(String entrie, int key) {
        //data
        String cipher = "";
        for(char c : entrie.toCharArray() )
            cipher += (char)( c + key );

        return cipher;
    }

    public static void main(String[] args) {
        //data
        String entrie = "";  

        //read entries archive
        while(!isFim(entrie = MyIO.readLine())) {
            MyIO.println((execute(entrie)));
        }
    }
}
