package edu.neu.ccs.cs5010;


/**
 * Visit neighborhood members
 */
public interface Visitor {

    void visit(Neighborhood neighborhood);

    void visit(NeighborhoodMemberLeaf neighborhoodLeaf);

    void visit(NeighborhoodMember neighborhoodMember);

    void visit(Household household);

    void visit(NeighborhoodCandy neighborhoodCandy);

    void visit(Mansion mansion);

    void visit(DetachedHouse detachedHouse);

    void visit(Duplex duplex);

    void visit(Townhome townhome);

}
