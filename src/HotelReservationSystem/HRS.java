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
     * Getter for the ArrayList of hotels in the HotelReservationSystem.HRS
     *
     * @return the ArrayList of hotels
     */
    public ArrayList<Hotel> getHotels() {
        return hotels;
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
     * Method that simplifies input handling for integer inputs
     *
     * @param sc Scanner object
     * @return the scanned input of type int
     */
    public int getInput(Scanner sc) {
        try {
            int var = sc.nextInt();
            sc.nextLine();

            return var;
        } catch (InputMismatchException e) {
            System.out.printf("\n\033[31mError. Invalid input. Expected input with type `int`.\033[37m\n");
            sc.nextLine();

            return -1;
        }
    }

    /**
     * Method that simplifies input handling for double inputs
     *
     * @param sc Scanner object
     * @return the scanned input of type double
     */
    public double getInputDBL(Scanner sc) {
        try {
            double var = sc.nextDouble();
            sc.nextLine();

            return var;
        } catch (InputMismatchException e) {
            System.out.printf("\n\033[31mError. Invalid input. Expected input with type `double`.\033[37m\n");
            sc.nextLine();

            return -1;
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
            Hotel hotel = new Hotel(hotelName);

            System.out.printf("\nEnter number of Standard rooms to create: ");

            int num = getInput(sc);

            if (num > 0 && hotel.getRoomCount() + num < 51) {
                hotels.add(hotel);
                for (int i = 0; i < num; i++) {

                    Room newRoom = hotel.newRoom(1); // add a standard room

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
                System.out.printf("\n\033[31mError. Invalid number of rooms to create (0).\033[37m\n");
            } else {
                System.out.printf(
                        "\n\033[31mError. Entered number %d will cause the number of rooms in hotel '%s' to overflow.\n",
                        num, hotel.getName());
            }
            if (fetchHotel(hotelName) != null) {
                System.out.printf("\n\033[34mHotel with the name '%s' created successfully.\033[37m\n", hotelName);
            } else {
                System.out.printf("\n\033[31mError. Sorry! Hotel with the name '%s' created unsuccessfully.\033[37m\n", hotelName);
            }

        }

    }

    /**
     * GUI for creating hotel
     * @param hotelName the name of the hotel
     * @param type the type of rooms to create the hotel at the start
     * @param count the number of rooms to create with the hotel
     */
    public void createHotel(String hotelName, int type, int count){
        Hotel hotel = new Hotel(hotelName);
        hotel.setBasePrice(1299);
        hotels.add(hotel);
        for (int i = 0; i < count; i++) {
            hotel.newRoom(type);
        }
    }

    /**
     * GUI for 
     */

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
                    System.out.printf("\n\033[33mHotel '%s':", hotel.getName());
                    System.out.printf("\n\thas %d Standard rooms", hotel.roomTypeCount()[0]);
                    System.out.printf("\n\thas %d Deluxe rooms", hotel.roomTypeCount()[1]);
                    System.out.printf("\n\thas %d Executive rooms", hotel.roomTypeCount()[2]);
                    System.out.printf("\n\thas earned %.2f", hotel.getEarnings());
                    System.out.printf("\n\thas %d reservations", hotel.getReservationCount());
                    System.out.printf("\n\tRoom base price range from %.2f to %.2f", hotel.getBasePrice(), hotel.getBasePrice() + (hotel.getBasePrice() * 0.35));
                    System.out.printf("\n\thas average date price modifier %.2f\033[37m\n", hotel.getAverageDPC());
                }
                case 2 -> {
                    // low level information

                    System.out.printf("You selected to \033[34mview low-level information\033[37m on hotel '%s'.\n",
                            hotel.getName());
                    System.out.printf("\n=========================== OPTIONS ===========================\n");
                    System.out.printf("\33[33m1\33[37m\t:\tview available/booked rooms for a selected date\n");
                    System.out.printf("\33[33m2\33[37m\t:\tview details of a specific room\n");
                    System.out.printf("\33[33m3\33[37m\t:\tview details of a specific reservation\n");
                    System.out.printf("===============================================================");

                    System.out.printf("\nChoose an option: ");

                    int option = getInput(sc);

                    switch (option) {
                        case 1 -> {
                            // view booked rooms

                            System.out.printf("You selected to \033[34mview booked rooms for a date\033[37m for hotel '%s'.\n",
                                    hotel.getName());
                            System.out.print("\nEnter date (1-31): ");
                            int date = getInput(sc);
                            // print available rooms with proper formatting and handling
                            if (hotel.fetchAvails(1, date).isEmpty()) {
                                System.out.printf("Available rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n",
                                        hotel.getName(), date);
                            } else {
                                System.out.printf("Available rooms of hotel '%s' on day %d:\n", hotel.getName(), date);
                                for (String room : hotel.fetchAvails(1, date)) {
                                    System.out.printf("\t\033[33m%s\033[37m\n", room);
                                }
                            }
                            // print booked rooms with proper formatting and handling
                            if (hotel.fetchAvails(0, date).isEmpty()) {
                                System.out.printf("\nBooked rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n",
                                        hotel.getName(), date);
                            } else {
                                System.out.printf("\nBooked rooms of hotel '%s' on day %d:\n", hotel.getName(), date);
                                for (String room : hotel.fetchAvails(0, date)) {
                                    System.out.printf("\t\033[33m%s\033[37m\n", room);
                                }
                            }
                        }
                        case 2 -> {
                            // view room reservation

                            System.out.printf("You selected to \033[34mview a room\033[37m for hotel '%s'.\n",
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
                        case 3 -> {
                            System.out.printf("You selected to \033[34mview a reservation\033[37m for hotel '%s'.\n", hotel.getName());
                            System.out.printf("\nEnter guest name: ");
                            String guestName = sc.nextLine(); // get the guest name of the reservation
                            boolean isReservation = false;
                            for (Reservation reservation : hotel.getReservations()) {
                                // check the guest name of the reservation
                                if (reservation.getGuest().equals(guestName)) {
                                    System.out.printf("\n\033[34mReservation %d under guest %s\033[37m\n", reservation.getReservationNumber(), reservation.getGuest());
                                    System.out.printf("\n\033[33m===== RECEIPT =====\033[37m");
                                    System.out.printf("\n\033[33mname\033[37m:\t%s", reservation.getGuest());
                                    System.out.printf("\n\033[33mhtl \033[37m:\thotel %s", hotel.getName());
                                    System.out.printf("\n\033[33mroom\033[37m:\t%s", reservation.getRoom().getName());
                                    System.out.printf("\n\033[33min  \033[37m:\t%d", reservation.getCheckin());
                                    System.out.printf("\n\033[33mout \033[37m:\t%d", reservation.getCheckout());
                                    System.out.printf("\n\033[33mcost\033[37m:\tPHP %.2f", reservation.getTotal());
                                    System.out.printf("\n\033[33m===================\033[37m\n");
                                    isReservation = true;
                                }
                            }
                            if (!isReservation) {
                                System.out.printf("\n\033[31mNo reservations made under the name '%s'.\033[37m\n", guestName);
                            }
                        }
                        default ->
                            System.out.printf("\n\033[31mError. Invalid choice. Try again.\033[37m\n");
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
            System.out.printf("\33[33m6\33[37m\t:\t change price modifiers for a room in '%s'\n", name);
            System.out.printf("\33[33m7\33[37m\t:\t remove hotel '%s'\n", name);
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

                    System.out.printf("\n========== ROOM TYPES ==========\n");
                    System.out.printf("\033[33m1\033[37m\t:\t Standard Room\n");
                    System.out.printf("\033[33m2\033[37m\t:\t Deluxe Room\n");
                    System.out.printf("\033[33m3\033[37m\t:\t Executive Room\n");
                    System.out.printf("================================\n");
                    System.out.printf("\nEnter the type of room: ");

                    int type = getInput(sc);

                    if (num > 0 && hotel.getRoomCount() + num < 51) {
                        for (int i = 0; i < num; i++) {

                            Room newRoom = hotel.newRoom(type);

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
                        System.out.printf("\n\033[31mError. Invalid number of rooms to create (0).\033[37m\n");
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

                                if (room.getReservation() == null) { // checks if there are reservation
                                    hotel.delRoom(index); // run delRoomUI
                                    System.out.printf(
                                            "\n\033[33mRoom number %d in hotel '%s' has been successfully deleted.\033[37m\n",
                                            index, hotel.getName());

                                    // when we delete a room we need to move the names of the rooms back by one
                                    // so say we removed index = 10, we rename all rooms index = 10 + n with the decrement of its room number
                                } else {
                                    System.out.printf("\n\033[31mError. Room number %d has an active reservation.\033[37m\n", index);
                                }

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
                    System.out.printf("\nEnter guest name for reservation removal: ");
                    String guestName = sc.nextLine();

                    // get the check-in date
                    System.out.printf("\nEnter check-in date of the reservation to remove (1-31): ");
                    int checkinDate = getInput(sc);

                    if (checkinDate < 0 || checkinDate > 31) {
                        System.out.printf("\n\033[31mError. Check-in date %d not within this month.\033[37m\n", checkinDate);
                    } else if (hotel.removeReservation(guestName, checkinDate)) {
                        // if hotel reservation is removed or true
                        System.out.printf("\n\033[33mReservation removed successfully.\033[37m\n");
                    } else {
                        // doesn't cancel the reservation if invalid
                        // if the remove reservation does not remove anything
                        System.out.printf("\n\033[31mError. Reservation with guest '%s' and check-in date %d not found.\033[37m\n", guestName, checkinDate);
                    }
                }
                case 6 -> {
                    System.out.printf("You selected to \033[34mchange price modifiers of a room in '%s'\033[37m.\n", name);

                    System.out.printf("\n===================== OPTIONS =====================\n");
                    System.out.printf("\33[33m1\33[37m\t:\t change price modifier for one room\n");
                    System.out.printf("\33[33m2\33[37m\t:\t change price modifier for all room\n");
                    System.out.printf("\33[31m0\33[37m\t:\t exit\n");
                    System.out.printf("===================================================");
                    System.out.printf("\nChoose an option: ");
                    int option_61 = getInput(sc);

                    switch (option_61) {
                        case 0 -> System.out.println("\nReturning to main menu.");
                        case 1, 2 -> {
                            System.out.printf("\n===================== OPTIONS =====================\n");
                            System.out.printf("\33[33m1\33[37m\t:\t change date price modifier per day\n");
                            System.out.printf("\33[33m2\33[37m\t:\t select a preset\n");
                            System.out.printf("===================================================");
                            System.out.printf("\nChoose an option: ");
                            int option_62 = getInput(sc);
                            if (option_62 == 1) {
                                // change DPM per day
                                System.out.printf("\nEnter the day: ");
                                int day = getInput(sc);

                                System.out.printf("\nEnter the new date price modifer for day %d: ", day);
                                double newDPM = getInputDBL(sc);

                                // check if newDPM is valid (i.e, >0)
                                if (newDPM > 0) {

                                    // check if for one day or all rooms
                                    if (option_61 == 1) {
                                        // for one room only
                                        System.out.printf("\nEnter new room number to change price modifiers: ");
                                        int roomNumber = getInput(sc);
                                        Room room = hotel.fetchRoom(hotel.getName() + "_Room_" + roomNumber);
                                        room.changeDPM(day - 1, newDPM);
                                    } else {
                                        // for all rooms
                                        hotel.changeDPMs(day - 1, newDPM);
                                    }
                                    System.out.printf("\n\033[33mDate Price Modifier changes successfully for hotel '%s'.\033[37m\n", hotel.getName());
                                } else {
                                    System.out.printf("\n\033[31mError. Negative number or zero. Try again.\033[37m\n");
                                }
                            } else {
                                // change DPM based on a preset
                                System.out.printf("\n============================== OPTIONS ===============================\n");
                                System.out.printf("\33[33m1\33[37m\t:\t double price for weekends and 75%% off for weekdays\n");
                                System.out.printf("\33[33m2\33[37m\t:\t triple price for weekends and same price for weekdays\n");
                                System.out.printf("=======================================================================");

                                System.out.printf("\nChoose an option: ");
                                int preset = getInput(sc);

                                // if option_61 is 1 (or one room only)

                                System.out.printf("\nEnter new room number to change price modifiers: ");
                                int roomNumber = getInput(sc);
                                Room Room_61 = hotel.fetchRoom(hotel.getName() + "_Room_" + roomNumber);
                                
                                switch (preset) {
                                    case 1 -> {
                                        // 2x for weekend
                                        // 75% for weelday
                                        for (int i = 1; i < 32; i++) {
                                            if (i  % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27) {
                                                // if it's a weekend
                                                if (option_61 == 1) {
                                                    // if for one room only
                                                    Room_61.changeDPM(i - 1, Room_61.getDPM()[i - 1] * 2);
                                                } else {
                                                    // for all rooms
                                                    for (Room room : hotel.getRooms()) {
                                                        room.changeDPM(i - 1, room.getDPM()[i - 1] * 2);
                                                    }
                                                }
                                            } else {
                                                // if its a weekday
                                                if (option_61 == 1) {
                                                    // if for one room only
                                                    Room_61.changeDPM(i - 1, Room_61.getDPM()[i - 1] * 0.75);
                                                } else {
                                                    // for all rooms
                                                    for (Room room : hotel.getRooms()) {
                                                        room.changeDPM(i - 1, room.getDPM()[i - 1] * 0.75);
                                                    }
                                                }
                                            }
                                        }
                                        System.out.printf("\n\033[33mDate Price Modifier changes successfully for hotel '%s'.\033[37m\n", hotel.getName());
                                    }
                                    case 2 -> {
                                        // 3x for weekend
                                        for (int i = 1; i < 32; i++) {
                                            if (i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27) {
                                                // if it's a weekend
                                                if (option_61 == 1) {
                                                    // if for one room only
                                                    Room_61.changeDPM(i - 1, Room_61.getDPM()[i - 1] * 3);
                                                } else {
                                                    // for all rooms
                                                    for (Room room : hotel.getRooms()) {
                                                        room.changeDPM(i - 1, room.getDPM()[i - 1] * 3);
                                                    }
                                                }
                                            }
                                        }
                                        System.out.printf("\n\033[33mDate Price Modifier changes successfully for hotel '%s'.\033[37m\n", hotel.getName());
                                    }
                                    default ->
                                        System.out.printf("\n\033[31mError. Invalid choice. Try again.\033[37m\n");
                                }
                            }
                        }
                        default ->
                            System.out.printf("\n\033[31mError. Invalid choice. Try again.\033[37m\n");
                    }
                }
                case 7 -> {
                    System.out.printf("You selected to \033[34mdelete the hotel '%s'\033[37m.\n", name);
                    System.out.printf("\n\033[33mPreparing hotel '%s' for removal..\033[37m\n", hotel.getName());

                    hotel.prepareForRemoval();

                    System.out.printf("\n\033[33mHotel data cleared..\033[37m\n");

                    hotels.remove(hotel);

                    System.out.printf("\n\033[33mHotel removed successfully.\033[37m\n");
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

                        int checkin = getInput(sc);

                        // if this auto-submits then add sc.nextLine(); after this (u jinxed it)
                        System.out.print("\nEnter the day of your check-out: ");

                        int checkout = getInput(sc);
                        
                        // ask for the type of room
                        System.out.printf("\n========== ROOM TYPES ==========\n");
                        System.out.printf("\033[33m1\033[37m\t:\t Standard Room\n");
                        System.out.printf("\033[33m2\033[37m\t:\t Deluxe Room\n");
                        System.out.printf("\033[33m3\033[37m\t:\t Executive Room\n");
                        System.out.printf("================================\n");
                        System.out.printf("\nEnter the type of room: ");

                        int roomType = getInput(sc);

                        System.out.printf("\nEnter a discount code: ");

                        String code = verifyDiscount(sc.nextLine());

                        // checks if the booking dates are in bound
                        if (checkout > checkin && (checkout >= 2 && checkout <= 31)
                                && (checkin >= 1 && checkin <= 30)) {
                            hotel.bookRoom(guestName, checkin, checkout, roomType, code);

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

    /**
     * Verifies the discount code entered by the user
     * @param code the discount code entered by the user
     * @return the discount code entered by the user, {null} otherwise
     */
    public String verifyDiscount(String code){
        if (code.equals("I_WORK_HERE") || code.equals("STAY4_GET1") || code.equals("PAYDAY")){
            return code;
        }
        return null;
    }
}
