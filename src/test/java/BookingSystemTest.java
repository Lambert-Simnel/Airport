import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingSystemTest {

    private BookingSystem testSystem;
    private Passenger testPassenger;
    private Flight testFlight;

    @BeforeEach
    public void setup() {
        testSystem = new BookingSystem();
        testPassenger = new Passenger("test", 1234556);
        testFlight = new Flight("London");
    }

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

    @Test
    public void writeDetailsToFile(){
        testSystem.addFlight(testFlight);
        testSystem.addPassenger(testPassenger);
        try {
            testSystem.addPassengerToFlight(testFlight.getFlightID(), testPassenger.getID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        testSystem.writeDetailsToFile(testPassenger, testFlight);

        File createdFile = new File("bookingDetails.txt");
        File validFile = new File("src/test/Resources/validBookingDetails.txt");

        assertEquals("The files differ!",
                FileUtils.readFileToString(file1, "utf-8"),
                FileUtils.readFileToString(file2, "utf-8"));

    }




}