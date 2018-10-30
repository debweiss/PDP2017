package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * PopulateNeighborhoodVisitor - Takes a new neighborhood and populates it
 * with households and candies
 */
public class PopulateNeighborhoodVisitor implements Visitor {
    
    private List<NeighborhoodMember> householdList = new ArrayList<>();
    private CandyCategories candyCategories = new CandyCategories();
    
    List<NeighborhoodMember> getHouseholdList() {
        
        return householdList;
    }
    
    void setHouseholdList(List<NeighborhoodMember> householdList) {
        
        this.householdList = householdList;
    }
    
    /** Visits the neighborhood and populates it with households,
     * then populates the households with candies.
     * @param neighborhood the neighborhood being visited
     */
    public void visit(Neighborhood neighborhood) {
        
        for (NeighborhoodMember household : this.householdList) {
            
            household.setParent(neighborhood);
            
            neighborhood.getHouseholdList().add(household);
            
        }
        
        for (NeighborhoodMember household : neighborhood.getHouseholdList()) {
            
            household.accept(this);
        }
    }
    
    /** Populates a mansion with the appropriate candies
     * @param mansion Household that is populated
     */
    public void visit(Mansion mansion) {
        
        this.candyCategories.buildMansionCandyNameList();
        this.candyCategories.buildMansionCandySizeList();
        
        /* use each candy name and size in the name and size
        lists to create a new Neighborhood Candy */
        for (int i = 0; i < this.candyCategories
            .getMansionCandyNameList().size(); i++) {
            
            NeighborhoodCandy mansionCandy =
                new NeighborhoodCandy(this.candyCategories
                    .getMansionCandyNameList().get(i),
                    this.candyCategories.getMansionCandySizeList().get(i));
            
            /* set the mansion as the candy's parent & add the candy
            to the list of children */
            mansionCandy.setParent(mansion);
            mansion.getChildren().add(mansionCandy);
            
        }
    }
    
    /** Populates a detached house with the appropriate candies
     * @param detachedHouse Household that is populated
     */
    public void visit(DetachedHouse detachedHouse) {
        
        /* use each candy name and size in the name and size
        lists to create a new Neighborhood Candy */
        this.candyCategories.buildDetachedHouseCandyNameList();
        this.candyCategories.buildDetachedHouseCandySizeList();
        
        for (int i = 0; i < this.candyCategories
            .getDetachedHouseCandyNameList().size(); i++) {
            
            NeighborhoodCandy detachedHouseCandy =
                new NeighborhoodCandy(this.candyCategories
                    .getDetachedHouseCandyNameList().get(i),
                    this.candyCategories.getDetachedHouseCandySizeList().get(i));
            
            /* set the detached house as the candy's parent & add the candy
            to the list of children */
            detachedHouseCandy.setParent(detachedHouse);
            detachedHouse.getChildren().add(detachedHouseCandy);
            
        }
    }
    
    /** Populates a duplex with the appropriate candies
     * @param duplex Household that is populated
     */
    public void visit(Duplex duplex) {
        
         /* use each candy name and size in the name and size
        lists to create a new Neighborhood Candy */
        this.candyCategories.buildDuplexCandyNameList();
        this.candyCategories.buildDuplexCandySizeList();
        
        for (int i = 0; i < this.candyCategories
            .getDuplexCandyNameList().size(); i++) {
            
            NeighborhoodCandy duplexCandy =
                new NeighborhoodCandy(this.candyCategories
                    .getDuplexCandyNameList().get(i),
                    this.candyCategories.getDuplexCandySizeList().get(i));
            
             /* set the duplex as the candy's parent & add the candy
            to the list of children */
            duplexCandy.setParent(duplex);
            duplex.getChildren().add(duplexCandy);
            
        }
    }
    
    /** Populates a duplex with the appropriate candies
     * @param townhome Household that is populated
     */
    public void visit(Townhome townhome) {
        
         /* use each candy name and size in the name and size
        lists to create a new Neighborhood Candy */
        this.candyCategories.buildTownhomeCandyNameList();
        this.candyCategories.buildTownhomeCandySizeList();
        
        for (int i = 0; i < this.candyCategories
            .getTownhomeCandyNameList().size(); i++) {
            
            NeighborhoodCandy townhomeCandy =
                new NeighborhoodCandy(this.candyCategories
                    .getTownhomeCandyNameList().get(i),
                    this.candyCategories.getTownhomeCandySizeList().get(i));
            
            /* set the townhome as the candy's parent & add the candy
            to the list of children */
            townhomeCandy.setParent(townhome);
            townhome.getChildren().add(townhomeCandy);
            
        }
    }
    
    @Override
    public void visit(NeighborhoodMemberLeaf neighborhoodLeaf) {
    
    }
    
    @Override
    public void visit(NeighborhoodMember neighborhoodMember) {
    
    }
    
    @Override
    public void visit(Household household) {
    
    }
    
    @Override
    public void visit(NeighborhoodCandy neighborhoodCandy) {
    
    }
    
    @Override
    public int hashCode() {
        int result = householdList.hashCode();
        result = 31 * result + candyCategories.hashCode();
        return result;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PopulateNeighborhoodVisitor)) {
            return false;
        }
        
        PopulateNeighborhoodVisitor that = (PopulateNeighborhoodVisitor) o;
        
        if (!householdList.equals(that.householdList)) {
            return false;
        }
        return candyCategories.equals(that.candyCategories);
    }
    
    @Override
    public String toString() {
        
        return "PopulateNeighborhoodVisitor{}";
    }
    
}
