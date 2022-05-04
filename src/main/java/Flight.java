import java.util.ArrayList;
import java.util.List;

public class Flight {

    private ArrayList<Passenger> passengerManifest;
    private int flightID;
    private String flightDestination;

    public Flight(int flightID, String flightDestination) {
        this.passengerManifest = new ArrayList<Passenger>();
        this.flightID = flightID;
        this.flightDestination = flightDestination;
    }

    public ArrayList<Passenger> getPassengerManifest() {
        return passengerManifest;
    }

    public void setPassengerManifest(ArrayList<Passenger> passengerManifest) {
        this.passengerManifest = passengerManifest;
    }

    public void addPassenger(Passenger newPassenger) {
        this.passengerManifest.add(newPassenger);
    }

    public void removePassenger(Passenger removedPassenger) {
        this.passengerManifest.remove(removedPassenger);
    }

    public int getFlightID() {
        return flightID;
    }

    public String getPrintFlightID() { return Integer.toString(flightID); }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }






}
