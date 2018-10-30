package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * Neighborhood provides access to the households in the neighborhood,
 * which in turn provide access to the candies within those households.
 */
class Neighborhood extends NeighborhoodMember {
    
    private List<NeighborhoodMember> householdList = new ArrayList<>();
    
    Neighborhood(String neighborhoodName) {
        
        super(neighborhoodName);
        
    }
    
    Neighborhood(String neighborhoodName, List<NeighborhoodMember>
        householdList) {
        
        super(neighborhoodName);
        this.householdList = householdList;
        
    }
    
    List<NeighborhoodMember> getHouseholdList() {
        
        return householdList;
    }
    
    void setHouseholdList(List<NeighborhoodMember> householdList) {
        
        this.householdList = householdList;
    }
    
    /** Accepts a NeighborhoodCandyTraversalVisitor, which visits each
     * household in the Neighborhood, and in turn each candy in each household,
     * to determine whether the desired candy matches any of the candies in
     * the Neighborhood households
     * @param visitor visits neighborhood, households, candies to find a match
     *                with desired candy
     */
    @Override
    public void accept(NeighborhoodCandyTraversalVisitor visitor) {
        
        visitor.visit(this);
    
        // if there are households in the neighborhood
        if (this.getHouseholdList() != null) {
            
            // go through each household
            for (NeighborhoodMember child : this.getHouseholdList()) {
    
                // if a candy match hasn't yet been found
                if (!visitor.getDesiredCandyMatch()) {
    
                    // visit the households and then the candies
                    child.accept(visitor);
                    
                } else { // if a candy match has been found
                    
                    return; // stop the visit and return
                }
            }
            
        }
        
    }
    
    /** Accepts a PopulateNeighborhoodVisitor, which visits each household in
     * the Neighborhood and populates it with the appropriate candies.
     * @param visitor visits each household, populates with candies
     */
    @Override
    public void accept(PopulateNeighborhoodVisitor visitor) {
        
        visitor.visit(this);
        
        for (NeighborhoodMember child : this.getHouseholdList()) {
            
            child.accept(visitor);
        }
    }
    
}