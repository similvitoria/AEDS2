import java.util.Random;

public class Quicksort {
    //variáveis globais
    private static int[] array = new int[10];
    public static int arraySize = array.length;

    private static void aleatorio() {
        Random random = new Random();
        for(int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(100);
        }
    }

    private static void swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void quicksort(int esq, int dir) {
        //dados
        int i = esq, j = dir, meio = (esq + dir)/2;

        while ( i <= j ) {
            while ( array[i] < array[meio] ) {
                i++;
            }
            while ( array[j] > array[meio] ) {
                j--;
            }

            if ( i <= j ) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if ( esq < j ) quicksort(esq, j);
        if ( i < dir) quicksort(i, dir);
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
        quicksort(0, arraySize - 1);

        //mostrar array ordenado
        System.out.println("Array ordenado: ");
        mostrar();
    }
}
