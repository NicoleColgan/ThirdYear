%keep calling function till we get to end
%then appendreverse([],Z,Z).
reverseList(L,X):-
    reverse(L,X,[]).

reverse([H|T],Final,Empty) :-
    reverse(T,Final,[H|Empty]).

reverse([],Z,Z).

