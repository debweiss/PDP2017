  package edu.neu.ccs.cs5010;

  public enum UserInputCategory {

      // each category describes user input

      EMAIL_TEMPLATE("--email-template"), // location of email template
      OUTPUT_DIR("--output-dir"), // desired directory for output emails
      CSV_FILE("--csv-file"), // csv file that represents the flight manifest
      FLIGHT_EVENT("--event"); // indicates the event (dep or arr) prompting the email

      private final String category;

      UserInputCategory(String category) {

          this.category = category;
      }

      public String getValue() {

            return category;
      }

  }


