package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SizeCategoriesTest {

    private SizeCategories sizeCategories;

    @Before
    public void setUp() throws Exception {

        sizeCategories = new SizeCategories();
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

        sizeCategories.buildMansionCandySizeList();

        Assert.assertTrue("the list of candy size categories created above should be the same as the one " +
             "created by the constructor", sizeCategories.getMansionCandySizeList().equals(mansionCandySizeList));


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

        sizeCategories.buildDuplexCandySizeList();

        Assert.assertTrue("the list of candy size categories created above should be the same as the one " +
             "created by the constructor", sizeCategories.getDuplexCandySizeList().equals(duplexCandySizeList));
            


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


        sizeCategories.buildDetachedHouseCandySizeList();

        Assert.assertTrue("the list of candy size categories created above should be the same as the one " +
             "created by the constructor", sizeCategories.getDetachedHouseCandySizeList().equals(detachedHouseCandySizeList));
            


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

        sizeCategories.buildTownhomeCandySizeList();

        Assert.assertTrue("the list of candy size categories created above should be the same as the one " +
             "created by the constructor", sizeCategories.getTownhomeCandySizeList().equals(townhomeCandySizeList));
    }

}