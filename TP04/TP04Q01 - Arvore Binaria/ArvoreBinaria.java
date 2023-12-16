/**
 * TP04Q01 - Arvore Binária em java
 * @author: Vitória Símil Araújo  
* */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

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
 * Representa um nó em uma árvore binária de pesquisa.
 */
class No {
    // Atributos
    public Jogador jogador; // Jogador associado ao nó
    public No esq, dir; // Filhos esquerdo e direito do nó

    /**
     * Construtor padrão que inicializa um nó sem jogador associado.
     */
    public No() {
        this(null);
    }

    /**
     * Construtor que inicializa um nó com um jogador específico.
     *
     * @param jogador O jogador a ser associado ao nó.
     */
    public No(Jogador jogador) {
        this.jogador = jogador;
        esq = dir = null; // Inicializa os filhos como nulos.
    }
}

/**
 * Representa uma árvore binária de pesquisa de jogadores.
 */
class Arvore {
    // Atributos
    private No raiz; // Raiz da árvore
    private int comparacoes;

    /**
     * Construtor padrão que inicializa uma árvore vazia.
     */
    public Arvore() {
        raiz = null;
        comparacoes = 0;
    }

    /**
     * Insere um jogador na árvore.
     *
     * @param jogador O jogador a ser inserido na árvore.
     * @throws Exception Se ocorrer um erro durante a inserção do jogador.
     */
    public void inserir(Jogador jogador) throws Exception {
        raiz = inserir(jogador, raiz);
    }

    /**
     * Insere um jogador em um nó específico da árvore.
     *
     * @param jogador O jogador a ser inserido.
     * @param i O nó onde o jogador será inserido.
     * @return O nó atualizado após a inserção.
     * @throws Exception Se ocorrer um erro durante a inserção do jogador.
     */
    public No inserir(Jogador jogador, No i) throws Exception {
        if (i == null) {
            i = new No(jogador);
        } else if (jogador.getNome().compareTo(i.jogador.getNome()) < 0) {
            i.esq = inserir(jogador, i.esq);
        } else if (jogador.getNome().compareTo(i.jogador.getNome()) > 0) {
            i.dir = inserir(jogador, i.dir);
        } else {
            throw new Exception("Erro ao inserir Jogador: jogador já existe na árvore.");
        }

        return i;
    }

    /**
     * Pesquisa um jogador na árvore pelo nome.
     *
     * @param nome O nome do jogador a ser pesquisado.
     */
    public void pesquisar(String nome) {
        System.out.print(nome + " raiz");
        pesquisar(nome, raiz);
    }

    /**
     * Pesquisa um jogador na subárvore enraizada no nó fornecido.
     *
     * @param nome O nome do jogador a ser pesquisado.
     * @param i O nó raiz da subárvore onde a pesquisa será realizada.
     */
    public void pesquisar(String nome, No i) {
        comparacoes++;
        if (i == null) {
            System.out.println(" NAO");
        } else if (nome.compareTo(i.jogador.getNome()) == 0) {
            comparacoes++;
            System.out.println(" SIM");
        } else if (nome.compareTo(i.jogador.getNome()) < 0) {
            comparacoes++; 
            System.out.print(" esq");
            pesquisar(nome, i.esq);
        } else if (nome.compareTo(i.jogador.getNome()) > 0) {
            comparacoes++;
            System.out.print(" dir");
            pesquisar(nome, i.dir);
        }
    }

    public int getComparacoes() {
        return comparacoes;
    }
}


public class ArvoreBinaria {
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
        //arquivo de log
        FileWriter arquivo = new FileWriter ("740136_ab.txt");
        BufferedWriter ab = new BufferedWriter(arquivo);
        ab.write("740136" + "\t");


        // Array para armazenar as linhas de dados lidas do arquivo CSV.
        String entradas[];

        Arvore arvore = new Arvore();

        // Objeto Jogador para criar jogadores a partir dos dados.
        Jogador jogador = new Jogador();
        String entrada = "";

        entradas = jogador.ler();

        while (!(isFIM(entrada = MyIO.readLine()))) {
            int id = Integer.parseInt(entrada);
            jogador = criarJogador(entradas[id], jogador);
            arvore.inserir(jogador.clone(jogador));
        }

        //tempo antes de executar o algoritmo de seleção
        int tempoInicial = (int)System.currentTimeMillis();

        while (!(isFIM(entrada = MyIO.readLine()))) {
            arvore.pesquisar(entrada);
        }

        int tempoFinal = (int)System.currentTimeMillis();
        ab.write((tempoFinal - tempoInicial)+ "ms" + "\t");
        ab.write("" + arvore.getComparacoes() + "comparacoes");

        //close file
        ab.close();
        arquivo.close();

    }
}