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
                case 1 -> { 
                    String hotelName = scanner.nextLine(); 
                    // We don't use: createHotel(hotelName); to create a hotel
                    Hotel hotel = new Hotel(hotelName);
                }
                case 2 -> { 
                    String hotelName = scanner.nextLine();
                    viewHotel(hotelName);
                }
                case 3 -> {
                    System.out.println("TODO"); // added placeholders
                }
                case 4 -> {
                    System.out.println("TODO"); // added placeholders
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
