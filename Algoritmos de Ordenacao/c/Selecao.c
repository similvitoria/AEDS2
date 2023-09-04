#include "stdio.h"
#include "stdlib.h"

#define MAXTAM 100

//variavel globais
int array[MAXTAM];

//funcoes
void aleatorio();
void swap();
void selecao();
void mostrar();

int main(void) {
    aleatorio();
    mostrar();
    selecao();
    mostrar();
}

void aleatorio() {
    for ( int i = 0; i < MAXTAM; i++) {
        array[i] = rand() % 1000;
    }
}

void swap(int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
}

void selecao() {
    for ( int i = 0; i < (MAXTAM - 1); i++) {
        int menor = i;

        for (int j = (i + 1); j < MAXTAM; j++) {
            if ( array[j] < array[menor] ) {
                menor = j;
            }
        }

        swap(menor, i);
    }
}

void mostrar() {
    printf("%s", "[ ");
    for (int i = 0; i < MAXTAM; i++){
        printf("%i ", array[i]);
    }
    printf("%s\n", "]");
}