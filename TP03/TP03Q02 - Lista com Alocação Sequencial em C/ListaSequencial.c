/**
 * TP03Q02 - Lista Sequencial em c
 * @author: Vitória Símil Araújo  
* */

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"
#include "string.h"

// Definição de constantes
#define tamanhoAtributo 100
#define tamanhoEntrada 500
#define MAXTAM 200

// Declaração de uma variável global - array de entradas do arquivo CSV
char **entradas;

// Definição de uma estrutura de dados para representar um jogador
typedef struct {
    char nome[tamanhoAtributo],
        universidade[tamanhoAtributo],
        cidadeNascimento[tamanhoAtributo],
        estadoNascimento[tamanhoAtributo];

    int id, altura, peso, anoNascimento;
} Jogador;

// Definição de uma estrutura de dados para representar uma lista de jogadores
typedef struct {
    Jogador array[MAXTAM];
    int n; // Número atual de elementos na lista
} Lista;

// Cabeçalho de funções
bool isFIM(char input[]);
void ler();

//funções jogador
void criarJogador(char *entradasJogador, Jogador *jogador);
void substituirNaoInformado(char *str);
Jogador clone(Jogador *jogador);
void imprimir(Jogador *jogador);

//funções lista
void iniciar(Lista *lista);
void inserirInicio(Jogador jogador, Lista *lista);
void inserirFim(Jogador jogador, Lista *lista);
void inserir(Jogador jogador, int pos, Lista *lista);
Jogador removerInicio(Lista *lista);
Jogador removerFim(Lista *lista);
Jogador remover(int pos, Lista *lista);
void mostrar(Lista *lista);

// Função principal
int main(void) {
    // Declaração de variáveis
    Jogador jogador;
    Jogador jogadores[3922];
    char *entrada = malloc(sizeof(char*) * 10);
    
    int idJogador = 0;

    // Ler o documento CSV e armazenar os dados em um array
    ler();

    // Criar uma nova lista
    Lista lista;
    iniciar(&lista);

    // Preencher o array de jogadores com dados do arquivo CSV
    for(int i = 0; i < 3922; i++ ) {
        criarJogador(entradas[i], &jogador);
        jogadores[i] = jogador;
    }

    // Ler IDs de jogadores a partir da entrada padrão e inseri-los no final da lista
    scanf("%s", entrada);
    while(!isFIM(entrada)) {
        idJogador = atoi(entrada);
        inserirFim(clone(&jogadores[idJogador]), &lista);
        scanf("%s", entrada);
    }

    // Realizar operações adicionais na lista com base na entrada do usuário
    int n = 0, pos = 0;
    scanf("%d", &n);

    while ( n > 0 ) {
        scanf("%s ", entrada);
        if( strcmp(entrada, "II") == 0 ) {
            scanf("%d", &idJogador);
            inserirInicio(clone(&jogadores[idJogador]), &lista);
        }
        else if ( strcmp(entrada, "IF" ) == 0 ) {
            scanf("%d", &idJogador);
            inserirFim(clone(&jogadores[idJogador]), &lista);
        }
        else if ( strcmp(entrada, "I*" ) == 0 ) {
           scanf("%d", &pos);
           scanf("%d", &idJogador);
           inserir(clone(&jogadores[idJogador]), pos, &lista);
        }
        else if ( strcmp(entrada, "R*") == 0 ) {
            scanf("%d", &pos);
            jogador = remover(pos, &lista);
            printf("(R) %s\n", jogador.nome);
        }
        else if ( strcmp(entrada, "RI") == 0 ) {
            jogador = removerInicio(&lista);
            printf("(R) %s\n", jogador.nome);
        }
        else if ( strcmp(entrada, "RF" ) == 0 ){
            jogador = removerFim(&lista);
            printf("(R) %s\n", jogador.nome);
        }

        n--;
    }

    // Exibir a lista resultante
    mostrar(&lista);

    // Liberar memória alocada
    free(entrada);
    free(entradas);
}

// Função para verificar se a entrada é "FIM"
bool isFIM(char input[]) {
    return (input[0] == 'F' && input[1] == 'I' && input[2] == 'M');
}

// Função para ler os dados do arquivo CSV e armazená-los em um array
void ler() {
    char jogador[tamanhoEntrada];

    // Alocar vetor de entradas
    entradas = (char **) malloc(4000 * sizeof(char *));

    // Abrir arquivo CSV para leitura
    FILE *csv = fopen("/tmp/players.csv", "r");

    // Ler e ignorar o cabeçalho do arquivo CSV
    fgets(jogador, sizeof(jogador), csv);

    for(int i = 0; i < 3922; i++) {
        // Alocar espaço para string no array de entradas
        entradas[i] = (char *) malloc(tamanhoEntrada * sizeof(char));

        if(fgets(jogador, sizeof(jogador), csv) != NULL) {
            jogador[strcspn(jogador, "\n")] = '\0'; 
            strcpy(entradas[i], jogador);
        }
    }

    fclose(csv);
}

// Função para criar um objeto Jogador a partir de uma string de entrada CSV
void criarJogador(char *entradasJogador, Jogador *jogador) {
    substituirNaoInformado(entradasJogador);

    char *atributo;
    atributo = strtok(entradasJogador, ",");
    jogador->id = atoi(atributo);

    atributo = strtok(NULL, ",");
    strcpy(jogador->nome, atributo);
    
    atributo = strtok(NULL, ",");
    jogador->altura = atoi(atributo);

    atributo = strtok(NULL, ",");
    jogador->peso = atoi(atributo);

    atributo = strtok(NULL, ",");
    strcpy(jogador->universidade, atributo);

    atributo = strtok(NULL, ",");
    jogador->anoNascimento = atoi(atributo);

    atributo = strtok(NULL, ",");
    if(atributo == NULL) strcpy(jogador->cidadeNascimento, "nao informado");
    else strcpy(jogador->cidadeNascimento, atributo);

    atributo = strtok(NULL, ",");
    if(atributo == NULL) strcpy(jogador->estadoNascimento, "nao informado");
    else strcpy(jogador->estadoNascimento, atributo);
}

// Função para substituir valores não informados na string por "nao informado"
void substituirNaoInformado(char *str) {
    char *pos = strstr(str, ",,");
    
    while (pos != NULL) {
        int indice = pos - str;
        
        // Substituir ",," por ",nao informado,"
        memmove(str + indice + strlen("nao informado"), str + indice, strlen(str) - indice);
        strncpy(str + indice + 1, "nao informado", strlen("nao informado"));
        
        // Atualizar a posição
        pos = strstr(str, ",,");
    }
}

// Função para clonar um objeto Jogador
Jogador clone(Jogador *jogador) {
    Jogador jogadorClonado;

    strcpy(jogadorClonado.nome, jogador->nome);
    strcpy(jogadorClonado.universidade, jogador->universidade);
    strcpy(jogadorClonado.cidadeNascimento, jogador->cidadeNascimento);
    strcpy(jogadorClonado.estadoNascimento, jogador->estadoNascimento);

    jogadorClonado.id = jogador->id;
    jogadorClonado.altura = jogador->altura;
    jogadorClonado.peso = jogador->peso;
    jogadorClonado.anoNascimento = jogador->anoNascimento;

    return jogadorClonado;
}

// Função para imprimir os dados de um jogador
void imprimir(Jogador *jogador) {
    printf(" ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n",
    jogador->nome, jogador->altura, jogador->peso,jogador->anoNascimento,jogador->universidade,
    jogador->cidadeNascimento, jogador->estadoNascimento);
}

// Função para inicializar uma lista
void iniciar(Lista *lista) {
    lista->n = 0;
}

// Função para inserir um jogador no início da lista
void inserirInicio(Jogador jogador, Lista *lista) {
    if (lista -> n == MAXTAM) {
        exit(1);
    }

    for ( int i = lista -> n; i > 0; i-- ) {
        lista -> array[i] = lista -> array[i - 1];
    }

    lista -> array[0] = jogador;

    lista -> n++;
}

// Função para inserir um jogador no final da lista
void inserirFim(Jogador jogador, Lista *lista) {
    if (lista -> n == MAXTAM) {
        exit(1);
    }

    lista -> array[lista -> n++] = jogador;
}

// Função para inserir um jogador em uma posição específica na lista
void inserir(Jogador jogador, int pos, Lista *lista) {
    if (lista -> n == MAXTAM || pos < 0 || pos > lista -> n)  {
        exit(1);
    }

    for ( int i = lista -> n; i > pos; i--) {
        lista -> array[i] = lista -> array[i - 1];
    }

    lista -> array[pos] = jogador;
    lista -> n++;
}

// Função para remover o jogador no início da lista
Jogador removerInicio(Lista *lista) {
    if (lista -> n == 0) {
        exit(1);
    }

    Jogador resposta = lista -> array[0];
    lista -> n--;

    for ( int i = 0; i < lista -> n; i++) {
        lista -> array[i] = lista -> array[i + 1];
    }

    return resposta;
}

// Função para remover o jogador no final da lista
Jogador removerFim(Lista *lista) {
    if (lista -> n == 0) {
        exit(1);
    }

    lista -> n--;

    Jogador resposta = lista -> array[lista -> n];

    return resposta;
}

// Função para remover o jogador em uma posição específica na lista
Jogador remover(int pos, Lista *lista) {
    if (lista -> n == 0) {
        exit(1);
    }

    Jogador resposta = lista -> array[pos];

    lista -> n--;

    for ( int i = pos; i < lista -> n; i++) {
        lista -> array[i] = lista -> array[ i + 1 ];
    }

    return resposta;
}

// Função para exibir os jogadores na lista
void mostrar(Lista *lista) {
    for(int i = 0; i < lista->n; i++ ) {
        printf("[%i]", i);
        imprimir(&lista->array[i]);
    }
}
