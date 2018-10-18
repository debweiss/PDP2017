package edu.neu.ccs.cs5010;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Visits the neighborhood, and in turn visits households and then candies
 * within those households */

public class NeighborhoodCandyTraversalVisitor implements INeighborhoodCandyTraversalVisitor {

    private String compareCandyName; // desired candy name that neighborhood candy name is compared to
    private String compareCandySize; // desired candy size that neighborhood candy name is compared to
    private boolean compareCandyMatch = false; // whether candies match
    private List<String> candyPath = new ArrayList<>(); // traversal path if traversal exists


    /******************* GETTERS & SETTERS *****************/

    String getCompareCandyName() {

        return this.compareCandyName;
    }

    void setCompareCandyName(String compareCandyName) {

        this.compareCandyName = compareCandyName;
    }

    String getCompareCandySize() {

        return this.compareCandySize;
    }

    void setCompareCandySize(String compareCandySize) {

        this.compareCandySize = compareCandySize;
    }

    List<String> getCandyPath() {

        return this.candyPath;
    }

    void setCandyPath(List<String> candyPath) {

        this.candyPath = candyPath;
    }

    @Override
    public boolean getCompareCandyMatch() {

        return compareCandyMatch;
    }

    public void setCompareCandyMatch(boolean compareCandyMatch) {

        this.compareCandyMatch = compareCandyMatch;
    }

    /************** VISIT methods *********************************/
    @Override
    public void visit(Neighborhood neighborhood) {

        neighborhood.setVisited(true);

        for (NeighborhoodMember household : neighborhood.getHouseholdList()) {

            household.accept(this);
        }

    }

    @Override
    public void visit(NeighborhoodMemberLeaf neighborhoodLeaf) {

        neighborhoodLeaf.setVisited(true);

    }

    @Override
    public void visit(NeighborhoodMember neighborhoodMember) {

        neighborhoodMember.setVisited(true);

    }

    @Override
    public void visit(Household household) {

        household.setVisited(true);
        int i = 0;

        for (NeighborhoodMemberLeaf candy : household.getChildren()) {

            if (!this.compareCandyMatch) {

                candy.accept(this);

            }

            else {

                break;
            }
        }
    }

    public void visit(NeighborhoodCandy neighborhoodCandy) {

        neighborhoodCandy.setVisited(true);

        // if the candy name and size in the doc matches the visited candy
        if (this.compareCandyName.toLowerCase().equals(neighborhoodCandy.getCandyName().toLowerCase())
           && this.compareCandySize.toLowerCase().equals(neighborhoodCandy.getCandySize().toLowerCase())) {


            String subPath = neighborhoodCandy.getCandyName() //build a string to write to the csv file
                + ","
                + neighborhoodCandy.getCandySize()
                + ","
                + neighborhoodCandy.getParent().getName();

            this.candyPath.add(subPath); // add it to the candy path arraylist

            this.compareCandyMatch = true;

        }
    }


    /**
     * Takes the candy path (for each candy found, lists candy type, candy size, household type)
     * and writes it to a new file
     *
     * @param filename   name of the file to write to ("Dream Traversal"
     * @param fileNumber number to append to the name of the file
     * @throws IOException
     */
    void writeCandyPathToFile(String filename, int fileNumber) throws IOException {

        try (BufferedWriter candyPathWriter = new BufferedWriter(new FileWriter(filename + fileNumber))) {

            candyPathWriter.write("Candy type, Candy size, Household type\n"); // file header

            for (String candyNode : this.candyPath) {

                candyPathWriter.write(candyNode + "\n");

            }

            candyPathWriter.close();
        }
    }
}

