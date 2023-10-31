class Celula {
    // Atributos
    public Celula prox;  // Referência para a próxima célula na pilha
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

class Pilha {
    // Atributos
    private Celula topo; // Referência para o elemento no topo da pilha

    public Pilha() {
        topo = null;
    }

    /**
     * Insere um elemento no topo da pilha.
     *
     * @param elemento O valor a ser inserido na pilha.
     */
    public void inserir(int elemento) {
        Celula tmp = new Celula(elemento);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    /**
     * Remove e retorna o elemento no topo da pilha.
     *
     * @return O valor removido do topo da pilha.
     * @throws Exception Se a pilha estiver vazia.
     */
    public int remover() throws Exception {
        if (topo == null)
            throw new Exception("Pilha vazia.");
        int resposta = topo.elemento;
        Celula tmp = topo.prox;
        topo.prox = null;
        topo = tmp;
        tmp = null;
        return resposta;
    }


    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = topo; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
}


public class PilhaFlexivel {
    public static void main(String[] args) throws Exception {
        Pilha pilha = new Pilha();
        pilha.inserir(3);
        pilha.inserir(5);
        pilha.inserir(15);
        pilha.inserir(7);
        pilha.remover();
        pilha.mostrar();
    }
}
