#include "stdio.h"
#include "stdlib.h"

#define MAXTAM 6

typedef struct {
    int array[MAXTAM];
    int n;

} Lista;

//functions
void iniciar(Lista *lista);
void inserirInicio(int elemento, Lista *lista);
void inserirFim(int elemento, Lista *lista);
void inserir(int elemento, int pos, Lista *lista);
int removerInicio(Lista *lista);
int removerFim(Lista *lista);
int remover(int pos, Lista *lista);
void mostrar(Lista *lista);


int main(void) {
    //dados
    Lista lista;

    //exemplos de comando
    iniciar(&lista);
    inserirFim(2, &lista);
    inserirFim(1, &lista);
    remover(1, &lista);
    removerFim(&lista);
    inserir(1, 0, &lista);
    inserir(2, 0, &lista);
    mostrar(&lista);
}

/*
    @param lista
*/
void iniciar(Lista *lista) {
    lista -> n = 0;
}

/*
    @param  elemento
    @param lista
*/
void inserirInicio(int elemento, Lista *lista) {
    if (lista -> n == MAXTAM) {
        exit(1);
    }

    for ( int i = lista -> n; i > 0; i-- ) {
        lista -> array[i] = lista -> array[i - 1];
    }

    lista -> array[0] = elemento;

    lista -> n++;
}

/*
    @param  elemento
    @param lista
*/
void inserirFim(int elemento, Lista *lista) {
    if (lista -> n == MAXTAM) {
        exit(1);
    }

    lista -> array[lista -> n++] = elemento;
}

/*
    @param  elemento
    @param lista
*/
void inserir(int elemento, int pos, Lista *lista) {
    if (lista -> n == MAXTAM || pos < 0 || pos > lista -> n)  {
        exit(1);
    }

    for ( int i = lista -> n; i > pos; i--) {
        lista -> array[i] = lista -> array[i - 1];
    }

    lista -> array[pos] = elemento;
    lista -> n++;
}

/*
   @param lista
*/
int removerInicio(Lista *lista) {
    if (lista -> n == 0) {
        exit(1);
    }

    int resposta = lista -> array[0];
    lista -> n--;

    for ( int i = 0; i < lista -> n; i++) {
        lista -> array[i] = lista -> array[i + 1];
    }

    return resposta;
}

/*
   @param lista
*/
int removerFim(Lista *lista) {
    if (lista -> n == 0) {
        exit(1);
    }

    lista -> n--;

    int resposta = lista -> array[lista -> n];

    return resposta;
}

/*
    @param  pos
    @param lista
*/
int remover(int pos, Lista *lista) {
    if (lista -> n == 0) {
        exit(1);
    }

    int resposta = lista -> array[pos];

    lista -> n--;

    for ( int i = pos; i < lista -> n; i++) {
        lista -> array[i] = lista -> array[ i + 1 ];
    }
}

/*
    @param lista
*/
void mostrar(Lista *lista) {
    printf("%s", "[ ");

    for(int i = 0; i < lista -> n; i++) {
        printf("%i %s", lista -> array[i], "");
    }

    printf("%s", "]\n");
}

