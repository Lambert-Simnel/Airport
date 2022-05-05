import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookingSystem {

    private List<Passenger> passengers;
    private List<Flight> flights;


        // list all destinations you can choose

        //input user's destination

    public void addFlight(Flight flight){
        flight.setFlightID(UUID.randomUUID().toString());
        flights.add(flight);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void cancelFlight(Flight flight) { flights.remove(flight); }



    public void cancelFlightWithId(String id){
        flights.removeIf(flight -> flight.getFlightID().equals(id));
    }

    public void addPassengerToFlight(String flightID, int passID) throws Exception{
        Flight flight = findFlightByID(flightID);
        if (flight == null){
            throw new Exception("Invalid flight no." + flightID);
        }
        Passenger passenger = findPassByID(passID);
        if (passenger == null){
            throw new Exception("Invalid passenger no." + passID);
        }
        flight.addPassenger(passenger);
    }

    public Flight findFlightByID(String flightID){
        return flights.stream().filter(flight-> flight.getFlightID().equals(flightID)).findAny().orElse(null);
    }

    public Passenger findPassByID(int passID){
        return passengers.stream().filter(passenger -> passenger.getID() == passID).findAny().orElse(null);
    }

    public List<Flight> getFlights() {
        return flights;
    }

//    public void printFLights() { flights.stream().map(f -> System.out.println(f.getPrintFlightID()));}
}
