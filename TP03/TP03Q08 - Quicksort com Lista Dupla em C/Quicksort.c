/**
 * TP03Q08 - Quicksort com lista dupla em C
 * @author: Vitória Símil Araújo  
 */

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"
#include "string.h"
#include "time.h"

#define tamanhoAtributo 100
#define tamanhoEntrada 500

// Variável global - array de entradas do arquivo csv
char **entradas;

// Definição da estrutura Jogador
typedef struct {
    char nome[tamanhoAtributo],
         universidade[tamanhoAtributo],
         cidadeNascimento[tamanhoAtributo],
         estadoNascimento[tamanhoAtributo];

    int id, altura, peso, anoNascimento;
} Jogador;

// Definição da estrutura Celula
typedef struct Celula {
    struct Celula* prox;
    struct Celula* ant;
    Jogador elemento;
} Celula;

// Definição da estrutura Lista
typedef struct Lista {
    Celula* primeiro;
    Celula* ultimo;
    int movimentacoes, comparacoes, tamanho;
} Lista;

// Cabeçalho - declaração das funções
bool isFIM(char input[]);
void ler();

//funções do jogador
void criarJogador(char *entradasJogador, Jogador *jogador);
void substituirNaoInformado(char *str);
Jogador clone(Jogador *jogador);
void imprimir(Jogador *jogador);

//funções da lista
Celula* novaCelula(Jogador jogador);
void iniciar(Lista *lista);
void inserir(Jogador jogador, Lista *lista);
void mostrar(Lista *lista);

//Funções para executar quicksort
Celula* getElemento(int pos, Lista *lista);
void swap(Celula* a, Celula* b);
void quicksort(int esq, int dir, Lista *lista);

// Função principal - main
int main(void) {
    //arquivo para escrita
    FILE *fw;
    fw = fopen("740136_quickSort.txt", "w+");

    // Dados 
    Jogador jogador;
    Jogador jogadores[3922];
    char *entrada = malloc(sizeof(char*) * 10);
    
    int idJogador = 0;

    // Ler documento csv
    ler();

    // Criar nova lista
    Lista lista;
    iniciar(&lista);

    // Preencher array de jogadores
    for(int i = 0; i < 3922; i++ ) {
        criarJogador(entradas[i], &jogador);
        jogadores[i] = jogador;
    }

    // Ler entrada e inserir jogadores na lista até encontrar "FIM"
    scanf("%s", entrada);
    while (!isFIM(entrada)) {
        idJogador = atoi(entrada);
        inserir(clone(&jogadores[idJogador]), &lista);
        scanf("%s", entrada);
    }

    //get time before doing the selection sort
    clock_t start = clock();

    // Ordenar a lista usando o algoritmo Quicksort
    quicksort(0, lista.tamanho - 1, &lista);

    // Exibir jogadores ordenados
    mostrar(&lista);

    //write 
    fprintf(fw, "740136\t%lims\t%icomparisons\t%imovements", (clock() - start), lista.comparacoes, lista.movimentacoes);
    
    //close file
    fclose(fw);

    // Liberar memória alocada
    free(entrada);
    free(entradas);
    return 0;
}

// Função para verificar se a entrada é "FIM"
bool isFIM(char input[]) {
    return (input[0] == 'F' && input[1] == 'I' && input[2] == 'M');
}

// Função para ler dados do arquivo CSV
void ler() {
    // Dados
    char jogador[tamanhoEntrada];

    // Alocar vetor de entradas
    entradas = (char **) malloc(4000 * sizeof(char *));

    // Abrir arquivo csv para leitura
    FILE *csv = fopen("/tmp/players.csv", "r");

    // Ler e ignorar o cabeçalho do arquivo csv
    fgets(jogador, sizeof(jogador), csv);

    // Preencher array de entradas
    for(int i = 0; i < 3922; i++) {
        // Alocar espaço para string no array de entradas
        entradas[i] = (char *) malloc(tamanhoEntrada * sizeof(char));

        // Ler linha do arquivo e armazenar no array
        if(fgets(jogador, sizeof(jogador), csv) != NULL) {
            jogador[strcspn(jogador, "\n")] = '\0'; 
            strcpy(entradas[i], jogador);
        }
    }

    // Fechar arquivo csv
    fclose(csv);
}

// Função para criar um jogador a partir de uma string CSV
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

// Função para substituir campos não informados por "nao informado" na string CSV
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

// Função para criar uma cópia do jogador
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

// Função para imprimir informações de um jogador
void imprimir(Jogador *jogador) {
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n",
           jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento,
           jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

// Função para inicializar a lista
void iniciar(Lista *lista) {
    Jogador j;
    lista->primeiro = novaCelula(j);
    lista->ultimo = lista->primeiro;
    lista->movimentacoes = lista->comparacoes = lista->tamanho = 0;
}

// Função para criar uma nova célula da lista
Celula* novaCelula(Jogador jogador) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = jogador;
    nova->prox = nova->ant = NULL;
    return nova;
}

// Função para inserir um jogador na lista
void inserir(Jogador jogador, Lista *lista) {
    lista->ultimo->prox = novaCelula(jogador);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
    lista->tamanho++;
}

// Função para mostrar os jogadores da lista
void mostrar(Lista *lista) {
    for(Celula* tmp = lista->primeiro->prox; tmp != NULL; tmp = tmp->prox) {
        imprimir(&tmp->elemento);
    }
}

// Função para obter a célula em uma posição específica na lista
Celula* getElemento(int pos, Lista *lista) {
    Celula* tmp = lista->primeiro->prox;
    for (int i = 0; i < pos; i++, tmp = tmp->prox);
    return tmp;
}

// Função para trocar dois elementos da lista
void swap(Celula* a, Celula* b) {
    Jogador tmp = a->elemento;
    a->elemento = b->elemento;
    b->elemento = tmp;
}

// Função que implementa o algoritmo Quicksort para ordenar a lista
void quicksort(int esquerda, int direita, Lista *lista) {
    // Escolhe o pivô como o elemento do meio
    int pivo = (esquerda + direita) / 2, i = esquerda, j = direita;

    Celula* l = getElemento(esquerda, lista);
    Celula* r = getElemento(direita, lista);
    Jogador h = getElemento(pivo, lista)->elemento;

        // Parte principal do algoritmo quicksort
        while (i <= j) {
            while (strcmp(l->elemento.estadoNascimento, h.estadoNascimento) < 0
                || (strcmp(l->elemento.estadoNascimento, h.estadoNascimento) == 0
                    && strcmp(l->elemento.nome, h.nome) < 0)) {
                lista->comparacoes++;
                l = l->prox;
                i++;
            }

            while (strcmp(r->elemento.estadoNascimento, h.estadoNascimento) > 0
                || (strcmp(r->elemento.estadoNascimento, h.estadoNascimento) == 0
                    && strcmp(r->elemento.nome, h.nome) > 0)) {
                lista->comparacoes++;
                r = r->ant;
                j--;
            }

            lista->comparacoes++;
            if (i <= j) {
                swap(l, r);
                l = l->prox;
                r = r->ant;
                i++;
                j--;
            }
        }

        // Chama recursivamente o quicksort para as partes não ordenadas
        if (esquerda < j)
            quicksort(esquerda, j, lista);
        if (direita > i)
            quicksort(i, direita, lista);
    }
