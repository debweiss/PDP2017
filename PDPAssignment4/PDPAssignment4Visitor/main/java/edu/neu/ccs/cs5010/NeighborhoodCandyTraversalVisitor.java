package edu.neu.ccs.cs5010;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * NeighborhoodCandyTraversalVisitor - Visits the neighborhood
 * (households then candies) to find whether the desired candy name
 * and size matches a neighborhood candy name and size within those
 * households.
 */
public class NeighborhoodCandyTraversalVisitor implements Visitor {
    
    // desired candy name and size to compare with neighborhood candy
    private String desiredCandyName;
    private String desiredCandySize;
    
    // is there a match with desired candy name and size
    private boolean desiredCandyMatch = false;
    // traversal path if traversal exists
    private List<String> candyPath = new ArrayList<>();
    
    
    String getDesiredCandyName() {
        
        return this.desiredCandyName;
    }
    
    void setDesiredCandyName(String desiredCandyName) {
        
        this.desiredCandyName = desiredCandyName;
    }
    
    String getDesiredCandySize() {
        
        return this.desiredCandySize;
    }
    
    void setDesiredCandySize(String desiredCandySize) {
        
        this.desiredCandySize = desiredCandySize;
    }
    
    boolean getDesiredCandyMatch() {
        
        return desiredCandyMatch;
    }
    
    void setDesiredCandyMatch(boolean desiredCandyMatch) {
        
        this.desiredCandyMatch = desiredCandyMatch;
    }
    
    List<String> getCandyPath() {
        
        return this.candyPath;
    }
    
    void setCandyPath(List<String> candyPath) {
        
        this.candyPath = candyPath;
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
    
    /**
     * Sets the household visited state to true, visits each candy in
     * the household to see if it matches the desired candy. If it
     * finds a match, it stops visiting candies,if it doesn't find a match,
     * it keeps going.
     *
     * @param household the household that contains the neighborhood
     *                  candies.
     */
    @Override
    public void visit(Household household) {
        
        household.setVisited(true);
        
        int i = 0;
        
        for (NeighborhoodMemberLeaf candy : household.getChildren()) {
            
            if (!this.desiredCandyMatch) {
                
                candy.accept(this);
                
            } else {
                
                break;
            }
        }
    }
    
    /**
     * Sets the visited state of the neighborhood candy to true, then compares
     * the desired candy to the neighborhood candy. If they match, writes the
     * Candy Name, Candy Size, and Household in which the candy resides to the
     * candy path (candy path represents the traversal through the neighborhood),
     * and sets the desiredCandyMatch variable to true.
     *
     * @param neighborhoodCandy candy that is compared to desired candy.
     */
    public void visit(NeighborhoodCandy neighborhoodCandy) {
        
        neighborhoodCandy.setVisited(true);
        
        // if the candy name and size in the doc matches the visited candy
        if (this.desiredCandyName.toLowerCase().equals(neighborhoodCandy
            .getCandyName().toLowerCase())
            && this.desiredCandySize.toLowerCase().equals(neighborhoodCandy
            .getCandySize().toLowerCase())) {
            
            //build a string to write to the csv file
            String subPath = neighborhoodCandy.getCandyName()
                + ","
                + neighborhoodCandy.getCandySize()
                + ","
                + neighborhoodCandy.getParent().getName();
            
            this.candyPath.add(subPath); // add it to the candy path arraylist
            
            this.desiredCandyMatch = true; // true because there's a match
            
        }
    }
    
    /**
     * Takes the candy subpath (for each candy found, lists candy type,
     * candy size, household type) and writes it to a new file
     *
     * @param fileName   name of the file to write to ("Dream Traversal")
     * @param fileNumber number to append to the name of the file
     * @throws IOException when file is not found or not properly closed
     */
    void writeCandyPathToFile(String fileName, int fileNumber) throws IOException {
        
        // get path to file regardless of platform and create a writer for the file
        Path desiredCandyPathFile = FileSystems.getDefault().getPath
            (fileName + fileNumber).toAbsolutePath();
        BufferedWriter candyPathWriter = Files.newBufferedWriter(desiredCandyPathFile);
        
        // first write the header
        candyPathWriter.write("Candy type, Candy size, Household type\n");
        
        // for each subpath in the candyPath
        for (String candyNode : this.candyPath) {
            
            // write it to the "DreamTraversalX" file
            candyPathWriter.write(candyNode + "\n");
            
        }
        candyPathWriter.close();
    }
    
    @Override
    public void visit(Mansion mansion) {
    
    }
    
    @Override
    public void visit(DetachedHouse detachedHouse) {
    
    }
    
    @Override
    public void visit(Duplex duplex) {
    
    }
    
    @Override
    public void visit(Townhome townhome) {
    
    }
}

