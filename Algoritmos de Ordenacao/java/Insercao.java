import java.util.Random;

public class Insercao {
    //variáveis globais
    public static int[] array = new int[10];
    public static int arraySize = array.length;

    public static void aleatorio() {
        Random random = new Random();
        for(int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(100);
        }
    }

    public static void insercao() {
        //dados
        int j = 0, tmp = 0;

        for ( int i = 1; i < arraySize; i++) {
            tmp = array[i];
            j = i - 1;

            while((j >= 0) && array[j] > tmp ) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = tmp;
        }
    }

    private static void mostrar() {
        System.out.print("[");

        for (int i = 0; i < arraySize; i++) {
            System.out.print(" "  + array[i]);
        }

        System.out.println(" ]");
    }

    public static void main(String[] args) {
        //criar novo array de randoms
        aleatorio();

        //mostrar random array
        System.out.println("Array desordenado: ");
        mostrar();

        //ordenar array
        insercao();
        
        //mostrar array ordenado();
        System.out.println("Array ordenado: ");
        mostrar();
    }
}
