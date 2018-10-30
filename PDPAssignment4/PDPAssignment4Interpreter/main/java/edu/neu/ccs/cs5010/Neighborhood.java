package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * Neighborhood contains the path through the neighborhood to gather
 * desired candies
 */
class Neighborhood  {

    private List<String> candyPath = new ArrayList<>();


    List<String> getCandyPath() {

        return candyPath;
    }

    public void setCandyPath(List<String> candyPath) {

        this.candyPath = candyPath;
    }

}