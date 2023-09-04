import java.util.Random;

public class Selecao {
    //variáveis globais
    public static int[] array = new int[100];
    public static int arraySize = array.length;

    public static void aleatorio() {
        Random random = new Random();
        for(int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(1000);
        }
    }

    public static void mostrar() {
      System.out.print("[");

      for (int i = 0; i < arraySize; i++) {
        System.out.print(" "  + array[i]);
      }

      System.out.println("]");
    }

    private static void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void selecao() {
        for ( int i = 0; i < (arraySize - 1); i++) {
            int menor = i;
            for ( int j = (i + 1); j < arraySize; j++) {
                if ( array[j] < array[menor]) {
                    menor = j;
                }
            }
            swap(menor, i);
        }
    }

   public static void main(String[] args) {
        //criar novo array de randoms
        aleatorio();

        //mostrar random array
        System.out.println("Array desordenado: ");
        mostrar();

        //ordenar array  
        selecao();

        System.out.println("Array ordenado: ");
        //mostra array ordenado
        mostrar();
    }
}
