class Fila {
    //dados
    int[] array;
    int primeiro, ultimo;

    //construtores
    public Fila() {
        this(5);
    }

    /**
     * 
     * @param tamanho
     */
    public Fila(int tamanho) {
        this.array = new int[tamanho + 1];
        primeiro = ultimo = 0;
    }
    
    /**
     * 
     * @param elemento
     * @throws Exception
     */
    public void inserir(int elemento) throws Exception {
        if ( (ultimo + 1) % array.length == primeiro ) {
            throw new Exception("array cheio");
        }

        array[ultimo] = elemento;
        ultimo = (ultimo + 1) % array.length;
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public int remover() throws Exception {
        if ( ultimo == primeiro ) {
            throw new Exception("array vazio");
        }

        int resposta = array[primeiro];
        primeiro = (primeiro + 1 ) % array.length;

        return resposta;
    }

    public void mostrar() {
        int i = primeiro;

        System.out.print("[ ");
        while( i!= ultimo ) {
            System.out.print(array[i] + " ");
            i = (i + 1) % array.length;
        }

        System.out.println("]");
    }
}

public class FilaCircular {
    public static void main(String[] args)  throws Exception {
        //dados
        Fila fila = new Fila();

        //exemplos de comando
        /*fila.inserir(1);
        fila.inserir(3);
        fila.inserir(5);
        fila.inserir(7);
        fila.inserir(9);
        fila.remover();
        fila.remover();
        fila.inserir(4);
        fila.inserir(6);
        fila.remover();
        fila.inserir(8);
        fila.mostrar();*/
    }
}
