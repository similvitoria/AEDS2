class Lista {
    //dados
    int[] array;
    int n;

    //construtor
    public Lista() {
        this.array = new int[100];
        n = 0;
    }

    /**
     * 
     * @param tamanho
     */
    public Lista(int tamanho) {
        this.array = new int[tamanho];
        n = 0;
    }

    /**
     * 
     * @param elemento
     */
    public void inserirNoInicio(int elemento) throws Exception {
        if ( n >= array.length ) {
            throw new Exception("array cheio");
        }

        //levar elementos para o fim do array
        for ( int i = n; i > 0; i--) {
            array[i] = array[i-1];
        }  

        array[0] = elemento;
        n++;
    }

    /**
     * 
     * @param elemento
     */
    public void inserirNoFim(int elemento) throws Exception {
        if ( n >= array.length ) {
            throw new Exception("array cheio");
        }

        array[n] = elemento;
        n++;
    }

    /**
     * 
     * @param elemento
     * @param pos
     */
    public void inserir(int elemento, int pos) throws Exception {
        if ( n >= array.length || pos < 0 || pos > n ) {
            throw new Exception("array cheio ou posição inválida");
        }

        for(int i = n; i > pos; i++ ) {
            array[i] = array[i - 1];
        }

        array[pos] = elemento;
        n++;
    }

    /**
     * 
     * @return
     */
    public int removerNoInicio() throws Exception {
        if ( n == 0) {
            throw new Exception("array vazio");
        }

        int elemento = array[0];

        //levar elemento para o inicio do array
        for (int i = 0; i < (n-1); i++) {
            array[i] = array[i+1];
        }

        n--;

        return elemento;
    }

    /**
     * 
     * @return
     */
    public int removerNoFim() throws Exception {
        if ( n == 0 ) {
            throw new Exception("array vazio");
        }
        
        return array[--n];
    }

    /**
     * 
     * @param pos
     * @return
     */
    public int remover(int pos) throws Exception {
        if ( n <= 0 || pos < 0 || pos >= n) {
            throw new Exception("array vazio ou posição inválida");
        }

        int elemento = array[pos];
        n--;

        for( int i = pos; i < n; i++) {
            array[i] = array[i+1];
        }

        return elemento;
    }

    public void mostrar() {
        System.out.print("[");
        for(int i = 0; i < n; i++ ) {
            System.out.print(" "+ array[i]);
        }

        System.out.println(" ]");
    }
}

public class ListaLinear {
    public static void main(String[] args) throws Exception {
        //dados
        final Lista lista = new Lista(3);
        
        //exemplos de comandos
        /*lista.inserirNoFim(1);
        lista.inserirNoInicio(2);
        lista.remover(1);
        lista.removerNoFim();
        lista.inserir(1, 0);
        lista.mostrar();*/
    }
}