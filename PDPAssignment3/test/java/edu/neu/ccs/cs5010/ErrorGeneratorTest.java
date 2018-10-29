package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class ErrorGeneratorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private StringBuilder buildUsageError = new StringBuilder();
    private StringBuilder error = new StringBuilder();
    private ErrorGenerator errorGenerator = new ErrorGenerator();
    private String templateNotExist;
    private String csvNotExist;
    private String outputDirNotExist;
    private String missingTemplate;
    private String missingCSV;
    private String missingOutputDir;
    private String missingEvent;
    private String invalidCSVFormat;
    private String invalidEvent;


    @Before

    public void setUp() {

        templateNotExist = "The template file specified does not exist. Please double-check the path"
             + " and spelling of this file and rerun the program";

        csvNotExist = "The csv file specified does not exist. Please double-check the path and spelling "
            + "of this file and rerun the program.";

        outputDirNotExist = "The output directory specified does not exist. Please double-check the path and "
             + "spelling of this directory and rerun the program";

        missingTemplate = "You have not input a template file. Please input this file in the format "
             + "'--email-template <file>'";

        missingOutputDir = "You have not input an output directory for your template(s) to reside. "
             + "Please input this output directory in the format '--output-dir <path>' and rerun the program.";

        missingCSV = "You have not input a csv file. Please input the name of the csv file in the "
             + "following format: '--csv-file Flight<id>From<departure-city>To<destination-city>.csv' "
             + "and rerun the program";

        missingEvent = "You have not input an event for the flight. Please input an event.";

        invalidCSVFormat = "The csv file name is in the wrong format. Please double-check the spelling "
             + "of this file and rerun the program.";

        invalidEvent = "The event specified is not an event recognized by the program. Please double-check "
            + "the spelling of this file and rerun the program.";


        buildUsageError = new StringBuilder("Usage\n" + "\n")
            .append("--email-template <file>     accepts a filename that holds an email template\n" + "\n")
            .append("--output-dir <path>         accepts the name of a folder all output is placed\n")
            .append("                            in this folder\n" + "\n")
            .append("--csv-file <path>           accepts the name of the csv file to process in the\n")
            .append("                            following format\n")
            .append("                            Flight<id>From<departure-city>To<destination-city>.csv\n" + "\n")
            .append("--event<details>            specifies if the delay refers to departure or arrival");

    }


    @Test
    public void testGenerateErrorMissing() throws Exception {


       Assert.assertTrue("should return missing arg error and print usage for EMAIL_TEMPLATE",
            errorGenerator.generateError("missingLast", UserInputCategory.EMAIL_TEMPLATE)
                .equals(missingTemplate + "\n" + buildUsageError.toString() + "\n"));

       Assert.assertTrue("should return missing arg error for EMAIL_TEMPLATE",
            errorGenerator.generateError("missing", UserInputCategory.EMAIL_TEMPLATE)
                .equals(missingTemplate + "\n"));

        Assert.assertTrue("should return missingCsvFileArgError plus print usage for CSV_FILE",
            errorGenerator.generateError("missingLast", UserInputCategory.CSV_FILE)
                .equals(missingCSV + "\n" + buildUsageError.toString() + "\n")
        );

        Assert.assertTrue("should return missingCsvFileArgError",
            errorGenerator.generateError("missing", UserInputCategory.CSV_FILE)
                .equals(missingCSV + "\n"));

        Assert.assertTrue("should return missingOutPutDirArgError plus print usage for OUTPUT_DIR",
            errorGenerator.generateError("missingLast", UserInputCategory.OUTPUT_DIR)
                .equals(missingOutputDir + "\n" + buildUsageError.toString() + "\n"));

        Assert.assertTrue("should return missingOutputDirArgError",
            errorGenerator.generateError("missing", UserInputCategory.OUTPUT_DIR)
                .equals(missingOutputDir + "\n"));

        Assert.assertTrue("should return missingEventError plus print usage for FLIGHT_EVENT",
            errorGenerator.generateError("missingLast", UserInputCategory.FLIGHT_EVENT)
                .equals(missingEvent + "\n" + buildUsageError.toString() + "\n"));

        Assert.assertTrue("should return missingEventError",
            errorGenerator.generateMissingArgError(UserInputCategory.FLIGHT_EVENT)
                .equals(missingEvent));
    }

    @Test
    public void testGenerateErrorFileNoExist() throws Exception {

        /* EMAIL_TEMPLATE */

        String templateNoExist = "The template file specified does not "
            + "exist. Please double-check the path and spelling of this file and rerun "
            + "the program";

        Assert.assertTrue("should return fileNoExist and print usage for EMAIL_TEMPLATE",
            errorGenerator.generateError("fileNoExistLast", UserInputCategory.EMAIL_TEMPLATE)
                .equals(templateNoExist +  "\n" + buildUsageError.toString() + "\n"));


        Assert.assertTrue("should return fileNoExistError for EMAIL_TEMPLATE",
            errorGenerator.generateError("fileNoExist", UserInputCategory.EMAIL_TEMPLATE)
                .equals(templateNoExist + "\n")
        );

         /* CSV_FILE */

        Assert.assertTrue("should return fileNoExistError plus print usage for CSV_FILE",
            errorGenerator.generateError("fileNoExistLast", UserInputCategory.CSV_FILE)
                .equals(csvNotExist + "\n" + buildUsageError.toString() + "\n")
        );

        Assert.assertTrue("should return fileNoExistError for CSV_FILE",
            errorGenerator.generateError("fileNoExist", UserInputCategory.CSV_FILE)
                .equals(csvNotExist + "\n"));

         /* OUTPUT_DIR */

        Assert.assertTrue("should return fileNoExistError plus print usage for OUTPUT_DIR",
            errorGenerator.generateError("fileNoExistLast", UserInputCategory.OUTPUT_DIR)
                .equals(outputDirNotExist + "\n" + buildUsageError.toString() + "\n")
        );

        Assert.assertTrue("should return fileNoExistError",
            errorGenerator.generateError("fileNoExist", UserInputCategory.OUTPUT_DIR)
                .equals(outputDirNotExist + "\n"));

    }

    @Test
    public void testGenerateWrongError() throws Exception {



        Assert.assertTrue("should return csvInvalidFormat error plus print usage for CSV_FILE",
            errorGenerator.generateError("invalidCsvLast", UserInputCategory.CSV_FILE)
                .equals(invalidCSVFormat + "\n" + buildUsageError.toString() + "\n")
        );

        Assert.assertTrue("should return csvInvalidFormat error",
            errorGenerator.generateError("invalidCsv", UserInputCategory.CSV_FILE)
                .equals(invalidCSVFormat + "\n")
        );

        Assert.assertTrue("should return invalidEvent error plus print usage for FLIGHT_EVENT",
            errorGenerator.generateError("invalidEventLast", UserInputCategory.FLIGHT_EVENT)
                .equals(invalidEvent + "\n" + buildUsageError.toString() + "\n")
        );


        Assert.assertTrue("should return the invalidEventError", errorGenerator
             .generateError("invalidEvent", UserInputCategory.FLIGHT_EVENT)
             .equals(invalidEvent + "\n"));
    }

    @Test
    public void testGenerateMissingArgError() throws Exception {

        Assert.assertTrue("should return missingTemplateArgError",
            errorGenerator.generateMissingArgError(UserInputCategory.EMAIL_TEMPLATE)
                .equals(missingTemplate));

        Assert.assertTrue("should return missingCsvFileArgError",
            errorGenerator.generateMissingArgError(UserInputCategory.CSV_FILE)
                .equals(missingCSV));

        Assert.assertTrue("should return missingOutputDirArgError",
            errorGenerator.generateMissingArgError(UserInputCategory.OUTPUT_DIR)
                .equals(missingOutputDir));

        Assert.assertTrue("should return missingEventError",
            errorGenerator.generateMissingArgError(UserInputCategory.FLIGHT_EVENT)
                .equals(missingEvent));

    }

    @Test
    public void testGenerateFileOrDirDoesNotExistError() throws Exception {
    
        Assert.assertTrue("should return templateFileDoesNotExistError",
            errorGenerator.generateFileOrDirDoesNotExistError(UserInputCategory.EMAIL_TEMPLATE)
                .equals(templateNotExist));
    
        Assert.assertTrue("should return the csvFileDoesNotExistError",
            errorGenerator.generateFileOrDirDoesNotExistError(UserInputCategory.CSV_FILE)
                .equals(csvNotExist));
    
        Assert.assertTrue("should return the outputDirDoesNotExistError",
            errorGenerator.generateFileOrDirDoesNotExistError(UserInputCategory.OUTPUT_DIR)
                .equals(outputDirNotExist));
    
    
    }

    @Test
    public void testGenerateWrongOrInvalidFormatError() throws Exception {

        Assert.assertTrue("should return the csvFileInvalidFormatError",
            errorGenerator.generateWrongOrInvalidFormatError(UserInputCategory.CSV_FILE)
                .equals(invalidCSVFormat));

        Assert.assertTrue("should return the invalidEventError",
            errorGenerator.generateWrongOrInvalidFormatError(UserInputCategory.FLIGHT_EVENT)
                .equals(invalidEvent));

        Assert.assertTrue("should return empty string",
            errorGenerator.generateWrongOrInvalidFormatError(UserInputCategory.OUTPUT_DIR)
                .equals(" "));
    }
    


    @Test
    public void printUsageErrorTest() throws Exception {

        String myString = (errorGenerator.printUsageError());
        Assert.assertTrue("printUsageError() should instructions for user input",
            myString.equals(buildUsageError.toString()));
    }


}