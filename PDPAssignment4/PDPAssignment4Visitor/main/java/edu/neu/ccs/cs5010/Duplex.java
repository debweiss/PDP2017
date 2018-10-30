package edu.neu.ccs.cs5010;

/**
 * Duplex provides access to household name
 * and list of candies for duplexes
 */
public class Duplex extends Household {

    Duplex(String householdName) {

        super(householdName);
    }
    
    /** Accepts a PopulateNeighborhoodVisitor, which populates
     * the duplex with the appropriate candies
     * @param popVisitor populates the household with candies
     */
    public void accept(PopulateNeighborhoodVisitor popVisitor) {

        popVisitor.visit(this);

    }

}
