#lang racket

;Attempt to make a tree that looks like the following
;                              5
;                            /   \
;                           2     6
;                          / \   / \
;                         1   3 () ()
;                        / \ / \
;                      () ()() ()
;make a tree using define - syntax (define id expr)
;node syntax - leftChild nodeData rightChild
(define bst '(((() 1 ())2 (() 3 ())) 5 (() 6 ())))

;A. display in sorted order the contents of a binary search tree..
(define (traverse bst)
  ;unless (expression) (do this)
  ;unless next is null, keep going until we get to very bottom left child
  ;recursively traverse through left subtree until next left node is null
  (unless (empty? (car bst)) (traverse (car bst)))
   ;when the leaf is null, display parent
    (display (cadr bst))
  ;recursively traverse through right subtree
  (unless (empty? (caddr bst)) (traverse (caddr bst)))
  )
;(traverse bst) 

;B. Return #t or #f if a given item is present or absent in tree
(define (findItem el bst)
  (cond
    ;if el = root node (of tree or sub-tree) - true
    [(equal? el (cadr bst)) #t] 
    ;if el < root value, then were concerned with left sub-tree
    ;check left subtree exists
    ;if it doesnt - false (weve traversed as far as we can and cant find the el)
    ;otherwise - recursively call function on sub-tree until we either find
    ;or dont find a match 
    ;(node = value: Y-#t, N-continue
    [(< el (cadr bst)) (if (empty? (car bst)) #f (findItem el (car bst)))]
    ;right sub-tree
    [else (if (empty? (caddr bst)) #f (findItem el (caddr bst)))]
    )
  )
;(display "\nTesting find item\n")
;(findItem 1 bst)
;(display "\n")
;(findItem 19 bst)

;C. Insert an item correctly into a list representing a binary tree
(display "Testing insert\n")
(define (insert el tree)
  (cond
    ;is car lst null
    ;if yes make el car list
    ;if no continue
    
    [(empty? (cadr tree)) (list (car tree) (list '() el '()) (caddr tree))]
    ;is current node = el
    ;if yes - return item found
    ;if no continue
    [(equal? el (cadr tree)) tree]
    [(< el (cadr tree)) (if (empty? (car tree))
      ;make a new tree with el as left sub-tree
      (list (list '() el '()) (cadr tree) (caddr tree))
      ;else its meant to be in the left side so we need to keep traversing
      ;to find out where exactly it fits and add to new list 
      (list (insert el (car tree)) (cadr tree) (caddr tree)))]
    [else
     ;its on the right sub-tree
     ;check if right sub tree is null
     (if (empty? (caddr tree))
         (list (car tree) (cadr tree) (list '() el '()))
         ;else traverse right sub-tree and add to new list
         (list (car tree) (cadr tree) (insert el (caddr tree))))]
    ))
(insert 0 bst)
(display "\n")
(insert 10 bst)
;D. Take a list of items and insert them into a binary search tree
(display "Testing insert list\n")
(define (insertList lst tree)
  (cond
    ;if nothing was added, return tree as is
    [(null? lst) tree]
    ;else insert the items in the list node by node and recursivly call method
    ;removing first item as its added
    [else (insertList (cdr lst) (insert (car lst) tree))] 
    ))

(insertList '(0 12 13 10) bst)
;E. Implement a tree sort algorithm. Function should take a list of items and
;display them in sorted order
(define (treeSort lst)
  (insertList lst '()) 
  )

(treeSort '(1 3 2 4)) 

;F. implement a higher order version of the tree-sort function that takes a list
;and a function that determines the sorted order. For example, write a version
;that sorts the list in ascending, descending, ans ascending based on the last digit