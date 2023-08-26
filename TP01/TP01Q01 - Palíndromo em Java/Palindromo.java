//TP01Q01 - Palindromo em java
//@author: Vitória Símil Araújo

public class Palindromo {
    private static boolean isFim(String input) {
        return (input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    public static boolean isPalindrome(String entrie) {
        if ( entrie.length() == 0 || entrie.length() == 1 ) 
            return true;
        if ( entrie.charAt(0) == entrie.charAt(entrie.length() - 1)) 
            return isPalindrome(entrie.substring(1, entrie.length() - 1));
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        //data
        String entrie = "";  

        //read entries archive
        while(!isFim(entrie = MyIO.readLine())) {
            MyIO.println((isPalindrome(entrie) == true)? "SIM" : "NAO");
        }
    }
}