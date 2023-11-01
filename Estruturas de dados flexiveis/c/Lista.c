#include <stdlib.h>
#include <stdio.h>
#include <err.h>


// Definição da estrutura Celula que será usada na lista.
typedef struct {
    int elemento;            // Valor armazenado na célula
    struct Celula* prox;     // Ponteiro para a próxima célula na lista
} Celula;

typedef struct {
    Celula* primeiro;
    Celula* ultimo;
    int tamanho;
} Lista;

// Função para criar uma nova célula com um valor específico.
Celula* novaCelula(int elemento) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->prox = NULL;
    nova->elemento = elemento;
    return nova;
}

// Função para iniciar a lista (torna o primeiro elemento nulo e zera o tamanho).
void start(Lista *lista) {
    lista->primeiro = novaCelula(-1);
    lista->ultimo = lista->primeiro;
    lista->tamanho = 0;
}

// Função para inserir um elemento no início da lista.
void inserirInicio(int elemento, Lista *lista) {
    Celula* tmp = novaCelula(elemento);
    tmp->prox = lista->primeiro->prox;
    lista->primeiro->prox = tmp;
    if(lista->primeiro == lista->ultimo) lista->ultimo = tmp;
    lista->tamanho++;
    tmp = NULL;
}

// Função para inserir um elemento no fim da lista.
void inserirFim(int elemento, Lista *lista) {
    lista->ultimo->prox = novaCelula(elemento);
    lista->ultimo = lista->ultimo->prox;
    lista->tamanho++;
}

// Função para inserir um elemento em uma posição específica na lista.
void inserir(int pos, int elemento, Lista *lista) {
    if( pos < 0 || pos > lista->tamanho ) errx(1, "Posição inválida.");
    else if ( pos == 0 ) inserirInicio(elemento, lista);
    else if ( pos == lista->tamanho ) inserirFim(elemento, lista);
    else {
        Celula* i = lista->primeiro;
        for(int j = 0; j < pos; j++, i = i->prox);
        Celula* tmp = novaCelula(elemento);
        tmp->prox = i->prox;
        i->prox = tmp;
        tmp = NULL;
        i = NULL;
        lista->tamanho++;
    }
}

// Função para remover o elemento no início da lista e retornar seu valor.
int removerInicio(Lista *lista) {
    if(lista->primeiro == lista->ultimo ) errx(1, "Lista vazia.");
    Celula* tmp = lista->primeiro->prox;
    int resposta = tmp->elemento;
    lista->primeiro->prox = NULL;
    lista->primeiro = tmp;
    tmp = NULL;
    free(tmp);
    lista->tamanho--;
    return resposta;
}

// Função para remover o elemento no fim da lista e retornar seu valor.
int removerFim(Lista *lista) {
    if(lista->primeiro == lista->ultimo ) errx(1, "Lista vazia.");
    int resposta = lista->ultimo->elemento;
    
    Celula* i;
    for(i = lista->primeiro->prox; i->prox != lista->ultimo; i = i->prox);
    lista->ultimo = i;
    lista->ultimo->prox = NULL;
    i = NULL;
    free(i);
    lista->tamanho--;
    return resposta;
}
// Função para remover o elemento em uma posição específica na lista e retornar seu valor.
int remover(int pos, Lista *lista) {
    int resposta = 0;
    if( lista->primeiro == lista->ultimo || pos < 0 || pos >= lista->tamanho ) errx(1, "Lista vazia ou posição inválida");
    else if ( pos == 0 ) removerInicio(lista);
    else if ( pos == lista->tamanho - 1) removerFim(lista);
    else {
        Celula* i = lista->primeiro;
        for(int j = 0; j < pos; j++, i = i->prox);
        Celula* tmp = i->prox;
        resposta = tmp->elemento;
        i->prox = tmp->prox;
        tmp = tmp->prox = NULL;
        free(tmp);
        lista->tamanho--;
    }
    return resposta;
}

void mostrar(Lista *lista) {
    printf("[ ");
    for (Celula* i = lista->primeiro->prox; i != NULL; i = i->prox) {
        printf("%d ", i->elemento);  
    }
    printf("] \n");
}

int main(void) {
    Lista lista;
    start(&lista);
    inserirFim(5, &lista);
    inserirFim(7, &lista);
    mostrar(&lista);
    removerInicio(&lista);
    mostrar(&lista);
    inserirInicio(5, &lista);
    inserirInicio(3, &lista);
    mostrar(&lista);
    removerFim(&lista);
    mostrar(&lista);
    inserirFim(7, &lista);
    mostrar(&lista);
    inserir(2, 6, &lista);
    inserir(1, 4, &lista);
    removerInicio(&lista);
    removerInicio(&lista);
    remover(1, &lista);
    mostrar(&lista);

}