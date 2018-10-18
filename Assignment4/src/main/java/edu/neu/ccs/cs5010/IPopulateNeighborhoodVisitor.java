package edu.neu.ccs.cs5010;


/**
 * Populate neighborhood members
 */
public interface IPopulateNeighborhoodVisitor extends INeighborhoodVisitor {

    void visit(Mansion mansion);

    void visit(DetachedHouse detachedHouse);

    void visit(Duplex duplex);

    void visit(Townhome townhome);
}
