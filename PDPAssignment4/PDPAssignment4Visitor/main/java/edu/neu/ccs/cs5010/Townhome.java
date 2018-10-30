package edu.neu.ccs.cs5010;

/**
 * Townhome provides access to household name
 * and list of candies for townhomes
 */
public class Townhome extends Household {


    Townhome(String householdName) {

        super(householdName);
    }
    
    /** Accepts a PopulateNeighborhoodVisitor, which populates
     * the townhome with the appropriate candies
     * @param popVisitor populates the household with candies
     */
    public void accept(PopulateNeighborhoodVisitor popVisitor) {

        popVisitor.visit(this);

    }

}
