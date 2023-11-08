/**
 * TP03Q01 - Lista Sequencial em java
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
 * Classe que representa uma lista de jogadores.
 */
class Lista {
    // Atributos

    /** Array que armazena os jogadores. */
    public Jogador[] array;

    /** Número atual de elementos na lista. */
    public int n;

    /**
     * Construtor padrão que inicializa a lista com capacidade para 100 elementos.
     */
    public Lista() {
        this.array = new Jogador[100];
        n = 0;
    }

    /**
     * Construtor que permite especificar a capacidade inicial da lista.
     *
     * @param tamanho Capacidade inicial da lista.
     */
    public Lista(int tamanho) {
        this.array = new Jogador[tamanho];
        n = 0;
    }

    /**
     * Insere um jogador no início da lista.
     *
     * @param jogador jogador a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirNoInicio(Jogador jogador) throws Exception {
        if (n == array.length) throw new Exception("Lista cheia");

        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = jogador;
        n++;
    }

    /**
     * Insere um jogador no final da lista.
     *
     * @param jogador jogador a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirNoFim(Jogador jogador) throws Exception {
        if (n == array.length) throw new Exception("Lista cheia");

        array[n] = jogador;
        n++;
    }

    /**
     * Insere um jogador em uma posição específica da lista.
     *
     * @param pos Posição em que o jogador será inserido.
     * @param jogador jogador a ser inserido.
     * @throws Exception Se a lista estiver cheia ou a posição for inválida.
     */
    public void inserir(int pos, Jogador jogador) throws Exception {
        if (n == array.length || pos < 0 || pos > n) throw new Exception("Lista cheia ou posição inválida");

        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }

        array[pos] = jogador;
        n++;
    }

    /**
     * Remove o jogador do início da lista.
     *
     * @return jogador removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerInicio() throws Exception {
        if (n == 0) throw new Exception("Lista vazia");

        Jogador jogador = array[0];

        for (int i = 0; i < n - 1; i++) {
            array[i] = array[i + 1];
        }

        n--;

        return jogador;
    }

    /**
     * Remove o jogador do final da lista.
     *
     * @return jogador removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerFim() throws Exception {
        if (n == 0) throw new Exception("Lista vazia");

        return array[--n];
    }

    /**
     * Remove o jogador de uma posição específica da lista.
     *
     * @param pos Posição do jogador a ser removido.
     * @return jogador removido.
     * @throws Exception Se a lista estiver vazia ou a posição for inválida.
     */
    public Jogador remover(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos > n) throw new Exception("Lista vazia ou posição inválida");

        Jogador jogador = array[pos];

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }

        n--;

        return jogador;
    }

    public void mostrar() {
        for(int i = 0; i < n; i++) {
            System.out.println("["+ i+ "] ## " + array[i].imprimir() + " ##");
        }

        
    }
}


public class ListaSequencial {
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

        Lista lista = new Lista(200);

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
