package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class NeighborhoodCandyTraversalVisitorTest {
    
    private Neighborhood neighborhood = new Neighborhood("dreamCandyNH");
    private PopulateNeighborhoodVisitor popVisitor = new PopulateNeighborhoodVisitor();
    private NeighborhoodCandyTraversalVisitor candyTraversalVisitor =
        new NeighborhoodCandyTraversalVisitor();
    
    
    @Before
    public void setUp() throws Exception {
    
        List<NeighborhoodMember> dreamCandyHouseholdList = new ArrayList<>();
    
        dreamCandyHouseholdList.add(new Mansion("mansion"));
        dreamCandyHouseholdList.add(new Duplex("duplex"));
        dreamCandyHouseholdList.add(new DetachedHouse("detached house"));
        dreamCandyHouseholdList.add(new Townhome("townhome"));
    
        popVisitor.setHouseholdList(dreamCandyHouseholdList);
        popVisitor.visit(neighborhood);
    
        candyTraversalVisitor.setDesiredCandyName("twix");
        candyTraversalVisitor.setDesiredCandySize("super size");
        candyTraversalVisitor.visit(neighborhood);
        
    }
    
    @Test
    public void testVisitNeighborhood() throws Exception {
    
        
        
        Assert.assertTrue("neighborhood should be set as visited",
            neighborhood.getVisited());
        
        boolean allHouseholdsVisited = false;
        boolean allCandiesVisited = false;
        
        for(NeighborhoodMemberLeaf household : neighborhood.getHouseholdList()) {
            
            allHouseholdsVisited = household.getVisited();
            
            for(NeighborhoodMemberLeaf candy : neighborhood.getHouseholdList()) {
                
                allCandiesVisited = candy.getVisited();
            }
        }
    
        Assert.assertTrue("all households in the neighborhood should " +
                "be set as visited", allHouseholdsVisited);
        Assert.assertTrue("all candies in the households in the " +
            "neighborhood should be set as visited", allCandiesVisited);
        
    }
    
    @Test
    public void testVisitHousehold() throws Exception {
    
        candyTraversalVisitor.setDesiredCandyMatch(false);
        candyTraversalVisitor.visit(neighborhood.getHouseholdList().get(0));
    
        Assert.assertTrue("household visited state is true because it has been " +
            "visited", neighborhood.getHouseholdList().get(0).getVisited());
        
    }
    
    @Test
    public void testVisitNeighborhoodCandy() throws Exception {
        
        Mansion newMansion = new Mansion("mansion");
        
        NeighborhoodCandy superSizeTwix = new NeighborhoodCandy("twix",
            "super size");
        
        superSizeTwix.setParent(newMansion);
        
        candyTraversalVisitor.setDesiredCandyMatch(false);
        
        candyTraversalVisitor.visit(superSizeTwix);
    
        Assert.assertTrue("candy visited state is true because it has been " +
            "visited", superSizeTwix.getVisited());
        
        Assert.assertTrue("desiredCandyMatch is true because the candy is " +
            "super size twix and the first household in the neighborhood is a mansion",
            candyTraversalVisitor.getDesiredCandyMatch());
        
        
    }
    
    
    @Test
    public void testWriteCandyPathToFile() throws Exception {
    
    
    }
    
}