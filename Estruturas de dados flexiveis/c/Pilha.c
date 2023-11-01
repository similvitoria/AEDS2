#include <stdio.h>
#include <stdlib.h>
#include <err.h>

// Definindo a estrutura da célula que será usada na pilha.
typedef struct Celula {
    int elemento;           // O valor armazenado na célula
    struct Celula* prox;    // Ponteiro para a próxima célula na pilha
} Celula;

// Função para criar uma nova célula com um valor específico.
Celula* novaCelula(int elemento) {
    Celula* nova = (Celula*) malloc(sizeof(Celula)); 
    nova->elemento = elemento;  
    nova->prox = NULL;          
    return nova;               
}

// Definindo a estrutura da pilha que contém um ponteiro para o topo.
typedef struct {
    Celula* topo; // O ponteiro para o topo da pilha
} Pilha;

// Função para inicializar a pilha (torna o topo nulo).
void start(Pilha *pilha) {
    pilha->topo = NULL;  
}

// Função para empilhar (adicionar) um elemento na pilha.
void empilhar(int x, Pilha *pilha) {
    Celula* tmp = novaCelula(x);    
    tmp->prox = pilha->topo;        
    pilha->topo = tmp;              
    tmp = NULL;                     
}

// Função para desempilhar (remover) um elemento da pilha e retornar o valor.
int desempilhar(Pilha *pilha) {
    if (pilha->topo == NULL)      
        errx(1, "Pilha vazia.");  

    int elemento = pilha->topo->elemento;  
    Celula* tmp = pilha->topo->prox;       
    pilha->topo->prox = NULL;              
    pilha->topo = tmp;                    
    tmp = NULL;                           
    free(tmp);                            
    return elemento;                      
}

void mostrar(Pilha *pilha) {
    printf("[ ");
    for (Celula* i = pilha->topo; i != NULL; i = i->prox) {
        printf("%d ", i->elemento);  
    }
    printf("] \n");
}

int main(void) {
    Pilha pilha;  
    start(&pilha);  

    empilhar(1, &pilha);
    empilhar(2, &pilha);
    empilhar(3, &pilha);

    desempilhar(&pilha);

    mostrar(&pilha);
}
