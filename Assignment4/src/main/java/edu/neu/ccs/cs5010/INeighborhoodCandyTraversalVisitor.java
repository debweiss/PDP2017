package edu.neu.ccs.cs5010;

/**
 *  Visit neighborhood and compare candies
 */
public interface INeighborhoodCandyTraversalVisitor extends INeighborhoodVisitor {

    boolean getCompareCandyMatch();
    void setCompareCandyMatch(boolean result);
}
