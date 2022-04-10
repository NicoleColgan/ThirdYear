#include <stdio.h>

int main(int arg, char* argc[]){
	int number;
	int* numberPointer;
	long longNumber;
	double* pointerTypeDouble;
	char** charDoublePointer;
	printf("\nSize of int data type: %d", sizeof(number));
	printf("\nSize of pointer to type int %d: ", sizeof(*numberPointer));
	printf("\nSize of long data type: %ld", sizeof(longNumber));
	printf("\nSize of pointer to type double: %d", sizeof(*pointerTypeDouble));
	printf("\nSize of double pointer to type char: %c", sizeof(**charDoublePointer));
	

	//stack storage
	int y;
	char* str;
	y = 4;
	printf("\nstack memory: %d", y);

	//heap storage example
	str = malloc(100 * sizeof(char));	//store 100 chars
	str[0] = 'm';

	printf("\nheap memory: %c", str[0]);
	free(str);
  return 0;
}
