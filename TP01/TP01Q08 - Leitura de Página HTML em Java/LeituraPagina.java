//TP01Q08 - Leitura de Pagina HTML
//@Author: Vitoria Simil Araujo

import java.io.*;
import java.net.*;

class LeituraPagina {
    //method to verify if a string equals FIM
    private static boolean isEnd(String input) {
        return (input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    //method to verify if a char equals a consonant
    private static boolean isConsonant(int code) {
        return ((code >= 97 && code <= 122) && (code != 65 && code != 97) && (code != 69 && code != 101) && (code != 73 && code != 105) && (code != 79 && code != 111) && (code != 85 && code != 117));
    }

    //method to verify if a string equals "<br>"
    private static boolean isBr(String input) {
        return (input.charAt(0) == '<' && input.charAt(1) == 'b' && input.charAt(2) == 'r' && input.charAt(3) == '>');
    }
    
    //method to verify if a string equals <table>
    private static boolean isTable(String input) {
        return (input.charAt(0) == '<' && input.charAt(1) == 't' && input.charAt(2) == 'a' && input.charAt(3) == 'b' && input.charAt(4) == 'l' && input.charAt(5) == 'e' && input.charAt(6) == '>');
    }

    //get htmlURL
    private static String getHtml(String input) throws IOException {
        URL url = new URL(input);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "", answer = "";

        try {
            while((line = br.readLine()) != null){
                answer += line + "\n";
            }
        }
        catch(MalformedURLException mue) {
            mue.printStackTrace();
        }
        catch(IOException ioe ) {
            ioe.printStackTrace();
        }

        is.close();

        return answer;
    }


    //method to execute
    public static void execute(String pageAdress, String pageName) throws IOException {
        //data
        String htmlURL = getHtml(pageAdress);

        //consonats and tags and ã && õ
        int consonants = 0, table = 0, br = 0, lowerA5 = 0, lowerO5 = 0;

        //vowels a-u
        int lowerA = 0, lowerE = 0, lowerI = 0, lowerO = 0, lowerU = 0;

        //vowels á-ú
        int lowerA2 = 0, lowerE2 = 0, lowerI2 = 0, lowerO2 = 0, lowerU2 = 0;

        //vowels à-ù
        int lowerA3 = 0, lowerE3 = 0, lowerI3 = 0, lowerO3 = 0, lowerU3 = 0;

        //vowels â-û
        int lowerA4 = 0, lowerE4 = 0, lowerI4 = 0, lowerO4 = 0, lowerU4 = 0;

        for ( int i = 0; i < htmlURL.length(); i++ ) {
            int charCode = (int)htmlURL.charAt(i);
            
            //verifying <table> and <br>
            if ( charCode == 60 ) 
            {
                String tag = "";
                //storing the next 7 characters to verify if it equals <br> or <table>
                for ( int j = 0; j < 7; j++ ) {
                    tag += htmlURL.charAt(i + j);
                }
                if ( isTable(tag) ) 
                {
                    table++;
                    i += 7; //add 7 positions to the loop to skip the chars that complete <table>
                }
                else if ( isBr (tag))
                {
                    br++;
                    i += 4; //add 4 positions to the loop to skip the chars that complete <br>
                }
            }
            //verifying consonants and normal vowels
            else if (( charCode >= 65 && charCode <= 90 ) || ( charCode >= 97 && charCode <= 122))
            {
                if ( charCode == 97 ) //a
                    lowerA++;
                else if ( charCode == 101 )//e
                    lowerE++;
                else if ( charCode == 105 )//i
                    lowerI++;
                else if ( charCode == 111 )//o
                    lowerO++;
                else if ( charCode == 117 )//u
                    lowerU++;
                else if (isConsonant(charCode))
                consonants++;
                
            }
            //verifying other cases
            else
            {   //á - ú
                if ( charCode == 225 ) lowerA2++; //á
                if ( charCode == 233 ) lowerE2++; //é
                if ( charCode == 237 ) lowerI2++; //í
                if ( charCode == 243 ) lowerO2++; //ó
                if ( charCode == 250 ) lowerU2++; //ú

                //à - ù
                if ( charCode == 224 ) lowerA3++; //à
                if ( charCode == 232 ) lowerE3++; //è
                if ( charCode == 236 ) lowerI3++; //ì
                if ( charCode == 242 ) lowerO3++; //ò
                if ( charCode == 249 ) lowerU3++; //ù

                //â-û
                if ( charCode == 226 ) lowerA4++; //â
                if ( charCode == 234 ) lowerE4++; //ê
                if ( charCode == 238 ) lowerI4++; //î
                if ( charCode == 244 ) lowerO4++; //ô
                if ( charCode == 251 ) lowerU4++; //û

                //ã && õ
                if ( charCode == 227 ) lowerA5++; 
                if ( charCode == 245 ) lowerO5++;
            }
        }

        //show answer
        MyIO.println("a(" + lowerA + ") e(" + lowerE + ") i(" + lowerI + ") o(" + lowerO + ") u(" + lowerU + ") á(" + lowerA2 + ") é(" + lowerE2 + ") í(" + lowerI2 + ") ó(" + lowerO2 + ") ú(" + lowerU2 + ") à(" + lowerA3 + ") è(" + lowerE3 + ") ì(" + lowerI3 +
		") ò(" + lowerO3 + ") ù(" + lowerU3 + ") ã(" + lowerA5 + ") õ(" + lowerO5 + ") â(" + lowerA4 + ") ê(" + lowerE4 + ") î(" + lowerI4 + ") ô(" + lowerO4 + ") û(" + lowerU4 + ") consoante(" + consonants + ") <br>(" + br + ") <table>(" +
		table + ") " + pageName);
    }

    public static void main(String[] args) throws IOException {
        //data
        String pageName = "", pageAdress = "";

        //read the input archive
        pageName = MyIO.readLine();
        while ( !isEnd(pageName) ) {
            pageAdress = MyIO.readLine();
            execute(pageAdress, pageName);
            //read new line
            pageName = MyIO.readLine();
        }
    }

}