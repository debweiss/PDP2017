package edu.neu.ccs.cs5010;


/**
 * Visit neighborhood members
 */
public interface INeighborhoodVisitor {

    void visit(Neighborhood neighborhood);

    void visit(NeighborhoodMemberLeaf neighborhoodLeaf);

    void visit(NeighborhoodMember neighborhoodMember);

    void visit(Household household);

    void visit(NeighborhoodCandy neighborhoodCandy);

}
