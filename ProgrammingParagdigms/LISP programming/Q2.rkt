#lang racket
#|Functions name ins_beg that receives element and a list and inserts
element into the beginning of the list|#

#|The provide function is necessary for the unity test to work
Use (provide) for each function|#
(provide ins_beg)

(define (ins_beg el lst)
  (cons el lst))

(display "ins_beg\n")
(ins_beg '1 '(2 3 4))

(ins_beg '(1 2) '(2 3 4))

#|Function names ins_end which when passes an element and a list,
inserts the element into the end of the list|#
(provide ins_end)
(display "ins_end\n")

(define (ins_end el lst)
  ;append takes lists and appends them to make a concatenation of these
  ;lists => we have to append a list consisting of the element(s)
  ;(make it into a list if it isnt already) to the list already passed
  ;in
  (append lst (list el)))

(ins_end '(1 2) '(3 4 5))
(ins_end 1 '(2 3 4))


(provide count_top_level)
#|Count the number of items in a list|#
(display "counting top levels in a list\n")
(define (count_top_level lst)
  #|check if list is null|#
  (if
   (null? lst) 0 #|empty list|#
   #|Introduce a loop count to see how many times function was called|#
        (+ 1 (count_top_level(cdr lst))))
  )

(count_top_level '(1 2 3))
(count_top_level '(1 2 3 (2 3)))

(display "non-tail recursive function that counts the number of times an item occurs in a list\n")
#|asume all items are atomic|#
#|an atom is a number or string of contiguous characters|#
(provide count_instances)

(define (count_instances el lst)
  (cond
    ((null? lst) 0)
    ((equal? el (car lst))
     (+ 1 (count_instances el (cdr lst))))
    (else (count_instances el (cdr lst)))
))
(count_instances 1 '(1 1 3 4))
(count_instances 1 '(1 1 3 4 (1 2 1)))

(display "tail recursive function that counts the number of times an item occurs in a list\n")
(provide count_instances_tr)
(define (count_instances_tr el lst)
  (count_instances_wrapper_function el lst))

(define (count_instances_wrapper_function el lst)
  (cond
    ((null? lst) 0)
    ((equal? el (car lst))
     (+ 1 (count_instances_wrapper_function el (cdr lst))))
    (else (count_instances_wrapper_function el (cdr lst)))
))
(count_instances_tr 2 '(2 2 2 4 '(2 2 4)))


;count instances deep checks how many times an item occurs in a list including
;sub lists
(display "count_instances_deep\n")
(provide count_instances_deep)

(define (count_instances_deep el lst)
  (cond
    ((null? lst) 0)
    ((list? (car lst))
     (count_instances_deep el (car lst)))
    ((equal? el (car lst))
     (+ 1 (count_instances_deep el (cdr lst))))
    (else (count_instances_deep el (cdr lst)))
))
(count_instances_deep 1 '(1 1 (1 1 2)));ans should be 4