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
    public void writeDetailsToFile() throws Exception {
        //Given
        testFlight.setFlightID("7d1f4f11-5e9e-4f79-9a92-c2cda3b10a34");
        testPassenger.setID("3a282889-a162-42cc-bad7-7ddac1b78f93");
        testSystem.addFlight(testFlight);
        testSystem.addPassenger(testPassenger);

        //When
        testSystem.addPassengerToFlight(testFlight.getFlightID(), testPassenger.getID());

        //Then
        File generatedFile = new File("bookingDetails.txt");
        File validTestFile = new File("src/test/Resources/validBookingDetails.txt");

        assertEquals(FileUtils.readFileToString(generatedFile, "utf-8"),
                FileUtils.readFileToString(validTestFile, "utf-8"));

    }




}