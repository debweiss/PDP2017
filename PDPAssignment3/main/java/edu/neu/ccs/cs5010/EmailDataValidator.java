package edu.neu.ccs.cs5010;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Data Validator</h1></h1>
 * Provides access to regex patterns related to passenger
 * information, flight information, and email template text
 * placeholders, contains functions that take input and
 * validates it against patterns for the purposes of generating the email. .
 * <p>
 */

class EmailDataValidator {

    /*  Regexes for identifying placeholder text in email template and
     csv file validation */
    final String emailTemplatePattern = "(?i)\\[\\[(.*?)\\]\\]"; // placeholder text in brackets
    final Pattern emailPattern = Pattern.compile(emailTemplatePattern);
    final String csvFilePatternString = "(?i)^Flight(.*?)From(.*?)To(.*?)\\.csv"; // flight no, dep city, dest city
    final String FLIGHT_NO_FROM_FILE_PATTERN = "(?i)Flight(.*?)From.*?";

    /* Regexes for determining flight information */
    final String DEP_CITY_FROM_FILE_PATTERN = "(?i)Flight(.*?)From(.*?)To.*?\\.csv";
    final String DEP_CITY_PATTERN = Pattern.quote("From") + "(.*?)" + Pattern.quote("To");
    final String DEST_CITY_FROM_FILE_PATTERN = "(?i)Flight(.*?)From(.*?)To(.*?)\\.csv";
    final String DEST_CITY_PATTERN = Pattern.quote("To") + "(.*?)" + Pattern.quote(".csv");
    final String EVENT_PATTERN = "(?i)departure|arrival";
    final String DATE_HEADER_PATTERN = "(?i)date";
    final String FIRST_NAME_HEADER_PATTERN = "(?i)first_name|firstname|first\\s(name)";

    /* Regexes for determining passenger information */
    
    final String VALID_COMMA_IN_PASSENGER_ENTRY_PATTERN = ",(?!\\\"|[0-9])"; // comma not followed by quote or number
    final String LAST_NAME_HEADER_PATTERN = "(?i)last_name|lasttname|lastName|last\\s(name)";
    final String ADDRESS_HEADER_PATTERN = "(?i)address";
    final String EMAIL_HEADER_PATTERN = "(?i)e-mail|email";
    final String PHONE_HEADER_PATTERN = "(?i)phone|telephone";
    final String CITY_HEADER_PATTERN = "(?i)city";
    final String STATE_HEADER_PATTERN = "(?i)state";
    final String ZIP_HEADER_PATTERN = "(?i)zip|zipcode|zip\\s(code)";
    final String COUNTY_HEADER_PATTERN = "(?i)county";
    final String REWARDS_HEADER_PATTERN = "(?i)rewards";
    final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    final String PHONE_PATTERN = "^\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$";
    final String ZIP_PATTERN = "^[0-9]{5}(?:-[0-9]{4})?$";
    final String REWARDS_PATTERN = "(?i)bronze|silver|gold";

    /**
     * patternValidator takes a regex pattern and a String, and determines whether
     * the string matches the pattern. This is used to validate the email, zip code, and
     * other passenger-related information listed above.
     * <p>
     *
     * @param regex          represents the regex pattern to be compared against.
     * @param stringToSearch represents the information to validate.
     * @return true if the pattern is matched, false if not
     */
    boolean patternValidator(String regex, String stringToSearch) {

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(stringToSearch);

        return m.matches();
    }

    /**
     * createMatcher takes the regex pattern and the String, and returns
     * a Matcher object that can then be used to search and replace
     * in the String
     * <p>
     *
     * @param regex          represents the regex pattern to be compared against.
     * @param stringToSearch represents the information to validate.
     * @return Matcher to be used to search and replace in the string
     */
    Matcher createMatcher(String regex, String stringToSearch) {

        Pattern p = Pattern.compile(regex);

        return p.matcher(stringToSearch);
    }

    /**
     * matchGroup takes a regex pattern, a String, and an Integer representing the group
     * number (i.e., what section of the string the desired char or phrase is in. Returns
     * substring indicated by the group number if the group matches, empty String if it
     * doesn't match.
     * <p>
     *
     * @param regex          represents the regex pattern to be compared against.
     * @param stringToSearch represents the information to validate.
     * @param groupNum       indicates which substring to return.
     * @return substring if group matches, empty string if not.
     */
    String matchGroup(String regex, String stringToSearch, Integer groupNum) {

        String matchGroup;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(stringToSearch);

        if (m.find()) {
            matchGroup = m.group(groupNum);

        } else {

            matchGroup = "";

        }

        return matchGroup;
    }

}

