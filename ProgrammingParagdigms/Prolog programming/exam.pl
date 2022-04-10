learns(nicole,IT).
learns(kacper,IT).
teaches(Jack,IT).

instructs(X,Y):-
    learns(y,Z),
    teaches(X,Z).
