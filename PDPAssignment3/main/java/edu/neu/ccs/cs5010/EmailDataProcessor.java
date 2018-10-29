package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.neu.ccs.cs5010.FlightInfoCategory.*;
import static edu.neu.ccs.cs5010.UserInputCategory.CSV_FILE;
import static edu.neu.ccs.cs5010.UserInputCategory.FLIGHT_EVENT;

/**
 * <h1>EmailDataProcessor</h1></h1>
 * Processes user input necessary to generate the email (email template,
 * output dir, csvfile that has flight and passenger information,
 * and event (departure or arrival))
 * <p>
 */

class EmailDataProcessor {

    private static final int NUM_USER_INPUT_ARGS = 8; // number of req'd input arguments;
    private static final String NO_SUCH_PLACEHOLDER_EX_MSG = "There is no replacement " // exception msg
          + "text for this placeholder value, please check your email "
          + "template for misformatted or missing placeholder text";

   /* *****************************USER INPUT FUNCTIONS *********************************
     These functions read, validate, and retrieve user input. */

    /**
     * readEmailIUserInput: reads in the user input (output dir, csv file name,
     * email template, and event) and quits the program with an error if any of the
     * required arguments are missing. Otherwise it forwards the user input to
     * processEmailUserInput, which checks the validity of the input.
     * <p>
     *
     * @param args represents all of the input necessary to run the email generator program
     * @return argMap contains value type and value of all necessary input (output dir,
     * csv file name, email template, event)
     * @throws EmailDataErrorException if there is a problem with the data the user input
     */
    HashMap<String, String> readEmailUserInput(String args[], ErrorGenerator emailErrors)
        throws EmailDataErrorException {

        HashMap<String, String> argMap = new HashMap<>();
        List<UserInputCategory> missingValues = new ArrayList<>();

        if (args.length != NUM_USER_INPUT_ARGS) { // if one or more pieces of req'd input missing

            for (UserInputCategory cat : UserInputCategory.values()) {

                if (!argMap.keySet().contains(cat.getValue())) { // find the one(s) missing

                    missingValues.add(cat);
                }
            }

                for(UserInputCategory missingCat : missingValues) {

                    System.out.print(emailErrors.generateError("missing", missingCat)); // generate error
                }

                System.out.println(emailErrors.printUsageError()); // print instructions
                throw new EmailDataErrorException("quitting program");

            } else { // otherwise if nothing is missing

            for (int i = 0; i < args.length - 1; i++) {

                argMap.put(args[i], args[i + 1]); // value type = key, value = value
                
                i++;

            }

        }

        return argMap; // return the map to the program for further validation
    }

    /**
     * readEmailTemplateData takes the template file, extracts a list of placeholder strings
     * representing passenger information (first name, last name, email, address, zip, city,
     * state, rewards membership status) and flight information (flight number, departure city,
     * destination city, event (departure or arrival)), retrieves the replacement(actual) values
     * from the Passenger and Flight objects, and returns the placeholder and replacement text.
     * <p>
     *
     * @param emailPattern regex Pattern checks against the placeholders in the email
     *                     template to determine which values to replace them with.
     * @param inputFile    includes the flight and passenger information for the email.
     * @param flight       includes flight information to be subtituted for the placeholder
     *                     text
     * @param passenger    provides access to passenger information listed above
     *
     * @return substitutions represents the flight and passenger placeholder and replacement text
     * for the values listed above. Can be empty, but not null.
     * @throws IOException if inputTemplate file was empty or could not be read
     * @throws NoSuchPlaceholderException if the placeholder text does not have a corresponding value
     */

    HashMap<String, String> readEmailTemplateData(Pattern emailPattern, File inputFile,
        Flight flight, Passenger passenger, EmailDataValidator dataValidator) throws IOException,
        NoSuchPlaceholderException {

        BufferedReader inputTemplate; // buffered reader to read the template file
        String line; // line in the template file
        String termWithInsertIndicators; // placeholder text
        String termWoutInsertIndicators; // placeholder text w/out brackets to match repl text

        HashMap<String, String> substitutions = new HashMap<>(); // <placeholder text, repl value>

        String flightInfo = null; // replacement value extracted from Flight object
        String passengerInfo = null; // replacement value extracted from the Passenger object

        try {

            inputTemplate = new BufferedReader(new FileReader(inputFile));

            while ((line = inputTemplate.readLine()) != null) { // read each line of the file

                Matcher mat = emailPattern.matcher(line); // finds placeholder text

                /* For each placeholder found, look for corresponding value in Flight
                object, and if found, add to the substitutions map. If not found, look
                in Passenger object and if not found in passenger, throw exception and
                return empty map */

                while (mat.find()) {

                    termWithInsertIndicators = mat.group(); // placeholder text w/brackets
                    termWoutInsertIndicators = mat.group(1); // placeholder text w/out brackets

                    flightInfo = retrieveFlightInfo(termWoutInsertIndicators, flight);

                    if (flightInfo != null) {
                        
                        substitutions.put(termWithInsertIndicators, flightInfo);

                    }
                    else {

                        passengerInfo = retrievePassengerInfo(termWoutInsertIndicators,
                            passenger, dataValidator);

                        if (passengerInfo != null) {

                            substitutions.put(termWithInsertIndicators, passengerInfo);

                        } else {
                                throw new NoSuchPlaceholderException(NO_SUCH_PLACEHOLDER_EX_MSG);
                            }
                        
                    }

                }

            }

            } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }

        return substitutions;
    }

    /* *****************************FLIGHT PROCESSING FUNCTIONS *********************************
     These functions read, validate, and retrieve flight information. */

    /**
     * processFlightInfo takes fileName and event string (arrival or departure event). If
     * the fileName is not in the right format, it generates the proper error and quits the
     * program. If filename is valid, it extracts the flight number, departure city, and
     * destination city from the filename, and returns a Flight object that contains the
     * appropriate flight information.
     * <p>
     *
     * @param fileName       file name of the csv file that includes this information.
     * @param flightErrorGen ErrorGenerator object--produces errors related to missing
     *                       or misformatted input.
     * @param event          describes whether event occurred during arrival or departure
     * @return Flight object that represents a flight populated with flightno,
     * depCity, destCity, and event information.
     * @throws NullPointerException if event or filename was null
     * @throws EmailDataErrorException if the data from the csv file is invalid
     */

    Flight generateFlight(String fileName, ErrorGenerator flightErrorGen, String event,
        EmailDataValidator dataValidator) throws NullPointerException, EmailDataErrorException {

        Flight newFlight = new Flight(); // flight object to populate
        Integer groupNo; // group no indicates which part of string needed
        List<UserInputCategory> invalidValues = new ArrayList<>();

        if (fileName != null) {

            if(!dataValidator.patternValidator(dataValidator.csvFilePatternString, fileName)) {

                System.out.print(flightErrorGen.generateError("invalidCsvLast", CSV_FILE)); // error & print instructions
                throw new EmailDataErrorException("quitting program");
            }

            /* Extract Flight Number, departure city, destination city, from the
            filename, generating the proper error if invalid. */

            if (dataValidator.patternValidator(dataValidator
                .FLIGHT_NO_FROM_FILE_PATTERN,fileName)) {
                groupNo = 1;
                populateFlightInfo(newFlight, fileName, groupNo,
                    FLIGHT_NO, dataValidator);

            }

            if (dataValidator.patternValidator(dataValidator
                .DEP_CITY_FROM_FILE_PATTERN,fileName)) {
                groupNo = 2;
                populateFlightInfo(newFlight, fileName, groupNo,
                    FLIGHT_DEP_CITY, dataValidator);

            }

            if (dataValidator.patternValidator(dataValidator
                .DEST_CITY_FROM_FILE_PATTERN,fileName)) {
                groupNo = 3;
                populateFlightInfo(newFlight, fileName, groupNo,
                    FLIGHT_DEST_CITY, dataValidator);

            } else {

                System.out.print(flightErrorGen.generateError("invalidCsvLast", CSV_FILE)); // error & print instructions
                throw new EmailDataErrorException("quitting program");
            }
        }

        /* If event text is valid, add to flight object, otherwise throw error and quit */

        if (event != null) {

            if (dataValidator.patternValidator(dataValidator
                .EVENT_PATTERN, event)){
                groupNo = 0;
                newFlight.setEvent((dataValidator.matchGroup(dataValidator
                .EVENT_PATTERN, event, groupNo)));

            } else {

                flightErrorGen.generateError("invalidEventLast", FLIGHT_EVENT); // error & print instructions
                throw new EmailDataErrorException("quitting program");
            }
        }

        return newFlight;
    }


    /**
     * populateFlightInfo sets the information for the flight once that information
     * is validated.
     * <p>
     *
     * @param flight   Flight object where the values are set.
     * @param fileName represents the csvfilename that contains the header values
     * @param groupNo  indicates which part of the filename should be extracted to
     *                 set the flight value.
     * @param category The enum value that represents the header value to match against.
     */

    private void populateFlightInfo(Flight flight, String fileName, Integer groupNo,
        FlightInfoCategory category, EmailDataValidator dataValidator) {

        /* For a given category, extract the value from the filename and set that
        value in the flight object. */

        switch (category) {

            case FLIGHT_NO: // extract and set flight number

                flight.setFlightNo(dataValidator.matchGroup(dataValidator
                    .FLIGHT_NO_FROM_FILE_PATTERN, fileName, groupNo));

                break;

            case FLIGHT_DEP_CITY: // extract and set departure city

                flight.setDepCity(dataValidator.matchGroup(dataValidator
                    .DEP_CITY_FROM_FILE_PATTERN, fileName, groupNo));

                break;

            case FLIGHT_DEST_CITY: // extract and set destination city

                flight.setDestCity(dataValidator.matchGroup(dataValidator.
                    DEST_CITY_FROM_FILE_PATTERN, fileName, groupNo));

                break;

            default:

                break;
        }
    }

    /**
     * retrieveFlightInfo helper function for readEmailTemplateData. Retrieves a value from a
     * Flight object based on the placeholder text received (flight number, departure city,
     * destination city, or event(whether departure or arrival)).
     * <p>
     *
     * @param termWoutInsertIndicators term w/out brackets to match against flight information
     * @param flight                   provides the flight values to map to the placeholders.
     * @return flightInfo represents the value retrieved from the Flight object.
     */

    private String retrieveFlightInfo(String termWoutInsertIndicators, Flight flight) {

        String flightInfo = null;

        /* For a given type of flight info value, retrieve that value from the Flight object */

        if (termWoutInsertIndicators.equals(FLIGHT_NO.getValue())) {

            flightInfo = flight.getFlightNo();

        } else if (termWoutInsertIndicators.equals(FLIGHT_DEP_CITY.getValue())) {

            flightInfo = flight.getDepCity();

        } else if (termWoutInsertIndicators.equals(FLIGHT_DEST_CITY.getValue())) {

            flightInfo = flight.getDestCity();

        } else if (termWoutInsertIndicators.equals(FLIGHT_EVENT.getValue().substring(2))) {

            flightInfo = flight.getEvent();
        }
        
        return flightInfo;
    }

    /* ******************************PASSENGER PRCOCESSING FUNCTIONS *********************************
     * These functions read, validate, and retrieve passenger information. */

    /**
     * retrievePassengerInfo helper function for readEmailTemplateData. Retrieves a value
     * from a Passenger object based on the placeholder text received.
     * <p>
     *
     * @param termWoutInsertIndicators String to evaluate to see what value is being asked for.
     * @param passenger                Passenger object that is used to retrieve the passenger
     *                                 values to map to the placeholders.
     * @return passengerInfo String that represents the value retrieved from the
     * passenger object.
     */
    private String retrievePassengerInfo(String termWoutInsertIndicators,
        Passenger passenger, EmailDataValidator dataValidator) {

        String passengerInfo = null;

        /* For a given type of passenger info value, retrieve that value from the
        Passenger object */

        if (dataValidator.patternValidator(dataValidator
            .FIRST_NAME_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getFirstName();

        } else if (dataValidator.patternValidator(dataValidator
            .LAST_NAME_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getLastName();

        } else if (dataValidator.patternValidator(dataValidator
            .ADDRESS_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getAddress();

        } else if (dataValidator.patternValidator(dataValidator
            .EMAIL_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getEmail();

        } else if (dataValidator.patternValidator(dataValidator
            .PHONE_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getPhone();

        } else if (dataValidator.patternValidator(dataValidator
            .CITY_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getCity();

        } else if (dataValidator.patternValidator(dataValidator
            .COUNTY_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getCounty();

        } else if (dataValidator.patternValidator(dataValidator
            .STATE_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getState();

        } else if (dataValidator.patternValidator(dataValidator
            .ZIP_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getZip();

        } else if (dataValidator.patternValidator(dataValidator
            .REWARDS_HEADER_PATTERN, termWoutInsertIndicators)) {
            passengerInfo = passenger.getRewards();

        } else if (dataValidator.patternValidator(dataValidator
            .DATE_HEADER_PATTERN, termWoutInsertIndicators)) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy");
            LocalDate localDate = LocalDate.now();
            passengerInfo = dtf.format(localDate); //2016/11/16
        }

        return passengerInfo;
    }

    /**
     * generatePassengerList takes a file with passenger info value types and values
     * (first name, last name, address, city, county, state, zip code, phone number, email,
     * and rewards membership category), goes through each line of the file, populates
     * a new Passenger object with that passenger info, and returns a list of all the
     * passengers described in the file.
     * <p>
     *
     * @param passengerFile File representing the passenger info column headers
     * @param dataValidator DataValidator object to validate the data
     * @return ArrayList<Passenger></Passenger>
     */

    ArrayList<Passenger> generatePassengerList(File passengerFile, EmailDataValidator dataValidator) {

        ArrayList<Passenger> passengerList = new ArrayList<>();

        try {

            BufferedReader inputFile = new BufferedReader(new FileReader(passengerFile));
            String headerLine; // pass. info value types (e.g., "first name")
            String line; // passenger info values (e.g., "Fred")
            headerLine = inputFile.readLine(); // get value types from first line
            String[] passengerHeaders = headerLine.split(",");

            while ((line = inputFile.readLine()) != null) {

                    Matcher m = dataValidator.createMatcher(dataValidator
                        .VALID_COMMA_IN_PASSENGER_ENTRY_PATTERN, line);
                    
                    if(m.find()) {
    
                        line = line.replaceAll(dataValidator.VALID_COMMA_IN_PASSENGER_ENTRY_PATTERN, "");
                    }
                
                String[] passengerInfo = line.split(","); // get pass. info (values)
                
                Passenger newPassenger; // create new passenger
                newPassenger = processPassengerInfo(passengerHeaders, // populate with data
                    passengerInfo, dataValidator);
                passengerList.add(newPassenger); // add passenger to passenger list
            }

            inputFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return passengerList;
    }

    /**
     * processPassengerInfo helper function for generatePassengerList. Takes a String
     * representing a passenger info column header (e.g., first name, last name, address,
     * city, county, state, zip code, phone number, email, and rewards membership category),
     * another string that is the corresponding information, and a Passenger object and sets
     * the info for that Passenger.
     * <p>
     *
     * @param passengerHeaders String[] representing passenger info column headers
     * @param passengerInfo    String[] representing the corresponding info
     * @param dataValidator    DataValidator object to validate the data
     * @throws NullPointerException      if passengerHeaders[] or passengerInfo[] == null
     * @throws IndexOutOfBoundsException if passengerHeaders[] length != passengerInfo[] length
     * @return newPassenger information about the passenger (see above)
     */
    private static Passenger processPassengerInfo(String passengerHeaders[],
        String[] passengerInfo, EmailDataValidator dataValidator)
        throws NullPointerException, IndexOutOfBoundsException {

        Passenger newPassenger = new Passenger();

        for (int i = 0; i < passengerHeaders.length; i++) {

            /* Remove the quotes around the headers and values (e.g.,
            "last_name" -> last_name) */

            String QUOTE_PATTERN2 = "([\\\"])([^\\\"]+)([\\\"])";

            Pattern p = Pattern.compile(QUOTE_PATTERN2);
            
            Matcher m = p.matcher(passengerHeaders[i]);
            Matcher m2 = p.matcher(passengerInfo[i]);

            if(m.matches()) {

                passengerHeaders[i] = m.group(2);

            }

            if(m2.matches()) {

                passengerInfo[i] = m2.group(2);

            }

            /* For a given type of passenger info value, set the matching
            value in the passenger object */

            if (dataValidator.patternValidator(dataValidator
                .FIRST_NAME_HEADER_PATTERN, passengerHeaders[i])) {
                newPassenger.setFirstName(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .LAST_NAME_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setLastName(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .ADDRESS_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setAddress(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .COUNTY_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setCounty(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .STATE_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setState(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .ZIP_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setZip(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .EMAIL_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setEmail(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .PHONE_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setPhone(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .CITY_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setCity(passengerInfo[i]);

            } else if (dataValidator.patternValidator(dataValidator
                .REWARDS_HEADER_PATTERN,passengerHeaders[i])) {
                newPassenger.setRewards(passengerInfo[i]);

            } else {
                System.out.println("nothing matches");
            }
        }

        return newPassenger;
    }
}