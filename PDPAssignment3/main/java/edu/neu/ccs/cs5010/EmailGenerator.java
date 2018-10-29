package edu.neu.ccs.cs5010;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Email Generator program</h1></h1>
 * Takes user input and a file representing a flight
 * manifest and generates an email for each passenger
 * on the manifest based on a specified template.
 * <p>
 */
public class EmailGenerator {

    public static Email email;
    private static EmailDataValidator dataValidator = new EmailDataValidator();
    private static EmailDataProcessor dataReader = new EmailDataProcessor();
    
    
    /**
     * Builds an email by reading a template file, substituting new values in the
     * template areas ([[text in brackets]]) and writes the new text out to file which
     * is the new email.
     *
     * @param templateFile    template file that shows placeholder text to substitute passenger and flight
     *                        info values
     * @param emailOutputFile file that contains the new email built from the template
     * @param substitutions   shows mapping between template text to substitute and proper repl values
     */
    static void buildEmail(File templateFile, File emailOutputFile, HashMap<String, String> substitutions) {
        
        String line;
        
        try {
            
            BufferedReader templateReader = new BufferedReader(new FileReader(templateFile));
            BufferedWriter emailWriter = new BufferedWriter(new FileWriter(emailOutputFile));
            
            try {
                
                while ((line = templateReader.readLine()) != null) { // read each line
                    
                    for (Map.Entry<String, String> term : substitutions.entrySet()) {
                        
                        if (line.contains(term.getKey())) {  // if line has placeholder text
                            
                            line = line.replace(term.getKey(), term.getValue()); // replace text with value
                            
                        }
                    }
                    
                    emailWriter.write(line + "\n"); // write the line to the emailOutputFile
                    
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            templateReader.close();
            emailWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* User input & flight information */
    public static void main(String args[]) throws IOException, EmailDataErrorException,
        NoSuchPlaceholderException {

        HashMap<String, String> emailUserInput = new HashMap<>();
        HashMap<String, String> emailSubstitutions = new HashMap<>();
        ErrorGenerator mailErrors = new ErrorGenerator();
        Flight emailFlight;
        ArrayList<Passenger> passengerList;

        String osFileSeparator = File.separator; // for cross-platform file paths

        /* retrieve and process user input */
        emailUserInput = dataReader.readEmailUserInput(args, mailErrors);

        /* read and set flight info */
        File csvFile = new File(emailUserInput.get(UserInputCategory
            .CSV_FILE.getValue()));

        emailFlight = dataReader.generateFlight(csvFile.getName(), mailErrors,
            emailUserInput.get(UserInputCategory.FLIGHT_EVENT.getValue()), dataValidator);

        /* read and set passenger info */
        passengerList = dataReader.generatePassengerList(csvFile, dataValidator);
        emailFlight.setPassengerList(passengerList);

        /* create the output directory for the emails */
    
        File outputDir = new File(emailUserInput.get(UserInputCategory
            .OUTPUT_DIR.getValue()));
        if (! outputDir.exists()){
            boolean mkdir = outputDir.mkdir();
        }
        
        /* take the passenger list and generate an email for each one */
        for (int i = 0; i < emailFlight.getPassengerList().size(); i++) {

            /* get input email template */
            File inputTemplate = new File(osFileSeparator +
                 emailUserInput.get(UserInputCategory.EMAIL_TEMPLATE.getValue()));
            
            /* number to append to email */
            int passengerEmailNumber = i + 1;

            /* set it up to increment each email name */
            String outputEmailName = osFileSeparator + "PassengerEmail" + passengerEmailNumber + ".txt";

            /* get the full output path to write to */
            String fullOutputPath = outputDir + outputEmailName;

            /* retrieve the placeholder text and values for each passenger */
            emailSubstitutions = dataReader.readEmailTemplateData
                (dataValidator.emailPattern, inputTemplate, emailFlight, emailFlight
                    .getPassengerList().get(i), dataValidator);

            /* if there are substitutions to be made */
            if (emailSubstitutions != null) {
                /* set substitutions for that particular email object */
                email = new Email(emailSubstitutions);
            }

            try {
                /* create output email */
                File outputEmail = new File(fullOutputPath);

                /* generate the email from the substitutions set on the email object */
                buildEmail(inputTemplate, outputEmail, emailSubstitutions);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}