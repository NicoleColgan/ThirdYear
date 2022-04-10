takes(tom, ct331).
takes(mary, ct331).
takes(joe, ct331).
takes(tom, ct345).
takes(mary, ct345).
instructs(bob, ct331).
instructs(ann, ct345).
teaches(X,Y):-
    instructs(X,Z),
    takes(Y,Z).
classmates(STUDENTONE,STUDENTTWO):-
    takes(STUDENTONE,Z),
    takes(STUDENTTWO,Z).
