/**
 * TP03Q07 - Fila Circular com alocação flexível em C
 * @author: Vitória Símil Araújo  
*/

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"
#include "string.h"
#include "math.h"

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
typedef struct Celula {
    struct Celula* prox;
    Jogador elemento;
} Celula;

// Definição da estrutura de dados para representar uma fila circular
typedef struct Fila {
    Celula* primeiro;
    Celula* ultimo;
    int elementos;
} Fila;

// Cabeçalho de funções
bool isFIM(char input[]);
void ler();

//funções jogador
void criarJogador(char *entradasJogador, Jogador *jogador);
void substituirNaoInformado(char *str);
Jogador clone(Jogador *jogador);
void imprimir(Jogador *jogador);

//funções fila
Celula* novaCelula(Jogador jogador);
void iniciar(Fila *fila);
void inserir(Jogador jogador, Fila *fila);
Jogador remover(Fila *fila);
int getAltura(Fila *fila);
void mostrar(Fila *fila);

// Função principal
int main(void) {
    // Dados 
    Jogador jogador;
    Jogador jogadores[3922];
    char *entrada = malloc(sizeof(char*) * 10);
    
    int idJogador = 0;

    // Ler documento CSV
    ler();

    // Criar nova fila
    Fila fila;
    iniciar(&fila);

    // Preencher o array de jogadores com dados do arquivo CSV
    for(int i = 0; i < 3922; i++ ) {
        criarJogador(entradas[i], &jogador);
        jogadores[i] = jogador;
    }

    // Ler IDs de jogadores a partir da entrada padrão e inseri-los na fila
    scanf("%s", entrada);
    while(!isFIM(entrada)) {
        idJogador = atoi(entrada);
        inserir(clone(&jogadores[idJogador]), &fila);
        printf("%d\n", getAltura(&fila));
        scanf("%s", entrada);
    }

    int n = 0, pos = 0;
    scanf("%d", &n);

    while ( n > 0 ) {
        scanf("%s", entrada);
        if( strcmp(entrada, "I") == 0 ) {
            scanf("%d", &idJogador);
            inserir(clone(&jogadores[idJogador]), &fila);
            printf("%d\n", getAltura(&fila));
        }
        else if ( strcmp(entrada, "R" ) == 0 ){
            jogador = remover(&fila);
            printf("(R) %s\n", jogador.nome);
        }

        n--;
    }

    // Exibir a fila resultante
    mostrar(&fila);

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

// Função para criar uma nova célula
Celula* novaCelula(Jogador jogador) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = jogador;
    nova->prox = NULL;
    return nova;
}

// Função para inicializar a fila
void iniciar(Fila *fila) {
    Jogador j;
    fila->primeiro = novaCelula(j);
    fila->ultimo = fila->primeiro;
    fila->elementos = 0;
}

// Função para inserir um jogador na fila
void inserir(Jogador jogador, Fila *fila) {
    if ( fila->elementos == 5 ) remover(fila);
    fila->ultimo->prox = novaCelula(jogador);
    fila->ultimo = fila->ultimo->prox;
    fila->elementos++;
}

// Função para remover um jogador da fila
Jogador remover(Fila *fila) {
    if ( fila->primeiro == fila->ultimo ) {
        exit(0);
    }

    Celula* tmp = fila->primeiro;
    fila->primeiro = fila->primeiro->prox;
    Jogador jogador = fila->primeiro->elemento;
    tmp->prox = NULL;
    free(tmp);

    fila->elementos--;

    return jogador;
}

// Função para calcular e retornar a altura média dos jogadores na fila
int getAltura(Fila *fila) {
    double altura = 0;
    
    for ( Celula* i = fila->primeiro->prox; i != NULL; i = i->prox) {
        altura += i->elemento.altura;
    }

    double media = altura/fila->elementos;

    return ((media - (int)media)  < 0.5 ) ? round(media) : ceil(media);
}

// Função para mostrar os jogadores na fila
void mostrar(Fila *fila) {
    int j = 0;
    for(Celula* i = fila->primeiro->prox; i != NULL; i=i->prox, j++) {
        printf("[%i]", j);
        imprimir(&i->elemento);
    }
}
