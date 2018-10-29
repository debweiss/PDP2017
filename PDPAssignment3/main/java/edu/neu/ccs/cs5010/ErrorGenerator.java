
  package edu.neu.ccs.cs5010;

  /**
   * <h1>Error Generator</h1>
   * Creates errors specific to missing or invalid input from
   * the user.
   * <p>
   */

  class ErrorGenerator {

      private String missingTemplateArgError;
      private String missingOutputDirArgError;
      private String missingCsvFileArgError;
      private String missingEventError;
      private String templateFileDoesNotExistError;
      private String outputDirDoesNotExistError;
      private String csvFileDoesNotExistError;
      private String csvFileInvalidFormatError;
      private String invalidEventError;

      /**
       * Constructor -- Sets the text of the error messages to show to the user
       */
      ErrorGenerator() {

          this.missingTemplateArgError = "You have not input a template file. "
                + "Please input this file in the format '--email-template <file>'";

          this.missingOutputDirArgError = "You have not input an output directory for your "
              + "template(s) to reside. Please input this output directory in the format "
              + "'--output-dir <path>' and rerun the program.";

          this.missingCsvFileArgError = "You have not input a csv file. Please input the name"
              + " of the csv file in the following format: "
              + "'--csv-file Flight<id>From<departure-city>To<destination-city>.csv' "
              + "and rerun the program";

          this.missingEventError = "You have not input an event for the flight. "
              + "Please input an event.";

          this.templateFileDoesNotExistError = "The template file specified does not "
                + "exist. Please double-check the path and spelling of this file and rerun "
                + "the program";

          this.outputDirDoesNotExistError = "The output directory specified does not exist. "
              + "Please double-check the path and spelling of this directory and rerun "
              + "the program";

          this.csvFileDoesNotExistError = "The csv file specified does not exist. "
              + "Please double-check the path and spelling of this file and rerun the program.";

          this.csvFileInvalidFormatError = "The csv file name is in the wrong format. "
              + "Please double-check the spelling of this file and rerun the program.";

          this.invalidEventError = "The event specified is not an event recognized by "
              + "the program. Please double-check the spelling of this file and rerun the program.";
      }

      /**
       * generateError: prints an error message when the user fails
       * to provide the necessary input, either from command line or input file.
       * If the error is the last error in the series, also prints out instructions
       * for proper input and quits the program.
       * <p>
       * @param errorType String that represents whether the error is that the element is
       * missing from the user input, is wrong/invalid input, or file does not exist, and
       * whether the error is the last in the series or not.
       * @param userInput UserInputCategory Enum that indicates the specific input that has
       * an error (email template, output directory, csvfile, and event)
       */

      String generateError(String errorType, UserInputCategory userInput) {

          StringBuilder errorBuilder = new StringBuilder();

          if (errorType.equals("missingLast")) {

              errorBuilder.append(generateMissingArgError(userInput))
              .append("\n")
              .append(printUsageError())
              .append("\n");
              return errorBuilder.toString();

          } else if(errorType.equals("missing")){

              errorBuilder.append(generateMissingArgError(userInput))
              .append("\n");
              return errorBuilder.toString();
          }

          if (errorType.equals("fileNoExistLast")) {

              errorBuilder.append(generateFileOrDirDoesNotExistError(userInput))
              .append("\n")
              .append(printUsageError())
              .append("\n");
              return errorBuilder.toString();

          } else if (errorType.equals("fileNoExist")) {
              errorBuilder.append(generateFileOrDirDoesNotExistError(userInput))
              .append("\n");
              return errorBuilder.toString();
          }

          if (errorType.equals("invalidCsvLast")) {

              errorBuilder.append(generateWrongOrInvalidFormatError(userInput))
              .append("\n")
              .append(printUsageError())
              .append("\n");
              return errorBuilder.toString();

          } else if (errorType.equals("invalidCsv")) {

              errorBuilder.append(generateWrongOrInvalidFormatError(userInput))
              .append("\n");
              return errorBuilder.toString();
          }

          if (errorType.equals("invalidEventLast")) {
              errorBuilder.append(generateWrongOrInvalidFormatError(userInput))
              .append("\n")
              .append(printUsageError())
              .append("\n");
              return errorBuilder.toString();

          } else if (errorType.equals("invalidEvent")) {
              errorBuilder.append(generateWrongOrInvalidFormatError(userInput))
              .append("\n");
              return errorBuilder.toString();
          }

          return errorBuilder.toString();
      }

      /**
       * generateMissingArgError: prints an error message when the user fails
       * to provide the necessary input (email template, output directory, csvfile,
       * and/or flight event).
       * <p>
       * @param userInput Enum element that represents information that is missing.
       */
      String generateMissingArgError(UserInputCategory userInput) {

          String missingError = null;

            switch (userInput) {

                case EMAIL_TEMPLATE:
                    missingError = this.missingTemplateArgError;
                    break;
                case OUTPUT_DIR:
                    missingError = this.missingOutputDirArgError;
                    break;
                case CSV_FILE:
                    missingError = this.missingCsvFileArgError;
                    break;
                case FLIGHT_EVENT:
                    missingError = this.missingEventError;
                    break;
                default:
                    break;
            }

            return missingError;

      }

      /**
       * generateFileOrDirDoesNotExistError: prints an error message if the
       * file or dirname given does not point to an existing file or directory.
       * <p>
       * @param fileNotExistItem Enum element that represents the type of file or
       * directory that is missing (email template, output directory, and/or csv file).
       */
       String generateFileOrDirDoesNotExistError(UserInputCategory fileNotExistItem) {

           String fileNotExistError = null;

           switch (fileNotExistItem) {
                case EMAIL_TEMPLATE:
                    fileNotExistError = this.templateFileDoesNotExistError;
                    break;
                case OUTPUT_DIR:
                    fileNotExistError = this.outputDirDoesNotExistError;
                    break;
                case CSV_FILE:
                    fileNotExistError = this.csvFileDoesNotExistError;
                    break;
                default:
                    break;
           }

           return fileNotExistError;
       }


      /**
       * generateWrongOrInvalidFormatError: prints an error message when the user fails
       * to provide the csv file in a format that allows for extraction of information
       * for the email (flight number, departure city, destination city).
       * <p>
       * @param userInput Enum element that represents the invalid information.
       */
      String generateWrongOrInvalidFormatError(UserInputCategory userInput) {

          String invalidError = " ";

          if(userInput.equals(UserInputCategory.CSV_FILE)) {

              invalidError = this.csvFileInvalidFormatError;
          }

          else if(userInput.equals(UserInputCategory.FLIGHT_EVENT)) {

              invalidError = this.invalidEventError;

          }

          return invalidError;
      }

      /**
       * printUsageError: Prints instructions for the user as to how to properly input
       * data into the program.
       */
      String printUsageError() {

          StringBuilder buildUsageError = new StringBuilder("Usage\n" + "\n")
          .append("--email-template <file>     accepts a filename that holds an email template\n" + "\n")
          .append("--output-dir <path>         accepts the name of a folder all output is placed\n")
          .append("                            in this folder\n" + "\n")
          .append("--csv-file <path>           accepts the name of the csv file to process in the\n")
          .append("                            following format\n")
          .append("                            Flight<id>From<departure-city>To<destination-city>.csv\n" + "\n")
          .append("--event<details>            specifies if the delay refers to departure or arrival");

          return buildUsageError.toString();
      }
    }
