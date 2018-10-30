package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;


/**
 * Household -- Each household is part of a neighborhood and contains
 * a specific set of candies.
 */
public abstract class Household extends NeighborhoodMember {
    
    private List<NeighborhoodMemberLeaf> candyList = new ArrayList<>();
    
    public Household(String householdName) {
        
        super(householdName);
        
    }
    
    public Household(String householdName, List<NeighborhoodMemberLeaf> candies) {
        
        super(householdName, candies);
        
        for (NeighborhoodMemberLeaf candy : candies) {
            
            candy.setParent(this);
        }
        
    }
    
    List<NeighborhoodMemberLeaf> getCandyList() {
        
        return candyList;
    }
    
    void setCandyList(List<NeighborhoodMemberLeaf> candyList) {
        
        this.candyList = candyList;
    }
    /* accepts a NeighborhoodCandyTraversalVisitor, which visits
    each household to check whether that household has the desired candy */
    public void accept(NeighborhoodCandyTraversalVisitor nVisitor) {
        
        nVisitor.visit(this);
    }
    
    @Override
    public int hashCode() {
        
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        
        return super.equals(obj);
    }
    
    @Override
    public String toString() {
        
        return "Household{}";
    }
    
    
}

