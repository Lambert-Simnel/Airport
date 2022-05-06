import java.util.*;

public class CLI {

    private static int menuSelection = 0;
    private static Scanner in = new Scanner(System.in);
    private static BookingSystem bookingSystem = new BookingSystem();


    public static void main(String[] args) {

        addFlights();
        addPassengers();
        System.out.println("Airline Management Console");

        do {
            displayMainMenu();
            menuSelection = in.nextInt();

            switch (menuSelection){
                case 1:
                    flightManagement();
                    break;
                case 2:
                    passengerManagement();
                    break;
                case 3:
                    System.out.println("Goodbye");
                    break;
            }
        } while (menuSelection != 3);

    }

    private static void flightManagement(){
        menuSelection = 0;

        do {
            displayFlightManagementMenu();
            menuSelection = in.nextInt();

            switch (menuSelection) {
                case 1:
                    System.out.println("Enter destination");
                    bookingSystem.addFlight(new Flight(askForLine(in, 1)));
                    break;
                case 2:
                    try {
                        bookingSystem.printFLights();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Select flight to cancel");
                    try {
                        bookingSystem.printFLights();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Please input the flight ID");
                    String flightIDForCancel = askForLine(in,1);
                    System.out.println(flightIDForCancel);
                    try {
                        Flight flightLineToCancel = bookingSystem.findFlightByID(flightIDForCancel);
                        bookingSystem.cancelFlight(flightLineToCancel);
                        System.out.println("\nFlight cancelled!");
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    boolean searchLoop = true;
                    while(searchLoop) {
                    System.out.println("Enter destination you want to search for or type \"EXIT\" to exit");
                        String searchDestination = askForLine(in, 1);
                        try {
                            if (searchDestination.equals("EXIT")) {
                                searchLoop = false;
                            } else {
                                bookingSystem.findAndPrintFlightByDestination(searchDestination);
                                searchLoop = false;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            searchLoop = false;
                        }
                    }
                    break;

                case 5:
                    try {
                        bookingSystem.printFLights();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Enter Flight ID");
                    try {
                        bookingSystem.findFlightByID(askForLine(in, 1)).printManifest();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    break;
            }
        } while (menuSelection != 6);
    }

    private static void passengerManagement(){
        menuSelection = 0;

        do {
            displayPassengerManagementMenu();
            menuSelection = in.nextInt();

            switch (menuSelection) {
                case 1:
                    System.out.println("Enter passenger name");
                    String name = askForLine(in, 1);
                    System.out.println("Enter contact info");
                    int contactInfo = askForNumber(in);
                    bookingSystem.addPassenger(new Passenger(name, contactInfo));
                    break;
                case 2:
                    try {
                        bookingSystem.printFLights();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Enter Flight ID");
                    String flightID = askForLine(in, 1);
                    try {
                        bookingSystem.printPassengers();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Enter Passenger ID");
                    String passID = askForLine(in, 0);
                    try {
                        bookingSystem.addPassengerToFlight(flightID, passID);
                        System.out.println("\nFlight booked!");
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                case 3:
                    try {
                        bookingSystem.printPassengers();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Enter Passenger ID");
                    try {
                        Passenger passenger = bookingSystem.findPassByID(askForLine(in, 1));
                        passenger.printPassengerFlights();
                        System.out.println("Enter Flight ID");
                        String flightIDForCancel = in.next();
                        bookingSystem.cancelPassengerFlight(passenger, flightIDForCancel);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case 4:
                    System.out.println("All passengers :");
                    try {
                        bookingSystem.printPassengers();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    break;
            }
        } while (menuSelection != 5);
    }

    private static void displayMainMenu() {
        System.out.println("");
        System.out.println("===================");
        System.out.println("Select an Option:");
        System.out.println("1) Flight management");
        System.out.println("2) Passenger management");
        System.out.println("3) Shut down");
        System.out.println("===================");
    }

    private static void displayFlightManagementMenu() {
        System.out.println("");
        System.out.println("===================");
        System.out.println("Select an Option:");
        System.out.println("1) Add new flight");
        System.out.println("2) Display all flights");
        System.out.println("3) Cancel flight");
        System.out.println("4) Search for destination");
        System.out.println("5) Print passenger manifest");
        System.out.println("6) Return to main menu");
        System.out.println("===================");
    }

    private static void displayPassengerManagementMenu() {
        System.out.println("");
        System.out.println("===================");
        System.out.println("Select an Option:");
        System.out.println("1) Create new passenger");
        System.out.println("2) Book passenger onto flight");
        System.out.println("3) Cancel passenger's flight");
        System.out.println("4) Display all passengers");
        System.out.println("5) Return to main menu");
        System.out.println("===================");
    }

    private static int askForNumber(Scanner in) {
        int userInput;
        System.out.print("Please enter a number: ");
        while (!in.hasNextInt()) {
            String input = in.next();
            System.out.printf("\"%s\" is not a valid input\n", input);
            System.out.print("Please enter a number: ");
        }
        userInput = in.nextInt();
        return userInput;
    }

    private static String askForLine(Scanner in, int thisOrNext) {
        System.out.print("Enter a string data: ");
        switch (thisOrNext){
            case 0:
                return in.next();
            case 1:
                in.nextLine();
                return in.nextLine();
            default:
                return null;
        }
    }

    private static void addFlights(){
        bookingSystem.addFlight(new Flight("London"));
        bookingSystem.addFlight(new Flight("New York"));
        bookingSystem.addFlight(new Flight("Sydney"));
        bookingSystem.addFlight(new Flight("Los Angeles"));
        bookingSystem.addFlight(new Flight("Beijing"));
        bookingSystem.addFlight(new Flight("Bangkok"));
        bookingSystem.addFlight(new Flight("Paris"));
        bookingSystem.addFlight(new Flight("Berlin"));
    }

    private static void addPassengers(){
        bookingSystem.addPassenger(new Passenger("Edward", 8236438));
        bookingSystem.addPassenger(new Passenger("Falak", 456789));
        bookingSystem.addPassenger(new Passenger("Joseph", 92372));
        bookingSystem.addPassenger(new Passenger("Carol", 873239));
        bookingSystem.addPassenger(new Passenger("Colin", 834674));
        bookingSystem.addPassenger(new Passenger("Lucasz", 462784));
        bookingSystem.addPassenger(new Passenger("Valeria", 3346943));
    }
}
