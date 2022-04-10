sumOddNumbers([],[]).


sumOddNumbers([H|T],Sum):-
    integer(H),
    (    H mod 2 =:= 0 -> Sum is Sum+H;sumOddNumbers(T,Sum)).

