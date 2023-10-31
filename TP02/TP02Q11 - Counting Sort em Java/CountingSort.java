/**
 * TP02Q11 - Counting sort em java
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
        return "[" + id + " ## "
                + nome + " ## "
                + altura + " ## "
                + peso + " ## "
                + anoNascimento + " ## "
                + universidade + " ## "
                + cidadeNascimento
                + " ## " + estadoNascimento + "]";
    }
}

class Lista {
    // Atributos - dados
    private static Jogador arrayJogadores[];
    private static int n;
    private static int comparacoes;
    private int movimentacoes;

    public Lista() {
        this(6);
    }

    /**
     * @param tamanho O tamanho da lista de jogadores a ser criada.
     */
    public Lista(int tamanho) {
        arrayJogadores = new Jogador[tamanho];
        n = 0;
    }

    /**
     * @param jogador O jogador a ser inserido na lista.
     */
    public void inserir(Jogador jogador) throws Exception {
        //verify if the array is full
        if ( n >= arrayJogadores.length ) throw new Exception("Array is full");
        arrayJogadores[n] = jogador;
        n++;
    }

    private int getMaior() {
        //dados
        double biggest = arrayJogadores[0].getAltura();

        for ( int i = 1; i < n; i++ ) {
            double tmp = arrayJogadores[i].getAltura();

            comparacoes++;
            if ( biggest < tmp ) {
                biggest = tmp;
            }
        }

        return (int)biggest;
    }

    public void countingSort() {
        //dados
        int[] count = new int[getMaior()+1];
        Jogador[] j = new Jogador[n];

        for ( int i = 0; i < count.length; count[i] = 0, i++ );

        for ( int i = 0; i < n; count[(int)arrayJogadores[i].getAltura()]++, i++ );

        for ( int i = 1; i < count.length; count[i] += count[i-1], movimentacoes++, i++);

        //order
        for ( int i = n -1; i >= 0; j[count[(int)arrayJogadores[i].getAltura()]-1] = arrayJogadores[i], movimentacoes +=2, count[(int)arrayJogadores[i].getAltura()]--, i--);

        //copying to original arrayJogadores
        for ( int i = 0; i < n; arrayJogadores[i] = j[i], movimentacoes++, i++);
    }

    //selecao: ordernar por nome após couting
    public void selecao() {
        //execute sort
        for ( int i = 0; i < n - 1; i++ ) {
            int menor = i;

            for ( int j = i + 1; j < n; j++) {
                if ( arrayJogadores[menor].getNome().compareTo(arrayJogadores[j].getNome()) > 0 && arrayJogadores[menor].getAltura() == arrayJogadores[j].getAltura() ) {
                    swap(j, i);
                }
            }
        }
    }

    //swap
    private void swap(int a, int b) {
        Jogador tmp = arrayJogadores[a];
        arrayJogadores[a] = arrayJogadores[b];
        arrayJogadores[b] = tmp;
    }
    
    
    public void mostrar() {
        for(int i = 0; i < n; i++) {
            System.out.println(arrayJogadores[i].imprimir());
        }
    }

    public int getComparacoes() {
    	return comparacoes;
    }

    public int getMovimentacoes() {
    	return movimentacoes;
    }
}

public class CountingSort {
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
        FileWriter arquivo = new FileWriter ("740136_counting.txt");
        BufferedWriter arquivoCounting = new BufferedWriter(arquivo);
        arquivoCounting.write("740136" + "\t");

        //Instância de lista
        Lista lista = new Lista(500);

        // Array para armazenar as linhas de dados lidas do arquivo CSV.
        String entradas[];

        // Objeto Jogador para criar jogadores a partir dos dados.
        Jogador jogador = new Jogador();
        String entrada = "";

        entradas = jogador.ler();

        while (!(isFIM(entrada = MyIO.readLine()))) {
            int id = Integer.parseInt(entrada);
            jogador = criarJogador(entradas[id], jogador);
            lista.inserir(jogador.clone(jogador));
        }

        //tempo antes de executar o algoritmo de inserção
        int tempoInicial = (int)System.currentTimeMillis();
        lista.countingSort();
        
        int tempoFinal = (int)System.currentTimeMillis();
        arquivoCounting.write((tempoFinal - tempoInicial)+ "ms" + "\t");
        
        //escrever número de comparações da pesquisa
        arquivoCounting.write("" + lista.getComparacoes() + "comparações\t");
        arquivoCounting.write("" + lista.getMovimentacoes() + "movimentações");
        
        lista.selecao();
        lista.mostrar();
        
        //close file
        arquivoCounting.close();
        arquivo.close();
    }
}