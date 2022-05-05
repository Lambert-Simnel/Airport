import java.awt.print.Book;
import java.util.Scanner;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class CLI {

    private static BookingSystem bookingSystem = new BookingSystem();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Happy Company!!");
        System.out.println("What would you like to do? Type \"1\" to add a new flight, \"2\" to display all available flights, \"3\" to add a new passenger, \"4\" to book a passenger on to a flight or \"5\" to cancel a flight");
        int bookOrCancel = in.nextInt();

        switch (bookOrCancel) {
            case 1:
                System.out.println("Enter destination");
                String destination = in.next();
                bookingSystem.addFlight(new Flight(destination));
                break;
            case 2:
                break;
            case 3:
                System.out.println("Enter passenger name");
                String name = in.next();
                System.out.println("Enter ID");
                int passID = in.nextInt();

                String uuid = UUID.randomUUID().toString();

                System.out.println("Enter contact info");
                String contactInfo = in.next();
                bookingSystem.addPassenger(new Passenger(name, passID, contactInfo));
                break;
            case 4:
                try{
//                bookingSystem.addPassengerToFlight(56867, 786544);
                } catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            case 5:
                break;
        }
    }
}
