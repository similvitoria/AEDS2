/**
 * TP04Q04 - Arvore Alvinegra em java
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

class No {
    // Atributos
    public Jogador jogador; // Objeto Jogador associado ao nó
    public No esq, dir; // Referências para os filhos esquerdo e direito
    public boolean cor; // Cor do nó (true para vermelho, false para preto)

    /**
     * Construtor padrão. Cria um nó com jogador nulo.
     */
    public No() {
        this(null);
    }

    /**
     * Construtor. Cria um nó com o jogador especificado e cor preta.
     *
     * @param jogador O jogador associado ao nó.
     */
    public No(Jogador jogador) {
        this(jogador, false, null, null);
    }

    /**
     * Construtor. Cria um nó com jogador e cor especificados.
     *
     * @param jogador O jogador associado ao nó.
     * @param cor     A cor do nó (true para vermelho, false para preto).
     */
    public No(Jogador jogador, boolean cor) {
        this(jogador, cor, null, null);
    }

    /**
     * Construtor. Cria um nó com jogador, cor e filhos especificados.
     *
     * @param jogador O jogador associado ao nó.
     * @param cor     A cor do nó (true para vermelho, false para preto).
     * @param esq     O nó filho à esquerda.
     * @param dir     O nó filho à direita.
     */
    public No(Jogador jogador, boolean cor, No esq, No dir) {
        this.jogador = jogador;
        this.cor = cor;
        this.esq = esq;
        this.dir = dir;
    }
}

class Arvore {
    private No raiz; // Raiz da árvore
    private int comparacoes; // Contador de comparações realizadas

    /**
     * Construtor da classe Arvore. Inicializa a raiz como nula e o contador de comparações como zero.
     */
    public Arvore() {
        raiz = null;
        comparacoes = 0;
    }

    /**
     * Insere um jogador na árvore.
     *
     * @param jogador O jogador a ser inserido.
     * @throws Exception Exceção lançada em caso de jogador repetido.
     */
    public void inserir(Jogador jogador) throws Exception {
        if (raiz == null) {
            raiz = new No(jogador);
        } else if (raiz.esq == null && raiz.dir == null) {
            if (jogador.getNome().compareTo(raiz.jogador.getNome()) < 0) {
                raiz.esq = new No(jogador);
            } else {
                raiz.dir = new No(jogador);
            }
        } else if (raiz.esq == null) {
            if (jogador.getNome().compareTo(raiz.jogador.getNome()) < 0) {
                raiz.esq = new No(jogador);
            } else if (jogador.getNome().compareTo(raiz.dir.jogador.getNome()) < 0) {
                raiz.esq = new No(raiz.jogador);
                raiz.jogador = jogador;
            } else {
                raiz.esq = new No(raiz.jogador);
                raiz.jogador = raiz.dir.jogador;
                raiz.dir.jogador = jogador;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        } else if (raiz.dir == null) {
            if (jogador.getNome().compareTo(raiz.jogador.getNome()) > 0) {
                raiz.dir = new No(jogador);
            } else if (jogador.getNome().compareTo(raiz.esq.jogador.getNome()) > 0) {
                raiz.dir = new No(raiz.jogador);
                raiz.jogador = jogador;
            } else {
                raiz.dir = new No(raiz.jogador);
                raiz.jogador = raiz.esq.jogador;
                raiz.esq.jogador = jogador;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        } else {
            inserir(jogador, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    /**
     * Realiza as rotações necessárias para balancear a árvore após uma inserção.
     *
     * @param bisavo O nó bisavô.
     * @param avo    O nó avô.
     * @param pai    O nó pai.
     * @param i      O nó atual.
     */
    private void balancear(No bisavo, No avo, No pai, No i) {
        if (pai.cor == true) {
            if (pai.jogador.getNome().compareTo(avo.jogador.getNome()) > 0) {
                if (i.jogador.getNome().compareTo(pai.jogador.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else {
                if (i.jogador.getNome().compareTo(pai.jogador.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.jogador.getNome().compareTo(bisavo.jogador.getNome()) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    /**
     * Método privado para inserir um jogador na árvore, utilizando recursão.
     *
     * @param jogador O jogador a ser inserido.
     * @param bisavo  O nó bisavô.
     * @param avo     O nó avô.
     * @param pai     O nó pai.
     * @param i       O nó atual.
     * @throws Exception Exceção lançada em caso de jogador repetido.
     */
    private void inserir(Jogador jogador, No bisavo, No avo, No pai, No i) throws Exception {
        if (i == null) {
            if (jogador.getNome().compareTo(pai.jogador.getNome()) < 0) {
                i = pai.esq = new No(jogador, true);
            } else {
                i = pai.dir = new No(jogador, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (jogador.getNome().compareTo(i.jogador.getNome()) < 0) {
                inserir(jogador, avo, pai, i, i.esq);
            } else if (jogador.getNome().compareTo(i.jogador.getNome()) > 0) {
                inserir(jogador, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (jogador repetido)!");
            }
        }
    }

    private No rotacaoDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
    /**
     * Pesquisa um jogador na árvore a partir do nome.
     *
     * @param nome O nome do jogador a ser pesquisado.
     */
    public void pesquisar(String nome) {
        System.out.print(nome + " raiz");
        pesquisar(nome, raiz);
    }

    /**
     * Método privado para pesquisa recursiva de um jogador na árvore.
     *
     * @param nome O nome do jogador a ser pesquisado.
     * @param i    O nó atual.
     */
    private void pesquisar(String nome, No i) {
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
        } else {
            comparacoes++;
            System.out.print(" dir");
            pesquisar(nome, i.dir);
        }
    }

    /**
     * Retorna o número total de comparações realizadas.
     *
     * @return O número de comparações.
     */
    public int getComparacoes() {
        return comparacoes;
    }
}

public class ArvoreAlvinegra {
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
        FileWriter arquivo = new FileWriter ("740136_an.txt");
        BufferedWriter an = new BufferedWriter(arquivo);
        an.write("740136" + "\t");

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
        an.write((tempoFinal - tempoInicial)+ "ms" + "\t");
        an.write("" + arvore.getComparacoes() + "comparacoes");

        //close file
        an.close();
        arquivo.close();
    }
}