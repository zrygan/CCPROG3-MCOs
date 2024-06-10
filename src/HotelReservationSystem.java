import java.util.*;

public class HotelReservationSystem {
    private List<Hotel> hotels;

    public void createHotel(String name) {

    }
    
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Hotel");
            System.out.println("2. View Hotel");
            System.out.println("3. Manage Hotel");
            System.out.println("4. Simulate Booking");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1 -> { String hotelName = scanner.nextLine(); 
                    createHotel(hotelName);

                }
                case 2 -> { String hotelName = scanner.nextLine();
                    viewHotel(hotelName);
                }
                case 3 -> 
                case 4 -> 
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /* viewHotel
     * views the hotel information, checks either high-level information or low-level information from
     * the hotel.
     * 
     * @params:
     *  - none
     *
     * @returns:
     *  - none
     *
     * @author: Zhean Ganituen and Jaztin Jimenez
     */
    public void viewHotel(String name) {
        Scanner scanner = new Scanner(System.in);
        Hotel infoHotel = null;
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                infoHotel = hotel;
                break;
            }
            
        }

        if (infoHotel != null) { // checks if the hotel exists

            System.out.println("Enter 1 to view high-level hotel information or 2 to view low-level hotel information: ");
            int level = scanner.nextInt();
            
            switch (level) {
                case 1 -> System.out.printf("Hotel \"%s\" with %d rooms has earned PHP %.2f", infoHotel.getName(), infoHotel.getRooms().size(), infoHotel.getEarnings());
                case 2 -> {
                    System.out.print("Enter 1 to view available/booked rooms for a selected date or 2 to view details of a specific room or reservation: ");
                    int option = scanner.nextInt();
                    if (option == 1) {
                        System.out.print("Enter date (1-31): ");
                        int date = scanner.nextInt();
                            List<String> availableRooms = new ArrayList<>();
                            List<String> bookedRooms = new ArrayList<>();
                            for (Room room : infoHotel.getRooms()) {
                                if (room.isAvailable(date)) {
                                    availableRooms.add(room.getName());
                                } else {
                                    bookedRooms.add(room.getName());
                                }
                            }
                            System.out.println("Available Rooms on day " + date + ": " + availableRooms);
                            System.out.println("Booked Rooms on day " + date + ": " + bookedRooms);
                        } 
                    else if (option == 2) {
                        System.out.print("Enter room number: ");
                        int roomNumber = scanner.nextInt();
                            if (0 <= roomNumber && roomNumber < infoHotel.getRoomCount())   {
                                Room room = infoHotel.getRooms(roomNumber);
                // calculate availability

                int availability = 31 - room.getDaysBooked();

                System.out.printf("The Room \"%s\" in Hotel \"%s\" costs %.2f per night and is available for %d days of the month.\n", room.getName(), name, room.getBasePrice(), availability);

                    }
                }
                default -> System.out.println("Hotel not found.");
            }
        }
        else {
            System.out.println("Hotel not found.");
            }
    }

            

            
}
