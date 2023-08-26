//TP01Q12 - Palindromo recursivo em c
//@Author: Vitoria Simil Araujo

#include "stdio.h"
#include "string.h"
#include "stdbool.h"

//functions
bool isEnd(char input[]);
char* execute(char input[]);
char* isPalindrome(int index, char input[]);

int main(void) {
    //data
    char input[1000] = {};

    //read the input archive
    scanf("%[^\n]", input);
    getchar();
    while ( !isEnd(input) ) {
        printf("%s\n", execute(input));
        scanf("%[^\n]", input);
        getchar();
    }
}

//method to verify if a string equals FIM
bool isEnd(char input[]) {
    return (input[0] == 'F' && input[1] == 'I' && input[2] == 'M');
}

//method to execute
char* execute(char input[]) {
    return isPalindrome(0, input);
}

//method to execute
char* isPalindrome(int index, char input[]) {
    if ( index >= strlen(input) )
        return "SIM";
    else if (input[index] != input[strlen(input) - 1 - index])
        return "NAO";
    else 
        return isPalindrome(index + 1, input);
}
