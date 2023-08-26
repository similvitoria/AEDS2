//TP01Q09 - Arquivo em java
//@Author: Vitoria Simil Araujo

import java.io.RandomAccessFile;

class Arquivo {
    //method to execute
    public static void execute(int size) throws Exception {
        //data
        RandomAccessFile readFile = new RandomAccessFile("file.txt", "r");
        double number = 0.0;

        for(int i = size; i > 0; i--){
			    //pointing seek
            readFile.seek((9 * (i - 1)));
			      number = readFile.readDouble();

            if ( number - (int)number == 0 ) {
                MyIO.println((int)number);
            }
            else {
                MyIO.println(number);
            }
            
			  readFile.readByte();
		}

        //close file
		    readFile.close();
    }

    public static void main(String[] args) throws Exception {
        //data
        RandomAccessFile readFile = new RandomAccessFile("file.txt", "rw");
        int n = 0, size = 0;
        double number;

        //read the input archive
        n = MyIO.readInt(); //number of numbers to be read
        size = n;

        while ( n > 0 )
        {
            number = MyIO.readDouble();
            readFile.writeDouble(number);
            readFile.writeBytes("\n");
            n--;
            
        }
        
        //close file
        readFile.close();
        execute(size);
    }
}
