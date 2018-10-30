package edu.neu.ccs.cs5010;

/**
 * Mansion provides access to household name
 * and list of candies for mansions
 */
public class Mansion extends Household {


    Mansion(String householdName) {

        super(householdName);
    }
    
    /** Accepts a PopulateNeighborhoodVisitor, which populates
     * the mansion with the appropriate candies
     * @param popVisitor populates the household with candies
     */
    public void accept(PopulateNeighborhoodVisitor popVisitor) {

        popVisitor.visit(this);

    }
}
