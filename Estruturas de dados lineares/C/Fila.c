#include "stdio.h"
#include "stdlib.h"

#define MAXTAM 6

typedef struct {
    //dados
    int array[MAXTAM];
    int n;
}Fila;

//funções
void iniciar(Fila *fila);
void inserir(int elemento, Fila *fila);
int remover(Fila *fila);
void mostrar(Fila *fila);


int main(void) {
    //dados
    Fila fila;

    //exemplos de comando
    inserir(1, &fila);
    inserir(5, &fila);
    remover(&fila);
    inserir(6, &fila);
    remover(&fila);
    mostrar(&fila);
}

/*
    @param fila
*/
void iniciar(Fila *fila) {
    fila->n = 0;
}

/*
    @param elemento
    @param fila
*/
void inserir(int elemento, Fila *fila) {
    if ( fila->n == MAXTAM ) {
        exit(1);
    }

    fila->array[fila->n] = elemento;

    fila->n++;
}

/*
    @param fila
*/
int remover(Fila *fila) {
    if ( fila->n == 0 ) {
        exit(1);
    }

    int resposta = fila->array[0];
    fila->n--;

    for (int i = 0; i < fila->n; i++ ) {
        fila->array[i] = fila->array[i+1];
    }

    return resposta;
}

/*
    @param fila
*/
void mostrar(Fila *fila) {
    printf("%s", "[ ");

    for(int i = 0; i < fila -> n; i++) {
        printf("%i %s", fila -> array[i], "");
    }

    printf("%s", "]\n");
}

