class Celula {
    // Atributos
    public Celula prox;  // Referência para a próxima célula na fila
    public int elemento; // Valor armazenado na célula

    public Celula() {
        this(0);
    }

    /**
     * Construtor que permite especificar um valor ao criar a célula.
     *
     * @param elemento O valor a ser armazenado na célula.
     */
    public Celula(int elemento) {
        this.elemento = elemento;
        prox = null;
    }
}

class Fila {
    // Atributos
    private Celula primeiro, ultimo;  // Referências para o primeiro e último elementos na fila

    public Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Insere um elemento no final da fila.
     *
     * @param elemento O valor a ser inserido na fila.
     */
    public void inserir(int elemento) {
        ultimo.prox = new Celula(elemento);
        ultimo = ultimo.prox;
    }

    /**
     * Remove e retorna o elemento no início da fila.
     *
     * @return O valor removido do início da fila.
     * @throws Exception Se a fila estiver vazia.
     */
    public int remover() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Fila vazia");
        Celula tmp = primeiro.prox;
        int resposta = tmp.elemento;
        primeiro.prox = null;
        primeiro = tmp;
        tmp = null;
        return resposta;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
}

public class FilaFlexivel {
    public static void main(String[] args) throws Exception {
        Fila fila = new Fila();
        fila.inserir(3);
        fila.inserir(5);
        fila.inserir(7);
        fila.remover();
        fila.mostrar();
    }
}
