/**
 * TP03Q05 - Lista Flexivel em java
 * @author: Vitória Símil Araújo  
* */

import java.io.BufferedReader;
import java.io.FileReader;


class Jogador {
    // Atributos - dados
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    // Construtor vazio
    public Jogador() {
        this.id = this.altura = this.peso = this.anoNascimento = 0;
        this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
    }

    /**
     * Construtor para inicializar um objeto Jogador com informações específicas.
     *
     * @param id               
     * @param altura           
     * @param peso             
     * @param anoNascimento    
     * @param nome             
     * @param universidade     
     * @param cidadeNascimento 
     * @param estadoNascimento 
     */
    public Jogador(int id, int altura, int peso, int anoNascimento, String nome, String universidade, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    /**
    * GETTERS: @return - atributo do jogador a ser retornado
    * SETTERS: @param paramentro - novo atributo a ser atribuido ao jogador
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }
    
    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }


    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    /**
     * Cria uma cópia do jogador.
     *
     * @param jogador Jogador a ser clonado.
     * @return Uma nova instância de Jogador que é uma cópia do jogador fornecido.
     */
    public Jogador clone(Jogador jogador) {
        return new Jogador(
            jogador.id,
            jogador.altura,
            jogador.peso,
            jogador.anoNascimento,
            jogador.nome,
            jogador.universidade,
            jogador.cidadeNascimento,
            jogador.estadoNascimento
        );
    }

    /**
     * Lê dados de um arquivo CSV de jogadores e os armazena em um array de strings.
     *
     * @return Um array de strings contendo os dados dos jogadores lidos do arquivo CSV.
     */
    public String[] ler() {
        // Caminho do arquivo CSV a ser lido.
        String arquivoCSV = "/tmp/players.csv";

        // Objeto BufferedReader para ler o arquivo.
        BufferedReader br = null;

        //dados
        String linha = "";
        String[] jogadores = new String[3922];
        int contador = 0;

        try {
            br = new BufferedReader(new FileReader(arquivoCSV));

            // Lê e ignora a primeira linha (cabeçalho)
            linha = br.readLine();

            // Loop para ler todas as linhas de dados dos jogadores.
            while ((linha = br.readLine()) != null) {
                jogadores[contador++] = linha;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    // Fecha o BufferedReader.
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return jogadores;
    }


    /**
     * Retorna uma representação em formato de string dos dados do jogador.
     *
     * @return Uma string formatada com os dados do jogador.
     */
    public String imprimir() {
        return  nome + " ## "
                + altura + " ## "
                + peso + " ## "
                + anoNascimento + " ## "
                + universidade + " ## "
                + cidadeNascimento
                + " ## " + estadoNascimento;
    }
}

/**
 * A classe Celula representa um elemento na estrutura de dados da Lista,
 * contendo uma referência para o próximo elemento (prox) e um objeto Jogador.
 */
class Celula {
    /** Referência para a próxima célula na lista. */
    public Celula prox;

    /** Objeto Jogador armazenado na célula. */
    public Jogador elemento;

    /**
     * Construtor vazio da Celula.
     * Inicializa a célula com null.
     */
    public Celula() {
        this(null);
    }

    /**
     * Construtor da Celula que inicializa com um jogador específico.
     *
     * @param elemento O objeto Jogador a ser armazenado na célula.
     */
    public Celula(Jogador elemento) {
        this.elemento = elemento;
        prox = null;
    }
}

/**
 * A classe Lista representa uma lista de jogadores utilizando a estrutura de células encadeadas.
 */
class Lista {
    /** Referência para a primeira célula da lista. */
    private Celula primeiro;

    /** Referência para a última célula da lista. */
    private Celula ultimo;

    /** Número de elementos na lista. */
    private int tamanho;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
        tamanho = 0;
    }

    /**
     * Insere um jogador no início da lista.
     *
     * @param jogador O objeto Jogador a ser inserido.
     */
    public void inserirNoInicio(Jogador jogador) {
        Celula tmp = new Celula(jogador);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) ultimo = tmp;
        tmp = null;
        tamanho++;
    }

    /**
     * Insere um jogador no final da lista.
     *
     * @param jogador O objeto Jogador a ser inserido.
     */
    public void inserirNoFim(Jogador jogador) {
        ultimo.prox = new Celula(jogador);
        ultimo = ultimo.prox;
        tamanho++;
    }

    /**
     * Insere um jogador em uma posição específica na lista.
     *
     * @param pos     A posição desejada para a inserção.
     * @param jogador O objeto Jogador a ser inserido.
     * @throws Exception Se a posição for inválida.
     */
    public void inserir(int pos, Jogador jogador) throws Exception {
        if (pos < 0 || pos > tamanho) throw new Exception("Posição inválida.");
        else if (pos == 0) inserirNoInicio(jogador);
        else if (pos == tamanho) inserirNoFim(jogador);
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(jogador);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
            tamanho++;
        }
    }

    /**
     * Remove o jogador do início da lista.
     *
     * @return O jogador removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerInicio() throws Exception {
        if (primeiro == ultimo) throw new Exception("Lista vazia.");
        Celula tmp = primeiro.prox;
        Jogador jogador = tmp.elemento;
        primeiro.prox = null;
        primeiro = tmp;
        tmp = null;
        tamanho--;
        return jogador;
    }

    /**
     * Remove o jogador do final da lista.
     *
     * @return O jogador removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerFim() throws Exception {
        if (primeiro == ultimo) throw new Exception("Lista vazia.");
        Jogador jogador = ultimo.elemento;
        Celula i;
        for (i = primeiro.prox; i.prox != ultimo; i = i.prox);
        ultimo = i;
        i = ultimo.prox = null;
        tamanho--;
        return jogador;
    }

    /**
     * Remove o jogador de uma posição específica na lista.
     *
     * @param pos A posição desejada para a remoção.
     * @return O jogador removido.
     * @throws Exception Se a lista estiver vazia ou a posição for inválida.
     */
    public Jogador remover(int pos) throws Exception {
        Jogador jogador = null;
        if (primeiro == ultimo || pos < 0 || pos >= tamanho) throw new Exception("Lista vazia ou posição inválida.");
        else if (pos == 0) removerInicio();
        else if (pos == tamanho - 1) removerFim();
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            jogador = tmp.elemento;
            i.prox = tmp.prox;
            tmp = tmp.prox = i = null;
            tamanho--;
        }
        return jogador;
    }

    public void mostrar() {
        Celula tmp = primeiro.prox;
        for (int i = 0; i < tamanho; i++, tmp = tmp.prox) {
            System.out.println("[" + i + "] ## " + tmp.elemento.imprimir() + " ##");
        }
    }
}

public class ListaFlexivel {
    /**
     * @param entrada
     * @return boolean 
     * metodo para verificar se uma entrada é igual a FIM
     */
    private static boolean isFIM(String entrada) {
        return (entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M');
    }

    /**
     * Cria um objeto Jogador com base em uma string de dados.
     *
     * @param j  string contendo os dados do jogador no formato "id,nome,altura,peso,universidade,anoNascimento,cidadeNascimento,estadoNascimento".
     * @param jogador objeto Jogador que será preenchido com os dados extraídos da string.
     * @return objeto Jogador preenchido com os dados da string.
     */
    public static Jogador criarJogador(String j, Jogador jogador) {
        // array contendo os valores de j separado por virgula
        String[] atributos = j.split(",");

        int id = Integer.parseInt(atributos[0]);
        jogador.setId(id);

        String nome = atributos[1];
        jogador.setNome(nome);

        int altura = Integer.parseInt(atributos[2]);
        jogador.setAltura(altura);
        
        int peso = Integer.parseInt(atributos[3]);
        jogador.setPeso(peso);

        String universidade = (atributos[4].isEmpty()) ? "nao informado" : atributos[4];
        jogador.setUniversidade(universidade);

        int anoNascimento = Integer.parseInt(atributos[5]);
        jogador.setAnoNascimento(anoNascimento);
        
        String cidadeNascimento = (atributos.length == 6 ) ? "nao informado" : atributos[6];
        jogador.setCidadeNascimento(cidadeNascimento);

        String estadoNascimento = (atributos.length == 8 ) ? atributos[7]  : "nao informado";
        jogador.setEstadoNascimento(estadoNascimento);

        return jogador;
    }

    public static void main(String[] args) throws Exception{
        // Array para armazenar as linhas de dados lidas do arquivo CSV.
        String entradas[];

        Lista lista = new Lista();

        // Objeto Jogador para criar jogadores a partir dos dados.
        Jogador jogador = new Jogador();
        String entrada = "";

        entradas = jogador.ler();

        while (!(isFIM(entrada = MyIO.readLine()))) {
            int id = Integer.parseInt(entrada);
            jogador = criarJogador(entradas[id], jogador);
            lista.inserirNoFim(jogador.clone(jogador));
        }

        int n = Integer.parseInt(MyIO.readLine());
        int id = 0;

        while ( n > 0 ) {
            entrada = MyIO.readLine();

            String operacao = entrada.substring(0, 2);

            if ( entrada.charAt(0) == 'I') {
                id = ( entrada.charAt(1) != '*' ) ? Integer.parseInt(entrada.substring(3)) : Integer.parseInt(entrada.substring(6));

                jogador = criarJogador(entradas[id], jogador);
            }

            switch (operacao) {
                case "II":
                    lista.inserirNoInicio(jogador.clone(jogador));
                    break;

                case "IF":
                    lista.inserirNoFim(jogador.clone(jogador));
                    break;

                case "I*":
                    int pos = Integer.parseInt(entrada.substring(3,5));
        
                    lista.inserir(pos, jogador.clone(jogador));
                    break;

                case "RI":
                case "RF":
                    jogador = (operacao.charAt(1) == 'I') ? lista.removerInicio(): lista.removerFim();
        
                    System.out.println("(R) " + jogador.getNome());
        
                    break;
                
                case "R*":
                    pos = Integer.parseInt(entrada.substring(3,5));
                    
                    jogador = lista.remover(pos);
    
                    System.out.println("(R) " + jogador.getNome());
                    break;


                default:
                    break;
            }

            n--;
        }

        lista.mostrar();
    }
}