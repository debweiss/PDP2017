package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes a new neighborhood and populates it with households and candies
 */
public class PopulateNeighborhoodVisitor implements IPopulateNeighborhoodVisitor {

    private List<NeighborhoodMember> householdList = new ArrayList<>();
    private CandyCategories candyCategories = new CandyCategories();
    private SizeCategories sizeCategories = new SizeCategories();


    public List<NeighborhoodMember> getHouseholdList() {

        return householdList;
    }

    void setHouseholdList(List<NeighborhoodMember> householdList) {

        this.householdList = householdList;
    }

    @Override
    public void visit(Neighborhood neighborhood) {

        for (NeighborhoodMember household : this.householdList) {

            household.setParent(neighborhood);

            neighborhood.getHouseholdList().add(household);

        }

        for (NeighborhoodMember household : neighborhood.getHouseholdList()) {

            household.accept(this);
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

    public void visit(Mansion mansion) {

        this.candyCategories.buildMansionCandyNameList();
        this.sizeCategories.buildMansionCandySizeList();

        for (int i = 0; i < this.candyCategories.getMansionCandyNameList().size(); i++) {

            NeighborhoodCandy mansionCandy =
                new NeighborhoodCandy(this.candyCategories.getMansionCandyNameList().get(i),
                                         this.sizeCategories.getMansionCandySizeList().get(i));


            mansionCandy.setParent(mansion);
            mansion.getChildren().add(mansionCandy);


        }
    }


    public void visit(DetachedHouse detachedHouse) {


        this.candyCategories.buildDetachedHouseCandyNameList();
        this.sizeCategories.buildDetachedHouseCandySizeList();

        for (int i = 0; i < this.candyCategories.getDetachedHouseCandyNameList().size(); i++) {

            NeighborhoodCandy detachedHouseCandy =
                new NeighborhoodCandy(this.candyCategories.getDetachedHouseCandyNameList().get(i),
                                         this.sizeCategories.getDetachedHouseCandySizeList().get(i));

            detachedHouseCandy.setParent(detachedHouse);
            detachedHouse.getChildren().add(detachedHouseCandy);

        }
    }


    public void visit(Duplex duplex) {

        this.candyCategories.buildDuplexCandyNameList();
        this.sizeCategories.buildDuplexCandySizeList();

        for (int i = 0; i < this.candyCategories.getDuplexCandyNameList().size(); i++) {

            NeighborhoodCandy duplexCandy =
                new NeighborhoodCandy(this.candyCategories.getDuplexCandyNameList().get(i),
                                         this.sizeCategories.getDuplexCandySizeList().get(i));

            duplexCandy.setParent(duplex);
            duplex.getChildren().add(duplexCandy);

        }
    }

    public void visit(Townhome townhome) {

        this.candyCategories.buildTownhomeCandyNameList();
        this.sizeCategories.buildTownhomeCandySizeList();

        for (int i = 0; i < this.candyCategories.getTownhomeCandyNameList().size(); i++) {

            NeighborhoodCandy townhomeCandy =
                new NeighborhoodCandy(this.candyCategories.getTownhomeCandyNameList().get(i),
                                         this.sizeCategories.getTownhomeCandySizeList().get(i));

            townhomeCandy.setParent(townhome);
            townhome.getChildren().add(townhomeCandy);

        }
    }

    @Override
    public int hashCode() {
        int result = householdList.hashCode();
        result = 31 * result + candyCategories.hashCode();
        result = 31 * result + sizeCategories.hashCode();
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
        if (!candyCategories.equals(that.candyCategories)) {
            return false;
        }
        return sizeCategories.equals(that.sizeCategories);
    }

    @Override
    public String toString() {

        return "PopulateNeighborhoodVisitor{}";
    }
}
