package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;


/**
 * Household -- Each household is part of a neighborhood and contains
 * a specific set of candies.
 */
public abstract class Household extends NeighborhoodMember {

    private List<NeighborhoodMemberLeaf> candyList = new ArrayList<>(); // list of candies


    /*********************** GETTERS & SETTERS *************************/

    public Household(String householdName) {

        super(householdName);

    }

    public Household(String householdName, List<NeighborhoodMemberLeaf> candies) {

        super(householdName, candies);
        for (NeighborhoodMemberLeaf candy : candies) {

            candy.setParent(this);
        }

    }

    public List<NeighborhoodMemberLeaf> getCandyList() {

        return candyList;
    }

    public void setHouseholdList(List<NeighborhoodMemberLeaf> candyList) {

        this.candyList = candyList;
    }

    public void accept(INeighborhoodCandyTraversalVisitor visitor) {

        visitor.visit(this);

    }

    /******************************************************************/

    public abstract void accept(IPopulateNeighborhoodVisitor popVisitor); // populates household

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public String toString() {

        return "Household{}";
    }

    public void accept(INeighborhoodVisitor visitor) {

        visitor.visit(this);

    }

}

