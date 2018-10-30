package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CandyTest {

    private NeighborhoodCandy candy;

    @Before
    public void setUp() throws Exception {

        candy = new NeighborhoodCandy("twix", "super size");
    }

    @Test
    public void testGetCandySize() throws Exception {

        Assert.assertTrue("should return true because candy size is 'super size'",
            candy.getCandySize().equals("super size"));
    }

    @Test
    public void testSetCandySize() throws Exception {

        candy.setCandySize("king size");

        Assert.assertTrue("should return true because the set candy size is 'king size'",
            candy.getCandySize().equals("king size"));
    }

}