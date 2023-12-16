/**
 * TP04Q07  Hash direta em java
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
 * Classe que representa uma tabela hash para armazenar instâncias da classe Jogador.
 * Essa classe utiliza uma combinação de sondagem linear e encadeamento separado para a resolução de colisões.
 */
class Hash {
    //dados

    /** O array que representa a tabela hash. */
    private Jogador tabela[];

    /** O tamanho da tabela hash primária. */
    private int m1;

    /** O tamanho da tabela hash secundária (reserva). */
    private int m2;

    /** O número de elementos na tabela hash de reserva. */
    private int reserva;

    /** O tamanho total da tabela hash (m1 + m2). */
    private int tamanhoHash;

    /** A contagem de comparações realizadas durante as operações na tabela hash. */
    private int comparacoes;

    /**
     * Constrói um objeto Hash com os tamanhos especificados para as tabelas hash primária e secundária.
     *
     * @param m1 O tamanho da tabela hash primária.
     * @param m2 O tamanho da tabela hash secundária (reserva).
     */
    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        tamanhoHash = m1 + m2;
        tabela = new Jogador[tamanhoHash];

        for (int i = 0; i < m1; i++) tabela[i] = null;

        reserva = comparacoes = 0;
    }

    /**
     * Calcula o valor de hash para um nome dado, usando uma simples soma dos valores dos caracteres.
     *
     * @param jogador
     * @return O valor de hash.
     */
    public int getH(Jogador jogador) {
        return jogador.getAltura() % m1;
    }

    /**
     * Insere um objeto Jogador na tabela hash usando sondagem linear e encadeamento separado para colisões.
     *
     * @param jogador O objeto Jogador a ser inserido.
     */
    public void inserir(Jogador jogador) {
        if (jogador != null) {
            int posicao = getH(jogador);

            if (tabela[posicao] == null) {
                tabela[posicao] = jogador;
            } else if (reserva < m2) {
                tabela[m1 + reserva] = jogador;
                reserva++;
            }
        }
    }

    /**
     * Pesquisa um nome na tabela hash e exibe o resultado (SIM ou NAO) no console.
     *
     * @param nome O nome a ser pesquisado.
     */
    public void pesquisar(Jogador jogador) {
        if(jogador.getNome().contains("*")) {
            String nome = jogador.getNome().substring(0, jogador.getNome().length() - 1);
            System.out.print(nome);
        }
        else {
            System.out.print(jogador.getNome());
        }
        System.out.println(pesquisarNome(jogador) ? " SIM" : " NAO");
    }

    /**
     * Método privado utilizado para realizar a pesquisa de um nome na tabela hash.
     *
     * @param nome O nome a ser pesquisado.
     * @return true se o nome for encontrado, false caso contrário.
     */
    private boolean pesquisarNome(Jogador jogador) {
        boolean resp = false;
        int posicao = getH(jogador);

        comparacoes++;
        if (tabela[posicao] != null) {
            comparacoes++;
            if (tabela[posicao].getNome().compareTo(jogador.getNome()) == 0) {
                resp = true;
            } else {
                for (int i = 0; i < reserva; i++) {
                    if (tabela[m1 + i].getNome().compareTo(jogador.getNome()) == 0) {
                        comparacoes++;
                        resp = true;
                        i = reserva;
                    }
                }
            }
        }

        return resp;
    }

    /**
     * Obtém o número total de comparações realizadas durante as operações na tabela hash.
     *
     * @return O número de comparações.
     */
    public int getComparacoes() {
        return comparacoes;
    }
}



public class HashDireta {
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
        FileWriter arquivo = new FileWriter ("740136_hash.txt");
        BufferedWriter hash = new BufferedWriter(arquivo);
        hash.write("740136" + "\t");


        // Array para armazenar as linhas de dados lidas do arquivo CSV.
        String entradas[];

        Hash h = new Hash(21, 9);

        // Objeto Jogador para criar jogadores a partir dos dados.
        Jogador jogador = new Jogador();
        String entrada = "";

        entradas = jogador.ler();

        while (!(isFIM(entrada = MyIO.readLine()))) {
            int id = Integer.parseInt(entrada);
            jogador = criarJogador(entradas[id], jogador);
            h.inserir(jogador.clone(jogador));
        }

        //tempo antes de executar a pesquisa
        int tempoInicial = (int)System.currentTimeMillis();

        while (!(isFIM(entrada = MyIO.readLine()))) {
            for ( int i = 0; i < 3922; i++ ) {
                if(entradas[i].contains(entrada)) {
                    jogador = criarJogador(entradas[i], jogador);
                    i = 3922;
                }
            }
            h.pesquisar(jogador);
        }

        int tempoFinal = (int)System.currentTimeMillis();
        hash.write((tempoFinal - tempoInicial)+ "ms" + "\t");
        hash.write("" + h.getComparacoes() + "comparacoes");

        //close file
        hash.close();
        arquivo.close();
    }
}