# Context-Free-Grammars-First-and-Follow
For this task you will implement the algorithms computing the functions First and Follow, introduced in Lecture 4 of CSEN1003, for the variables of a given context-free grammar. Recall that a CFG is a quadruple (V,Σ,R,S) where V and Σ are disjoint alphabets (respectively, containing variables and terminals), R ⊆ V × (V ∪ Σ)∗ is a set of rules, and S ∈ V is the start variable.

Requirements:
I should implement a class constructor CfgFirstFollow, and two methods; first, and follow.

1.CfgFirstFollow, a class constructor, takes one parameter which is a string description of a CFG and constructs a CFG instance. A string encoding a CFG is of the form V #T #R.

The output of each of first and follow is a semi-colon-separated sequence of items, where each item is a /-separated pair. The first element of each pair is a variable of the grammar and the second element is a string representing the First or, respectively, the Follow set of that variable. The symbols in these strings should appear in alphabetical order. ($ always appears first.) The items themselves should appear in the order in which their respective variables appear in the input CFG.
