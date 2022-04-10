mergeLists(L1,L2,L3,Merged):-
    conc(L1,L2,L1L2),%Merge L1 & L2
    conc(L1L2,L3,Merged).%final case

conc([],X,X).  %base case - unify list one with other list which kept concatenating head till it has all els
conc([H|T],X,[H|Z]):-%keep adding to fi
    conc(T,X,Z). %keep concatenating head onto Merged list
