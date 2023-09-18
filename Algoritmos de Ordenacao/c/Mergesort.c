#include "stdio.h"
#include "stdlib.h"
#include "limits.h"

#define MAXTAM 10

//variavel globais
int array[MAXTAM];

//funcoes
void aleatorio();
void mergesort(int esq, int dir);
void intercalar(int esq, int meio, int dir);
void mostrar();

int main(void) {
    aleatorio();
    mostrar();
    mergesort(0, MAXTAM);
    mostrar();
}

void aleatorio() {
    for ( int i = 0; i < MAXTAM; i++) {
        array[i] = rand() % 100;
    }
}

void intercalar(int esq, int meio, int dir) {
    //dados
    int iEsq = 0, iDir = 0, i = 0;
    int nEsq = (meio + 1) - esq;
    int nDir = dir - meio;

    //criar subarrays
    int arrayEsq[nEsq + 1], arrayDir[nDir + 1];

    arrayEsq[nEsq] = arrayDir[nDir] = INT_MAX;

    //montar subarrays
    for (iEsq = 0; iEsq < nEsq; iEsq ++) {
        arrayEsq[iEsq] = array[esq+iEsq];
    }

    for (iDir = 0; iDir < nDir; iDir++) {
        arrayDir[iDir] = array[(meio + 1) + iDir];
    }

    //intercalação propriamente dita
    for (iEsq = iDir = 0, i = esq; i <= dir; i++){
        array[i] = (arrayEsq[iEsq] <= arrayDir[iDir]) ? arrayEsq[iEsq++] : arrayDir[iDir++];
    }
}

void mergesort(int esq, int dir) {
    if ( esq < dir ) {
        int meio = (esq + dir) / 2;
        mergesort(esq, meio);
        mergesort(meio+1, dir);
        intercalar(esq, meio, dir);
    }
}

void mostrar() {
    printf("%s", "[ ");
    for (int i = 0; i < MAXTAM; i++){
        printf("%i ", array[i]);
    }
    printf("%s\n", "]");
}