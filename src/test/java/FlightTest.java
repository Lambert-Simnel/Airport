import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightTest {

    private Flight flightToLondon;
    private Passenger passengerToLondon;



    @Test
    public void testAddPassenger() {
        flightToLondon = new Flight("London");
        passengerToLondon = new Passenger("nameword", 012076120);
        flightToLondon.addPassenger(passengerToLondon);
        assertEquals(1, flightToLondon.getPassengerManifest().size());
    }

    



}
