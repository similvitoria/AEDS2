//TP01Q16 - Alteração Aleatória em C
//@author: Vitória Símil Araújo

#include <stdlib.h> 
#include <stdio.h> 
#include <time.h> 
#include <string.h>
#include <stdbool.h>

//functions
bool isEnd(char entrie[]);
char* execute(char entrie[]);
char* changeString(char entrie[], char first, char second);


int main(void) {
    //data
    char entrie[1000] = {};

    //create generator
    srand(4);

    //read the input archive
    scanf("%[^\n]", entrie);
    getchar();
    while ( !isEnd(entrie) ) {
        printf("%s\n", execute(entrie));
        scanf("%[^\n]", entrie);
        getchar();
    }
}

//method to verify if a string equals FIM
bool isEnd(char entrie[]) {
    return (entrie[0] == 'F' && entrie[1] == 'I' && entrie[2] == 'M');
}

char* execute(char entrie[]) {
    //get two random characters
    char first = (char) 'a' + rand() % 26;
    char second = (char) 'a' + rand() % 26;

    return changeString(entrie, first, second);
}

char* changeString(char entrie[], char first, char second) {    
    //data
    int size = strlen(entrie);

    for(int i = 0; i < size; i++ ) {
        if ( entrie[i] == first ) {
            entrie[i] = second;
        }
    }
    return entrie;
}