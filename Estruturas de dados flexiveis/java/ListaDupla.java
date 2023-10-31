class Celula {
    // Atributos
    public Celula ant, prox; // Referências para a célula anterior e seguinte na lista
    public int elemento; // O valor armazenado na célula

    public Celula() {
        this(0);
    }

    /**
     * Construtor que inicializa a célula com um valor específico.
     *
     * @param elemento O valor a ser armazenado na célula.
     */
    public Celula(int elemento) {
        this.elemento = elemento;
        ant = prox = null;
    }
}

class Lista {
    // Atributos
    private Celula primeiro, ultimo; // Referências para o primeiro e último elementos da lista
    private int tamanho; // O tamanho atual da lista

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
        tamanho = 0;
    }

    /**
     * Insere um elemento no início da lista.
     *
     * @param elemento O valor a ser inserido.
     */
    public void inserirInicio(int elemento) {
        Celula tmp = new Celula(elemento);
        tmp.prox = primeiro.prox;
        tmp.ant = primeiro;
        primeiro.prox = tmp;
        if(primeiro == ultimo) ultimo = tmp;
        else tmp.prox.ant = tmp;
        tmp = null;
        tamanho++;
    }

    /**
     * Insere um elemento no fim da lista.
     *
     * @param elemento O valor a ser inserido.
     */
    public void inserirFim(int elemento) {
        ultimo.prox = new Celula(elemento);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
        tamanho++;
    }

    /**
     * Insere um elemento em uma posição específica na lista.
     *
     * @param pos      A posição onde o elemento deve ser inserido.
     * @param elemento O valor a ser inserido.
     * @throws Exception Se a posição for inválida.
     */
    public void inserir(int pos, int elemento) throws Exception {
        if( pos < 0 || pos > tamanho ) throw new Exception("Posição inválida");
        else if ( pos == 0) inserirInicio(elemento);
        else if ( pos == tamanho ) inserirFim(elemento);
        else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox );
            Celula tmp = new Celula(elemento);
            tmp.prox = i.prox;
            tmp.ant = i;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
            tamanho++;
        }
    }

    /**
     * Remove o elemento no início da lista e retorna seu valor.
     *
     * @return O valor do elemento removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public int removerInicio() throws Exception {
        if(primeiro == ultimo) throw new Exception("Lista vazia");
        Celula tmp = primeiro.prox;
        int resposta = tmp.elemento;
        primeiro.prox = tmp.prox;
        primeiro.prox.ant = primeiro;
        tmp.prox = tmp.ant = tmp = null;
        tamanho--;
        return resposta;
    }

    /**
     * Remove o elemento no fim da lista e retorna seu valor.
     *
     * @return O valor do elemento removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public int removerFim() throws Exception {
        if(primeiro == ultimo) throw new Exception("Lista vazia");
        int resposta = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = ultimo.prox = null;
        tamanho--;
        return resposta;
    }

    /**
     * Remove o elemento em uma posição específica na lista e retorna seu valor.
     *
     * @param pos A posição do elemento a ser removido.
     * @return O valor do elemento removido.
     * @throws Exception Se a lista estiver vazia ou a posição for inválida.
     */
    public int remover(int pos) throws Exception {
        int resposta = 0;

        if(primeiro == ultimo || pos < 0 || pos >= tamanho ) throw new Exception("Lista vazia ou posição inválida");
        else if (pos == 0) removerInicio();
        else if (pos == tamanho - 1) removerFim();
        else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            resposta = tmp.elemento;
            i.prox = tmp.prox;
            i.prox.ant = i;
            tmp.ant = tmp.prox = tmp = i = null;
            tamanho--;
        }
        return resposta;
    }

    public void mostrar() {
        System.out.print("[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox ){
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

}

public class ListaDupla {
    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();

        lista.inserirInicio(1);
        lista.inserirInicio(2);
        lista.inserirInicio(3);
        lista.inserirFim(4);
        lista.inserir(2, 0);
        lista.removerInicio();
        lista.removerFim();
        lista.remover(1);
        lista.mostrar();
    }
}