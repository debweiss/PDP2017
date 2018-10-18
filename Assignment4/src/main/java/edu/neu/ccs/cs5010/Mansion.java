package edu.neu.ccs.cs5010;

/**
 * Mansion class
 */
public class Mansion extends Household {


    public Mansion(String householdName) {

        super(householdName);
    }


    @Override
    public void accept(IPopulateNeighborhoodVisitor visitor) {

        visitor.visit(this);

    }
}
