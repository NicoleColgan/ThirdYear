#ifndef CT331_ASSIGNMENT_LINKED_LIST
#define CT331_ASSIGNMENT_LINKED_LIST
/*
In the C Programming Language, the #ifndef directive allows for conditional compilation. The preprocessor determines if the provided macro does not exist before including the subsequent code in the compilation process.
*/

typedef struct listElementStruct listElement;

//Creates a new linked list element with given content of size
//Returns a pointer to the element
listElement* createEl(char* data, size_t size);

//Prints out each element in the list
void traverse(listElement* start);

//Inserts a new element after the given el
//Returns the pointer to the new element
listElement* insertAfter(listElement* after, char* data, size_t size);

//Delete the element after the given el
void deleteAfter(listElement* after);

int length(listElement* list);

void enqueue(listElement** list, char* data, size_t size);

listElement* dequeue(listElement** head);

void push(listElement** head, char* data, size_t size);

listElement* pop(listElement** head);

#endif
