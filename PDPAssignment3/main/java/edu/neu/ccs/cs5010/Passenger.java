package edu.neu.ccs.cs5010;

/**
 * Passenger -- represents the passenger on the flight and provides
 * access to passenger first name, last name, address, city, county,
 * state, zip, phone, email, and rewards status (gold, bronze, silver)
 */
public class Passenger {
    
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String county;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private String rewards;
    
    Passenger() {
    
    }
    
    /** Constructor -- creates a Passenger object with the below information:
     * @param firstName first name of the passenger
     * @param lastName last name of the passenger
     * @param address address of the passenger
     * @param city city where passenger resides
     * @param county county where passenger resides
     * @param state where the passenger resides
     * @param zip zip code where the passenger resides
     * @param phone phone number of the passenger
     * @param email email of the passenger
     * @param rewards status of the passenger (gold, bronze, silver)
     */
    Passenger(String firstName, String lastName, String address, String city, String county,
        String state, String zip, String phone, String email, String rewards) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.rewards = rewards;
        
    }
    
    /* Getters and Setters */
    
    String getFirstName() {
        
        return firstName;
    }
    
    void setFirstName(String firstName) {
        
        this.firstName = firstName;
    }
    
    String getLastName() {
        
        return lastName;
    }
    
    void setLastName(String lastName) {
        
        this.lastName = lastName;
    }
    
    String getAddress() {
        
        return address;
    }
    
    void setAddress(String address) {
        
        this.address = address;
    }
    
    public String getCity() {
        
        return city;
    }
    
    public void setCity(String city) {
        
        this.city = city;
    }
    
    String getCounty() {
        
        return county;
    }
    
    void setCounty(String county) {
        
        this.county = county;
    }
    
    String getState() {
        
        return state;
    }
    
    void setState(String state) {
        
        this.state = state;
    }
    
    String getZip() {
        
        return zip;
    }
    
    void setZip(String zip) {
        
        this.zip = zip;
    }
    
    String getPhone() {
        
        return phone;
    }
    
    void setPhone(String phone) {
        
        this.phone = phone;
    }
    
    public String getEmail() {
        
        return email;
    }
    
    public void setEmail(String email) {
        
        this.email = email;
    }
    
    String getRewards() {
        
        return rewards;
    }
    
    void setRewards(String rewards) {
        
        this.rewards = rewards;
    }
    
    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + county.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zip.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + rewards.hashCode();
        return result;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Passenger)) {
            return false;
        }
        
        Passenger passenger = (Passenger) o;
        
        if (!firstName.equals(passenger.firstName)) {
            return false;
        }
        if (!lastName.equals(passenger.lastName)) {
            return false;
        }
        if (!address.equals(passenger.address)) {
            return false;
        }
        if (!city.equals(passenger.city)) {
            return false;
        }
        if (!county.equals(passenger.county)) {
            return false;
        }
        if (!state.equals(passenger.state)) {
            return false;
        }
        if (!zip.equals(passenger.zip)) {
            return false;
        }
        if (!phone.equals(passenger.phone)) {
            return false;
        }
        if (!email.equals(passenger.email)) {
            return false;
        }
        return rewards.equals(passenger.rewards);
    }
    
    @Override
    public String toString() {
        return "Passenger{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", county='" + county + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", rewards='" + rewards + '\'' +
            '}';
    }
}
