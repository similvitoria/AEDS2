#include "stdio.h"
#include "stdlib.h"

#define MAXTAM 10

//variavel globai
int array[MAXTAM];

//funções
void aleatorio();
void insercao();
void mostrar();

int main(void) {
    aleatorio();
    mostrar();
    insercao();
    mostrar();
}

void aleatorio() {
    for ( int i = 0; i < MAXTAM; i++) {
        array[i] = rand() % 100;
    }
}

void insercao() {
    //dados
    int j = 0, tmp = 0;

    for ( int i = 1; i < MAXTAM; i++ ) {
        tmp = array[i];
        j = i - 1;

        while ( (j>=0) && array[j] > tmp ) {
            array[j + 1] = array[j];
            j--;
        }

        array[j+1] = tmp;
    }
}

void mostrar() {
    printf("%s", "[ ");
    for (int i = 0; i < MAXTAM; i++){
        printf("%i ", array[i]);
    }
    printf("%s\n", "]");
}