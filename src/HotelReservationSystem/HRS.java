package HotelReservationSystem;

import java.util.*;

/**
 * An object that handles the reservation system.
 */
public class HRS {

    // Variables
    private ArrayList<Hotel> hotels;

    /**
     * Constructor for the Hotel HotelReservationSystem.Reservation System
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
     * Creates a new hotel with a given name
     *
     * @param name the name of the new hotel
     * @param sc the scanner object
     *
     * @author Zhean Ganituen
     */
    public void createHotel(String name, Scanner sc) {
        Hotel hotel = new Hotel(name);
        hotels.add(hotel);
        hotel.addRoom(sc);
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

        for (Hotel hotel : hotels) {
            System.out.printf("%d\t'%s' has %d rooms.\n", i, hotel.getName(), hotel.getRoomCount());
            i++;
        }
    }

    /**
     * User I/O for creating a hotel
     *
     * @param sc the scanner object
     *
     * @author Zhean Ganituen
     */
    public void createHotelUI(Scanner sc) {
        System.out.printf("You selected to \033[34mcreate\033[37m a hotel!\n");

        System.out.printf("\nEnter the name of the hotel: ");
        String hotelName = sc.nextLine();

        if (fetchHotel(hotelName) != null) {
            System.out.printf("\n\033[31mError. Sorry! But that hotel name '%s' already exists.\033[37m\n", hotelName);
        } else {
            createHotel(hotelName, sc);
            System.out.printf("\n\033[34mHotel with then name '%s' created successfully.\033[37m\n", hotelName);
        }

    }

    /**
     * User I/O for viewing the details of a hotel
     *
     * @param sc Scanner object
     *
     * @author Zhean Ganituen
     */
    public void viewHotelUI(Scanner sc) {
        System.out.printf("You selected to \033[34mview\033[37m a hotel!\n");

        System.out.printf("\nEnter the name of the hotel: ");
        String hotelName = sc.nextLine();

        Hotel hotel = fetchHotel(hotelName);
        if (hotel != null) {
            hotel.viewHotel(sc);
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
    public void manageHotelUI(Scanner sc) {
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

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0 ->
                    System.out.println("\nReturning to main menu.");
                case 1 -> {
                    System.out.printf("You selected to \033[34mchange the name of hotel '%s'\033[37m.\n", name);
                    System.out.printf("\nEnter new hotel name: ");

                    String oldName = hotel.getName();

                    String newName = sc.nextLine();

                    // FIXME: check if this "newName" is unique
                    hotel.setName(newName); // set the name to the new name

                    System.out.printf("\n\33[33mHotel '%s' has been successfully renamed to '%s'.\33[37m\n", oldName,
                            hotel.getName());
                }
                case 2 -> {
                    System.out.printf("You selected to \033[34madd a room\033[37m in hotel '%s'.\n", name);

                    System.out.printf("\nEnter number of rooms to create: ");
                    int num = sc.nextInt();
                    sc.nextLine();

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
                    System.out.printf("You selected to \033[34mdelete a room\033[37m in hotel '%s'.\n", name);
                    hotel.delRoomUI(sc);
                }
                case 4 -> {
                    System.out.printf("You selected to \033[34mchange the price\033[37m of hotel '%s'.\n", name);
                    hotel.changePriceUI(sc);
                }
                case 5 -> {
                    System.out.printf("You selected to \033[34mremove a reseravation\033[37m in hotel '%s'.\n", name);
                    hotel.removeReservationUI(sc);
                }
                case 6 -> {
                    System.out.printf("You selected to \033[34mdelete the hotel '%s'\033[37m.\n", name);
                    hotel.prepareForRemoval();
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
    public void simBookingUI(Scanner sc) {
        System.out.println("Welcome to the Hotel HotelReservationSystem.Reservation System, User!");
        System.out.print("Please enter your name: ");

        String guestName = sc.nextLine(); // look out for this baddie

        System.out.print("Enter the name of the hotel: ");
        String hotelName = sc.nextLine();

        Hotel hotel = fetchHotel(hotelName);

        if (hotel != null) {

            System.out.print("Enter the day of your check-in: ");
            int checkIn = sc.nextInt(); // if this auto-submits then add sc.nextLine(); after this (u jinxed it)
            sc.nextLine();

            System.out.print("Enter the day of your check-out: ");
            int checkOut = sc.nextInt();
            sc.nextLine();

            // checks if the booking dates are in bound
            if (checkOut > checkIn && (checkOut >= 2 && checkOut <= 31) && (checkIn >= 1 && checkIn <= 30)) {
                hotel.bookRoom(guestName, checkIn, checkOut);
            } else {
                System.out.printf("Invalid dates for booking.\n");
            }

        } else {
            System.out.printf("Hotel '%s' is not found.\n", hotelName);
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
        System.out.printf("\033[33m9\033[37m\n");
        System.out.printf("===================================\n");
    }
}