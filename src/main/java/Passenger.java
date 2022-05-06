import java.util.ArrayList;
import java.util.UUID;

public class Passenger {

    private String name;
    private String ID;
    private int contactInfo;
    private ArrayList<Flight> flightsBooked;

    public Passenger(String name, int contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.ID = UUID.randomUUID().toString();
        this.flightsBooked = new ArrayList<Flight>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ArrayList<Flight> getFlightsBooked() {
        return flightsBooked;
    }

    public void setFlightsBooked(ArrayList<Flight> flightsBooked) {
        this.flightsBooked = flightsBooked;
    }

    public void addFlight(Flight flight){
        flightsBooked.add(flight);
    }

    public void removeFlight(Flight flight){
        flightsBooked.remove(flight);
    }

    public void printPassengerFlights() throws Exception{
        if(flightsBooked.isEmpty()){
            throw new Exception("\nNo flights booked!");
        } else flightsBooked.forEach(flight -> System.out.println(flight.getFlightID() + " - " + flight.getFlightDestination()));
    }
}
