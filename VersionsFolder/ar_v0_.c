#include <stdio.h>
#include <string.h>

int main(int argc, char argv[]){
    if(argc < 3){
        printf("Error: Invalid number of arguments");
        exit(1);
    }
    else{
        a = atoi(argv[1]);
        b = atoi(argv[2]);

        if(a < b){
            printf("Error: First argument must be greater than or equal to second argument");
            exit(1);
        }else{
            result = a / b;
            printf("Result: %d", result);
        }
    }

    return 0;
}