/**
 * TP02Q02 - Classe em c
 * @author: Vitória Símil Araújo  
* */

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"
#include "string.h"

#define tamanhoAtributo 100
#define tamanhoEntrada 500

//variável global - array de entradas do arquivo csv
char **entradas;

typedef struct {
    char nome[tamanhoAtributo],
        universidade[tamanhoAtributo],
        cidadeNascimento[tamanhoAtributo],
        estadoNascimento[tamanhoAtributo];

    int id, altura, peso, anoNascimento;
} Jogador;

//Cabeçalho - funções
bool isFIM(char input[]);
void ler();
void criarJogador(char *entradasJogador, Jogador *jogador);
void substituirNaoInformado(char *str);
void mostrar(Jogador *jogador);


//main
int main(void) {
    //dados 
    Jogador jogador;
    char *entrada = malloc(sizeof(char*) * 10);
    int idJogador = 0;

    //ler documento csv
    ler();

    //ler entrada de dados
    scanf("%[^\n]", entrada);
    getchar();
    
    idJogador = atoi(entrada);

    while ( !isFIM(entrada) ) {
        criarJogador(entradas[idJogador], &jogador);
        mostrar(&jogador);
        scanf("%[^\n]", entrada);
        getchar();
        idJogador = atoi(entrada);
    }

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
        
        // Substituir ",," por ",nao informado,"
        memmove(str + indice + strlen("nao informado"), str + indice, strlen(str) - indice);
        strncpy(str + indice + 1, "nao informado", strlen("nao informado"));
        
        //Atualizar a posição
        pos = strstr(str, ",,");
    }
}

void mostrar(Jogador *jogador) {
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n",
    jogador->id, jogador->nome, jogador->altura, jogador->peso,jogador->anoNascimento,jogador->universidade,
    jogador->cidadeNascimento, jogador->estadoNascimento);
}