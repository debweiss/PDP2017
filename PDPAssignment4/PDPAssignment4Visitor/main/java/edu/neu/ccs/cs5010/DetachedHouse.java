package edu.neu.ccs.cs5010;


/**
 * DetachedHouse provides access to household name
 * and list of candies for detached houses
 */
public class DetachedHouse extends Household {

    DetachedHouse(String householdName) {

        super(householdName);
    }
    
    /** Accepts a PopulateNeighborhoodVisitor, which populates
     * the detached house with the appropriate candies
     * @param popVisitor populates the household with candies
     */
    public void accept(PopulateNeighborhoodVisitor popVisitor) {

        popVisitor.visit(this);

    }
}
