package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;

public class EmailDataValidatorTest {
    
    
    private EmailDataValidator dataValidator = new EmailDataValidator();
    
    
    @Test
    public void testPatternValidator() throws Exception {
        
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.emailTemplatePattern, "[[city]]"));
        
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.emailTemplatePattern,
                "penguin"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.csvFilePatternString,
                "Flight25FromSeattleToBoston.csv"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.csvFilePatternString,
                "Flight50SeattleBoston.csv"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.FLIGHT_NO_FROM_FILE_PATTERN,
                "Flight25FromSeattleToBoston.csv"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.FLIGHT_NO_FROM_FILE_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.DEP_CITY_FROM_FILE_PATTERN,
                "Flight25FromSeattleToBoston.csv"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.DEP_CITY_FROM_FILE_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.DEST_CITY_FROM_FILE_PATTERN,
                "Flight25FromSeattleToBoston.csv"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.DEST_CITY_FROM_FILE_PATTERN,
                "penguin"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.EVENT_PATTERN,
                "departure"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.EVENT_PATTERN,
                "arrival"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.EVENT_PATTERN,
                "Flight50SeattleBoston.csv"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.DATE_HEADER_PATTERN,
                "DATE"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.DATE_HEADER_PATTERN,
                "Date"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.DATE_HEADER_PATTERN,
                "date"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.DATE_HEADER_PATTERN,
                "seattle"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.FIRST_NAME_HEADER_PATTERN,
                "FIRST NAME"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.FIRST_NAME_HEADER_PATTERN,
                "first_name"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.FIRST_NAME_HEADER_PATTERN,
                "firstName"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.FIRST_NAME_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.LAST_NAME_HEADER_PATTERN,
                "LAST NAME"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.LAST_NAME_HEADER_PATTERN,
                "last_name"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.LAST_NAME_HEADER_PATTERN,
                "lastName"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.LAST_NAME_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.ADDRESS_HEADER_PATTERN,
                "ADDRESS"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.ADDRESS_HEADER_PATTERN,
                "address"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.ADDRESS_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.EMAIL_HEADER_PATTERN,
                "E-MAIL"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.EMAIL_HEADER_PATTERN,
                "email"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.EMAIL_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.PHONE_HEADER_PATTERN,
                "PHONE"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.PHONE_HEADER_PATTERN,
                "telephone"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.PHONE_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.CITY_HEADER_PATTERN,
                "CITY"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.CITY_HEADER_PATTERN,
                "city"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.CITY_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.STATE_HEADER_PATTERN,
                "STATE"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.STATE_HEADER_PATTERN,
                "state")
        );
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.STATE_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.ZIP_HEADER_PATTERN,
                "ZIP"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.ZIP_HEADER_PATTERN,
                "zipCode"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.ZIP_HEADER_PATTERN,
                "zip code"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.ZIP_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.COUNTY_HEADER_PATTERN,
                "COUNTY"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.COUNTY_HEADER_PATTERN,
                "county"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.COUNTY_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.REWARDS_HEADER_PATTERN,
                "REWARDS"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.REWARDS_HEADER_PATTERN,
                "rewards"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.REWARDS_HEADER_PATTERN,
                "SeattleBoston"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.EMAIL_PATTERN,
                "debweiss@gmail.com"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.EMAIL_PATTERN,
                "cooperman.d@husky.neu.edu"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.EMAIL_PATTERN,
                "SeattleBoston@"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.PHONE_PATTERN,
                "206-456-6757"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.PHONE_PATTERN,
                "2064566757"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.PHONE_PATTERN,
                "206 456 6757"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.PHONE_PATTERN,
                "206-3333-45678"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.PHONE_PATTERN,
                "2222"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.ZIP_PATTERN,
                "98122"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.ZIP_PATTERN,
                "44444444"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.REWARDS_PATTERN,
                "BRONZE"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.REWARDS_PATTERN,
                "silver"));
        
        Assert.assertTrue("should return true", dataValidator
            .patternValidator(dataValidator.REWARDS_PATTERN,
                "gold"));
        
        Assert.assertFalse("should return false", dataValidator
            .patternValidator(dataValidator.REWARDS_PATTERN,
                "44444444"));
    }
    
    @Test
    public void testMatchGroup() throws Exception {
        
        // test flight number from file
        Assert.assertEquals("should return '20'", dataValidator
            .matchGroup(dataValidator.FLIGHT_NO_FROM_FILE_PATTERN,
                "Flight20FromBostonToSeattle",
                1), "20");
        
        Assert.assertEquals("should return ''", dataValidator
            .matchGroup(dataValidator.FLIGHT_NO_FROM_FILE_PATTERN,
                "FlightToSeattle", 1), "");
        
        // test departure city from file
        Assert.assertEquals("should return 'Boston'", dataValidator
            .matchGroup(dataValidator.DEP_CITY_PATTERN,
                "Flight20FromBostonToSeattle.csv",
                1), "Boston");
        
        Assert.assertEquals("should return ''", dataValidator
            .matchGroup(dataValidator.DEP_CITY_PATTERN,
                "FlightToSeattle", 2), "");
        
        // test destination city from file
        Assert.assertEquals("should return 'Seattle'", dataValidator
            .matchGroup(dataValidator.DEST_CITY_PATTERN,
                "Flight20FromBostonToSeattle.csv", 1), "Seattle");
        
        Assert.assertEquals("should return ''", dataValidator
            .matchGroup(dataValidator.DEST_CITY_FROM_FILE_PATTERN,
                "FlightToSeattle", 2), "");
        
        // test event pattern
        Assert.assertEquals("should return 'departure'", dataValidator
            .matchGroup(dataValidator.EVENT_PATTERN,
                "departure", 0), "departure");
        
        Assert.assertEquals("should return 'arrival'", dataValidator
            .matchGroup(dataValidator.EVENT_PATTERN,
                "ARRIVAL", 0), "ARRIVAL");
        
        Assert.assertEquals("should return ''", dataValidator
            .matchGroup(dataValidator.EVENT_PATTERN,
                "FlightToSeattle", 2), "");
        
        Assert.assertEquals("should return 'gold'", dataValidator
            .matchGroup(dataValidator.REWARDS_PATTERN,
                "gold", 0), "gold");
        
        
        Assert.assertTrue("should return ''", dataValidator
            .matchGroup(dataValidator.REWARDS_PATTERN, "44444444",
                0).equals(""));
    }
    
    @Test
    public void testCreateMatcher() throws Exception {
    
    // test that create matcher returns a Matcher object
        Assert.assertTrue("should return a Matcher object",
            dataValidator.createMatcher(dataValidator.REWARDS_PATTERN,
                "hi i have silver rewards") instanceof Matcher);
    }
}