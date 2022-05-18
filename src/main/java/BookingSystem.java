import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookingSystem {

    private ArrayList<Passenger> passengers;
    private ArrayList<Flight> flights;
    private ArrayList<String> destinations;

    public BookingSystem(){
        this.passengers = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.destinations = new ArrayList<>();
    }


// add, cancel and find flight
    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public void cancelFlight(Flight flight) {
        flights.remove(flight);
    }

    public void cancelFlightWithId(String id){
        flights.removeIf(flight -> flight.getFlightID().equals(id));
    }

    public void cancelPassengerFlight(Passenger passenger, String flightID){
        Flight flight = flights.stream().filter(f-> f.getFlightID().equals(flightID)).findAny().orElse(null);
        passenger.removeFlight(flight);
        flight.removePassenger(passenger);
    }

    public Flight findFlightByID(String flightID) throws Exception{
        Flight flight = flights.stream().filter(f-> f.getFlightID().equals(flightID)).findAny().orElse(null);
        if (flight == null){
            throw new Exception("\nFlight not found!");
        } else return flight;
    }

    public Passenger findPassByID(String passID) throws Exception{
        Passenger passenger = passengers.stream().filter(p -> p.getID().equals(passID)).findAny().orElse(null);
        if (passenger == null){
            throw new Exception("\nPassenger not found!");
        } else return passenger;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void addPassengerToFlight(String flightID, String passID) throws Exception{
        Flight flight = findFlightByID(flightID);
        Passenger passenger = findPassByID(passID);
        if (flight == null){
            throw new Exception("\nInvalid flight no." + flightID);
        }
        if (passenger == null){
            throw new Exception("\nInvalid passenger no." + passID);
        }
        flight.addPassenger(passenger);
        passenger.addFlight(flight);
        writeDetailsToFile(passenger, flight);
    }

    public void writeDetailsToFile(Passenger passenger, Flight flight){
        try {
            FileWriter fileWriter = new FileWriter("bookingDetails.txt");
            fileWriter.write("Passenger name: " + passenger.getName() + "\n");
            fileWriter.write("Passenger ID: " + passenger.getID() + "\n");
            fileWriter.write("Passenger contact information: " + passenger.getContactInfo() + "\n");
            fileWriter.write("Flight Destination: " + flight.getFlightDestination() + "\n");
            fileWriter.write("Flight ID: " + flight.getFlightID());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void findAndPrintFlightByDestination(String targetDestination) throws Exception{
        if (flights.stream().noneMatch(f -> f.getFlightDestination().equals(targetDestination))){
                    throw new Exception("\nDestination not found!");
                } else{
            flights.stream().filter(f -> f.getFlightDestination().equals(targetDestination)).forEach(flight -> System.out.println(flight.getFlightID() + " - " + flight.getFlightDestination()));
        }

    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void printFLights() throws Exception{
        if(flights.isEmpty()){
            throw new Exception("\nNo flights!");
        } else flights.forEach(flight -> System.out.println(flight.getFlightID() + " - " + flight.getFlightDestination()));
    }

    public void printPassengers() throws Exception{
        if(passengers.isEmpty()){
            throw new Exception("\nNo passengers!");
        } else passengers.forEach(passenger -> System.out.println(passenger.getID() + " - " + passenger.getName()));
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public ArrayList<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<String> destinations) {
        this.destinations = destinations;
    }
}
