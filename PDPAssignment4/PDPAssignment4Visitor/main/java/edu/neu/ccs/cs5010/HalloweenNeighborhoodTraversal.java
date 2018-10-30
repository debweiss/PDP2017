package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * HalloweenNeighborhoodTraversal -- creates and populates a neighborhood with
 * households and candies, takes each list of desired candies, checks it against
 * the neighborhood to determine if a traversal exists (i.e., all the desired candy
 * can be found in the neighborhood). If so, traces the traversal path and writes
 * it to a file.
 */
public class HalloweenNeighborhoodTraversal {
    
    // regex pattern for validating the DreamCandyX input file name
    private static final String INPUT_FILE_NAME_PATTERN = "^DreamCandy([0-9]+)\\.csv";
    // output file always starts with "DreamTraversal"
    private static final String OUTPUT_FILE_NAME = "DreamTraversal";
    
    // Through command line, main is passed one or more desired candy file names
    public static void main(String[] args) throws IOException {
        
        if (args.length == 0) {
            
            System.out.println("No filename was submitted, please try again");
            return;
            
        }
        
        int numberOfFiles = Integer.parseInt(args[0]); // number of candy files expected
        // Number to append to output file - same as integer 'X' in file 'DreamCandyX'
        int dreamTraversalFileNumber = 0;
        
        // false because we don't know whether there's a traversal yet
        boolean hasDesiredTraversalPath = false;
        
        // Create a new neighborhood (tree) and populate it with households and candies
        Neighborhood dreamCandyNH = new Neighborhood("DreamCandyNeighborhood");
        populateNeighborhood(dreamCandyNH);
        
        CandyParser candyParser = new CandyParser();
        
        for (int i = 1; i <= numberOfFiles; i++) { // for each filename
            
            if (candyParser.validateFileName(args[i], INPUT_FILE_NAME_PATTERN)) { // validate name
                
                // get path to file regardless of platform and create a reader for the file
                Path desiredCandyFilePath = FileSystems.getDefault().getPath(args[i])
                    .toAbsolutePath();
                BufferedReader desiredCandyFile = Files.newBufferedReader(desiredCandyFilePath);
                String line;
                
                NeighborhoodCandyTraversalVisitor neighborhoodCandyTraversalVisitor =
                    new NeighborhoodCandyTraversalVisitor();

                /* While candies remain in the list, and a traversal could still exist,
                read one line of the file at a time and split it into a list of candies */
                while ( (line = desiredCandyFile.readLine()) != null ) {
                    
                    String[] candies = line.split(",");

                    /* Check whether every candy in the DreamCandyX file can be found in
                    the neighborhood. If so, we have a traversal path. */
                    hasDesiredTraversalPath = traverseNeighborhood(dreamCandyNH,
                        candyParser, candies, neighborhoodCandyTraversalVisitor);
                    
                }
                
                desiredCandyFile.close();
                
                if (hasDesiredTraversalPath) { // if there's a way to get to all the candies

                    /* Change dreamTraversalFileNumber to the integer represented by
                    X in the original DreamCandyX file. */
                    dreamTraversalFileNumber = Integer.parseInt(args[i].substring(args[i]
                        .indexOf('y') + 1, args[i].length() - 4));
                    
                    // write the path to a file, appending the dreamTraversalFileNumber
                    neighborhoodCandyTraversalVisitor
                        .writeCandyPathToFile(OUTPUT_FILE_NAME, dreamTraversalFileNumber);
                    
                    // print congratulatory message
                    System.out.println("Congrats, you can retrieve all the desired candies from this ");
                    System.out.println("neighborhood. The traversal path has been written to file ");
                    System.out.println("'" + OUTPUT_FILE_NAME + dreamTraversalFileNumber + "'");
                } else {
                    // print message that there is no traversal
                    System.out.println("There is no way to traverse the neighborhood to ");
                    System.out.println("retrieve your desired list of candies for file " + args[i] + ".");
                }
                
            } else {
                
                System.out.println("Candy file is invalid, should be in the format 'DreamCandyX', " +
                    "where X is any integer");
            }
            
        }
    }
    
    /**
     * populateNeighborhood - populates an empty neighborhood with houses and candies
     *
     * @param neighborhood neighborhood to populate
     */
    private static void populateNeighborhood(Neighborhood neighborhood) {
        
        List<NeighborhoodMember> dreamCandyHouseholdList = new ArrayList<>();
        
        dreamCandyHouseholdList.add(new Mansion("mansion"));
        dreamCandyHouseholdList.add(new Duplex("duplex"));
        dreamCandyHouseholdList.add(new DetachedHouse("detached house"));
        dreamCandyHouseholdList.add(new Townhome("townhome"));
        
        PopulateNeighborhoodVisitor popVisitor = new PopulateNeighborhoodVisitor();
        popVisitor.setHouseholdList(dreamCandyHouseholdList);
        popVisitor.visit(neighborhood);
    }
    
    /**
     * For each desired candy, compares that candy name and size to the candies
     * in the Neighborhood. Returns true if a match is found, false if not.
     *
     * @param dreamCandyNH the Neighborhood to be traversed
     * @param candyParser  takes a file and extracts the desired candy names and sizes from it
     * @param candies      list of candies to find in the neighborhood
     * @return true if the desired candy matches one of the neighborhood candies,
     * false if not.
     */
    private static boolean traverseNeighborhood(Neighborhood dreamCandyNH,
        CandyParser candyParser, String[] candies, NeighborhoodCandyTraversalVisitor
        neighborhoodCandyTraversalVisitor) {
        
        List<String> candyNameAndSize = new ArrayList<>(); // desired candy name & size to compare
        
        for (String candy : candies) { // for each candy description in the list
            
            candyNameAndSize = candyParser.parseCandy(candy); // extract candy name & size

             /* set the information for desired candy's name and size in the visitor so
              * it can compare this candy with the Neighborhood candies. */
            neighborhoodCandyTraversalVisitor.setDesiredCandyName(candyNameAndSize.get(0));
            neighborhoodCandyTraversalVisitor.setDesiredCandySize(candyNameAndSize.get(1));
            
            // set to false because we don't know yet if the candy matches
            neighborhoodCandyTraversalVisitor.setDesiredCandyMatch(false);
            
            /* the visitor visits the neighborhood to see if it can match one of
             the neighborhood candies to the desired candy */
            dreamCandyNH.accept(neighborhoodCandyTraversalVisitor);
            if (!neighborhoodCandyTraversalVisitor.getDesiredCandyMatch()) {
                
                return false;
            }
        }
        
        return true;
    }
}