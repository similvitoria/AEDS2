#include <stdlib.h>
#include <stdio.h>
#include <err.h>

// Definição da estrutura Celula que será usada na fila.
typedef struct {
    int elemento;           // Valor armazenado na célula
    struct Celula* prox;    // Ponteiro para a próxima célula na fila
} Celula;

// Definição da estrutura Fila que contém os ponteiros para o primeiro e último elementos.
typedef struct {
    Celula* primeiro;       
    Celula* ultimo;         
} Fila;

// Função para criar uma nova célula com um valor específico.
Celula* novaCelula(int elemento) {
    Celula* nova = (Celula*) malloc(sizeof(Celula)); 
    nova->prox = NULL;             
    nova->elemento = elemento;     
    return nova;                   
}

// Função para iniciar a fila (torna o primeiro elemento nulo).
void start(Fila *fila) {
    fila->primeiro = novaCelula(-1);  
    fila->ultimo = fila->primeiro;    
}

// Função para inserir um elemento na fila.
void inserir(int x, Fila *fila) {
    fila->ultimo->prox = novaCelula(x);  
    fila->ultimo = fila->ultimo->prox;   
}

// Função para remover um elemento da fila e retornar o valor.
int remover(Fila *fila) {
    if (fila->primeiro == fila->ultimo)   
        errx(1, "fila vazia.");           

    Celula* tmp = fila->primeiro->prox;    
    int resposta = tmp->elemento;         
    fila->primeiro->prox = NULL;           
    fila->primeiro = tmp;                 
    tmp = NULL;                           
    free(tmp);                             
    return resposta;                       
}

// Função para mostrar os elementos da fila.
void mostrar(Fila *fila) {
    printf("[ ");
    for(Celula* i = fila->primeiro->prox; i != NULL; i = i->prox ) {
        printf("%d ", i->elemento);  
    }
    printf("]\n");
}

// Função principal (main) onde a fila é inicializada, elementos são inseridos, um é removido e a fila é mostrada.
int main(void) {
    Fila fila;      
    start(&fila);   

    inserir(1, &fila);   
    inserir(2, &fila);
    inserir(3, &fila);

    remover(&fila);    

    mostrar(&fila);    
}
