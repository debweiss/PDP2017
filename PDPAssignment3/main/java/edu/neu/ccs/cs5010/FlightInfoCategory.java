
  package edu.neu.ccs.cs5010;

  /**
   * <h1>FlightInfoCategory</h1>
   * Provides the categories of flight information
   * used to generate an email
   * <p>
   * @author  Debra Cooperman
   * @version 2.0
   * @since   2017-11-09
   */

  public enum FlightInfoCategory {

      FLIGHT_NO("flightNo"), // number of the referenced flight
      FLIGHT_DEP_CITY("departure-city"), // city where the flight originated
      FLIGHT_DEST_CITY("destination-city"); // city where the flight landed

      private final String category;

      FlightInfoCategory(String category) {

          this.category = category;
      }

      public String getValue() {

          return category;

      }

  }


