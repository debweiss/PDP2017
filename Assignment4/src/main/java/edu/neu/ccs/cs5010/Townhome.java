package edu.neu.ccs.cs5010;

/**
 * Townhome class
 */

public class Townhome extends Household {


    Townhome(String householdName) {

        super(householdName);
    }

    @Override
    public void accept(IPopulateNeighborhoodVisitor visitor) {

        visitor.visit(this);

    }


}
