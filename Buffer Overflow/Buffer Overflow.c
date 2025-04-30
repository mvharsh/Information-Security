#include <stdio.h>
#include <string.h>

void vulnerable_function(char *input) {
    char canary[4] = "ABC";
    char buffer[16];     
    strcpy(buffer, input);
    if (strcmp(canary, "ABC") != 0) {
        printf("Canary corrupted! Buffer overflow detected!\n");
        char *null_ptr = NULL;
        *null_ptr = 'x';
    }
    printf("Buffer content: %s\n", buffer);
}

int main(int argc, char *argv[]) {
   if (argc < 2) {
        printf("Usage: %s <input>\n", argv[0]);
        return 1;
    }
    vulnerable_function(argv[1]);
    return 0;
}
