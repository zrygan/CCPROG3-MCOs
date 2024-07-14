package HotelReservationSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * HotelReservationSystem.Driver class for the Hotel Reservation System
 */
public class Driver {
    
    /**
     * Constructor for the Driver Class (blank)
     */
    public Driver(){
        // just added this to make javadoc stop crying with how there's no comment constructor for the Driver file
    }

    /**
     * Main method for the Hotel Reservation System
     *
     * @param args arguments of the main method
     */
    public static void main(String[] args) {
        // Make instacnce of HRS
        HRS hrs = new HRS();            

        new Thread(() -> {
            GUIs gui = new GUIs(hrs);
        }).start();

        boolean run = true;
        
        // CLI
        try (Scanner sc = new Scanner(System.in)) // initialize scanner
        {   
            System.out.println("Welcome to the Hotel Reservation System or (HRS).");

            // add more instructions here if needed
            System.out.printf("\n\033[36mINSTRUCTIONS:\033[37m\n");
            System.out.printf("\t1: Enter the number of your choice when prompted with options.\n");
            System.out.printf("\t2: The hotel name is \033[31mCASE-SENSITIVE\033[37m.\n");

            // Thread.sleep(5000); // 5 second delay
            while (run) {
                hrs.showMenu();

                System.out.print("Choose an option: ");

                int option;

                try {
                    option = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.printf("\n\033[31mError. Invalid input. Expected input with type `int`.\033[37m\n");
                    sc.nextLine();
                    continue;
                }

                switch (option) {
                    case 1 ->
                        hrs.createHotel(sc);
                    case 2 ->
                        hrs.viewHotel(sc);
                    case 3 ->
                        hrs.manageHotel(sc);
                    case 4 ->
                        hrs.simBooking(sc);
                    case 0 -> {
                        System.out.println("Exiting the Hotel Reservation System...");
                        run = false;
                    }
                }

                System.out.print("\nEnter any key to continue...");
                sc.nextLine(); // consume new line
                System.out.printf("\033c"); // cls
            }
            System.out.println("This Hotel Reservation System is made by:");
            System.out.printf("\tZhean Robby Ganituen\n");
            System.out.printf("\tJaztin Jacob Jimenez\n\n");
            System.out.println("Date Finished: June 26, 2024");
        }
    }
}
