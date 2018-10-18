package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class NeighborhoodCandyExpressionTest {

    private NeighborhoodCandyExpression neighborhoodCandyExpression;

    @Before
    public void setUp() {

        neighborhoodCandyExpression = new NeighborhoodCandyExpression();
    }

    @Test
    public void testTraversalPossible() throws Exception {


        Assert.assertTrue("should return true because super size twix belongs in the neighborhood",
            neighborhoodCandyExpression.traversalPossible("super size twix"));

        Assert.assertTrue("should return true because fun size kit kat belongs in the neighborhood",
            neighborhoodCandyExpression.traversalPossible("fun size kit kat"));

        Assert.assertTrue("should return true because super size snickers belongs in the neighborhood",
            neighborhoodCandyExpression.traversalPossible("super size snickers"));

        Assert.assertTrue("should return true because regular size whoppers belongs in the neighborhood",
            neighborhoodCandyExpression.traversalPossible("regular size whoppers"));

        Assert.assertFalse("should return false because regular size hersheys don't belong in the neighborhood",
            neighborhoodCandyExpression.traversalPossible("regular size hersheys"));
    }

    @Test
    public void testGetHouseTypeFromCandy() throws Exception {

        Assert.assertTrue("should return true because super size twix belongs to a Mansion",
            neighborhoodCandyExpression.getHouseTypeFromCandy("super size twix").equals("Mansion"));

        Assert.assertTrue("should return true because fun size kit kat belongs to a Duplex",
            neighborhoodCandyExpression.getHouseTypeFromCandy("fun size kit kat").equals("Duplex"));

        Assert.assertTrue("should return true because super size milky way belongs to a Detached House",
            neighborhoodCandyExpression.getHouseTypeFromCandy("super size milky way").equals("Detached House"));

        Assert.assertTrue("should return true because regular size whoppers belongs to a Townhome",
            neighborhoodCandyExpression.getHouseTypeFromCandy("regular size whoppers").equals("Townhome"));


    }

}