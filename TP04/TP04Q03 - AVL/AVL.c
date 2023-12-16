/**
 * TP04Q03 Árvore AVL em c
 * @author: Vitória Símil Araújo  
*/

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"
#include "string.h"
#include "math.h"
#include "time.h"

#define tamanhoAtributo 100
#define tamanhoEntrada 500

// Variável global - array de entradas do arquivo csv
char **entradas;

// Definição da estrutura de dados para representar um jogador
typedef struct {
    char nome[tamanhoAtributo],
        universidade[tamanhoAtributo],
        cidadeNascimento[tamanhoAtributo],
        estadoNascimento[tamanhoAtributo];

    int id, altura, peso, anoNascimento;
} Jogador;

// Definição da estrutura de dados para representar uma célula da fila
typedef struct No {
    struct No* esq;
    struct No* dir;
    Jogador jogador;
    int nivel;
} No;

typedef struct Arvore {
    No* raiz;
    int comparacoes;
} Arvore;


// Cabeçalho de funções
bool isFIM(char input[]);
void ler();

//funções jogador
void criarJogador(char *entradasJogador, Jogador *jogador);
void substituirNaoInformado(char *str);
Jogador clone(Jogador *jogador);
void imprimir(Jogador *jogador);

//funções arvore
No* novoNo(Jogador jogador);
void iniciar(Arvore *arvore);
void setNivel(No *no);
int getNivel(No *no);
void inserir(Jogador jogador, Arvore *arvore);
No* inserirNo(Jogador jogador, No *i);
No* balancear(No *no);
No* rotacionarDir(No *no);
No* rotacionarEsq (No *no );
void pesquisar(char nome[], Arvore *arvore);
void pesquisarNome(char nome[], No* i, Arvore *arvore);
int getComparacoes(Arvore *arvore);

// Função principal
int main(void) {
    //arquivo para escrita
    FILE *fw;
    fw = fopen("740136_avl.txt", "w+");

    // Dados 
    Jogador jogador;
    Jogador jogadores[3922];
    char *entrada = malloc(sizeof(char*) * 10);
    
    int idJogador = 0;

    // Ler documento CSV
    ler();

    // Criar nova arvore
    Arvore arvore;
    iniciar(&arvore);

    // Preencher o array de jogadores com dados do arquivo CSV
    for(int i = 0; i < 3922; i++ ) {
        criarJogador(entradas[i], &jogador);
        jogadores[i] = jogador;
    }

    // Ler IDs de jogadores a partir da entrada padrão e inseri-los na arvore
    scanf("%s", entrada);
    while(!isFIM(entrada)) {
        idJogador = atoi(entrada);
        inserir(clone(&jogadores[idJogador]), &arvore);
        scanf("%s", entrada);
    }

    scanf("%[^\n]", entrada);
    getchar();
    scanf("%[^\n]", entrada);
    getchar();
    
    //tempo antes da pesquisa
    clock_t start = clock();

    while(!isFIM(entrada)) {
        pesquisar(entrada, &arvore);
        scanf("%[^\n]", entrada);
        getchar();
    }

    //escrever 
    fprintf(fw, "740136\t%lims\t%icomparacoes", (clock() - start), getComparacoes(&arvore));
    
    //fecharArquivo
    fclose(fw);

    // Liberar memória alocada
    free(entrada);
    free(entradas);
}

// Função para verificar se a entrada é "FIM"
bool isFIM(char input[]) {
    return (input[0] == 'F' && input[1] == 'I' && input[2] == 'M');
}

// Função para ler os dados do arquivo CSV e armazená-los em um array
void ler() {
    // Dados
    char jogador[tamanhoEntrada];

    // Alocar vetor de entradas
    entradas = (char **) malloc(4000 * sizeof(char *));

    // Abrir arquivo CSV para leitura
    FILE *csv = fopen("/tmp/players.csv", "r");

    // Ler e ignorar o cabeçalho do arquivo CSV
    fgets(jogador, sizeof(jogador), csv);

    for(int i = 0; i < 3922; i++) {
        // Alocar espaço para string no array de entradas
        entradas[i] = (char *) malloc(tamanhoEntrada * sizeof(char));

        if(fgets(jogador, sizeof(jogador), csv) != NULL) {
            jogador[strcspn(jogador, "\n")] = '\0'; 
            strcpy(entradas[i], jogador);
        }
    }

    fclose(csv);
}

// Função para criar um objeto Jogador a partir de uma string de entrada CSV
void criarJogador(char *entradasJogador, Jogador *jogador) {
    substituirNaoInformado(entradasJogador);

    char *atributo;
    atributo = strtok(entradasJogador, ",");
    jogador->id = atoi(atributo);

    atributo = strtok(NULL, ",");
    strcpy(jogador->nome, atributo);
    
    atributo = strtok(NULL, ",");
    jogador->altura = atoi(atributo);

    atributo = strtok(NULL, ",");
    jogador->peso = atoi(atributo);

    atributo = strtok(NULL, ",");
    strcpy(jogador->universidade, atributo);

    atributo = strtok(NULL, ",");
    jogador->anoNascimento = atoi(atributo);

    atributo = strtok(NULL, ",");
    if(atributo == NULL) strcpy(jogador->cidadeNascimento, "nao informado");
    else strcpy(jogador->cidadeNascimento, atributo);

    atributo = strtok(NULL, ",");
    if(atributo == NULL) strcpy(jogador->estadoNascimento, "nao informado");
    else strcpy(jogador->estadoNascimento, atributo);
}

// Função para substituir valores não informados na string por "nao informado"
void substituirNaoInformado(char *str) {
    char *pos = strstr(str, ",,");
    
    while (pos != NULL) {
        int indice = pos - str;
        
        // Substituir ",," por ",nao informado,"
        memmove(str + indice + strlen("nao informado"), str + indice, strlen(str) - indice);
        strncpy(str + indice + 1, "nao informado", strlen("nao informado"));
        
        // Atualizar a posição
        pos = strstr(str, ",,");
    }
}

// Função para clonar um objeto Jogador
Jogador clone(Jogador *jogador) {
    Jogador jogadorClonado;

    strcpy(jogadorClonado.nome, jogador->nome);
    strcpy(jogadorClonado.universidade, jogador->universidade);
    strcpy(jogadorClonado.cidadeNascimento, jogador->cidadeNascimento);
    strcpy(jogadorClonado.estadoNascimento, jogador->estadoNascimento);

    jogadorClonado.id = jogador->id;
    jogadorClonado.altura = jogador->altura;
    jogadorClonado.peso = jogador->peso;
    jogadorClonado.anoNascimento = jogador->anoNascimento;

    return jogadorClonado;
}

// Função para imprimir os dados de um jogador
void imprimir(Jogador *jogador) {
    printf(" ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n",
    jogador->nome, jogador->altura, jogador->peso,jogador->anoNascimento,jogador->universidade,
    jogador->cidadeNascimento, jogador->estadoNascimento);
}

// Função para criar um novo no
No* novoNo(Jogador jogador) {
    No* novo = (No*) malloc(sizeof(No));
    novo->jogador = jogador;
    novo->esq = NULL;
    novo->dir = NULL;
    novo->nivel = 1;
    return novo;
}

//função para setar nível de um nó
void setNivel(No *no) {
    int maximoEsq = getNivel(no->esq);
    int maximoDir = getNivel(no->dir);
    int max = (maximoDir > maximoEsq) ? maximoDir : maximoEsq;
    no->nivel = 1 + max;
}


int getNivel(No *no) {
    return ( no == NULL ) ? 0 : no->nivel;
}

//função para iniciar uma árvore vazia
void iniciar(Arvore *arvore) {
    arvore->raiz = NULL;
    arvore->comparacoes = 0;
}

//inserir um novo elemento no nó
void inserir(Jogador jogador, Arvore *arvore) {
    arvore->raiz = inserirNo(jogador, arvore->raiz);
}

No* inserirNo(Jogador jogador, No *i) {
    if ( i == NULL ) {
        i = novoNo(jogador);
    }
    else if ( strcmp(jogador.nome, i->jogador.nome) < 0 ) {
        i->esq = inserirNo(jogador, i->esq);
    }
    else if ( strcmp(jogador.nome, i->jogador.nome) > 0 ) {
        i->dir = inserirNo(jogador, i->dir);
    }
    else {

    }

    return balancear(i);
}

No* balancear(No *no) {
    if ( no != NULL ) {
        int fator = getNivel(no->dir) - getNivel(no->esq);

        //se balanceada
        if ( abs(fator) <= 1) {
            setNivel(no);
        }
        //se desbalanceada para a direita
        else if ( fator == 2 ) {
            int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

            if ( fatorFilhoDir == - 1 ) {
                no->dir = rotacionarDir(no->dir);
            }

            no = rotacionarEsq(no);
        }
        //se desbalanceada para a esquerda
        else if ( fator == -2 ) {
            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

            if ( fatorFilhoEsq == 1 ) {
                no->esq = rotacionarEsq(no->esq);
            }

            no = rotacionarDir(no);
        }
        else {

        }

        return no;
    }
}

No* rotacionarDir(No *no) {
    No* noEsq = no->esq;
    No* noESqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noESqDir;
    setNivel(no);
    setNivel(noEsq);

    return noEsq;
}


No* rotacionarEsq ( No *no ) {
    No* noDir = no->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    setNivel(no);
    setNivel(noDir);

    return noDir;
}

void pesquisar(char nome[], Arvore *arvore) {
    printf("%s raiz", nome);
    pesquisarNome(nome, arvore->raiz, arvore);
}

void pesquisarNome(char nome[], No* i, Arvore *arvore) {
    arvore->comparacoes++;
    if ( i == NULL ) {
        printf(" NAO\n");
    }
    else if ( strcmp(nome, i->jogador.nome) == 0 ) {
        arvore->comparacoes++;
        printf(" SIM\n");
    }
    else if ( strcmp(nome, i->jogador.nome) < 0 ) {
        arvore->comparacoes++;
        printf(" esq");
        pesquisarNome(nome, i->esq, arvore);
    }
    else if ( strcmp(nome, i->jogador.nome) > 0 ) {
        arvore->comparacoes++;
        printf(" dir");
        pesquisarNome(nome, i->dir, arvore);
    }
}

int getComparacoes(Arvore *arvore) {
    return arvore->comparacoes;
}