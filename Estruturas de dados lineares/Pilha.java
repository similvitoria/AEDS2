class FILO {
    //dados
    int[] array;
    int n;

    //construtor
    public FILO() {
        this.array = new int[100];
        n = 0;
    }

    /**
     * 
     * @param tamanho
     */
    public FILO(int tamanho) {
        this.array = new int[tamanho];
        n = 0;
    }

    /**
     * 
     * @param elemento
     */
    public void empilhar(int elemento) {
        if ( n == array.length )
        {
            System.out.println("array cheio");
            return;
        }
        
        array[n] = elemento;
        n++;
    }

    /**
     * 
     * @return
     */
    public int desempilhar() {
        if( n == 0 ) {
            System.out.println("array vazio");
            return - 1;
        }
        return array[--n];
    }

    public void mostrar() {
        System.out.print("[");
        for(int i = 0; i < n; i++ ) {
            System.out.print(" "+ array[i]);
        }

        System.out.println(" ]");
    }
}

public class Pilha {
    public static void main(String[] args) {
        //dados
        final FILO pilha = new FILO(3);

        //exemplos de comando
        /*pilha.empilhar(1);
        pilha.empilhar(5);
        pilha.desempilhar();
        pilha.mostrar();*/
    }
}