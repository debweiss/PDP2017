package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Test;


public class PassengerTest {
    
    private Passenger passenger = new Passenger("Dolores", "Pfefferman",
        "140 24th Street", "Seattle", "King", "WA",
        "98122", "206-329-2085", "debweiss@gmail.com", "gold");
    
    @Test
    public void testConstructor() throws Exception {
        
        Passenger newPassenger = new Passenger("Dolores", "Pfefferman",
            "140 24th Street", "Seattle", "King", "WA",
            "98122", "206-329-2085", "debweiss@gmail.com", "gold");
        
        Assert.assertEquals("should return 'Dolores'",
            newPassenger.getFirstName(), "Dolores");
        
        Assert.assertEquals("should return 'Pfefferman'",
            newPassenger.getLastName(), "Pfefferman");
        
        Assert.assertEquals("should return '140 24th Street'",
            passenger.getAddress(), "140 24th Street");
        
        Assert.assertEquals("should return 'Seattle'",
            passenger.getCity(), "Seattle");
        
        Assert.assertEquals("should return 'King'",
            passenger.getCounty(), "King");
        
        Assert.assertEquals("should return 'WA'",
            passenger.getState(), "WA");
        
        Assert.assertEquals("should return '98122'",
            passenger.getZip(), "98122");
        
        Assert.assertEquals("should return '206-329-2085'",
            passenger.getPhone(), "206-329-2085");
        
        Assert.assertEquals("should return '206-329-2085'",
            passenger.getPhone(), "206-329-2085");
        
        Assert.assertEquals("should return 'debweiss@gmail.com'",
            passenger.getEmail(), "debweiss@gmail.com");
        
        Assert.assertEquals("should return 'gold'",
            passenger.getRewards(), "gold");
    }
    
    @Test
    public void testGetFirstName() throws Exception {
        
        Assert.assertEquals("should return 'Dolores'",
            passenger.getFirstName(), "Dolores");
    }
    
    @Test
    public void testSetFirstName() throws Exception {
        
        passenger.setFirstName("Debra");
        Assert.assertEquals("should return 'Debra'",
            passenger.getFirstName(), "Debra");
    }
    
    @Test
    public void testGetLastName() throws Exception {
        
        Assert.assertEquals("should return 'Pfefferman'",
            passenger.getLastName(), "Pfefferman");
    }
    
    @Test
    public void testSetLastName() throws Exception {
        
        passenger.setLastName("Weissman");
        Assert.assertEquals("should return 'Weissman'",
            passenger.getLastName(), "Weissman");
    }
    
    @Test
    public void testGetAddress() throws Exception {
        
        Assert.assertEquals("should return '140 24th Street'",
            passenger.getAddress(), "140 24th Street");
    }
    
    @Test
    public void testSetAddress() throws Exception {
        
        passenger.setAddress("18 180th Street");
        Assert.assertEquals("should return '18 180th Street'",
            passenger.getAddress(), "18 180th Street");
    }
    
    @Test
    public void testGetCity() throws Exception {
        
        Assert.assertEquals("should return 'Seattle'",
            passenger.getCity(), "Seattle");
    }
    
    @Test
    public void testSetCity() throws Exception {
        
        passenger.setCity("Cheyenne");
        Assert.assertEquals("should return 'Cheyenne'",
            passenger.getCity(), "Cheyenne");
    }
    
    @Test
    public void testGetCounty() throws Exception {
        
        Assert.assertEquals("should return 'King'",
            passenger.getCounty(), "King");
    }
    
    @Test
    public void testSetCounty() throws Exception {
        
        passenger.setCounty("Queens");
        Assert.assertEquals("should return 'Queens'",
            passenger.getCounty(), "Queens");
    }
    
    @Test
    public void testGetState() {
        
        Assert.assertEquals("should return 'WA'",
            passenger.getState(), "WA");
        
    }
    
    @Test
    public void testSetState() {
        
        passenger.setState("CA");
        
        Assert.assertEquals("should return 'CA'",
            passenger.getState(), "CA");
    }
    
    @Test
    public void testGetZip() {
        
        Assert.assertEquals("should return '98122'",
            passenger.getZip(), "98122");
        
    }
    
    @Test
    public void testSetZip() {
        
        passenger.setZip("98115");
        
        Assert.assertEquals("should return '98115'",
            passenger.getZip(), "98115");
        
    }
    
    @Test
    public void testGetPhone() {
        
        
        Assert.assertEquals("should return '206-329-2085'",
            passenger.getPhone(), "206-329-2085");
    }
    
    @Test
    public void testSetPhone() {
        
        passenger.setPhone("206-444-2085");
        
        Assert.assertEquals("should return '206-444-2085'",
            passenger.getPhone(), "206-444-2085");
        
    }
    
    @Test
    public void testGetEmail() {
        
        Assert.assertEquals("should return 'debweiss@gmail.com'",
            passenger.getEmail(), "debweiss@gmail.com");
        
    }
    
    @Test
    public void testSetEmail() {
        
        passenger.setEmail("deb@gmail.com");
        Assert.assertEquals("should return 'deb@gmail.com'",
            passenger.getEmail(), "deb@gmail.com");
        
    }
    
    @Test
    public void testGetRewards() {
        
        Assert.assertEquals("should return 'gold'",
            passenger.getRewards(), "gold");
        
    }
    
    @Test
    public void testSetRewards() {
        
        passenger.setRewards("silver");
        Assert.assertEquals("should return 'silver'",
            passenger.getRewards(), "silver");
        
    }
    
}