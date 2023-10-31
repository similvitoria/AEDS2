/**
 * TP02Q04 - Pesquisa Binaria em c
 * @author: Vitória Símil Araújo  
* */

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"
#include "string.h"
#include "time.h"

#define tamanhoAtributo 50
#define tamanhoEntrada 500
#define tamanhoArray 500

//variável global - array de entradas do arquivo csv
char **entradas;

typedef struct {
    char nome[tamanhoAtributo],
        universidade[tamanhoAtributo],
        cidadeNascimento[tamanhoAtributo],
        estadoNascimento[tamanhoAtributo];

    int id, altura, peso, anoNascimento;
} Jogador;

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

typedef struct {
    Jogador array[tamanhoArray];
    int n, comparacoes, movimentos;

} Lista;

//Cabeçalho - funções
bool isFIM(char input[]);
void ler();
void criarJogador(char *entradasJogador, Jogador *jogador);
void substituirNaoInformado(char *str);
void mostrar(Jogador *jogador);
void iniciar(Lista *lista);
void inserir(Jogador jogador, Lista *lista);
void selecao(Lista *lista);
void swap(int a, int b, Lista *lista);
void pesquisaBinaria(char *nomeJogador, Lista *lista);
int getComparacoes(Lista *lista);


//main
int main(void) {
    //dados 
    Jogador jogador;
    char *entrada = malloc(sizeof(char*) * 10);
    int idJogador = 0;

    //arquivo log
    FILE *fw;
    fw = fopen("740136_pesquisaBinaria.txt", "w+");

    //lista
    Lista lista;
    iniciar(&lista);

    //ler documento csv
    ler();

    //ler entrada de dados
    scanf("%[^\n]", entrada);
    getchar();
    
    idJogador = atoi(entrada);

    while ( !isFIM(entrada) ) {
        criarJogador(entradas[idJogador], &jogador);
        inserir(clone(&jogador), &lista);
        scanf("%[^\n]", entrada);
        getchar();
        idJogador = atoi(entrada);
    }

    selecao(&lista);

    //tempo antes da execução da pesquisa
    clock_t tempoInicio = clock();

    scanf(" %[^\n]", entrada);
    while ( !(isFIM(entrada))) {
        //execute selection sort
        pesquisaBinaria(entrada, &lista);
        scanf(" %[^\n]", entrada);
    }

    //write 
    fprintf(fw, "740136\t%lims\t%icomparações", (clock() - tempoInicio), getComparacoes(&lista));
    
    fclose(fw);

    free(entradas);
    free(entrada);
}

bool isFIM(char input[]) {
    return (input[0] == 'F' && input[1] == 'I' && input[2] == 'M');
}


void ler() {
    //dados
    char jogador[tamanhoEntrada];

    //alocar vetor de entradas
    entradas = (char **) malloc(4000 * sizeof(char *));

    //abrir arquivo csv para leitura
    FILE *csv = fopen("/tmp/players.csv", "r");

    //ler e ignorar o cabeçalho do arquivo csv
    fgets(jogador, sizeof(jogador), csv);

    for(int i = 0; i < 3922; i++) {
        //alocar espaço para string no array de entradas
        entradas[i] = (char *) malloc(tamanhoEntrada * sizeof(char));

        if(fgets(jogador, sizeof(jogador), csv) != NULL) {
            jogador[strcspn(jogador, "\n")] = '\0'; 
            strcpy(entradas[i], jogador);
        }
    }

    fclose(csv);
}

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

void substituirNaoInformado(char *str) {
    char *pos = strstr(str, ",,");
    
    while (pos != NULL) {
        int indice = pos - str;
        
        // Substitua ",," por ",nao informado,"
        memmove(str + indice + strlen("nao informado"), str + indice, strlen(str) - indice);
        strncpy(str + indice + 1, "nao informado", strlen("nao informado"));
        
        // Atualize a posição
        pos = strstr(str, ",,");
    }
}

void mostrar(Jogador *jogador) {
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n",
    jogador->id, jogador->nome, jogador->altura, jogador->peso,jogador->anoNascimento,jogador->universidade,
    jogador->cidadeNascimento, jogador->estadoNascimento);
}

void iniciar(Lista *lista) {
    lista -> n = 0;
    lista-> comparacoes = 0;
}

void inserir(Jogador jogador, Lista *lista) {
    if (lista -> n == tamanhoArray) {
        exit(1);
    }
    lista -> array[lista -> n++] = jogador;
}

void selecao(Lista *lista) {
    int menor = 0;
    for(int i = 0; i < lista->n - 1; i++) {
        menor = i;
        for(int j = (i+1); j < lista->n; j++) {
            if(strcmp(lista->array[j].nome, lista->array[menor].nome) < 0) {
                menor = j;
            }
        }
        swap(i, menor, lista);
    }
}

void swap(int a, int b, Lista *lista) {
    Jogador tmp = lista->array[a];
    lista->array[a] = lista->array[b];
    lista->array[b] = tmp;
}


void pesquisaBinaria(char *nomeJogador, Lista *lista) {
    bool resposta = false;

    int esquerda = 0, direita = lista->n - 1, meio = 0;

    while(esquerda <= direita) {
        meio = (esquerda + direita)/2;

        lista->comparacoes++;
        if (strstr(lista->array[meio].nome, nomeJogador) != NULL ) {
            resposta = true;
            esquerda = lista->n;
        }
        else if (strcmp(lista->array[meio].nome, nomeJogador) < 0 ) {
            esquerda = meio + 1;
            lista->comparacoes++;
        }
        else {
            direita = meio - 1;
            lista->comparacoes += 2;
        }
    }

    if(resposta) printf("%s\n", "SIM");
    else printf("%s\n", "NAO");
}


int getComparacoes(Lista *lista) {
    return lista->comparacoes;
}