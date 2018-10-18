package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * Neighborhood adds a household list
 */
class Neighborhood extends NeighborhoodMember {

    private List<NeighborhoodMember> householdList = new ArrayList<>();

    Neighborhood(String neighborhoodName) {

        super(neighborhoodName);

    }

    Neighborhood(String neighborhoodName, List<NeighborhoodMember> householdList) {

        super(neighborhoodName);
        this.householdList = householdList;

    }

    /**
     * @param visitor goes through the neighborhood and determines whether it contains the desired candy
     */
    void accept(NeighborhoodCandyTraversalVisitor visitor) {

        visitor.visit(this);

        if (this.getHouseholdList() != null) { // if there are households in the neigorhood

            for (NeighborhoodMember child : this.getHouseholdList()) {

                if (!visitor.getCompareCandyMatch()) { // if a candy match hasn't yet been found

                    child.accept(visitor); // visit the households and then the candies
                }

                else { // if a candy match has been found

                    return; // stop going through the rest of the candies in the neighborhood
                }
            }

        }

    }

    List<NeighborhoodMember> getHouseholdList() {

        return householdList;
    }

    public void setHouseholdList(List<NeighborhoodMember> householdList) {

        this.householdList = householdList;
    }

    @Override
    public void accept(INeighborhoodVisitor visitor) {

        visitor.visit(this);


        for (NeighborhoodMember child : this.getHouseholdList()) {

            child.accept(visitor);
        }
    }


    void accept(PopulateNeighborhoodVisitor visitor) {

        visitor.visit(this); // visit will add the household list as children

        for (NeighborhoodMember child : this.getHouseholdList()) {

            child.accept(visitor);
        }
    }
}