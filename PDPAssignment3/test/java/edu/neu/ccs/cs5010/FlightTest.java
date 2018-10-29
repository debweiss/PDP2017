package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class FlightTest {

    private Flight flight = new Flight("25", "Seattle", "Phoenix");

    private Passenger passenger = new Passenger("Dolores", "Pfefferman", "140 24th Street",
        "Seattle", "King", "WA", "98122", "206-329-2085",
        "debweiss@gmail.com", "gold");

    private Passenger passenger2 = new Passenger("Debra", "Pfefferman", "140 18th Street",
        "Phoenix", "King","AZ", "98122", "206-329-2084",
        "debs@gmail.com", "silver");

    private ArrayList<Passenger> passengerList = new ArrayList();
    private ArrayList<Passenger> passengerList2 = new ArrayList();

    public void setUp() {
        flight.setEvent("arrival");
        passengerList.add(passenger);
        passengerList.add(passenger2);
        passengerList2.add(passenger);
    }

    @Test
    public void testFlightConstructor() throws Exception {

        Flight myNewFlight = new Flight("44", "Springfield", "Boston");
        ArrayList<Passenger> passengerList3 = new ArrayList();

        Assert.assertEquals("should return 44",
            myNewFlight.getFlightNo(), "44"
        );

        Assert.assertEquals("should return Springfield",
            myNewFlight.getDepCity(), "Springfield"
        );

        Assert.assertEquals("should return Boston",
            myNewFlight.getDestCity(), "Boston"
        );

        Assert.assertEquals("should return an empty passenger list",
            myNewFlight.getPassengerList(), passengerList3
        );
    }

    @Test
    public void testGetFlightNo() throws Exception {
        Assert.assertEquals("should return 25",
            flight.getFlightNo(), "25"
        );
    }

    @Test
    public void testSetFlightNo() throws Exception {

        flight.setFlightNo("26");
        Assert.assertEquals("should return 26",
            flight.getFlightNo(), "26"

        );
    }

    @Test
    public void tesGetDepCity() throws Exception {

        Assert.assertEquals("should return 'Seattle'",
            flight.getDepCity(), "Seattle"
        );
    }

    @Test
    public void testSetDepCity() throws Exception {

        flight.setDepCity("Baton Rouge");
        Assert.assertEquals("should return 'Baton Rouge'",
            flight.getDepCity(), "Baton Rouge"
        );
    }

    @Test
    public void testGetDestCity() throws Exception {

        Assert.assertEquals("should return 'Phoenix'",
            flight.getDestCity(), "Phoenix"
        );
    }

    @Test
    public void testSetDestCity() throws Exception {

        flight.setDestCity("New Orleans");
        Assert.assertEquals("should return 'New Orleans'",
            flight.getDestCity(), "New Orleans"
        );
    }

    @Test
    public void testGetEvent() throws Exception {

        flight.setEvent("arrival");

        Assert.assertEquals("should return 'arrival'",
            flight.getEvent(), "arrival"
        );
    }

    @Test
    public void testSetEvent() throws Exception {

        flight.setEvent("departure");

        Assert.assertEquals("should return 'departure'",
            flight.getEvent(), "departure"
        );
    }

    @Test
    public void testGetPassengerList() throws Exception {

        Assert.assertEquals("should return passengerList",
            flight.getPassengerList(), passengerList
        );
    }

    @Test
    public void testSetPassengerList() throws Exception {

        flight.setPassengerList(passengerList2);

        Assert.assertEquals("should return passengerList",
            flight.getPassengerList(), passengerList2
        );
    }

}