class Celula {
    // Atributos
    public Celula prox;  // Referência para a próxima célula na lista
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

class Lista {
    // Atributos
    private Celula primeiro, ultimo;  // Referências para o primeiro e último elementos na lista
    private int tamanho;  // Tamanho atual da lista

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
        tamanho = 0;
    }

    /**
     * Insere um elemento no início da lista.
     *
     * @param elemento O valor a ser inserido no início da lista.
     */
    public void inserirInicio(int elemento) {
        Celula tmp = new Celula(elemento);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) ultimo = tmp;
        tmp = null;
        tamanho++;
    }

    /**
     * Insere um elemento no final da lista.
     *
     * @param elemento O valor a ser inserido no final da lista.
     */
    public void inserirFim(int elemento) {
        ultimo.prox = new Celula(elemento);
        ultimo = ultimo.prox;
        tamanho++;
    }

    /**
     * Insere um elemento em uma posição específica na lista.
     *
     * @param pos      A posição em que o elemento será inserido.
     * @param elemento O valor a ser inserido na lista.
     * @throws Exception Se a posição for inválida.
     */
    public void inserir(int pos, int elemento) throws Exception {
        if (pos < 0 || pos > tamanho) throw new Exception("Posição inválida.");
        else if (pos == 0) inserirInicio(elemento);
        else if (pos == tamanho) inserirFim(elemento);
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(elemento);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
            tamanho++;
        }
    }

    /**
     * Remove e retorna o elemento no início da lista.
     *
     * @return O valor removido do início da lista.
     * @throws Exception Se a lista estiver vazia.
     */
    public int removerInicio() throws Exception {
        if (primeiro == ultimo) throw new Exception("Lista vazia.");
        Celula tmp = primeiro.prox;
        int resposta = tmp.elemento;
        primeiro.prox = null;
        primeiro = tmp;
        tmp = null;
        tamanho--;
        return resposta;
    }

    /**
     * Remove e retorna o elemento no final da lista.
     *
     * @return O valor removido do final da lista.
     * @throws Exception Se a lista estiver vazia.
     */
    public int removerFim() throws Exception {
        if (primeiro == ultimo) throw new Exception("Lista vazia.");
        int resposta = ultimo.elemento;
        Celula i;
        for (i = primeiro.prox; i.prox != ultimo; i = i.prox);
        ultimo = i;
        i = ultimo.prox = null;
        tamanho--;
        return resposta;
    }

    /**
     * Remove e retorna o elemento em uma posição específica na lista.
     *
     * @param pos A posição do elemento a ser removido.
     * @return O valor removido da posição especificada.
     * @throws Exception Se a lista estiver vazia ou a posição for inválida.
     */
    public int remover(int pos) throws Exception {
        int resposta = 0;
        if (primeiro == ultimo || pos < 0 || pos >= tamanho) throw new Exception("Lista vazia ou posição inválida.");
        else if (pos == 0) removerInicio();
        else if (pos == tamanho - 1) removerFim();
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            resposta = tmp.elemento;
            i.prox = tmp.prox;
            tmp = tmp.prox = i = null;
            tamanho--;
        }
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

public class ListaSimples {
    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();
        lista.inserirFim(5);
        lista.inserirFim(7);
        lista.mostrar();
        lista.removerInicio();
        lista.mostrar();
        lista.inserirInicio(5);
        lista.inserirInicio(3);
        lista.mostrar();
        lista.removerFim();
        lista.mostrar();
        lista.inserirFim(7);
        lista.mostrar();
        lista.inserir(2, 6);
        lista.inserir(1, 4);
        lista.removerInicio();
        lista.removerInicio();
        lista.remover(1);
        lista.mostrar();
    }
}
