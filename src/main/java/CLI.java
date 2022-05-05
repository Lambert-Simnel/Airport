import java.util.List;
import java.util.Scanner;

public class CLI {

    private static int menuSelection = 0;
    private static Scanner in = new Scanner(System.in);
    private static BookingSystem bookingSystem = new BookingSystem();


    public static void main(String[] args) {

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
                    bookingSystem.addFlight(new Flight(askForLine(in)));
                    break;
                case 2:
                    bookingSystem.printFLights();
                    break;
                case 3:
                    System.out.println("You are trying to delete the flight route");
                    bookingSystem.printFLights();
                    System.out.println("Please input the flight ID");
                    String flightIDForCancel = in.next();
                    for (int i = 0; i < bookingSystem.getFlights().size(); i++){
                        if (bookingSystem.getFlights().get(i).getFlightID() == flightIDForCancel){
                            bookingSystem.cancelFlight(bookingSystem.getFlights().get(i));
                        } else {
                            System.out.println("You enter the invalid ID");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter destination you want to search for or EXIT to exit");
                    boolean searchLoop = true;
                    while(searchLoop) {
                        String searchDestination = in.next();
                        try {
                            if (searchDestination.equals("EXIT")) {
                                searchLoop = false;
                            } else {
                                bookingSystem.findAndPrintFlightByDestination(searchDestination);
                            }
                        } catch (RuntimeException e) {
                            System.out.println("You must enter a valid destination");
                            in.next();
                        }
                    }
                    break;
                case 5:
                    break;
            }
        } while (menuSelection != 5);
    }

    private static void passengerManagement(){
        menuSelection = 0;

        do {
            displayPassengerManagementMenu();
            menuSelection = in.nextInt();

            switch (menuSelection) {
                case 1:
                    System.out.println("Enter passenger name");
                    String name = askForLine(in);
                    System.out.println("Enter contact info");
                    int contactInfo = askForNumber(in);
                    bookingSystem.addPassenger(new Passenger(name, contactInfo));
                    break;
                case 2:
                    bookingSystem.printPassengers();
                    bookingSystem.printFLights();
                    System.out.println("Enter Flight ID");
                    String flightID = askForLine(in);
                    System.out.println("Enter Passenger ID");
                    String passID = askForLine(in);
                    try {
                        bookingSystem.addPassengerToFlight(flightID, passID);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Enter Passenger ID");
                    passID = askForLine(in);
                    System.out.println("Enter Flight ID");
                    flightID = askForLine(in);
                    try {
                        bookingSystem.cancelFlightWithId(passID);
                    } catch (RuntimeException e) {
                        System.out.println("You entered an invalid ID");
                    }
                    break;
                case 4:
                    break;

            }
        } while (menuSelection != 4);
    }

    private static void displayMainMenu() {
        System.out.println("");
        System.out.println("===================");
        System.out.println("Select an Option:");
        System.out.println("1) Flight management");
        System.out.println("2) Passenger management");
        System.out.println("3) Quit");
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
        System.out.println("5) Return to main menu");
        System.out.println("===================");
    }

    private static void displayPassengerManagementMenu() {
        System.out.println("");
        System.out.println("===================");
        System.out.println("Select an Option:");
        System.out.println("1) Create new passenger");
        System.out.println("2) Book passenger onto flight");
        System.out.println("3) Cancel passenger's flight");
        System.out.println("4) Return to main menu");
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

    private static String askForLine(Scanner in) {

        System.out.print("Enter a string data: ");
        in.nextLine();
        return in.nextLine();
    }
}
