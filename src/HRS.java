/* HotelReservationSystem (HRS) Class
 * An object that handles the reservation system.
 * 
 * @params:
 *  - hotel : ArrayList<Hotel> : the list of hotels in the system
 */

import java.util.*;

public class HRS {

    // Variables
    private ArrayList<Hotel> hotels;

    // Constructor
    public HRS() {
        this.hotels = new ArrayList<>();
    }

    // Setters
    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    // Getters
    public ArrayList<Hotel> getHotels() {
        return this.hotels;
    }

    // Methods
    /* createHotel
     * creates a new hotel with a given name
     * 
     * @params:
     *  - name : String : the name of the new hotel
     * 
     * @returns: 
     *  - none
     * 
     * 
     * @author: Zhean Ganituen
     */
    public void createHotel(String name) {
        Hotel hotel = new Hotel(name);
        hotels.add(hotel);
        hotel.newRoom();
    }

    /* fetchHotel
     * returnss the hotel given the name of the hotel
     * 
     * @params:
     *  - name : String : name of the hotel
     * 
     * @returns:
     *  - room : Hotel : the hotel with the room name in the hotel
     *  - null : null  : the hotel was not found
     * 
     * @author: Zhean Ganituen
     */
    public Hotel fetchHotel(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                return hotel;
            }
        }

        return null;
    }

    /* createHotelUI
     * User I/O for creating a hotel
     * 
     * @params:
     *  - sc : Scanner : Scanner object
     * 
     * @returns:
     *  - none
     * 
     * @author:
     *  - Zhean Ganituen
     */
    public void createHotelUI(Scanner sc) {
        System.out.printf("You selected to \033[34mcreate\033[37m a hotel!\n");
        
        System.out.printf("\nEnter the name of the hotel: ");
        String hotelName = sc.nextLine();

        if (fetchHotel(hotelName) != null) {
            System.out.printf("\n\033[31mError. Sorry! But that hotel name '%s' already exists.\033[37m\n", hotelName);
        } else {
            createHotel(hotelName);
            System.out.printf("\n\033[34mHotel with then name '%s' created successfully.\033[37m\n", hotelName);
        }            
            
    }

    /* viewHotelUI
     * User I/O for viewing the details of a hotel
     * 
     * @params: 
     *  - sc : Scanner : Scanner object
     * 
     * @returns:
     *  - none
     * 
     * @author: Zhean Ganituen
     */
    public void viewHotelUI(Scanner sc) {
        System.out.printf("You selected to \033[34mview\033[37m a hotel!\n");
        
        System.out.printf("\nEnter the name of the hotel: ");
        String hotelName = sc.nextLine();

        Hotel hotel = fetchHotel(hotelName);
        if (hotel != null) {
            hotel.viewHotel(sc);
        } else {
            System.out.printf("\n\033[31mError. Sorry! But that hotel name '%s' is not found in the database.\033[37m\n", hotelName);
        }
    }

    /* manageHotelUI
     * User I/O for managing a hotel
     * 
     * @params:
     *  - sc : Scanner : Scanner object
     * 
     * @returns:
     *  - none
     * 
     * @author: Zhean Ganituen
     */
    public void manageHotelUI(Scanner sc) {
        System.out.print("Enter the name of the hotel: ");
        String name = sc.nextLine();
        Hotel hotel = fetchHotel(name);
        if (hotel != null) {
            System.out.println("1\t:\t to change the name of the hotel");
            System.out.println("2\t:\t to add a new room");
            System.out.println("3\t:\t remove a room");
            System.out.println("4\t:\t update the base price");
            System.out.println("5\t:\t remove a reservation");
            System.out.println("6\t:\t remove a hotel");

            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 ->
                    hotel.changeHotelName(sc);
                case 2 ->
                    hotel.addRoom(sc);
                case 3 ->
                    hotel.delRoomUI(sc);
                case 4 ->
                    hotel.changePriceUI(sc);
                case 5 ->
                    hotel.removeReservationUI(sc);
                case 6 -> {
                    hotel.prepareForRemoval();
                    hotels.remove(hotel);
                    System.out.println("Hotel removed successfully.");
                    return; // Exit after removal
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.printf("Hotel '%s' is not found.\n", name);
        }
    }

    /* simBookingUI
     * User I/O for booking simulation
     * 
     * @params:
     *  - sc : Scanner : Scanner object
     * 
     * @returns:
     *  - none
     * 
     * @authors: Zhean Ganituen, Jaztin Jimenez
     */
    public void simBookingUI(Scanner sc) {
        System.out.println("Welcome to the Hotel Reservation System, User!");
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

            // checks if the booking dates are in bound
            if (checkOut > checkIn && (checkOut >= 2 && checkOut <= 31) && (checkIn >= 1 && checkIn <= 30)) {
                hotel.bookRoom(guestName, checkIn, checkOut);
            }
            else {
                System.out.printf("Invalid dates for booking.\n");
            }

            
        } else {
            System.out.printf("Hotel '%s' is not found.\n", hotelName);
        }
    }

    /* showMenu
     * Shows the main menu of the program
     * 
     * @params:
     *  - none
     * 
     * @returns:
     *  - none
     * 
     * @author: Zhean Ganituen
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

    /* showHotels
     * Lists the hotels with their number of rooms
     * 
     * @params:
     *  - none
     * 
     * @returns:
     *  - none
     * 
     * @author: Zhean Ganituen
     */
    public void showHotels() {
        int i = 1;

        for (Hotel hotel : hotels) {
            System.out.printf("%d\t'%s' has %d rooms.\n", i, hotel.getName(), hotel.getRoomCount());
            i++;
        }
    }
}
