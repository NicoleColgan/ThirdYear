insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
vinsertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

insert(El,[H|T],[El,H|T]):-
    El<H,
    !.

insert(El,[H|T],[H|New]):-
    insert(El,T,New).

insert(El,[],[El]).
insertInOrder(El,Lst,NewLst):-
    insert(El,Lst,NewLst).

%if searched through all list
insertInOrder(El,[],[El]).

%if El<H we stop here and final list is [newEl, old head, old tail]
insertInOrder(El,[H|T],[El,H|T]):-
    El<H,
    !.

%El>!H so recursive call
insertInOrder(El,[H|T],[H|New]):-
    insertInOrder(El,T,New).

