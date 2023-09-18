#include "stdio.h"
#include "stdlib.h"

#define MAXTAM 10

//variavel globais
int array[MAXTAM];

//funcoes
void aleatorio();
void swap(int a, int b);
void quicksort(int esq, int dir);
void mostrar();

int main(void) {
    aleatorio();
    mostrar();
    quicksort(0, MAXTAM);
    mostrar();
}

void aleatorio() {
    for ( int i = 0; i < MAXTAM; i++) {
        array[i] = rand() % 100;
    }
}

void swap(int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
}

void quicksort(int esq, int dir) {
    //dados
    int i = esq, j = dir, meio = (esq + dir)/2;

    while ( i <= j ) {
        while ( array[i] < array[meio] ) {
            i++;
        }
        while ( array[j] > array[meio] ) {
            j--;
        }

        if ( i <= j ) {
            swap(i, j);
            i++;
            j--;
        }
    }

    if ( esq < j ) quicksort(esq, j);
    if ( i < dir ) quicksort(i, dir);
}

void mostrar() {
    printf("%s", "[ ");
    for (int i = 0; i < MAXTAM; i++){
        printf("%i ", array[i]);
    }
    printf("%s\n", "]");
}