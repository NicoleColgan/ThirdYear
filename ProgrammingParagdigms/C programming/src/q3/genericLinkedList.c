#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "genericLinkedList.h"

typedef struct listElementStruct {
    void* data; //data is now void so a listElement can have data of any type

    /*The user will pass in a name for this function pointer e.g. printInt.
    This name will corrospond to one of the functions below
    We can use the name function pointer as a placeholder for the passed in name whenever we want
    to print some data in traverse
    */
    void (*functionPointer)(void* data);    
    size_t size;    //i wonder should this be here now 
    struct listElementStruct* next;
} listElement;

//Creates a new linked list element with given content of size
//Returns a pointer to the element
//function now also takes in a function pointer
listElement* createEl(void* data, size_t size, void (*functionPointer)(void* data)) {
    listElement* e = malloc(sizeof(listElement));
    if (e == NULL) {
        //malloc has had an error
        return NULL; //return NULL to indicate an error.
    }
    //dataPointer is now void
    void* dataPointer = malloc(sizeof(void*) * size);
    if (dataPointer == NULL) {
        //malloc has had an error
        free(e); //release the previously allocated memory
        return NULL; //return NULL to indicate an error.
    }
    //no longer need string copy. just assign data to dataPointer
    dataPointer = data;
    e->data = dataPointer;
    e->size = size;
    e->next = NULL;
    e->functionPointer = (*functionPointer);    //function pointer is function pointer that was passed in
    return e;
}

//Prints out each element in the list
//traverse the linked list starting with whatever element is in the start memory location
void traverse(listElement* start) {
    //set current to be a pointer to the list element address that we want to start the traversal from
    listElement* current = start;
    while (current != NULL) {
        current->functionPointer(current->data);    //use function pointer to print
        current = current->next;
    }
}

//Inserts a new element after the given el
//Returns the pointer to the new element
//now takes in a function pointer
listElement* insertAfter(listElement* el, void* data, size_t size, void (*functionPointer)(void* data)) {
    //create new list element using the createEl method and pass in the data, size and function pointer
    listElement* newEl = createEl(data, size, (*functionPointer));
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

void deleteAfter(listElement* after) {
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
void enqueue(listElement** head, void* data, size_t size, void (*functionPointer)(void* data)) {
    listElement* newNode = createEl(data, size, (*functionPointer));
    listElement* currentNode = *head; //pointer to first list element

    while (currentNode->next != NULL) { //go to the last node in the list
        currentNode = currentNode->next;
    }
    currentNode->next = newNode;    //set the last nodes next to be the new node
}

//dequeue the list element from the head and return the new head (deqeue the first element)
listElement* dequeue(listElement** head) {

    listElement* dequeued = *head; //pointer to element to dequeue is the pointer to the head pointer
    listElement* newHead = dequeued->next;    //new head is the old head's next
    *head = newHead;    //change old head pointer to new head pointer
    free(dequeued);   //free memory allocated to old head because we used malloc
    return dequeued; //return dequeued element
}

//push puts an element in the first position of the list (top of the stack)
void push(listElement** head, void* data, size_t size, void (*functionPointer)(void* data)) {
    listElement* newHead = createEl(data, size, (*functionPointer));    //create new element
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

//function pointer prints
void printInt(void* data) {
    int* val = (int*)data;  //cast to int pointer
    printf("%d\n", *val);   //dereference pointer
}
void printDouble(void* data) {
    double* val = (double*)data;  //cast to double pointer
    printf("%lf\n", *val);  //dereference pointer
}
void printString(void* data) {
    char* val = (char*)data;    //cast to char pointer
    printf("%s\n", val);    //get value in address
}

void printChar(void* data) {
    char* val = (char*)data;    //cast to char pointer
    printf("%c\n", *val);   //dereference pointer
}


