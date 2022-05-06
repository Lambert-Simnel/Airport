import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassengerTest {

    @Test
    void passengerCreateTest(){
        Passenger passengerOne = new Passenger("Carol", 1234567);

        //check get contact information
        assertEquals(1234567,passengerOne.getContactInfo());
        //check get passenger name
        assertEquals("Carol", passengerOne.getName());







    }





}
