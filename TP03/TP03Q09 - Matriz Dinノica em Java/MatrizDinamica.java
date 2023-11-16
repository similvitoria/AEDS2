/**
 * TP03Q09 - Matriz Dinâmica em Java
 * @author: Vitória Símil Araújo  
* */
class Celula {
    public Celula inf, sup, esq, dir;
    public int elemento;

    /**
     * Construtor padrão da célula.
     */
    public Celula() {
        this(null, null, null, null, 0);
    }

    /**
     * Construtor da célula com parâmetros.
     *
     * @param inf      Célula inferior.
     * @param sup      Célula superior.
     * @param esq      Célula à esquerda.
     * @param dir      Célula à direita.
     * @param elemento Valor do elemento na célula.
     */
    public Celula(Celula inf, Celula sup, Celula esq, Celula dir, int elemento) {
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
        this.elemento = elemento;
    }
}

class Matriz {
    //atributos - dados
    private Celula inicio;
    private int linhas, colunas;

    /**
     * Construtor padrão da matriz com tamanho 3x3.
     */
    public Matriz() {
        this(3, 3);
    }

    /**
     * Construtor da matriz com tamanho personalizado.
     *
     * @param linhas  Número de linhas na matriz.
     * @param colunas Número de colunas na matriz.
     */
    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        inicio = new Celula();
        Celula tmp = inicio;

        // Criação das colunas
        for (int c = 1; c < colunas; c++) {
            tmp.dir = new Celula();
            tmp.dir.esq = tmp;
            tmp = tmp.dir;
        }

        tmp = inicio;

        // Criação das linhas
        for (int l = 1; l < linhas; l++) {
            tmp.inf = new Celula();
            tmp.inf.sup = tmp;
            tmp = tmp.inf;
            Celula aux = tmp;

            // Criação das colunas
            for (int c = 1; c < colunas; c++) {
                tmp.dir = new Celula();
                tmp.dir.esq = tmp;
                tmp.dir.sup = tmp.sup.dir;
                tmp.sup.dir.inf = tmp.dir;
                tmp = tmp.dir;
            }

            tmp = aux;
        }
    }

    /**
     * Constrói a matriz com os elementos fornecidos.
     *
     * @param elementos Um array de inteiros representando os elementos da matriz.
     */
    public void construirMatriz(int[] elementos) {
        Celula tmp = inicio, aux = inicio;
        int pos = 0;

        for (int l = 0; l < linhas; l++) {
            while (tmp != null) {
                tmp.elemento = elementos[pos];
                pos++;
                tmp = tmp.dir;
            }

            aux = aux.inf;
            tmp = aux;
        }
    }

    /**
     * Realiza a soma de duas matrizes e armazena o resultado na matriz atual.
     *
     * @param m1 Matriz 1.
     * @param m2 Matriz 2.
     */
    public void getSoma(Matriz m1, Matriz m2) {
        Celula tmpM1 = m1.inicio, auxM1 = m1.inicio;
        Celula tmpM2 = m2.inicio, auxM2 = m2.inicio;
        Celula tmp = inicio, aux = inicio;

        for (int l = 0; l < linhas; l++) {
            while (tmp != null) {
                tmp.elemento = tmpM1.elemento + tmpM2.elemento;
                tmp = tmp.dir;
                tmpM1 = tmpM1.dir;
                tmpM2 = tmpM2.dir;
            }

            aux = aux.inf;
            auxM1 = auxM1.inf;
            auxM2 = auxM2.inf;

            tmp = aux;
            tmpM1 = auxM1;
            tmpM2 = auxM2;
        }
    }

    /**
     * Realiza a multiplicação de duas matrizes e armazena o resultado na matriz atual.
     *
     * @param m1 Matriz 1.
     * @param m2 Matriz 2.
     */
    public void getMultiplicacao(Matriz m1, Matriz m2) {
        Celula tmpM1 = m1.inicio, auxM1 = m1.inicio;
        Celula tmpM2 = m2.inicio, auxM2 = m2.inicio;
        Celula tmp = inicio, aux = inicio;

        int sum = 0;

        for (int l = 0; l < linhas; l++) {
            while (tmp != null) {
                while (tmpM2.inf != null) {
                    sum += tmpM1.elemento * tmpM2.elemento;
                    tmpM1 = tmpM1.dir;
                    tmpM2 = tmpM2.inf;
                }

                sum += tmpM1.elemento * tmpM2.elemento;

                tmp.elemento = sum;
                sum = 0;
                tmp = tmp.dir;

                tmpM1 = auxM1;

                auxM2 = auxM2.dir;
                tmpM2 = auxM2;
            }

            aux = aux.inf;
            tmp = aux;

            auxM1 = auxM1.inf;
            tmpM1 = auxM1;

            tmpM2 = m2.inicio;
            auxM2 = m2.inicio;
        }
    }

    /**
     * Obtém e imprime a diagonal principal da matriz.
     */
    public void getDiagonalPrincipal() {
        if (isQuadratica()) {
            Celula tmp = inicio;

            while (tmp.dir != null) {
                System.out.print(tmp.elemento);
                System.out.print(" ");
                tmp = tmp.dir.inf;
            }

            System.out.println(tmp.elemento);
        }
    }

    /**
     * Obtém e imprime a diagonal secundária da matriz.
     */
    public void getDiagonalSecundaria() {
        if (isQuadratica()) {
            Celula tmp = inicio;

            for (int c = 1; c < colunas; c++) {
                tmp = tmp.dir;
            }

            while (tmp.esq != null) {
                System.out.print(tmp.elemento);
                System.out.print(" ");
                tmp = tmp.esq.inf;
            }
            System.out.println(tmp.elemento);
        }
    }

    /**
     * Verifica se a matriz é quadrática (número de linhas igual ao número de colunas).
     *
     * @return true se a matriz for quadrática, false caso contrário.
     */
    private boolean isQuadratica() {
        return linhas == colunas;
    }

    public void mostrarMatriz() {
        Celula tmp = inicio, aux = inicio;

        for (int l = 0; l < linhas; l++) {
            while (tmp != null) {
                System.out.print(tmp.elemento);
                System.out.print(" ");
                tmp = tmp.dir;
            }
            System.out.println();
            aux = aux.inf;
            tmp = aux;
        }
    }
}

public class MatrizDinamica {
    public static void main(String[] args) {
        //dados
        int operations = Integer.parseInt(MyIO.readLine());
        Matriz m1, m2, m3;

        while (operations > 0) {
            int linhas = Integer.parseInt(MyIO.readLine());
            int colunas = Integer.parseInt(MyIO.readLine());

            m1 = new Matriz(linhas, colunas);

            int count = 1;
            String e1 = MyIO.readLine();

            while (count < linhas) {
                e1 += MyIO.readLine();
                count++;
            }

            e1 = e1.replace(" ", "");

            int[] elementosM1 = new int[linhas * colunas];

            for (int i = 0; i < linhas * colunas; i++) {
                elementosM1[i] = Character.getNumericValue(e1.charAt(i));
            }

            m1.construirMatriz(elementosM1);
            m1.getDiagonalPrincipal();
            m1.getDiagonalSecundaria();

            linhas = Integer.parseInt(MyIO.readLine());
            colunas = Integer.parseInt(MyIO.readLine());

            m2 = new Matriz(linhas, colunas);

            count = 1;
            String e2 = MyIO.readLine();

            while (count < linhas) {
                e2 += MyIO.readLine();
                count++;
            }

            e2 = e2.replace(" ", "");

            int[] elementosM2 = new int[linhas * colunas];

            for (int i = 0; i < linhas * colunas; i++) {
                elementosM2[i] = Character.getNumericValue(e2.charAt(i));
            }

            m2.construirMatriz(elementosM2);

            m3 = new Matriz(linhas, colunas);
            m3.getSoma(m1, m2);
            m3.mostrarMatriz();

            m3.getMultiplicacao(m1, m2);
            m3.mostrarMatriz();

            operations--;
        }
    }
}
