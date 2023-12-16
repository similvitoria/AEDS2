/**
 * TP04Q02 - Arvore de Arvore em java
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
 * Representa um nó do tipo 1 na árvore, que armazena chaves inteiras.
 */
class NoTipo1 {
    public int chaveInt;
    public NoTipo1 esq, dir;
    public NoTipo2 no;

    /**
     * Construtor padrão que inicializa a chave inteira com 0.
     */
    public NoTipo1() {
        this(0);
    }

    /**
     * Construtor que inicializa o nó com a chave inteira fornecida.
     * @param chaveInt Chave inteira do nó.
     */
    public NoTipo1(int chaveInt) {
        this.chaveInt = chaveInt;
        esq = dir = null;
        no = null;
    }
}

/**
 * Representa um nó do tipo 2 na árvore, que armazena chaves de strings.
 */
class NoTipo2 {
    public String chaveString;
    public NoTipo2 esq, dir;

    /**
     * Construtor padrão que inicializa a chave de string com uma string vazia.
     */
    public NoTipo2() {
        this("");
    }

    /**
     * Construtor que inicializa o nó com a chave de string fornecida.
     * @param chaveString Chave de string do nó.
     */
    public NoTipo2(String chaveString) {
        this.chaveString = chaveString;
        esq = dir = null;
    }
}

/**
 * Representa uma árvore que pode armazenar elementos do tipo NoTipo1.
 */
class Arvore {
    private NoTipo1 raiz;
    private int comparacoes;

    /**
     * Construtor padrão que inicializa a raiz como nula e o contador de comparações como 0.
     */
    public Arvore() {
        raiz = null;
        comparacoes = 0;
    }

    /**
     * Insere um novo elemento na árvore com a chave inteira fornecida.
     * @param chave Chave inteira a ser inserida.
     * @throws Exception Lança uma exceção se houver um erro ao inserir.
     */
    public void inserir(int chave) throws Exception {
        raiz = inserir(chave, raiz);
    }

    /**
     * Método auxiliar para inserir um novo elemento na árvore com a chave inteira fornecida.
     * @param chave Chave inteira a ser inserida.
     * @param i Nó atual para realizar a inserção.
     * @return O nó resultante após a inserção.
     * @throws Exception Lança uma exceção se houver um erro ao inserir.
     */
    public NoTipo1 inserir(int chave, NoTipo1 i) throws Exception {
        if (i == null) {
            i = new NoTipo1(chave);
        } else if (chave < i.chaveInt) {
            i.esq = inserir(chave, i.esq);
        } else if (chave > i.chaveInt) {
            i.dir = inserir(chave, i.dir);
        } else {
            throw new Exception("Erro ao inserir.");
        }

        return i;
    }

    /**
     * Insere um jogador na árvore com base em sua altura e nome.
     * @param jogador Jogador a ser inserido.
     * @throws Exception Lança uma exceção se houver um erro ao inserir.
     */
    public void inserirNome(Jogador jogador) throws Exception {
        inserirNome(jogador, raiz);
    }

    /**
     * Método auxiliar para inserir um jogador na árvore com base em sua altura e nome.
     * @param jogador Jogador a ser inserido.
     * @param i Nó atual para realizar a inserção.
     * @throws Exception Lança uma exceção se houver um erro ao inserir.
     */
    public void inserirNome(Jogador jogador, NoTipo1 i) throws Exception {
        if (jogador.getAltura() % 15 == i.chaveInt) {
            i.no = inserirArvore2(jogador.getNome(), i.no);
        } else if (jogador.getAltura() % 15 < i.chaveInt) {
            inserirNome(jogador, i.esq);
        } else if (jogador.getAltura() % 15 > i.chaveInt) {
            inserirNome(jogador, i.dir);
        } else {
            throw new Exception("Erro ao inserir.");
        }
    }

    /**
     * Insere um elemento do tipo NoTipo2 na árvore com base na chave de string fornecida.
     * @param nome Chave de string a ser inserida.
     * @param i Nó atual para realizar a inserção.
     * @return O nó resultante após a inserção.
     * @throws Exception Lança uma exceção se houver um erro ao inserir.
     */
    public NoTipo2 inserirArvore2(String nome, NoTipo2 i) throws Exception {
        if (i == null) {
            i = new NoTipo2(nome);
        } else if (nome.compareTo(i.chaveString) < 0) {
            i.esq = inserirArvore2(nome, i.esq);
        } else if (nome.compareTo(i.chaveString) > 0) {
            i.dir = inserirArvore2(nome, i.dir);
        } else {
            throw new Exception("Erro ao inserir.");
        }

        return i;
    }

    /**
     * Realiza uma pesquisa na árvore com base no jogador fornecido.
     * @param jogador Jogador a ser pesquisado.
     */
    public void pesquisar(Jogador jogador) {
        System.out.print(jogador.getNome() + " raiz");
        System.out.println(pesquisar(jogador, raiz) == true ? " SIM" : " NAO");
    }

    /**
     * Método auxiliar para realizar uma pesquisa na árvore com base no jogador fornecido.
     * @param jogador Jogador a ser pesquisado.
     * @param i Nó atual para iniciar a pesquisa.
     * @return true se o jogador for encontrado, false caso contrário.
     */
    public boolean pesquisar(Jogador jogador, NoTipo1 i) {
        boolean resp = false;
        if (i != null) {
            resp = pesquisarNome(jogador.getNome(), i.no);

            if (!resp) {
                System.out.print(" esq");
                resp = pesquisar(jogador, i.esq);
            }

            if (!resp) {
                System.out.print(" dir");
                resp = pesquisar(jogador, i.dir);
            }
        }

        return resp;
    }

    /**
     * Realiza uma pesquisa na árvore com base no nome fornecido.
     * @param nome Nome a ser pesquisado.
     * @param i Nó atual para iniciar a pesquisa.
     * @return true se o nome for encontrado, false caso contrário.
     */
    public boolean pesquisarNome(String nome, NoTipo2 i) {
        boolean resp = false;
        if (i != null) {
            if (nome.compareTo(i.chaveString) == 0) {
                return true;
            }

            if (!resp) {
                System.out.print(" ESQ");
                resp = pesquisarNome(nome, i.esq);
            }

            if (!resp) {
                System.out.print(" DIR");
                resp = pesquisarNome(nome, i.dir);
            }
        }

        return resp;
    }

    /**
     * Obtém o número total de comparações realizadas durante as operações na árvore.
     * @return O número total de comparações.
     */
    public int getComparacoes() {
        return comparacoes;
    }
}



public class ArvoreDeArvore {
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
        FileWriter arquivo = new FileWriter ("740136_arvoreDeArvore.txt");
        BufferedWriter arvoreDeArvore = new BufferedWriter(arquivo);
        arvoreDeArvore.write("740136" + "\t");


        // Array para armazenar as linhas de dados lidas do arquivo CSV.
        String entradas[];

        Arvore arvore = new Arvore();

        // Objeto Jogador para criar jogadores a partir dos dados.
        Jogador jogador = new Jogador();
        String entrada = "";

        entradas = jogador.ler();

        arvore.inserir(7);
        arvore.inserir(3);
        arvore.inserir(11);
        arvore.inserir(1);
        arvore.inserir(5);
        arvore.inserir(9);
        arvore.inserir(12);
        arvore.inserir(0);
        arvore.inserir(2);
        arvore.inserir(4);
        arvore.inserir(6);
        arvore.inserir(8);
        arvore.inserir(10);
        arvore.inserir(13);
        arvore.inserir(14);

        while (!(isFIM(entrada = MyIO.readLine()))) {
            int id = Integer.parseInt(entrada);
            jogador = criarJogador(entradas[id], jogador);
            arvore.inserirNome(jogador.clone(jogador));
        }

        //tempo antes de executar o algoritmo de seleção
        int tempoInicial = (int)System.currentTimeMillis();

        while (!(isFIM(entrada = MyIO.readLine()))) {

            for ( int i = 0; i < 3922; i++ ) {
                if(entradas[i].contains(entrada)) {
                    jogador = criarJogador(entradas[i], jogador);
                    i = 3922;
                }
            }
            arvore.pesquisar(jogador);
        }

        int tempoFinal = (int)System.currentTimeMillis();
        arvoreDeArvore.write((tempoFinal - tempoInicial)+ "ms" + "\t");
        arvoreDeArvore.write("" + arvore.getComparacoes() + "comparacoes");

        //close file
        arvoreDeArvore.close();
        arquivo.close();

    }
}
