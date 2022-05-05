import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingSystemTest {

    private BookingSystem testSystem;
    private Passenger testPassenger;
    private Flight testFlight;

    @Test
    public void addPassengerTestSize() {
        testSystem.addPassenger(testPassenger);
        assertEquals(1, testSystem.getPassengers().size());
    }

    @Test
    public void addPassengerTestUUID() {
        testSystem.addPassenger(testPassenger);
        assertEquals(36, testPassenger.getID().length());
    }

    @Test
    public void addFlightTestSize() {
        testSystem.addFlight(testFlight);
        assertEquals(1, testSystem.getFlights().size());
    }

    @Test
    public void addFlightTestUUID() {
        testSystem.addFlight(testFlight);
        assertEquals(36, testFlight.getFlightID().length());
    }





}