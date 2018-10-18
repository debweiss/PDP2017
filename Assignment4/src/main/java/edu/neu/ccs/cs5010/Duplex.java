package edu.neu.ccs.cs5010;

/**
 * Detached House class
 */
public class Duplex extends Household {

    public Duplex(String householdName) {

        super(householdName);
    }

    @Override
    public void accept(IPopulateNeighborhoodVisitor visitor) {

        visitor.visit(this);

    }
}
