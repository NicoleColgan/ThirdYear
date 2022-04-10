%if el = H, false
isNotElementInList(El,[El|T]):-
    !,  %discard all choice points created because its not in list.
    false. %false because its the head of the list so its in the list

%if El != H, keep traversing.
isNotElementInList(El,[H|T]):-
    !,
    isNotElementInList(El,T).

%if list is empty, its not in list - true
isNotElementInList(EL,[]).


