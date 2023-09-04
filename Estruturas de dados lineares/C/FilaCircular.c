#include "stdio.h"
#include "stdlib.h"

#define MAXTAM 5

typedef struct {
    //dados
    int array[MAXTAM + 1];
    int primeiro, ultimo;
} FilaCircular;

//funções
void iniciar(FilaCircular *fila);
void inserir(int elemento, FilaCircular *fila);
int remover(FilaCircular *fila);
void mostrar(FilaCircular *fila);

int main(void) {
    //dados
    FilaCircular fila;

    //exemplos de comando
    inserir(1, &fila);
    inserir(3, &fila);
    inserir(5, &fila);
    inserir(7, &fila);
    inserir(9, &fila);
    remover(&fila);
    remover(&fila);
    inserir(4, &fila);
    inserir(6, &fila);
    remover(&fila);
    inserir(8, &fila);
    mostrar(&fila);
    
}

/*
    @param fila
*/
void iniciar(FilaCircular *fila) {
    fila->primeiro = fila->ultimo = 0;
}

/*
    @param elemento
    @param fila
*/
void inserir(int elemento, FilaCircular *fila) {
    if ( (fila->ultimo + 1) % (MAXTAM + 1) == fila->primeiro ) {
        exit(1);
    }

    fila->array[fila->ultimo] = elemento;
    fila->ultimo = (fila->ultimo + 1) % (MAXTAM + 1);
}

/*
    @param fila
*/
int remover(FilaCircular *fila) {
    if ( fila->primeiro == fila->ultimo ) {
        exit(1);
    }

    int resposta = fila->array[fila->primeiro];

    fila->primeiro = (fila->primeiro + 1) % (MAXTAM + 1);

    return resposta;
}

/*
    @param fila
*/
void mostrar(FilaCircular *fila) {
    //dados
    int i = fila->primeiro;

    printf("%s", "[ ");

    while(i != fila->ultimo) {
        printf("%i ", fila->array[i]);
        i = ( i+ 1 ) % (MAXTAM + 1);
    }

    printf("%s\n", "]");
}



