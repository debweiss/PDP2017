package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


public class EmailDataProcessorTest {
    
    private String[] userInput;
    private ErrorGenerator errorGenerator = new ErrorGenerator();
    private EmailDataProcessor dataProcessor = new  EmailDataProcessor();
    private EmailDataValidator dataValidator = new  EmailDataValidator();
    private Pattern emailPattern = dataValidator.emailPattern;
    
    @Before
    public void setUp() {
        
    
        String[] missingEventInput = {"--email-template email-template.txt'", "--output-dir emailOutput",
            "--csv-file  Flight363FromSeattleToBoston.csv"};
    
        String[] missingDirInput = {"--event arrival", "--email-template email-template.txt",
            "--csv-file  Flight363FromSeattleToBoston.csv"};
    
        String[] missingTemplateInput = {"--event arrival", "--output-dir emailOutput",
            "--csv-file  Flight363FromSeattleToBoston.csv"};
    
        String[] missingCsvInput = {"--event arrival", "--email-template email-template.txt",
            "--output-dir emailOutput", "--csv-file  Flight363FromSeattleToBoston.csv"};
    
        String[] outputDirNotExistInput = {"--event arrival", "--email-template email-template.txt",
            "--output-dir weirdDir", "--csv-file  Flight363FromSeattleToBoston.csv"};
    
        StringBuilder buildUsageError = new StringBuilder("Usage\n" + "\n")
            .append("--email-template <file>     accepts a filename that holds an email template\n" + "\n")
            .append("--output-dir <path>         accepts the name of a folder all output is placed\n")
            .append("                            in this folder\n" + "\n")
            .append("--csv-file <path>           accepts the name of the csv file to process in the\n")
            .append("                            following format\n")
            .append("                            Flight<id>From<departure-city>To<destination-city>.csv\n" + "\n")
            .append("--event<details>            specifies if the delay refers to departure or arrival");
        
    }
    
    @Test
    public void testReadEmailUserInput() throws Exception {
        
        /* valid input -- in order of instructions */
    
        String[] goodUserInput = {"--email-template", "email-template1.txt",
            "--output-dir", "emailOutput", "--csv-file",  "Flight363FromSeattleToBoston.csv",
            "--event", "arrival"};
        
        HashMap<String, String> testGoodUserInput;
        HashMap<String, String> emailUserInputSubs = new HashMap<>();
        
        emailUserInputSubs.put("--email-template", "email-template1.txt");
        emailUserInputSubs.put("--output-dir", "emailOutput");
        emailUserInputSubs.put("--csv-file", "Flight363FromSeattleToBoston.csv");
        emailUserInputSubs.put("--event", "arrival");
    
        testGoodUserInput = dataProcessor.readEmailUserInput
            (goodUserInput, errorGenerator);
        
        Assert.assertTrue("resulting maps should be equal",
            testGoodUserInput.entrySet().equals(emailUserInputSubs.entrySet()));
        
         /* valid input -- out of order */
    
        String[] differentOrderArgs = {"--event", "arrival", "--output-dir",
            "emailOutput", "--csv-file", "Flight363FromSeattleToBoston.csv",
            "--email-template", "email-template1.txt", };
    
        HashMap<String, String> testDifferentOrderInput;
        HashMap<String, String> differentOrderSubs = new HashMap<>();
    
        differentOrderSubs.put("--event", "arrival");
        differentOrderSubs.put("--output-dir", "emailOutput");
        differentOrderSubs.put("--csv-file", "Flight363FromSeattleToBoston.csv");
        differentOrderSubs.put("--email-template", "email-template1.txt");
        
        testDifferentOrderInput = dataProcessor.readEmailUserInput(differentOrderArgs,
            errorGenerator);
    
        Assert.assertTrue("resulting maps should be equal regardless " +
            "of order of arguments", testDifferentOrderInput.entrySet()
            .equals(differentOrderSubs.entrySet()));
    }
    
    @Test
    public void readEmailTemplateData() throws Exception {
    
        HashMap<String, String> substitutions = new HashMap<>();
        HashMap<String, String> testSubstitutions;
    
        File inputFile = new File("/Users/debweiss/Desktop/email-template.txt");
        File unknownPlaceholderFile = new File
            ("/Users/debweiss/Desktop/email-template-unknown-placeholder.txt");
    
        Flight flight = new Flight("363", "Seattle", "Boston");
        flight.setEvent("arrival");
    
        Passenger passenger = new Passenger("raul", "julia",
            "14 parker street", "Boston", "King", "MA",
            "98122", "555-555-5555", "deb@deb.com", "silver");
    
        String placeholderDate = "[[Date]]";
        String placeholderEmail = "[[email]]";
        String placeholderEvent = "[[event]]";
        String placeholderFirstName = "[[first_name]]";
        String placeholderLastName = "[[last_name]]";
        String placeholderDepCity = "[[departure-city]]";
        String placeholderDestCity = "[[destination-city]]";
        String placeholderRewards = "[[rewards]]";
    
        substitutions.put(placeholderDate, "theDate");
        substitutions.put(placeholderEmail, "deb@deb.com");
        substitutions.put(placeholderEvent, "arrival");
        substitutions.put(placeholderFirstName, "raul");
        substitutions.put(placeholderLastName, "julia");
        substitutions.put(placeholderDepCity, "Seattle");
        substitutions.put(placeholderDestCity, "Boston");
        substitutions.put(placeholderRewards, "silver");
    
        testSubstitutions = dataProcessor.readEmailTemplateData(emailPattern,
            inputFile, flight, passenger, dataValidator);
    
        Assert.assertTrue("'[[Date]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[Date]]"));
        Assert.assertTrue("'[[email]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[email]]"));
        Assert.assertTrue("'[[first_name]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[first_name]]"));
        Assert.assertTrue("'[[[last_name]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[last_name]]"));
        Assert.assertTrue("'[[event]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[event]]"));
        Assert.assertTrue("'[[departure-city]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[departure-city]]"));
        Assert.assertTrue("'[[destination-city]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[destination-city]]"));
        Assert.assertTrue("'[[rewards]]' should be one of the keys",
            testSubstitutions.keySet().contains("[[rewards]]"));
    
    
        Assert.assertTrue("'deb@deb.com' should be the value for key '[[email]]'",
            testSubstitutions.get("[[email]]").equals("deb@deb.com"));
        Assert.assertTrue("'arrival' should be the value for key '[[event]]'",
            testSubstitutions.get("[[event]]").equals("arrival"));
        Assert.assertTrue("'raul' should be the value for key '[[first_name]]'",
            testSubstitutions.get("[[first_name]]").equals("raul"));
        Assert.assertTrue("'julia' should be the value for key '[[last_name]]'",
            testSubstitutions.get("[[last_name]]").equals("julia"));
        Assert.assertTrue("'Seattle' should be the value for key '[[departure-city]]'",
            testSubstitutions.get("[[departure-city]]").equals("Seattle"));
        Assert.assertTrue("'Boston' should be the value for key '[[destination-city]]'",
            testSubstitutions.get("[[destination-city]]").equals("Boston"));
        Assert.assertTrue("'silver' should be the value for key '[[rewards]]'",
            testSubstitutions.get("[[rewards]]").equals("silver"));
    
    }
    
    @Rule
    public ExpectedException emailDataErrorException = ExpectedException.none();
    
    @Test
    public void testGenerateFlight() throws Exception {
    
        emailDataErrorException.expect(EmailDataErrorException.class);
    
        String flightFileName = "Flight363FromSeattleToBoston.csv";
        EmailDataValidator dataValidator = new EmailDataValidator();
        
        Flight testFlight = new Flight();
        testFlight.setEvent("arrival");
        testFlight.setDestCity("Boston");
        testFlight.setDepCity("Seattle");
        testFlight.setFlightNo("363");
        
        Flight compareFlight = dataProcessor.generateFlight(flightFileName,
            errorGenerator,"arrival", dataValidator);
    
        Assert.assertTrue("the two flights should have the same " +
             "flight number", testFlight.getFlightNo()
            .equals(compareFlight.getFlightNo()));
    
        Assert.assertTrue("the two flights should have the same " +
             "destination city", testFlight.getDestCity()
            .equals(compareFlight.getDestCity()));
    
        Assert.assertTrue("the two flights should have the same " +
            "departure city", testFlight.getDepCity()
            .equals(compareFlight.getDepCity()));
    
        Assert.assertTrue("the two flights should have the same event",
            testFlight.getEvent().equals(compareFlight.getEvent()));
        
        dataProcessor.generateFlight(flightFileName, errorGenerator,
            "crash", dataValidator);
        
        }
    
    @Test
    public void generatePassengerList() throws Exception {
    
        String flightFileName = "Flight3FromVancouverToSeattle";
        File passengerFile = new File("/Users/debweiss/Desktop/Flight3FromVancouverToSeattle.csv");
        List<Passenger> testPassengerList = new ArrayList<>();
        List<Passenger> generateList = new ArrayList<>();
        
        Passenger james = new Passenger("James","Butt",
            "6649 N Blue Gum St", "New Orleans","Orleans",
            "LA","70116", "504-621-8927", "jbutt@gmail.com",
            "gold");
        
        Passenger josephine = new Passenger("Josephine","Darakjy",
            "4 B Blue Ridge Blvd","Brighton","Livingston","MI",
            "48116","810-292-9388","josephine_darakjy@darakjy.org",
            "silver");
    
        Passenger art = new Passenger("Art","Venere",
            "8 W Cerritos Ave #54","Bridgeport","Gloucester",
            "NJ", "80144","856-636-8749","art@venere.org",
            "bronze");
    
        Passenger katherine = new Passenger("Katherine","Belgium CJ",
            "5th Avenue N", "New Orleans","Gloucester",
            "WA","98122","555-555-5555", "deb@deb.com",
            "silver");
        
        testPassengerList.add(james);
        testPassengerList.add(josephine);
        testPassengerList.add(art);
        testPassengerList.add(katherine);
        
        generateList = dataProcessor.generatePassengerList(passengerFile, dataValidator);
        
        Assert.assertTrue("the generated passenger list should be the same as the" +
                "test passenger list", generateList.equals(testPassengerList));
        
    }
    
    @Rule
    public ExpectedException noSuchPlaceholder = ExpectedException.none();
    
    @Test
    public void testNoSuchPlaceholderException() throws Exception {
        
        noSuchPlaceholder.expect(NoSuchPlaceholderException.class);
        
        File unknownPlaceholderFile = new File
            ("/Users/debweiss/Desktop/email-template-unknown-placeholder.txt");
        
        Flight flight = new Flight("363", "Seattle", "Boston");
        
        flight.setEvent("arrival");
        
        Passenger passenger = new Passenger("raul", "julia",
            "14 parker street", "Boston", "King", "MA",
            "98122", "555-555-5555", "deb@deb.com", "silver");
        
        HashMap<String, String> testNoSuchPlaceholder = new HashMap<>();
        
        testNoSuchPlaceholder = dataProcessor.readEmailTemplateData(emailPattern,
                unknownPlaceholderFile, flight, passenger, dataValidator);
        
    }
    
}
