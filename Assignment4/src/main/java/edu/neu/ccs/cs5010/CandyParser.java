package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the filename to make sure it is valid for the Dream Candy Neighborhood and
 * parses strings in a valid file to return candy name and candy size.
 */
class CandyParser {

    /**
     * Given a regex pattern (in this case a valid DreamCandy file name), returns whether
     * the filename provided matches that pattern.
     *
     * @param fileName        the name of the file to validate
     * @param fileNamePattern the regex pattern to be matched against
     * @return true if the filename matches (is valid), returns false if the filename is not valid
     */
    boolean validateFileName(String fileName, String fileNamePattern) {

        Pattern p = Pattern.compile(fileNamePattern);
        Matcher m = p.matcher(fileName);

        return m.matches();
    }

    /**
     * Parses a string that contains candy name and size and returns an array list with two strings:
     * Candy Name and Candy Size.
     *
     * @param candy string containing the name and size of the candy (one candy from the list of candies)
     * @return an ArrayList that contains candyName, candySize
     */
    List<String> parseCandy(String candy) {

        List<String> candyNameAndSize = new ArrayList<>();

        String candySize;
        String candyName;


        if (candy.toLowerCase().contains("size")) { // If the string has the word "size" in it

            // take everything up to the end of 'Size' to get candy size

            candy = candy.trim();

            candySize = candy.substring(0, candy.indexOf(" ") + 1) + "size";
            candySize = candySize.trim();

            // take everything from the end of 'Size'  to the end of the candy name for candyName
            candyName = (candy.substring(candySize.length() + 1, candy.length())).trim();

        }

        else {

            // if a size isn't listed in the doc, just call it regular size
            candySize = SizeCategories.SizeCategory.REGULAR_SIZE.getValue().trim();
            candyName = candy.trim();
        }

        candyNameAndSize.add(candyName); // add candy name to array
        candyNameAndSize.add(candySize); // add candy name to array

        return candyNameAndSize;
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

    @Override
    public String toString() {

        return "CandyParser{}";
    }
}