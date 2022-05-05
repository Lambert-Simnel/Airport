import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class BookingSystem {

    private List<Passenger> passengers;
    private List<Flight> flights;

    public BookingSystem(){
        this.passengers = new List<Passenger>();
        this.flights = new List<Flight>();
    }


// add, cancel and find flight
    public void addFlight(Flight flight){
        flight.setFlightID(UUID.randomUUID().toString());
        flights.add(flight);
    }

    public void cancelFlight(Flight flight) {
        flights.remove(flight);
    }

    public void cancelFlightWithId(String id){

        flights.removeIf(flight -> flight.getFlightID().equals(id));
    }

    public Flight findFlightByID(String flightID){
        return flights.stream().filter(flight-> flight.getFlightID().equals(flightID)).findAny().orElse(null);
    }



// create id for the passenger and add passenger to the flight, find passenger
    public void addPassenger(Passenger passenger) {
        passenger.setID(UUID.randomUUID().toString());
        passengers.add(passenger);
    }

    public void addPassengerToFlight(String flightID, String passID) throws Exception{
        Flight flight = findFlightByID(flightID);
        if (flight == null){
            throw new Exception("Invalid flight no." + flightID);
        }
        Passenger passenger = findPassByID(passID);
        if (passenger == null){
            throw new Exception("Invalid passenger no." + passID);
        }
        flight.addPassenger(passenger);
        writeDetailsToFile(passenger, flight);
    }

    public Passenger findPassByID(String passID){
        return passengers.stream().filter(passenger -> passenger.getID().equals(passID)).findAny().orElse(null);
    }


// print details
    public void writeDetailsToFile(Passenger passenger, Flight flight){
        try {
            FileWriter fileWriter = new FileWriter("bookingDetails.txt");
            fileWriter.write("Passenger name: " + passenger.getName());
            fileWriter.write("Passenger ID: " + passenger.getID());
            fileWriter.write("Passenger contact information: " + passenger.getContactInfo());
            fileWriter.write("Flight Destination: " + flight.getFlightDestination());
            fileWriter.write("Flight ID: " + flight.getFlightID());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void findAndPrintFlightByDestination(String targetDestination) {
        flights.stream().filter(f -> f.equals(targetDestination)).forEach(System.out::println);
    }






    public List<Flight> getFlights() {
        return flights;
    }

    public void printFLights() { flights.forEach(System.out::println);}

    public void printPassengers() {
        passengers.forEach(System.out::println);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

}
