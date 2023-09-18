import java.util.Random;

public class Mergesort {
    //variáveis globais
    private static int[] array = new int[10];
    public static int arraySize = array.length;

    private static void aleatorio() {
        Random random = new Random();
        for(int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(100);
        }
    }

    private static void intercalar(int esquerda, int meio, int direita) {
        //dados
        int nEsq = (meio + 1) - esquerda;
        int nDir = direita - meio;
        int iEsq = 0, iDir = 0, i;

        //criar subarrays
        int[] arrayEsq = new int[nEsq + 1];
        int[] arrayDir = new int[nDir + 1];

        arrayEsq[nEsq] = arrayDir[nDir] = 0x7FFFFFFF;

        //montar subarrays
        for (iEsq = 0; iEsq < nEsq; iEsq++) {
            arrayEsq[iEsq] = array [esquerda+iEsq];
        }

        for (iDir = 0; iDir < nDir; iDir++){
            arrayDir[iDir] = array[(meio+1)+iDir];
        }

        //Intercalacao propriamente dita
        for (iEsq = iDir = 0, i = esquerda; i <= direita; i++){
            array[i] = (arrayEsq[iEsq] <= arrayDir[iDir]) ? arrayEsq[iEsq++] : arrayDir[iDir++];
        }
    }

    public static void mergesort(int esquerda, int direita) {
        if(esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergesort(esquerda, meio);
            mergesort(meio+1, direita);
            intercalar(esquerda, meio, direita);
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
        mergesort(0, arraySize - 1);

        //mostrar array ordenado
        System.out.println("Array ordenado: ");
        mostrar();
    }
}