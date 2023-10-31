/**
 * TP02Q08 - shellsort c
 * @author: Vitória Símil Araújo  
* */

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"
#include "string.h"
#include "time.h"

#define tamanhoAtributo 100
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
void mostrarLista(Lista *lista);
void shellsort(Lista *lista);
void insercaoPorCor(int cor, int h, Lista *lista);
int getComparacoes(Lista *lista);
int getMovimentacoes(Lista *lista);


//main
int main(void) {
    //dados 
    Jogador jogador;
    char *entrada = malloc(sizeof(char*) * 10);
    int idJogador = 0;

    //arquivo log
    FILE *fw;
    fw = fopen("740136_shellsort.txt", "w+");

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


    //tempo antes da execução da pesquisa
    clock_t tempoInicio = clock();
    shellsort(&lista);

    //write 
    fprintf(fw, "740136\t%lims\t%icomparações\t%imovimentações", (clock() - tempoInicio), getComparacoes(&lista), getMovimentacoes(&lista));
    
    mostrarLista(&lista);
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


void mostrarLista(Lista *lista) {
    Jogador jogador;
    for(int i = 0; i < lista->n; i++) {
        jogador = lista->array[i];
        mostrar(&jogador);
    }
}

void shellsort(Lista *lista) {
    int h = 1;
    do{
        h = ( h * 3 ) + 1;
    } while( h < lista->n );

    do {
        h /= 3;
        for(int cor = 0; cor < h; cor++ ) {
            insercaoPorCor(cor, h, lista);
        }
    }while(h!=1);
}

void insercaoPorCor(int cor, int h, Lista *lista) {
    int j;
    Jogador tmp;
    for(int i = (h + cor); i < lista->n; i += h) {
        tmp = lista->array[i];
        j = i - h;
        while((j >= 0) && (lista->array[j].peso > tmp.peso) ||
        lista->array[j].peso == tmp.peso &&  strcmp(lista->array[j].nome, tmp.nome) > 0){
            lista->array[j + h] = lista->array[j];
            j -= h;
        }
        lista->array[j+h] = tmp;
    }
}

int getComparacoes(Lista *lista) {
    return lista->comparacoes;
}

int getMovimentacoes(Lista *lista) {
    return lista->movimentos;
}
