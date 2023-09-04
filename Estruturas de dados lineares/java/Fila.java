class FIFO {
    //dados
    int[] array;
    int n;

    //construtor
    public FIFO() {
        this(100);
    }

    /**
     * 
     * @param tamanho
     */
    public FIFO(int tamanho) {
        this.array = new int[tamanho];
        n = 0;
    }

    /**
     * 
     * @param elemento
     * @throws Exception
     */
    public void inserir(int elemento) throws Exception {
        if ( n == array.length ) {
            throw new Exception("array cheio");
        }

        array[n] = elemento;
        n++;
    }

    /**
     * 
     * @return
     * @throws Exception
     */ 
    public int remover() throws Exception{
        if ( n == 0 ) {
            throw new Exception("array vazio");
        }

        int resposta = array[0];
        n--;

        for(int i = 0; i < n ; i++) {
            array[i] = array[i + 1];
        }

        return resposta;
    }

    public void mostrar() {
        System.out.print("[");
        for(int i = 0; i < n; i++ ) {
            System.out.print(" "+ array[i]);
        }

        System.out.println(" ]");
    }
}



public class Fila {
    public static void main(String[] args) throws Exception {
        //dados
        FIFO fila = new FIFO(5);

        //exemplos de comandos
        /*fila.inserir(0);
        fila.inserir(1);
        fila.inserir(2);
        fila.inserir(3);
        fila.inserir(4);
        fila.mostrar();
        fila.remover();
        fila.remover();
        fila.mostrar();*/
    }
}
