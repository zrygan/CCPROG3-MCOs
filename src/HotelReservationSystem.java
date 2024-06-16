/* HotelReservationSystem (HRS) Class
 * An object that handles the reservation system.
 * 
 * @params:
 *  - hotel : List<Hotel> : the list of hotels in the system
 */

import java.util.*;

public class HotelReservationSystem {

    // Variables
    private List<Hotel> hotels;

    // Constructor
    public HotelReservationSystem() {
        this.hotels = new ArrayList<>();
    }

    // Setters
    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    // Getters
    public List<Hotel> getHotels() {
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
     * @fixme:
     *  - add a way to check if the name is unique or not
     * 
     * @author: Zhean Ganituen
     */
    public void createHotel(String name) {
        Hotel hotel = new Hotel(name);
        hotels.add(hotel);
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

    public void createHotelUI() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the name of the hotel: ");
            String hotelName = scanner.nextLine();

            createHotel(hotelName); // Directly call the instance method createHotel
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, input mismatch. Please try again.");
        }
    }

    public void viewHotelUI() {
        /*
        display the following
        1. [X] high level information
            - name
            - number of rooms
            - estimate earnings
        2. [ ] total number of booked rooms for a specific date
        3. [ ] information about a room
            - room name
            - price per night
            - availability accress the month
        4. [ ] information about reservation
            - guest and room info
            - check-in and check-out dates
            - total price of the booking
            - breakdown  
         */

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the name of the hotel: ");
            String hotelName = scanner.nextLine();
            Hotel hotel = fetchHotel(hotelName);
            if (hotel != null) {
                hotel.viewHotel();
            } else {
                System.out.printf("Hotel \"%s\" is not found\n", hotelName);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, input mismatch. Please try again.");
        }
    }

    public void showMenu() {
        System.out.println("1. Create Hotel");
        System.out.println("2. View Hotel");
        System.out.println("3. Manage Hotel");
        System.out.println("4. Simulate Booking");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    // game loop for HRS
    public static void main(String[] args) {
        HotelReservationSystem HRS = new HotelReservationSystem();
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        // Ganituen Debugging 11/06/2024 @ 01.23
        HRS.createHotel("max");
        Hotel max = HRS.fetchHotel("max");
        max.newRoom();
        max.newRoom(); // so we can expect we have 2 rooms in Hotel "max"
        max.getRooms().get(0).checkIn(); // check in one of the rooms
        // so now we can also expect some earnings

        HRS.showMenu();

        while (run) {
            if (scanner.hasNextInt()) {
                String option = scanner.nextLine();
                scanner.nextLine(); // Consume newline character

                switch (option) {
                    case "1" -> HRS.createHotelUI();
                    case "2" -> HRS.viewHotelUI();
                    case "3" -> System.out.println("TODO");
                    case "4" -> System.out.println("TODO");
                    case "5" -> {
                        System.out.println("Exiting...");
                        run = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid option. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        scanner.close(); // Close scanner when done
    }
}
