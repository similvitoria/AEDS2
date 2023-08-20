//TP01Q04 - Alteração aleatória em Java
//@author: Vitória Símil Araújo

import java.util.Random;

public class AlteracaoAleatoria {
    private static boolean isFim(String entrie) {
        return entrie.equals("FIM");
    }

    public static String execute(String entrie, Random generator) {
        //get two random characters
        char first = ((char)('a' + (Math.abs ( generator.nextInt ( ) ) % 26 ) ));
        char second = ((char)('a' + (Math.abs ( generator.nextInt ( ) ) % 26 ) ));

        return execute(entrie, first, second);
    }

    private static String execute(String entrie, char first, char second) {
        //data
        String changedString = "";

        for ( char c : entrie.toCharArray()) {
            if ( c == first ) 
                changedString += second;
            else 
                changedString += c;
        } 

        return changedString;
    }

    public static void main(String[] args) {
        //data
        String entrie = "";  

        //create generator
        Random generator = new Random();
        generator.setSeed(4);

        //read entries archive
        while(!isFim(entrie = MyIO.readLine())) {
            MyIO.println((execute(entrie,generator)));
        }
    }
}
