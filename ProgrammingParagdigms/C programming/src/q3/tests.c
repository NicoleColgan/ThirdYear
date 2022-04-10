#include <stdio.h>
#include "tests.h"
#include "genericLinkedList.h"

void runTests(){
	printf("Tests running...\n");
	

	char val3 = 'a'; 
	//pass in address for a (char pointer)
	//pass in the name of the function pointer you want to use
	listElement* l = createEl(&val3, 30, printChar);
	traverse(l);
	printf("\n");
	
	//Test insert after
	listElement* l2 = insertAfter(l, "test string", 30,printString);	//string can be passed in as is
	int val1 = 1;
	listElement* l3 = insertAfter(l2, &val1, 30, printInt);	//pass in address for int and printInt as the function pointer
	double val2 = 10.1;
	listElement* l4 = insertAfter(l3, &val2, 30, printDouble);	//pass in address for double and printDouble as function pointer
	traverse(l);
	printf("\n");
	
	// Test delete after
	printf("Testing delete after\n");
	deleteAfter(l3);
	traverse(l);
	printf("\n");
	
	//test lenght = 3
	printf("\nlenght of linked list: %d\n", length(l));
	printf("\n");
	
	//test enqueue 
	printf("Testing enqueue\n");
	double val4 = 11.98;
	enqueue(&l, &val4, 30, printDouble);
	printf("\n");
	traverse(l);
	
	//test dequeue
	printf("\nTesting dequeue\n");
	listElement* dequeuedEl = dequeue(&l);	//element that was dequeued
	printf("\n");
	traverse(l);
	
	//testing push
	printf("\nTesting push\n");
	char val5 = 'b';
	push(&l, &val5, 30, printChar);	//push new element on the stack
	printf("\n");
	traverse(l);
	
	//testing popped
	printf("\nTesting pop\n");
	listElement* popped = pop(&l);	//popped element is returned
	printf("\n");
	traverse(l);

  printf("\nTests complete.\n");
}
