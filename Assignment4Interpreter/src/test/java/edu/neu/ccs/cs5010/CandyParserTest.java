package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CandyParserTest {

    private CandyParser candyParser;
    private String FILE_NAME_PATTERN;
    private String badFileNameNotDream;
    private String badFileNameWrongCapitalization;
    private String badFileNameEndsWithWords;
    private String goodFileNameOneDigit;
    private String goodFileNameMultipleDigits;
    private String superSizeTwix;
    private String regularSizeSnickers;
    private String regularSizeKitKat;
    private String funSizeKitKat;
    private String kingSizeAlmondJoy;



    @Before
    public void setUp() throws Exception {

        candyParser = new CandyParser();
        FILE_NAME_PATTERN = "^DreamCandy([0-9]+)";

        badFileNameNotDream = "Creamcandy5";
        badFileNameWrongCapitalization = "Dreamcandy5";
        badFileNameEndsWithWords = "DreamCandy5Seven";
        goodFileNameOneDigit = "DreamCandy6";
        goodFileNameMultipleDigits = "DreamCandy100";

        superSizeTwix = "Super Size Twix";
        regularSizeSnickers = "Snickers";
        regularSizeKitKat = "Kit Kat";
        funSizeKitKat = "Fun Size Kit Kat";
        kingSizeAlmondJoy = "King Size Almond Joy";
    }

    @Test
    public void testValidateFileName() throws Exception {

        Assert.assertFalse("Should return false, because the file name is not valid " +
                               "(should be in the form 'DreamcandyX', where 'X' is an integer) ",
            candyParser.validateFileName(badFileNameNotDream, FILE_NAME_PATTERN));

        Assert.assertFalse("Should return false, because the file name is not valid " +
                               "(should be in the form 'DreamcandyX', where 'X' is an integer) ",
            candyParser.validateFileName(badFileNameWrongCapitalization, FILE_NAME_PATTERN));

        Assert.assertFalse("Should return false, because the file name is not valid " +
                               "(should be in the form 'DreamcandyX', where 'X' is an integer) ",
            candyParser.validateFileName(badFileNameEndsWithWords, FILE_NAME_PATTERN));

        Assert.assertTrue("Should return true, because the file name is valid " +
                              "(it is in the form 'DreamcandyX', where 'X' is an integer) ",
            candyParser.validateFileName(goodFileNameOneDigit, FILE_NAME_PATTERN));

        Assert.assertTrue("Should return true, because the file name is valid " +
                              "(it is in the form 'DreamcandyX', where 'X' is an integer) ",
            candyParser.validateFileName(goodFileNameMultipleDigits, FILE_NAME_PATTERN));

    }

    @Test
    public void testParseCandy() throws Exception {

        List<String> candyNameAndSize = new ArrayList<>();

        candyNameAndSize.add("twix");
        candyNameAndSize.add("super size");

        Assert.assertTrue("Should return true because arraylist should include 'twix', 'super size' ",
            candyParser.parseCandy(superSizeTwix).get(0).toLowerCase().equals(candyNameAndSize.get(0))
                && candyParser.parseCandy(superSizeTwix).get(1).toLowerCase().equals(candyNameAndSize.get(1)));

        candyNameAndSize.clear();
        candyNameAndSize.add("snickers");
        candyNameAndSize.add("regular size");

        Assert.assertTrue("Should return true because arraylist should include 'snickers', 'regular size' ",
            candyParser.parseCandy(regularSizeSnickers).get(0).toLowerCase().equals(candyNameAndSize.get(0))
                && candyParser.parseCandy(regularSizeSnickers).get(1).toLowerCase().equals(candyNameAndSize.get(1)));

        candyNameAndSize.clear();
        candyNameAndSize.add("kit kat");
        candyNameAndSize.add("regular size");

        Assert.assertTrue("Should return true because arraylist should include 'kit kat', 'regular size' ",
            candyParser.parseCandy(regularSizeKitKat).get(0).toLowerCase().equals(candyNameAndSize.get(0))
                && candyParser.parseCandy(regularSizeKitKat).get(1).toLowerCase().equals(candyNameAndSize.get(1)));

        candyNameAndSize.clear();
        candyNameAndSize.add("kit kat");
        candyNameAndSize.add("fun size");

        Assert.assertTrue("Should return true because arraylist should include 'kit kat', 'fun size' ",
            candyParser.parseCandy(funSizeKitKat).get(0).toLowerCase().equals(candyNameAndSize.get(0))
                && candyParser.parseCandy(funSizeKitKat).get(1).toLowerCase().equals(candyNameAndSize.get(1)));

        candyNameAndSize.clear();
        candyNameAndSize.add("almond joy");
        candyNameAndSize.add("king size");

        Assert.assertTrue("Should return true because arraylist should include 'almond joy', 'king size' ",
            candyParser.parseCandy(kingSizeAlmondJoy).get(0).toLowerCase().equals(candyNameAndSize.get(0))
                && candyParser.parseCandy(kingSizeAlmondJoy).get(1).toLowerCase().equals(candyNameAndSize.get(1)));

        candyNameAndSize.clear();
        candyNameAndSize.add("mars");
        candyNameAndSize.add("king size");

        Assert.assertTrue("Should return true because arraylist should include 'mars', 'king size' ",
            candyParser.parseCandy(" King Size Mars").get(0).toLowerCase().equals(candyNameAndSize.get(0))
                && candyParser.parseCandy(" King Size Mars").get(1).toLowerCase().equals(candyNameAndSize.get(1)));

    }


    @Test
    public void testEquals() throws Exception {

        CandyParser parser1 = new CandyParser();
        CandyParser parser2 = new CandyParser();


        Assert.assertTrue("Should return true because parser1 is the same object as itself",
            parser1.equals(parser1));

        Assert.assertFalse("Should return false because parser1 is not the same object as parser2",
            parser1.equals(parser2));

    }

    @Test
    public void testToString() throws Exception {

        String testString = "CandyParser{}";

        Assert.assertTrue("Should return true because CandyParser toString() should return 'CandyParser{}' ",
            candyParser.toString().equals(testString));

    }

}