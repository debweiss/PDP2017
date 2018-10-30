package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PopulateNeighborhoodVisitorTest {

    private PopulateNeighborhoodVisitor populateNeighborhoodVisitor;
    private PopulateNeighborhoodVisitor visitHouseholdsVisitor;
    private PopulateNeighborhoodVisitor visitHouseholdTypesVisitor;
    private List<NeighborhoodMember> dreamCandyHouseholdList;
    private Neighborhood dreamCandyNH;
    private Mansion visitMansion;
    private Duplex visitDuplex;
    private DetachedHouse visitDetachedHouse;
    private Townhome visitTownhome;
    private List<NeighborhoodMemberLeaf> mansionCandyList;
    private List<NeighborhoodMemberLeaf> duplexCandyList;
    private List<NeighborhoodMemberLeaf> detachedHouseCandyList;
    private List<NeighborhoodMemberLeaf> townhomeCandyList;


    @Before
    public void setUp() throws Exception {

        populateNeighborhoodVisitor = new PopulateNeighborhoodVisitor();
        visitHouseholdsVisitor = new PopulateNeighborhoodVisitor();
        visitHouseholdTypesVisitor = new PopulateNeighborhoodVisitor();

        dreamCandyNH = new Neighborhood("DreamCandyNeighborhood");
        dreamCandyHouseholdList = new ArrayList<>();

        dreamCandyHouseholdList.add(new Mansion("mansion"));
        dreamCandyHouseholdList.add(new Duplex("duplex"));
        dreamCandyHouseholdList.add(new DetachedHouse("detached house"));
        dreamCandyHouseholdList.add(new Townhome("townhome"));

        visitMansion = new Mansion("visitMansion");
        visitDetachedHouse = new DetachedHouse("visitDetachedHouse");
        visitDuplex = new Duplex("visitDuplex");
        visitTownhome = new Townhome("visitTownHome");

        mansionCandyList = new ArrayList<>();
        duplexCandyList = new ArrayList<>();
        detachedHouseCandyList = new ArrayList<>();
        townhomeCandyList = new ArrayList<>();
        
        mansionCandyList.add(new NeighborhoodCandy("twix",
            "super size"));
        mansionCandyList.add(new NeighborhoodCandy("snickers",
            "super size"));
        mansionCandyList.add(new NeighborhoodCandy("mars",
            "super size"));
        mansionCandyList.add(new NeighborhoodCandy("kit kat",
            "king size"));
        mansionCandyList.add(new NeighborhoodCandy("whoppers",
            "king size"));
        mansionCandyList.add(new NeighborhoodCandy("crunch",
            "king size"));
        mansionCandyList.add(new NeighborhoodCandy("toblerone",
            "fun size"));
        mansionCandyList.add(new NeighborhoodCandy("baby ruth",
            "fun size"));
        mansionCandyList.add(new NeighborhoodCandy("almond joy",
            "fun size"));
        
        detachedHouseCandyList.add(new NeighborhoodCandy("kit kat",
            "super size"));
        detachedHouseCandyList.add(new NeighborhoodCandy("whoppers",
            "super size"));
        detachedHouseCandyList.add(new NeighborhoodCandy("milky way",
            "super size"));
        detachedHouseCandyList.add(new NeighborhoodCandy("crunch",
            "super size"));
        detachedHouseCandyList.add(new NeighborhoodCandy("toblerone",
            "king size"));
        detachedHouseCandyList.add(new NeighborhoodCandy("twix",
            "fun size"));
        detachedHouseCandyList.add(new NeighborhoodCandy("snickers",
            "fun size"));
        detachedHouseCandyList.add(new NeighborhoodCandy("mars",
            "fun size"));

        duplexCandyList.add(new NeighborhoodCandy("toblerone",
            "super size"));
        duplexCandyList.add(new NeighborhoodCandy("baby ruth",
            "super size"));
        duplexCandyList.add(new NeighborhoodCandy("almond joy",
            "super size"));
        duplexCandyList.add(new NeighborhoodCandy("twix",
            "king size"));
        duplexCandyList.add(new NeighborhoodCandy("snickers",
            "king size"));
        duplexCandyList.add(new NeighborhoodCandy("mars",
            "king size"));
        duplexCandyList.add(new NeighborhoodCandy("kit kat",
            "fun size"));
        duplexCandyList.add(new NeighborhoodCandy("whoppers",
            "fun size"));
        duplexCandyList.add(new NeighborhoodCandy("milky way",
            "fun size"));
        duplexCandyList.add(new NeighborhoodCandy("crunch",
            "fun size"));

        townhomeCandyList.add(new NeighborhoodCandy("kit kat",
            "regular size"));
        townhomeCandyList.add(new NeighborhoodCandy("whoppers",
            "regular size"));
        townhomeCandyList.add(new NeighborhoodCandy("baby ruth",
            "regular size"));
        townhomeCandyList.add(new NeighborhoodCandy("almond joy",
            "regular size"));
        townhomeCandyList.add(new NeighborhoodCandy("toblerone",
            "regular size"));
        townhomeCandyList.add(new NeighborhoodCandy("twix",
            "regular size"));
        townhomeCandyList.add(new NeighborhoodCandy("snickers",
            "regular size"));
        townhomeCandyList.add(new NeighborhoodCandy("mars",
            "regular size"));

    }

    @Test
    public void testVisitNeighborhood() throws Exception {

        populateNeighborhoodVisitor.setHouseholdList(dreamCandyHouseholdList);

        populateNeighborhoodVisitor.visit(dreamCandyNH);

        assertTrue("the new household list after the neighborhood has been visited " +
            "should be the same as the dreamCandyHouseholdList", dreamCandyNH.getHouseholdList()
            .equals(dreamCandyHouseholdList));

        assertTrue("Should return true because the first household in the household" +
            " list should be a mansion", dreamCandyNH.getHouseholdList().get(0) instanceof Mansion);

        assertTrue("Should return true because the second household in the household " +
            "list should be a duplex", dreamCandyNH.getHouseholdList().get(1) instanceof Duplex);

        assertTrue("Should return true because the third household in the household " +
            "list should be a detached house", dreamCandyNH.getHouseholdList().get(2)
            instanceof DetachedHouse);

        assertTrue("Should return true because the fourth household in the " +
            "household list should be a townhome", dreamCandyNH.getHouseholdList().get(3)
            instanceof Townhome);
    }

    @Test
    public void testVisitHousehold() throws Exception {

        Mansion visitMansion2 = new Mansion("visitMansion2");

        visitHouseholdsVisitor.visit(visitMansion2);
        
        assertTrue("the new candy list after the mansion has been visited " +
            "should be the same as the mansionCandyList", visitMansion2.getChildren()
            .containsAll(mansionCandyList));

    }

    @Test
    public void testVisitMansion() throws Exception {

        visitHouseholdTypesVisitor.visit(visitMansion);

        assertTrue("the new candy list after the mansion has been visited " +
            "should be the same as the mansionCandyList", visitMansion.getChildren()
            .equals(mansionCandyList));
    }

    @Test
    public void testVisitDuplex() throws Exception {

        visitHouseholdTypesVisitor.visit(visitDuplex);

        assertTrue("the new candy list after the duplex has been visited" +
            " should be the same as the duplexCandyList", visitDuplex.getChildren()
            .equals(duplexCandyList));
    }

    @Test
    public void testVisitDetachedHouse() throws Exception {

        visitHouseholdTypesVisitor.visit(visitDetachedHouse);

        assertTrue("the new candy list after the detachedHouse has been visited " +
            "should be the same as the detachedHouseCandyList", visitDetachedHouse.getChildren()
            .equals(detachedHouseCandyList));
    }

    @Test
    public void testVisitTownhome() throws Exception {

        visitHouseholdTypesVisitor.visit(visitTownhome);

        assertTrue("the new candy list after the townhome has been visited should " +
            "be the same as the townhomeCandyList", visitTownhome.getChildren()
            .equals(townhomeCandyList));

    }

}