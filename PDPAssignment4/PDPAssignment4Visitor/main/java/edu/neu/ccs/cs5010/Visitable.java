package edu.neu.ccs.cs5010;

public interface Visitable { // accepts a visitor

    void accept(Visitor visitor);
    
    /* NeighborhoodCandyTraversalVisitor visits a neighborhood to find
    candy that matches it's desired candy */
    void accept(NeighborhoodCandyTraversalVisitor candyTraversalVisitor);
    
    /* PopulateNeigborhoodVisitor visits an empty neighborhood and
    populates it with households and candies */
    void accept(PopulateNeighborhoodVisitor populateNeighborhoodVisitor);
}
