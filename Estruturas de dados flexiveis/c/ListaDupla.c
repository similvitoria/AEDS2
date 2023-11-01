#include <stdio.h>
#include <stdlib.h>
#include <err.h>

// Definição da estrutura Celula que será usada na lista dupla.
typedef struct Celula {
    int elemento; // Valor armazenado na célula
    struct Celula* ant; // Ponteiro para a célula anterior na lista
    struct Celula* prox; // Ponteiro para a próxima célula na lista
} Celula;

// Definição da estrutura ListaDupla que contém os ponteiros para o primeiro, último elemento e o tamanho.
typedef struct {
    Celula* primeiro;
    Celula* ultimo;
    int tamanho;
} ListaDupla;

// Função para criar uma nova célula com um valor específico.
Celula* novaCelula(int elemento) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->ant = nova->prox = NULL;
    nova->elemento = elemento;
    return nova;
}

// Função para iniciar a lista dupla (torna o primeiro elemento nulo, inicializa o tamanho).
void start(ListaDupla *lista) {
    lista->primeiro = novaCelula(-1);
    lista->ultimo = lista->primeiro;
    lista->tamanho = 0;
}

// Função para inserir um elemento no início da lista dupla.
void inserirInicio(int elemento, ListaDupla *lista) {
    Celula* tmp = novaCelula(elemento);
    tmp->ant = lista->primeiro;
    tmp->prox = lista->primeiro->prox;
    lista->primeiro->prox = tmp;
    if(lista->primeiro == lista->ultimo) lista->ultimo = tmp;
    else tmp->prox->ant = tmp;
    lista->tamanho++;
    tmp = NULL;
}

// Função para inserir um elemento no fim da lista dupla.
void inserirFim(int elemento, ListaDupla *lista) {
    lista->ultimo->prox = novaCelula(elemento);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
    lista->tamanho++;
}

// Função para inserir um elemento em uma posição específica na lista dupla.
void inserir(int pos, int elemento, ListaDupla *lista) {
    if(pos < 0 || pos > lista->tamanho) errx(1, "Posição inválida.");
    else if ( pos == 0 ) inserirInicio(elemento, lista);
    else if ( pos == lista->tamanho ) inserirFim(elemento, lista);
    else {
        Celula* i = lista->primeiro;
        for(int j = 0; j < pos; j++, i = i->prox);
        Celula* tmp = novaCelula(elemento);
        tmp->ant = i;
        tmp->prox = i->prox;
        tmp->ant->prox = tmp;
        tmp->prox->ant = tmp;
        i = NULL;
        tmp = NULL;
        lista->tamanho++;
    }
}

// Função para remover o elemento no início da lista dupla e retornar seu valor.
int removerInicio(ListaDupla *lista) {
    if( lista->primeiro == lista->ultimo ) errx(1, "Lista vazia.");
    Celula* tmp = lista->primeiro->prox;
    int resposta = tmp->elemento;
    lista->primeiro->prox = NULL;
    lista->primeiro = tmp;
    tmp = NULL;
    free(tmp);
    lista->tamanho--;
    return resposta;
}

// Função para remover o elemento no fim da lista dupla e retornar seu valor.
int removerFim(ListaDupla *lista) {
    if( lista->primeiro == lista->ultimo ) errx(1, "Lista vazia.");
    int resposta = lista->ultimo->elemento;
    lista->ultimo = lista->ultimo->ant;
    lista->ultimo->prox->ant = NULL;
    lista->ultimo->prox = NULL;
    lista->tamanho--;
    free(lista->ultimo->prox);
    return resposta;
}

// Função para remover um elemento em uma posição específica na lista dupla.
int remover(int pos, ListaDupla *lista) {
    int resposta = 0;
    if( lista->primeiro == lista->ultimo || pos < 0 || pos >= lista->tamanho ) errx(1, "Lista vazia ou posição inválida.");
    else if ( pos == 0 ) removerInicio(lista);
    else if ( pos == lista->tamanho - 1 ) removerFim(lista);
    else {
        Celula* i = lista->primeiro;
        for(int j = 0; j < pos; j++, i = i->prox );
        Celula* tmp = i->prox;
        resposta = tmp->elemento;
        i->prox = tmp->prox;
        tmp->prox->ant = i;
        tmp->prox = NULL;
        tmp->ant = NULL;
        tmp = NULL;
        free(tmp);
        lista->tamanho--;
    }
    return resposta;
}

void mostrar(ListaDupla *lista) {
    printf("[ ");
    for(Celula* i = lista->primeiro->prox; i != NULL; i = i->prox) {
        printf("%d ", i->elemento);
    }
    printf("]\n");
}

int main(void) {
    ListaDupla lista;
    start(&lista);
    inserirInicio(1, &lista);
    inserirInicio(2, &lista);
    inserirInicio(3, &lista);
    inserirFim(4, &lista);
    inserir(2, 0, &lista);
    removerInicio(&lista);
    removerFim(&lista);
    remover(1, &lista);
    mostrar(&lista);
}