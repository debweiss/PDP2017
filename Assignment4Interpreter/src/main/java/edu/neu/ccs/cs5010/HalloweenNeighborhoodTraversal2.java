package edu.neu.ccs.cs5010;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



/**
 * HalloweenNeighborhoodTraversal2 -- creates and populates a neighborhood, takes
 * each list of desired candies, checks it against the neighborhood to determine if a
 * traversal exists, and if so, traces the traversal and writes the details of that
 * traversal to a file.
 */
public class HalloweenNeighborhoodTraversal2 {

    private static final String FILE_NAME = "DreamTraversal";

    public static void main(String[] args) throws IOException {

        NeighborhoodCandyExpression ncExpression = new NeighborhoodCandyExpression(); /* contains the expressions to
        evaluate whether a given candy exists in the neighborhood and/or belongs to a given house type */

        CandyParser candyParser = new CandyParser(); // extracts the desired candy names and sizes from the file
        String FILE_NAME_PATTERN = "^DreamCandy([0-9]+)\\.csv"; // pattern to check filename against
        Neighborhood neighborhood = new Neighborhood(); // neighborhood contains the candy traversal path

        if (args.length == 0) { // if there are no command line arguments...

            System.out.println("No filename was submitted, please try again"); // ask user to try again
            return;

        }

        boolean hasDesiredTraversalPath = false;

        int numberOfFiles = Integer.parseInt(args[0]);
        int dreamTraversalFileNumber = 0;

        for (int i = 1; i <= numberOfFiles; i++) { // for each of the filenames submitted

            if (candyParser.validateFileName(args[i], FILE_NAME_PATTERN)) { // ensure the filename is valid

                File candyFile = Paths.get(args[i]).toFile();

                BufferedReader inputFile = new BufferedReader(new FileReader(candyFile)); // get desired
                // candy file

                String line;

                /* While there are candies remaining in the list, read one line of the file at a time
                to extract the candy names and sizes */

                while ((line = inputFile.readLine()) != null) {

                    String[] candies = line.split(",");

                    // for each line of the file, check to see that all the desired candies exist in the neighborhood
                    hasDesiredTraversalPath = traverseNeighborhood(neighborhood, ncExpression, candyParser, candies);

                }

                inputFile.close();

                if (hasDesiredTraversalPath) { // if there's a way to get to all the candies

                    /* Output file that holds the traversal path needs to be called "DreamTraversal" plus
                    whatever integer is represented by X in the original 'DreamCandyX' file.
                    Take DreamCandyX file, and grab the substring that contains the integer only */
                    dreamTraversalFileNumber = Integer.parseInt(args[i].substring(args[i].indexOf('y') + 1,
                        args[i].length() - 4));

                    // write the path to a file
                    writeCandyPathToFile(neighborhood, "DreamTraversal", dreamTraversalFileNumber);

                    System.out.println("Congrats, you can retrieve all the desired candies from this ");
                    System.out.println("neighborhood. The traversal path has been written to file ");
                    System.out.println("'DreamTraversal" + dreamTraversalFileNumber + "'");
                }

                else {

                    System.out.println("There is no way to traverse the neighborhood to retrieve your desired list of" +
                                           " candies ");
                    System.out.println("for file " + args[i] + ".");
                }

            }

            else {

                System.out.println("Candy file is invalid, should be in the format 'DreamCandyX', " +
                                       "where X is any integer");
            }

        }
    }

    /** For each line in the desired candy file, uses candy parser to extract candy size and name,
     * a line at a time, extracts the desired candy names and sizes one at a time, and evaluates each
     * candy based on the expressions found in NeighborhoodCandyExpression, which describe which candy
     * name and size combinations exist in the neighborhood.
     *
     * @param neighborhood contains the candy traversal path to be updated if necessary
     * @param ncExpression contains the expressions to evaluate whether certain candies exist in the neighborhood
     * @param candyParser takes a file and extracts the desired candy names and sizes from it
     * @param candies     list of candies to find in the neighborhood
     *
     */
    private static boolean traverseNeighborhood(Neighborhood neighborhood, NeighborhoodCandyExpression ncExpression,
        CandyParser candyParser, String[] candies) {

        List<String> candySizeAndName = new ArrayList<>();

        for (String candy : candies) {

            candySizeAndName = candyParser.parseCandy(candy); // extract candy name and size

            if (!ncExpression.traversalPossible(candySizeAndName.get(0) + " " + candySizeAndName.get(1))) {

                return false;
            }

            String subPath = candySizeAndName.get(1) //build a string to write to the csv file; name
                                 + ","
                                 + candySizeAndName.get(0) //size
                                 + ","
                                 + ncExpression.getHouseTypeFromCandy(candySizeAndName.get(0) + " " +
                                                                          candySizeAndName.get(1)); // house type

            neighborhood.getCandyPath().add(subPath); // add it to the candy path arraylist

        }

        return true;
    }

    /**
     * Takes the candy path (Lists of size, name, household) and writes it to a new file
     *
     * @param filename   name of the file to write to ("Dream Traversal"
     * @param fileNumber number to append to the name of the file
     * @throws IOException, FileNotFoundException
     */
    private static void writeCandyPathToFile(Neighborhood neighborhood, String filename, int fileNumber)
        throws IOException, FileNotFoundException {

        try (BufferedWriter candyPathWriter = new BufferedWriter(new FileWriter(filename + fileNumber))) {

            candyPathWriter.write("Candy type, Candy size, Household type\n");

            for (String candyNode : neighborhood.getCandyPath()) {

                candyPathWriter.write(candyNode + "\n");

            }

            candyPathWriter.close();
        }
    }
}