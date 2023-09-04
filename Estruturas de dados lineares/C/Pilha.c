#include "stdio.h"
#include "stdlib.h"

#define MAXTAM 6

typedef struct {
    //dados
    int array[MAXTAM];
    int n;
} Pilha;

//funcões
void iniciar(Pilha *pilha);
void empilhar(int elemento, Pilha *pilha);
int desempilhar(Pilha *pilha);
void mostrar(Pilha *pilha);

int main(void) {
    //dados
    Pilha pilha;

    //exemplos de comando
    empilhar(1, &pilha);
    empilhar(5, &pilha);
    desempilhar(&pilha);
    mostrar(&pilha);
}

/*
    @param pilha
*/
void iniciar(Pilha *pilha) {
    pilha->n = 0;
}

/*
   @param elemento
   @param pilha
*/
void empilhar(int elemento, Pilha *pilha) {
    if ( pilha->n == MAXTAM ) {
        exit(1);
    }

    pilha->array[pilha->n++] = elemento;
}

/*
    @param pilha
*/
int desempilhar(Pilha *pilha) {
    if (pilha->n == 0 ) {
        exit(1);
    }

    pilha->n--;
    
    return pilha->array[pilha->n];
}

/*
    @param lista
*/
void mostrar(Pilha *pilha) {
    printf("%s", "[ ");

    for(int i = 0; i < pilha -> n; i++) {
        printf("%i %s", pilha -> array[i], "");
    }

    printf("%s", "]\n");
}