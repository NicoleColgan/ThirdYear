#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedList.h"

/*
* typeDef allows you to give a type a new like int is a type name
* Here were creating a structure called listElementStruct
* Member variables of same: a pointer to a start location where data will be stored
* an unsigned integer type to represent size
* a pointer to the nect listElementstruct location
* We can now refer to this whole structure as listElement because of the typeDef
* 
* The list element is like 1 node of the linked list
*/
typedef struct listElementStruct{
  char* data;
  size_t size;
  struct listElementStruct* next;
} listElement;

//Creates a new linked list element with given content of size
//Returns a pointer to the element
/*
ListElement is like void - the return type of this funcion is a pointer to a list element 
Function name: createEl
@args - a pointer called data which stores a character 
      - an unsigned size
 */
listElement* createEl(char* data, size_t size){
    //using type def which names our structure e to make a new pointer of type listElement
    //e is a pointer to a list element
    //we have to allocate this pointer some memory which will be the size of a listElement structure
  listElement* e = malloc(sizeof(listElement));
  if(e == NULL){
    //malloc has had an error
    return NULL; //return NULL to indicate an error.
  }
  //create a pointer called dataPointer of type char and alocate it a size of a character x the size passed in
  char* dataPointer = malloc(sizeof(char)*size);
  if(dataPointer == NULL){
    //malloc has had an error
    free(e); //release the previously allocated memory
    return NULL; //return NULL to indicate an error.
  }
  //copy the character pointer data into the character pointer data pointer
  strcpy(dataPointer, data);

  //"->" allows us to access the members of the structure
  //we previously assigned e to be a listElement structure
  //now set its data to be the dataPointer that we just created
  e->data = dataPointer;
  //set its size to be the size that we just passed in
  e->size = size;
  //initially its next is null because nohing else has been added to it
  e->next = NULL;
  return e;
}

//Prints out each element in the list
//traverse the linked list starting with whatever element is in the start memory location
void traverse(listElement* start){
    //set current to be a pointer to the list element address that we want to start the traversal from
  listElement* current = start;
  while(current != NULL){
    printf("%s\n", current->data);
    //next is a pointer to the nect list element
    current = current->next;
  }
}

//Inserts a new element after the given el
//Returns the pointer to the new element
//insert element after "el" (the previous list element) with a character data pointer
listElement* insertAfter(listElement* el, char* data, size_t size){
    //create new list element using the createEl method and pass in the data and size
  listElement* newEl = createEl(data, size);
  //next is a new listElement structure which is the element after the previous element (probably null)
  listElement* next = el->next;
  //since this element is going after "el", "el"'s nect will now be its next
  newEl->next = next;
  //els next is the element we jut created
  el->next = newEl;
  //return a pointer to the new element that we just created 
  return newEl;
}


//Delete the element after the given el

void deleteAfter(listElement* after){
    //listElelemt that we want to delete
  listElement* delete = after->next;
  //new listElement is the element after the one we want to delete (this can be a list element if were in the middle of a list or 
  //it can be null)
  listElement* newNext = delete->next;
  //reallocate to list element that was past in's nect to be the next after the elmement were deleting 
  after->next = newNext;
  //need to free the memory because we used malloc
  free(delete->data);
  free(delete);
}

/*
returns the number of elements in a linked list
*/
int length(listElement* list) {
    //get the first element and count hos man nexts there are until next is null
    //can use traverse and if a pointer is returned thats not null, incriment some value and return it
    //list can be a pointer to the first element in the list
    int length = 0;
    if (list != NULL) {
        length = 1;
    }
    while (list->next != NULL) {
        length++;
        list = list->next;
    }
    return length;
}

//enqueue a new element onto the head of a list
//double pointer allows you to change the head pointer directly (not needed for enqueue)
void enqueue(listElement** head, char* data, size_t size) {
    listElement* newNode= createEl(data, size);
    listElement* currentNode = *head; //pointer to first list element

    while (currentNode->next != NULL) { //go to the last node in the list
        currentNode = currentNode->next;
    }
    currentNode->next = newNode;    //set the last nodes next to be the new node
}

//dequeue the list element from the head and return the new head (deqeue the first element)
listElement* dequeue(listElement** head) {

    listElement* dequeued= *head; //pointer to element to dequeue is the pointer to the head pointer
    listElement* newHead = dequeued->next;    //new head is the old head's next
    *head = newHead;    //change old head pointer to new head pointer
    free(dequeued);   //free memory allocated to old head because we used malloc
    return dequeued; //return dequeued element
}

//push puts an element in the first position of the list (top of the stack)
void push(listElement** head, char* data, size_t size) {
    listElement* newHead = createEl(data, size);    //create new element
    newHead->next = *head; //new nodes next is the pointer to the old head
    *head = newHead; //head now stores the new node
}

//pop removes the first element in the queue
listElement* pop(listElement** head) {
    listElement* popped = *head;    //store pointer to the element to be popped
    listElement* newHead = popped->next;    //new head is the pointer to the element after the one to be popped
    *head = newHead;    //change pointer to head 
    free(popped);   //free memory where the popped element was removed because we used malloc
    return popped;  //return popped element
}
