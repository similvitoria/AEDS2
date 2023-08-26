//TP01Q07 - is em c
//@Author: Vitoria Simil Araujo

#include "stdio.h"
#include "string.h"
#include "stdbool.h"

//functions
bool isEnd(char input[]);
bool isLetter(char c);
bool isVowel(char c);
bool isDigit(char c);
char* isAllVowels(char input[]);
char* isAllConsonats(char input[]);
char* isInteger(char input[]);
char* isDouble(char input[]);
void execute(char input[]); 

int main(void) {
    char input[1000] = {};

    //read the input archive
    scanf("%[^\n]", input);
    getchar();
    while ( !isEnd(input) ) {
        execute(input);
        scanf("%[^\n]", input);
        getchar();
    }
}

//method to execute
void execute(char input[]) {
    printf("%s%s%s%s\n", isAllVowels(input), isAllConsonats(input), isInteger(input), isDouble(input));
}

//method to verify if a string equals FIM
bool isEnd(char input[]) {
    return (input[0] == 'F' && input[1] == 'I' && input[2] == 'M');
}

//method to verify if a char equals a letter
bool isLetter(char c) {
    //data
    int code = (int) c;

    return (( code >= 65 && code <= 90 ) || ( code >= 97 && code <= 121 ));
}

//method to verify if a char equals a vowel
bool isVowel(char c) {
    //data
    int vowels[10] = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};

    for (  int i = 0; i < 10; i++ ) {
        if ( vowels[i] == (int) c ) return true;
    }

    return false;
}

//method to verify if a char equals a digit
bool isDigit(char c) {
    //data
    int code = (int)c;

    return ( code >= 48 && code <= 57);
}

//method to verify if a string is composed only by vowels
char* isAllVowels(char input[]) {
    for( int i = 0; i < strlen(input) - 1; i++ ) {
        if (!(isVowel(input[i]))) return "NAO ";
    }

    return "SIM ";
}

//method to verify if a string is composed only by consonats
char* isAllConsonats(char input[]) {
    for ( int i = 0; i < strlen(input) - 1; i++ ) {
        if ( !(isLetter(input[i])) || (isVowel(input[i])) ) return "NAO ";
    }

    return "SIM ";
}

//method to verify if a string equals an integer number
char* isInteger(char input[]) {
    for ( int i = 0; i < strlen(input); i ++) {
        if ( !isDigit(input[i])) {
            return "NAO ";
        }
    }

    return "SIM ";
}

//method to verify if a string equals a double number
char* isDouble(char input[]) {
    //data
    int separators = 0;

    for ( int i = 0; i < strlen(input) - 1; i++ ) {
        if ( !isDigit((input[i]))) {
            if ( (input[i]) == ',' || (input[i]) == '.' ) separators++;
            else return "NAO";
        }
    }

    return ( separators == 0 || separators == 1 ) ? "SIM" : "NAO";
}