package edu.neu.ccs.cs5010;

import java.util.ArrayList;

/**
 * Represents a Flight and provides access to flight number, departure city,
 * destination city, event (departure or arrival) and passenger list.
 */
class Flight {
    
    private String flightNo;
    private String depCity;
    private String destCity;
    private String event;
    private ArrayList<Passenger> passengerList = new ArrayList<>();
    
    Flight() {
    
    }
    
    /** Constructor -- creates a new flight with the flight number, departure city,
     * destination city, and empty passenger list.
     * @param flightNo number of the flight
     * @param depCity departure city of the flight
     * @param destCity destination city of the flight
     */
    Flight(String flightNo, String depCity, String destCity) {
        this.flightNo = flightNo;
        this.depCity = depCity;
        this.destCity = destCity;
        this.passengerList = new ArrayList<Passenger>();
    }
    
    /**************Getters and Setters***********************/
    
    String getFlightNo() {
        
        return flightNo;
    }
    
    void setFlightNo(String flightNo) {
        
        this.flightNo = flightNo;
    }
    
    String getDepCity() {
        
        return depCity;
    }
    
    void setDepCity(String depCity) {
        
        this.depCity = depCity;
    }
    
    String getDestCity() {
        
        return destCity;
    }
    
    void setDestCity(String destCity) {
        
        this.destCity = destCity;
    }
    
    String getEvent() {
        
        return event;
    }
    
    void setEvent(String event) {
        
        this.event = event;
    }
    
    ArrayList<Passenger> getPassengerList() {
        
        return passengerList;
    }
    
    void setPassengerList(ArrayList<Passenger> passengerList) {
        
        this.passengerList = passengerList;
    }
}
