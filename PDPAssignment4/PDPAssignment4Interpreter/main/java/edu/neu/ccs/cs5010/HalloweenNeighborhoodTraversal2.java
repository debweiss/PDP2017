package edu.neu.ccs.cs5010;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * HalloweenNeighborhoodTraversal2 -- creates and populates a neighborhood, takes
 * each list of desired candies, checks that list against the neighborhood to determine if a
 * traversal exists, and if so, traces the traversal and writes the details of that
 * traversal to a file.
 */
public class HalloweenNeighborhoodTraversal2 {
    
    /* regex patterns to check against desired candy file, output file, and header for
    output file */
    private static final String INPUT_FILE_NAME_PATTERN = "^DreamCandy([0-9]+)\\.csv";
    private static final String OUTPUT_FILE_NAME = "DreamTraversal";
    private static final String OUTPUT_FILE_HEADER = "Candy type, Candy size, " +
        "Household type\n";
    
    public static void main(String[] args) throws IOException {
        
        /* contains the expressions to evaluate whether a given candy exists in the
        neighborhood and/or belongs to a given house type */
        NeighborhoodCandyExpression ncExpression = new NeighborhoodCandyExpression();
        
        CandyParser candyParser = new CandyParser();
        Neighborhood neighborhood = new Neighborhood();
        
        if (args.length == 0) { // if there are no command line arguments...
            
            /* ask user to try again */
            System.out.println("No filename(s) submitted, please try again");
            System.exit(1);
            
        }
        
        boolean hasDesiredTraversalPath = false;
        
        /* number of desired candy files that need to be evaluated and number that
         * will be incremented for each output file  */
        int numberOfFiles = Integer.parseInt(args[0]);
        int dreamTraversalFileNumber = 0;
        
        for (int i = 1; i <= numberOfFiles; i++) { // for each filename submitted
            
            /* ensure valid filename */
            if (candyParser.validateFileName(args[i], INPUT_FILE_NAME_PATTERN)) {
                
                /* Get desired candy file */
    
                Path desiredCandyFilePath = FileSystems.getDefault().getPath(args[i])
                    .toAbsolutePath();
                BufferedReader desiredCandyFile = Files.newBufferedReader(desiredCandyFilePath);
                String line;
                
                /* While there are candies remaining in the list, read one line of
                the file at a time to extract the candy names and sizes */
                while ( (line = desiredCandyFile.readLine()) != null ) {
                    
                    String[] candies = line.split(",");
                    
                    /* per line, check to see all desired candies exist in the neighborhood */
                    hasDesiredTraversalPath = traverseNeighborhood(neighborhood,
                        ncExpression, candyParser, candies);
                }
                
                desiredCandyFile.close();
                
                if (hasDesiredTraversalPath) { // if there's a way to get all the candies

                    /* Change dreamTraversalFileNumber to the integer represented by
                    X in the original DreamCandyX file. */
                    dreamTraversalFileNumber = Integer.parseInt(args[i].substring
                        (args[i].indexOf('y') + 1, args[i].length() - 4));
                    
                    /*  write the path to a file */
                    writeCandyPathToFile(neighborhood, OUTPUT_FILE_NAME,
                        dreamTraversalFileNumber);
                
                    /* Print message for user */
                    System.out.println("Congrats, you can retrieve all the desired " +
                        "candies from this ");
                    System.out.println("neighborhood. The traversal path has been " +
                        "written to file ");
                    System.out.println("'DreamTraversal" + dreamTraversalFileNumber + "'");
                    
                } else {
                    
                    System.out.println("There is no way to traverse the neighborhood to" +
                        "retrieve your desired list of candies for file " + args[i] + ".");
                }
                
            } else {
                
                System.out.println("Candy file is invalid, should be in the format " +
                    "'DreamCandyX', where X is any integer");
            }
        }
    }
    
    /**
     * For each line in the desired candy file, uses candy parser to extract candy
     * size and name, a line at a time, extracts the desired candy names and sizes
     * one at a time, and evaluates each candy based on the expressions found in
     * NeighborhoodCandyExpression, which describe which candy name and size combinations
     * exist in the neighborhood.
     *
     * @param neighborhood contains the candy traversal path to be updated if necessary
     * @param ncExpression contains the expressions to evaluate whether the desired candies
     *                     exist in the neighborhood
     * @param candyParser  takes a file and extracts the desired candy names and sizes from it
     * @param candies      list of desired candies to find in the neighborhood
     */
    private static boolean traverseNeighborhood(Neighborhood neighborhood,
        NeighborhoodCandyExpression ncExpression, CandyParser candyParser,
        String[] candies) {
        
        List<String> candySizeAndName = new ArrayList<>();
        
        for (String candy : candies) {
            
            /* extract candy size and name */
            candySizeAndName = candyParser.parseCandy(candy);
            
            /* if the expression is evaluated as false */
            if (!ncExpression.traversalPossible(candySizeAndName.get(0) + " "
                + candySizeAndName.get(1))) {
                
                return false; // return false to the program
            }

            /* If the traversal path is still possible, build a String consisting of
             candy name, candy size, and household type to write to the .csv file */
            String subPath = candySizeAndName.get(1) // name
                + ","
                + candySizeAndName.get(0) //size
                + ","
                + ncExpression.getHouseTypeFromCandy(candySizeAndName.get(0) + " " +
                candySizeAndName.get(1)); // house type
            
            neighborhood.getCandyPath().add(subPath); // add to candy path arraylist
            
        }
        
        return true; // traversal path still exists
    }
    
    /**
     * Takes the candy path (For each desired candy that exists in the neighborhood,
     * the candy path contains each candy's size, type, and household type that it
     * belongs to) and writes it to a new file in the format "DreamTraversalX",
     * where X represents the integer that was appended to the original "DreamCandyX"
     * input file.
     *
     * @param fileName   name of the file to write to ("DreamTraversal"
     * @param fileNumber number to append to the name of the file
     * @throws IOException, FileNotFoundException
     */
    private static void writeCandyPathToFile(Neighborhood neighborhood, String fileName,
        int fileNumber) throws IOException, FileNotFoundException {
        
        // get path to file regardless of platform and create a writer for the file
        Path desiredCandyPathFile = FileSystems.getDefault().getPath
            (fileName + fileNumber).toAbsolutePath();
        BufferedWriter candyPathWriter = Files.newBufferedWriter(desiredCandyPathFile);
            
            candyPathWriter.write(OUTPUT_FILE_HEADER);
            
            for (String candyNode : neighborhood.getCandyPath()) {
                
                candyPathWriter.write(candyNode + "\n");
                
            }
            
            candyPathWriter.close();
        }
    }
