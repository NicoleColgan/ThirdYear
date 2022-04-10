#include <stdio.h>
#include "tests.h"
#include "linkedList.h"

void runTests(){
  printf("Tests running...\n");
  
  //creating a pointer to an element using a method in createEl in Linked List.c
  listElement* l = createEl("Test String (1).", 30);
  //printf("%s\n%p\n", l->data, l->next);
  //Test create and traverse
  traverse(l);
  printf("\n");

  //Test insert after
  listElement* l2 = insertAfter(l, "another string (2)", 30);
  insertAfter(l2, "a final string (3)", 30);
  traverse(l);
  printf("\n");

  // Test delete after
  deleteAfter(l);
  traverse(l);
  printf("\n");

  //test lenght = 2
  printf("\nlenght of linked list: %d\n", length(l));
  printf("\n");

  //test enqueue 
  printf("Testing enqueue\n");
  enqueue(&l, "Test String (4).", 30); 
  printf("\n");
  traverse(l);

  //test dequeue
  printf("\nTesting dequeue\n");
  listElement* dequeuedEl = dequeue(&l);	//element that was dequeued
  printf("\n");
  traverse(l);

  //testing push
  printf("\nTesting push\n");
  push(&l, "Test String (1).", 30);	//push new element on the stack
  printf("\n");
  traverse(l);

  //testing popped
  printf("\nTesting pop\n");
  listElement* popped = pop(&l);	//popped element is returned
  printf("\n");
  traverse(l);

  printf("\nTests complete.\n");
}
