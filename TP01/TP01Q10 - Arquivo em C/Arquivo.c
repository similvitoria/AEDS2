//TP01Q10 - Arquivo em java
//@Author: Vitoria Simil Araujo

#include "stdio.h"
#include "stdlib.h"
#include "string.h"

//fuctions
void execute(int size);

int main(void) {
    //data
    FILE *file = fopen("file.txt", "wb"); //using binary file
    char input[1000] = {};
    float number = 0.0;
    
    //read the input archive
    scanf("%[^\n]", input);
    getchar();

    int n = atoi(input); //number of numbers to be read
    int size = n;

    while ( n > 0 ) {
        scanf("%[^\n]", input);
        getchar();
        number = atof(input);
        fwrite(&number, sizeof(float), 1, file);
        n--;
    }
    
    //close file
    fclose(file);

    //execute
    execute(size);
}

//method to execute
void execute(int size) {
    //data
    FILE *file = fopen("file.txt", "rb");
    float number = 0.0;
    
    for ( int i = size; i > 0; i-- ) {
        //pointing seek
        fseek(file, sizeof(float) * ( i - 1 ), SEEK_SET);
        fread(&number, sizeof(float), 1, file);
        printf("%g\n", number);
    }

    //close file
    fclose(file);
}