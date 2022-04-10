%head of list
head([H|_],X):-
     X=H. %unify X and the head of the list

%tail of list
tail([_,T],X):-
     X=T.

%Print head, head of tail and tail
headtail([H|T]):-
    head(H),%print head
    head(T),%print head of tail
    tail(T).%print tail

contains1(X,[H|_]):-
    X=:=H.

contains2(X,[_|T]):-
    X=T.


