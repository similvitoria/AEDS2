//TP01Q02 - Palindromo em C
//@author: Vitória Símil Araújo

#include "stdio.h"
#include "string.h"
#include "stdbool.h"

//functions
bool isEnd(char entrie[]);
char* isPalindrome(char entrie[]);

int main(void) {
    //data
    char entrie[1000] = {};

    //read the input archive
    scanf("%[^\n]", entrie);
    getchar();
    while ( !isEnd(entrie) ) {
        printf("%s\n", isPalindrome(entrie));
        scanf("%[^\n]", entrie);
        getchar();
    }
}

//method to verify if a string equals FIM
bool isEnd(char entrie[]) {
    return (entrie[0] == 'F' && entrie[1] == 'I' && entrie[2] == 'M');
}

//method to execute
char* isPalindrome(char entrie[] ) {
    //data
    int index = 0;
    int size = strlen(entrie);

    for(int i = 0; i < size; i++ ) {
        if ( entrie[i] != entrie[size - 1 - index] ) {
            return "NAO";
        }
        index++;
    }

    return "SIM";
}