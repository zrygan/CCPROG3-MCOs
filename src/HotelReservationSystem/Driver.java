package HotelReservationSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * HotelReservationSystem.Driver class for the Hotel Reservation System
 */
public class Driver {

    /**
     * Main method for the Hotel Reservation System
     *
     * @param args arguments of the main method
     */
    public static void main(String[] args){
        HRS hrs = new HRS();
        boolean run = true;
        Scanner sc = new Scanner(System.in); // initialize scanner

        // Debugging Code [DC1]
        // hrs.createHotel("max");
        // Hotel max = hrs.fetchHotel("max");
        // max.newRoom();
        // max.newRoom();
        // max.newRoom(); // there should be 3 rooms in `max` [room_0 to room_2]
        // max.getRooms().get(0).checkIn(); // check-in in one of the rooms
        // so now there should be some earnings in max
        // remove this if need to check for changing base price
        // [11.06.2024] Check View hotel >>> NO >> YES
        // [20.06.2024] Check I/O >>> NO >> YES
        // [20.06.2024] Check: (a) change name >>> YES
        // (b) add room >>> YES
        // (c) delete room >>> NO >> YES
        // (d) change price
        // reserved >>> NO >> NO
        // !reserved >>> NO >> YES
        System.out.println("Welcome to the Hotel Reservation System or (HRS).");

        // add more instructions here if needed
        System.out.printf("\n\033[36mINSTRUCTIONS:\033[37m\n");
        System.out.printf("\t1: Enter the number of your choice when prompted with options.\n");
        System.out.printf("\t2: The hotel name is \033[31mCASE-SENSITIVE\033[37m.\n");

        // Thread.sleep(5000); // 5 second delay
        while (run) {
            hrs.showMenu();

            System.out.print("Choose an option: ");
            
            int option = -1;
            
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException  e) {
                System.out.printf("\n\033[31mError. Invalid input. Expected input with type `int`.\033[37m\n");
                sc.nextLine();
                continue;
            }

            switch (option) {
                case 1 ->
                    hrs.createHotelUI(sc);
                case 2 ->
                    hrs.viewHotelUI(sc);
                case 3 ->
                    hrs.manageHotelUI(sc);
                case 4 ->
                    hrs.simBookingUI(sc);
                case 0 -> {
                    System.out.println("Exiting the Hotel Reservation System...");
                    run = false;
                }
                case 9 ->
                    hrs.showHotels();
            }

            System.out.print("\nEnter any key to continue...");
            sc.nextLine(); // consume new line
            System.out.printf("\033c"); // cls
        }
        System.out.println("This Hotel Reservation System is made by:");
        System.out.printf("\tZhean Robby Ganituen\n");
        System.out.printf("\tJaztin Jacob Jimenez\n\n");
        System.out.println("Date Finished: June 26, 2024");
        sc.close();
    }
}
