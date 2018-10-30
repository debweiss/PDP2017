package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CandyCategoriesTest {
    
    private CandyCategories candyCategories;
    
    @Before
    public void setUp() throws Exception {
        
        candyCategories = new CandyCategories();
    }
    
    @Test
    public void testGetHouseholdCandyNameList() throws Exception {

        /* Test mansion candy name list generation */
        
        List<String> mansionCandyNameList = new ArrayList<>();
        
        mansionCandyNameList.add("twix");
        mansionCandyNameList.add("snickers");
        mansionCandyNameList.add("mars");
        mansionCandyNameList.add("kit kat");
        mansionCandyNameList.add("whoppers");
        mansionCandyNameList.add("crunch");
        mansionCandyNameList.add("toblerone");
        mansionCandyNameList.add("baby ruth");
        mansionCandyNameList.add("almond joy");
        
        candyCategories.buildMansionCandyNameList();
        
        Assert.assertTrue("the list of candy categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getMansionCandyNameList().equals(mansionCandyNameList));


        /* Test duplex candy name list generation */
        
        List<String> duplexCandyNameList = new ArrayList<>();
        
        duplexCandyNameList.add("toblerone");
        duplexCandyNameList.add("baby ruth");
        duplexCandyNameList.add("almond joy");
        duplexCandyNameList.add("twix");
        duplexCandyNameList.add("snickers");
        duplexCandyNameList.add("mars");
        duplexCandyNameList.add("kit kat");
        duplexCandyNameList.add("whoppers");
        duplexCandyNameList.add("milky way");
        duplexCandyNameList.add("crunch");
        
        candyCategories.buildDuplexCandyNameList();
        
        Assert.assertTrue("the list of candy categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getDuplexCandyNameList().equals(duplexCandyNameList));


        /* Test detached house candy name list generation */
        
        List<String> DetachedHouseCandyNameList = new ArrayList<>();
        
        DetachedHouseCandyNameList.add("kit kat");
        DetachedHouseCandyNameList.add("whoppers");
        DetachedHouseCandyNameList.add("milky way");
        DetachedHouseCandyNameList.add("crunch");
        DetachedHouseCandyNameList.add("toblerone");
        DetachedHouseCandyNameList.add("twix");
        DetachedHouseCandyNameList.add("snickers");
        DetachedHouseCandyNameList.add("mars");
        
        candyCategories.buildDetachedHouseCandyNameList();
        
        Assert.assertTrue("the list of candy categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getDetachedHouseCandyNameList().equals(DetachedHouseCandyNameList));


         /* Test townhome house candy name list generation */
        
        List<String> TownhomeCandyNameList = new ArrayList<>();
        
        TownhomeCandyNameList.add("kit kat");
        TownhomeCandyNameList.add("whoppers");
        TownhomeCandyNameList.add("baby ruth");
        TownhomeCandyNameList.add("almond joy");
        TownhomeCandyNameList.add("toblerone");
        TownhomeCandyNameList.add("twix");
        TownhomeCandyNameList.add("snickers");
        TownhomeCandyNameList.add("mars");
        
        candyCategories.buildTownhomeCandyNameList();
        
        Assert.assertTrue("the list of candy categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getTownhomeCandyNameList().equals(TownhomeCandyNameList));
    }
    
    @Test
    public void testGetHouseholdSizeList() throws Exception {

        /* Test mansion candy name list generation */
        
        List<String> mansionCandySizeList = new ArrayList<>();
        
        
        mansionCandySizeList.add("super size");
        mansionCandySizeList.add("super size");
        mansionCandySizeList.add("super size");
        mansionCandySizeList.add("king size");
        mansionCandySizeList.add("king size");
        mansionCandySizeList.add("king size");
        mansionCandySizeList.add("fun size");
        mansionCandySizeList.add("fun size");
        mansionCandySizeList.add("fun size");
        
        candyCategories.buildMansionCandySizeList();
        
        Assert.assertTrue("the list of candy size categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getMansionCandySizeList().equals(mansionCandySizeList));


        /* Test duplex candy name list generation */
        
        List<String> duplexCandySizeList = new ArrayList<>();
        
        
        duplexCandySizeList.add("super size");
        duplexCandySizeList.add("super size");
        duplexCandySizeList.add("super size");
        duplexCandySizeList.add("king size");
        duplexCandySizeList.add("king size");
        duplexCandySizeList.add("king size");
        duplexCandySizeList.add("fun size");
        duplexCandySizeList.add("fun size");
        duplexCandySizeList.add("fun size");
        duplexCandySizeList.add("fun size");
        
        candyCategories.buildDuplexCandySizeList();
        
        Assert.assertTrue("the list of candy size categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getDuplexCandySizeList().equals(duplexCandySizeList));
        
        /* Test detached house candy name list generation */
        
        List<String> detachedHouseCandySizeList = new ArrayList<>();
        
        detachedHouseCandySizeList.add("super size");
        detachedHouseCandySizeList.add("super size");
        detachedHouseCandySizeList.add("super size");
        detachedHouseCandySizeList.add("super size");
        detachedHouseCandySizeList.add("king size");
        detachedHouseCandySizeList.add("fun size");
        detachedHouseCandySizeList.add("fun size");
        detachedHouseCandySizeList.add("fun size");
        
        
        candyCategories.buildDetachedHouseCandySizeList();
        
        Assert.assertTrue("the list of candy size categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getDetachedHouseCandySizeList().equals(detachedHouseCandySizeList));

        
         /* Test townhome house candy name list generation */
        
        List<String> townhomeCandySizeList = new ArrayList<>();
        
        townhomeCandySizeList.add("regular size");
        townhomeCandySizeList.add("regular size");
        townhomeCandySizeList.add("regular size");
        townhomeCandySizeList.add("regular size");
        townhomeCandySizeList.add("regular size");
        townhomeCandySizeList.add("regular size");
        townhomeCandySizeList.add("regular size");
        townhomeCandySizeList.add("regular size");
        
        candyCategories.buildTownhomeCandySizeList();
        
        Assert.assertTrue("the list of candy size categories created above " +
            "should be the same as the one created by the constructor",
            candyCategories.getTownhomeCandySizeList().equals(townhomeCandySizeList));
    }
}