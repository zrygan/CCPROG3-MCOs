package HotelReservationSystem;

import java.util.*;

/**
 * An object that handles the reservation system.
 */
public class HRS {

    // Variables
    private ArrayList<Hotel> hotels;

    /**
     * Constructor for the Hotel Reservation System
     */
    public HRS() {
        this.hotels = new ArrayList<>();
    }

    /**
     * Sets the hotels in the ArrayList of Hotel
     *
     * @param hotels the array of hotels in the HotelReservationSystem.HRS
     */
    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * Getter for the ArrayList of hotels in the HotelReservationSystem.HRS
     *
     * @return the ArrayList of hotels
     */
    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    /**
     * Method that simplifies input handling for integer inputs
     * @param sc Scanner object
     * @return the scanned input of type int
     */
    public int getInput(Scanner sc){
        try{
            int var =  sc.nextInt();
            sc.nextLine();

            return var;
        } catch (InputMismatchException e){
            System.out.printf("\n\033[31mError. Invalid input. Expected input with type `int`.\033[37m\n");
            sc.nextLine();

            return -1;
        }
    }
    
    /**
     * Method that simplifies input handling for double inputs
     * @param sc Scanner object
     * @return the scanned input of type double
     */
    public double getInputDBL(Scanner sc){
        try{
            double var =  sc.nextDouble();
            sc.nextLine();

            return var;
        } catch (InputMismatchException e){
            System.out.printf("\n\033[31mError. Invalid input. Expected input with type `int`.\033[37m\n");
            sc.nextLine();

            return -1;
        }
    }

    /**
     * Creates a new hotel with a given name
     *
     * @param name the name of the new hotel
     * @param sc the scanner object
     *
     * @author Zhean Ganituen
     */
    public void createHotel(String name, Scanner sc) {
        Hotel hotel = new Hotel(name);
        
        System.out.printf("\nEnter number of rooms to create: ");

        int num = getInput(sc);

        if (num > 0 && hotel.getRoomCount() + num < 51) {
            hotels.add(hotel);
            for (int i = 0; i < num; i++) {

                Room newRoom = hotel.newRoom();

                if (newRoom != null) {

                    System.out.printf(
                            "\n\033[33mA new room '%s' has been successfully added in hotel '%s'.\033[37m\n",
                            newRoom.getName(), hotel.getName());

                } else {
                    System.out.printf(
                            "\n\033[31mError. A new room cannot be created since there are 50 rooms in hotel '%s' already.\033[37m\n",
                            hotel.getName());
                }
            }
        } else if (num <= 0) {
            System.out.printf("\n\033[31mError. Invalid number of rooms to create (0).\033[37m\n",
                    hotel.getName());
        } else {
            System.out.printf(
                    "\n\033[31mError. Entered number %d will cause the number of rooms in hotel '%s' to overflow.\n",
                    num, name);
        }
        
        
    }

    /**
     * returns the hotel given the name of the hotel
     *
     * @param name name of the hotel
     * @return {room} the hotel with the room name in the hotel, {null} the
     * hotel was not found
     *
     * @author Zhean Ganituen
     */
    public Hotel fetchHotel(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                return hotel;
            }
        }

        return null;
    }

    /**
     * Lists the hotels with their number of rooms
     *
     * @author Zhean Ganituen
     */
    public void showHotels() {
        int i = 1;
        System.out.printf("\n================ SHOW HOTELS ================\n");
        for (Hotel hotel : hotels) {
            System.out.printf("%d\t\033[94m'%s'\033[37m\thas \033[94m%d\033[37m rooms.\n", i, hotel.getName(),
                    hotel.getRoomCount());
            i++;
        }
        System.out.println("=============================================");
    }

    /**
     * User I/O for creating a hotel
     *
     * @param sc the scanner object
     *
     * @author Zhean Ganituen
     */
    public void createHotel(Scanner sc) {
        System.out.printf("You selected to \033[34mcreate\033[37m a hotel!\n");

        System.out.printf("\nEnter the name of the hotel: ");
        String hotelName = sc.nextLine();

        if (fetchHotel(hotelName) != null) {
            System.out.printf("\n\033[31mError. Sorry! But that hotel name '%s' already exists.\033[37m\n", hotelName);
        } else {
            createHotel(hotelName, sc);
            if (fetchHotel(hotelName) != null) {
                System.out.printf("\n\033[34mHotel with the name '%s' created successfully.\033[37m\n", hotelName);
            }
            else {
                System.out.printf("\n\033[31mError. Sorry! Hotel with the name '%s' created unsuccessfully.\033[37m\n", hotelName);
            }
            
        }

    }

    /**
     * User I/O for viewing the details of a hotel
     *
     * @param sc Scanner object
     *
     * @author Zhean Ganituen
     */
    public void viewHotel(Scanner sc) {
        System.out.printf("You selected to \033[34mview\033[37m a hotel!\n");

        System.out.printf("\nEnter the name of the hotel: ");
        String hotelName = sc.nextLine();

        Hotel hotel = fetchHotel(hotelName);
        if (hotel != null) {
            System.out.printf("\n==================== OPTIONS ====================\n");
        System.out.printf("\33[33m1\33[37m\t:\tview high-level hotel information\n");
        System.out.printf("\33[33m2\33[37m\t:\tview low-level hotel information\n");
        System.out.printf("\33[31m0\33[37m\t:\texit\n");
        System.out.printf("=================================================");

        System.out.printf("\nChoose an option: ");

        int level = getInput(sc);

        switch (level) {
            case 0 ->
                System.out.println("\nReturning to main menu.");
            case 1 -> {
                // high level information

                System.out.printf("You selected to \033[34mview high-level information\033[37m for hotel '%s'.\n",
                        hotel.getName());
                System.out.printf("\n\033[33mHotel '%s' with %d rooms has earned PHP %.2f.\033[37m\n", hotel.getName(),
                        hotel.getRooms().size(), hotel.getEarnings());
            }
            case 2 -> {
                // low level information

                System.out.printf("You selected to \033[34mview low-level information\033[37m on hotel '%s'.\n",
                        hotel.getName());
                System.out.printf("\n=========================== OPTIONS ===========================\n");
                System.out.printf("\33[33m1\33[37m\t:\tview available/booked rooms for a selected date\n");
                System.out.printf("\33[33m2\33[37m\t:\tview details of a specific room or reservation\n");
                System.out.printf("===============================================================");

                System.out.printf("\nChoose an option: ");

                int option = getInput(sc);

                if (option == 1) {
                    // view booked rooms

                    System.out.printf("You selected to \033[34mview booked rooms for a date\033[37m for hotel '%s'.\n",
                            hotel.getName());

                    System.out.print("\nEnter date (1-31): ");

                    int date = getInput(sc);                    

                    // print available rooms with proper formatting and handling
                    if (hotel.fetchAvailableRoomNames(1, date).isEmpty()) {
                        System.out.printf("Available rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n",
                                hotel.getName(), date);
                    } else {
                        System.out.printf("Available rooms of hotel '%s' on day %d:\n", hotel.getName(), date);
                        for (String room : hotel.fetchAvailableRoomNames(1, date)) {
                            System.out.printf("\t\033[33m%s\033[37m\n", room);
                        }
                    }

                    // print booked rooms with proper formatting and handling
                    if (hotel.fetchAvailableRoomNames(0, date).isEmpty()) {
                        System.out.printf("\nBooked rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n",
                                hotel.getName(), date);
                    } else {
                        System.out.printf("\nBooked rooms of hotel '%s' on day %d:\n", hotel.getName(), date);
                        for (String room : hotel.fetchAvailableRoomNames(0, date)) {
                            System.out.printf("\t\033[33m%s\033[37m\n", room);
                        }
                    }

                } else if (option == 2) {
                    // view room reservation

                    System.out.printf("You selected to \033[34mview a room reservation\033[37m for hotel '%s'.\n",
                            hotel.getName());

                    System.out.printf("\nEnter room number: ");

                    int roomNum = getInput(sc);

                    String roomName = hotel.getName() + "_Room_" + roomNum; // reformat the name

                    // get room
                    Room roomQuery = hotel.fetchRoom(roomName);

                    // if room exists
                    if (roomQuery != null) {
                        // calculate availability
                        int days = 31 - roomQuery.getDaysBooked();

                        System.out.printf(
                                "\n\033[34mThe Room %d in Hotel '%s' costs %.2f per night and is available for %d days of the month.\033[37m\n",
                                roomNum, hotel.getName(), roomQuery.getBasePrice(), days);
                    } else {
                        System.out.printf(
                                "\n\033[31mError. Sorry! But the room %d in Hotel '%s' does not exist.\033[37m\n",
                                roomNum, hotel.getName());
                    }
                }
            }
        }
        } else {
            System.out.printf(
                    "\n\033[31mError. Sorry! But that hotel name '%s' is not found in the database.\033[37m\n",
                    hotelName);
        }
    }

    /**
     * User I/O for managing a hotel
     *
     * @param sc Scanner object
     *
     * @author Zhean Ganituen
     */
    public void manageHotel(Scanner sc) {
        System.out.printf("You selected to \033[34mmanage\033[37m a hotel!\n");

        System.out.print("\nEnter the name of the hotel: ");
        String name = sc.nextLine();
        Hotel hotel = fetchHotel(name);

        if (hotel != null) {
            System.out.printf("\n=================== OPTIONS ===================\n");
            System.out.printf("\33[33m1\33[37m\t:\t change the name of hotel '%s'\n", name);
            System.out.printf("\33[33m2\33[37m\t:\t add a new room\n");
            System.out.printf("\33[33m3\33[37m\t:\t remove a room\n");
            System.out.printf("\33[33m4\33[37m\t:\t update the base price\n");
            System.out.printf("\33[33m5\33[37m\t:\t remove a reservation\n");
            System.out.printf("\33[33m6\33[37m\t:\t remove hotel '%s'\n", name);
            System.out.printf("\33[31m0\33[37m\t:\t exit\n");
            System.out.printf("===============================================");

            System.out.printf("\nChoose an option: ");

            int choice = getInput(sc);

            switch (choice) {
                case 0 -> // exit to main menu
                    System.out.println("\nReturning to main menu.");
                case 1 -> {
                    // change name 

                    System.out.printf("You selected to \033[34mchange the name of hotel '%s'\033[37m.\n", name);
                    System.out.printf("\nEnter new hotel name: ");

                    String oldName = hotel.getName();

                    String newName = sc.nextLine();

                    if (fetchHotel(newName) != null) {
                        System.out.printf("\n\033[31mError. Sorry! But that hotel name '%s' already exists.\033[37m\n",
                                newName);

                    } else {
                        hotel.setName(newName); // set the name to the new name
                        System.out.printf("\n\33[33mHotel '%s' has been successfully renamed to '%s'.\33[37m\n",
                                oldName, hotel.getName());
                        Hotel changeName = fetchHotel(newName);

                        changeName.changeRoomName(newName);
                    }

                }
                case 2 -> {
                    // add room 

                    System.out.printf("You selected to \033[34madd a room\033[37m in hotel '%s'.\n", name);

                    System.out.printf("\nEnter number of rooms to create: ");

                    int num = getInput(sc);

                    if (num > 0 && hotel.getRoomCount() + num < 51) {
                        for (int i = 0; i < num; i++) {

                            Room newRoom = hotel.newRoom();

                            if (newRoom != null) {

                                System.out.printf(
                                        "\n\033[33mA new room '%s' has been successfully added in hotel '%s'.\033[37m\n",
                                        newRoom.getName(), hotel.getName());

                            } else {
                                System.out.printf(
                                        "\n\033[31mError. A new room cannot be created since there are 50 rooms in hotel '%s' already.\033[37m\n",
                                        hotel.getName());
                            }
                        }
                    } else if (num <= 0) {
                        System.out.printf("\n\033[31mError. Invalid number of rooms to create (0).\033[37m\n",
                                hotel.getName());
                    } else {
                        System.out.printf(
                                "\n\033[31mError. Entered number %d will cause the number of rooms in hotel '%s' to overflow.\nThere are %d rooms in hotel '%s', maximum rooms that can be added is %d.\033[37m\n",
                                num, name, hotel.getRoomCount(), name, 50 - hotel.getRoomCount());
                    }
                }
                case 3 -> {
                    // delete a room

                    System.out.printf("You selected to \033[34mdelete a room\033[37m in hotel '%s'.\n", name);

                    // hotel.delRoomUI(sc);
                    if (hotel.getRoomCount() > 1) {
                        System.out.printf("\nEnter room number to delete: ");

                        int index = getInput(sc);

                        // check if index is within bounds
                        if (index < 0 || index > 51) {
                            System.out.printf("\n\033[31mError. Entered number out of range. From 1 to 50 only.\033[37m\n");
                        } else {
                            // format the name
                            String roomName = hotel.getName() + "_Room_" + index;

                            // get the room
                            Room room = hotel.fetchRoom(roomName);

                            // check if the rooms exists
                            if (room != null) {
                                hotel.delRoom(index); // run delRoomUI
                                System.out.printf(
                                        "\n\033[33mRoom number %d in hotel '%s' has been successfully deleted.\033[37m\n",
                                        index, hotel.getName());

                                // when we delete a room we need to move the names of the rooms back by one
                                // so say we removed index = 10, we rename all rooms index = 10 + n with the decrement of its room number
                            } else {
                                System.out.printf("\n\033[31mError. Room number %d not found.\033[37m\n", index);
                            }
                        }
                    } else {
                        System.out.printf(
                                "\n\033[31mError. Cannot delete room. There is only 1 room left in hotel '%s'.\033[37m\n",
                                hotel.getName());
                    }
                }
                case 4 -> {
                    // change price

                    System.out.printf("You selected to \033[34mchange the price\033[37m of hotel '%s'.\n", name);
                    System.out.printf("\nEnter the new price for the rooms of the hotel: ");

                    double newPrice = getInputDBL(sc);

                    if (hotel.changePrice(newPrice)) {
                        System.out.printf("\033[33m\nThe rooms of hotel '%s' have been changed to %.2f.\033[37m\n", hotel.getName(),
                                hotel.getBasePrice());
                    } else if (newPrice <= 100) {
                        System.out.printf(
                                "\n\033[31mError. The base price of hotel '%s' has not been changed because the new price is too low. It must be greater than or equal to 100.\033[37m\n",
                                hotel.getName());
                    } else {
                        System.out.printf(
                                "\n\033[31mError. The base price of hotel '%s' has not been changed because there's an ongoing reservation.\033[37m\n",
                                hotel.getName());
                    }
                }

                case 5 -> {
                    System.out.printf("You selected to \033[34mremove a reseravation\033[37m in hotel '%s'.\n", name);

                    // get the name
                    System.out.print("Enter guest name for reservation removal: ");
                    String guestName = sc.nextLine();

                    // get the check-in date
                    System.out.print("Enter check-in date of the reservation to remove (1-31): ");
                    int checkInDate = getInput(sc);

                    if (hotel.removeReservation(guestName, checkInDate)) {
                        // if hotel reservation is removed or true
                        System.out.println("Reservation removed successfully.");
                    } else {
                        // doesn't cancel the reservation if invalid
                        // if the remove reservation does not remove anything
                        System.out.println("Reservation not found.");
                    }
                }
                case 6 -> {
                    System.out.printf("You selected to \033[34mdelete the hotel '%s'\033[37m.\n", name);
                    System.out.printf("\n\033[33mPreparing hotel '%s' for removal..\033[37m\n", hotel.getName());

                    hotel.prepareForRemoval();

                    System.out.printf("\n\033[33mHotel data cleared..\033[37m\n");

                    hotels.remove(hotel);

                    System.out.println("Hotel removed successfully.");

                    return; // Exit after removal
                }
                default ->
                    System.out.printf("\n\033[31mError. Invalid choice. Try again.\033[37m\n");
            }
        } else {
            System.out.printf("\n\033[31mError. Hotel '%s' is not found.\033[37m\n", name);
        }
    }

    /**
     * User I/O for booking simulation
     *
     * @param sc Scanner object
     *
     * @author Zhean Ganituen
     * @author Jaztin Jimenez
     */
    public void simBooking(Scanner sc) {
        System.out.printf("You selected to \033[34msimulate booking a room\033[37m.\n");
        System.out.printf("\nWelcome to the Hotel Reservation System, User!\n");
        System.out.printf("\nPlease enter your name: ");

        String guestName = sc.nextLine(); // look out for this baddie

        System.out.printf("\n\033[96mHint:\tenter '? help' to get the list of hotels.\033[37m\n");
        System.out.printf("\033[96m\tenter '? exit' to leave the simulation.\033[37m\n");

        boolean run = true;

        while (run) {
            System.out.printf("\nDear %s, please enter the name of the hotel: ", guestName);
            String hotelName = sc.nextLine();

            switch (hotelName) {
                case "? help" ->
                    this.showHotels();
                case "? exit" ->
                    run = false;
                default -> {
                    System.out.printf("You selected to \033[34mbook a room in hotel '%s'\033[37m.\n", hotelName);

                    Hotel hotel = fetchHotel(hotelName);

                    if (hotel != null) {
                        System.out.printf("\nEnter the day of your check-in: ");

                        int checkIn = getInput(sc);

                        // if this auto-submits then add sc.nextLine(); after this (u jinxed it)
                        System.out.print("\nEnter the day of your check-out: ");

                        int checkOut = getInput(sc);

                        // checks if the booking dates are in bound
                        if (checkOut > checkIn && (checkOut >= 2 && checkOut <= 31)
                                && (checkIn >= 1 && checkIn <= 30)) {
                            hotel.bookRoom(guestName, checkIn, checkOut);
                            run = false;
                        } else {
                            System.out.printf("\n\033[31mError. Invalid dates for booking.\033[37m\n");
                            run = false;
                        }
                    } else {
                        System.out.printf("\n\033[31mError. Hotel '%s' is not found.\033[37m\n", hotelName);
                    }
                }
            }
        }
    }

    /**
     * Shows the main menu of the program
     *
     * @author Zhean Ganituen
     */
    public void showMenu() {
        System.out.printf("\n===================================\n");
        System.out.printf("\033[33m1\033[37m\t:\t Create Hotel\n");
        System.out.printf("\033[33m2\033[37m\t:\t View Hotel\n");
        System.out.printf("\033[33m3\033[37m\t:\t Manage Hotel\n");
        System.out.printf("\033[33m4\033[37m\t:\t Simulate Booking\n");
        System.out.printf("\033[31m0\033[37m\t:\t Exit\n");
        System.out.printf("===================================\n");
    }
}
