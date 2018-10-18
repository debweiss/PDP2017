package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.io.File;


/**
 * HalloweenNeighborhoodTraversal -- creates and populates a neighborhood, takes
 * each list of desired candies, checks it against the neighborhood to determine if a
 * traversal exists, and if so, traces the traversal and writes the details of that
 * traversal to a file.
 */
public class HalloweenNeighborhoodTraversal {

    private static final String FILE_NAME = "DreamTraversal";

    /* Through command line, main is passed one or more desired candy file names */
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {

            System.out.println("No filename was submitted, please try again");
            return;

        }

        /* Create a new neighborhood (tree) and populate it with households and candies */
        Neighborhood dreamCandyNH = new Neighborhood("DreamCandyNeighborhood");

        populateNeighborhood(dreamCandyNH);

        String FILE_NAME_PATTERN = "^DreamCandy([0-9]+)\\.csv";

        CandyParser candyParser = new CandyParser(); // extracts the desired candy names and sizes from the file

        boolean hasDesiredTraversalPath = false;

        int numberOfFiles = Integer.parseInt(args[0]);
        int dreamTraversalFileNumber = 0;

        for (int i = 1; i <= numberOfFiles; i++) { // for each filename

            if (candyParser.validateFileName(args[i], FILE_NAME_PATTERN)) { // ensure the filename is valid

                File candyFile = Paths.get(args[i]).toFile();

                BufferedReader inputFile = new BufferedReader(new FileReader(candyFile)); // get desired candy file

                String line;

                NeighborhoodCandyTraversalVisitor neighborhoodCandyTraversalVisitor =
                    new NeighborhoodCandyTraversalVisitor();

                /* While there are candies remaining in the list, and it's still
                possible that a traversal exists read one line of the file at a time
                to extract the candy name and size */
                while ((line = inputFile.readLine()) != null) {

                    String[] candies = line.split(",");

                    /* For each line of the file, traverse neighborhood checks to see that
                    all the candies match */
                    hasDesiredTraversalPath = traverseNeighborhood(dreamCandyNH,
                        candyParser, candies, neighborhoodCandyTraversalVisitor);

                }

                inputFile.close();

                if (hasDesiredTraversalPath) { // if there's a way to get to all the candies

                    /* Output file that holds the traversal path needs to be called "DreamTraversal" plus
                    whatever integer is represented by X in the original 'DreamCandyX' file.
                    Take DreamCandyX file, and grab the substring that contains the integer only */
                    dreamTraversalFileNumber = Integer.parseInt(args[i].substring(args[i].indexOf('y') + 1,
                        args[i].length() - 4));

                    neighborhoodCandyTraversalVisitor // write the path to a file
                        .writeCandyPathToFile(FILE_NAME, dreamTraversalFileNumber);

                    System.out.println("Congrats, you can retrieve all the desired candies from this ");
                    System.out.println("neighborhood. The traversal path has been written to file ");
                    System.out.println("'"+ FILE_NAME + dreamTraversalFileNumber + "'");
                }

                else {

                    System.out.println("There is no way to traverse the neighborhood to retrieve your " +
                       "desired list of candies ");
                    System.out.println("for file " + args[i]+ ".");
                }

            }

            else {

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
    public static void populateNeighborhood(Neighborhood neighborhood) {

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
     * Takes a Neighborhood, a filename, a CandyParser, and a Neighborhood visitor, reads
     * the file a line at a time, extracts the desired candy names and sizes one at a time,
     * gives them to the NeighborhoodVisitor so that the visitor can visit each household and
     * candy in the neighborhood and compare the desired candies to what is in
     * the neighborhood
     *
     * @param dreamCandyNH the Neighborhood to be traversed
     * @param candyParser  takes a file and extracts the desired candy names and sizes from it
     * @param candies      list of candies to find in the neighborhood
     */
    static boolean traverseNeighborhood(Neighborhood dreamCandyNH, CandyParser candyParser, String[] candies,
                                        NeighborhoodCandyTraversalVisitor neighborhoodCandyTraversalVisitor) {

        List<String> candyNameAndSize = new ArrayList<>(); // holds desired candy name and size to be compared

        for (String candy : candies) {

            candyNameAndSize = candyParser.parseCandy(candy); // extract candy name and size & put in list

             /* first list item will be compared with candy names in the neighborhood,
            second list item with candy sizes in the neighborhood */
            neighborhoodCandyTraversalVisitor.setCompareCandyName(candyNameAndSize.get(0));
            neighborhoodCandyTraversalVisitor.setCompareCandySize(candyNameAndSize.get(1));

            neighborhoodCandyTraversalVisitor.setCompareCandyMatch(false);

            dreamCandyNH.accept(neighborhoodCandyTraversalVisitor); // visitor has the desired candy name and size,
            // now can visit the NH

            if (!neighborhoodCandyTraversalVisitor.getCompareCandyMatch()) {

                return false;
            }
        }

        return true;
    }
}