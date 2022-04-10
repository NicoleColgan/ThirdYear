#lang racket
#|A cons pair of two numbers|#
(cons 1 2)

#|A list of three numbers using the cons function|#
#|Cons parametes - first param = an element or a list
second param = usually a list.|#
#|Lists are really just cons pairs where the second element is empty
or another list|#
#|Two ways to do this|#
#|e.g. cons pairs where second element is another list|#
(cons 2 (cons 3 '(4)))
#|cons pair where second element in last cons is empty|#
(cons 2(cons 3(cons 4 empty)))

#|A list containing
A string, a number, a nested list of three numbers
Using only the list function|#
#|List function jut returns a list from its components|#
(list "hello" 1 '(3 4 4))

#|A list containing
A string, a number, a nested list of three numbers
Using only the append function|#
#|append collects componenets from several lists into one|#
#|So all arguments have to be a list|#
(append '("hello") '(1) '((3 4 4)))