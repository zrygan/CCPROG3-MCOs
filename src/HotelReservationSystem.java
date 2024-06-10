import java.util.*;

public class HotelReservationSystem {
    
    // Variables
    private List<Hotel> hotels;

    // Constructor
    public HotelReservationSystem(){
        this.hotels = new ArrayList<>();
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
     * @author: Zhean Ganituen
     */
    public void createHotel(String name){
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
    public Hotel fetchHotel(String name){
        for(Hotel hotel : hotels){
            if(hotel.getName().equals(name)){
                return hotel;
            }
        }

        return null;
    }

    public static void main (String[] args) {
        HotelReservationSystem HRS = new HotelReservationSystem(); // create instance of the HRS

        Scanner scanner = new Scanner(System.in); // make scanner
        Boolean run = true; // game loop for HRS

        // Ganituen Debugging 11/06/2024 @ 01.23
        /*
        HRS.createHotel("max");
        Hotel max = HRS.fetchHotel("max");
        max.newRoom();
        max.newRoom(); // so we can expect we have 2 rooms in Hotel "max"
        max.getRooms().get(0).checkIn(); // check in one of the rooms
                                           // so now we can also expect some earnings
        */

        while (run) {
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
                    System.out.print("Enter the name of the hotel: ");
                    String hotelName = scanner.nextLine(); 
                    // We don't use: createHotel(hotelName); to create a hotel
                    HRS.createHotel(hotelName); 
                    break;
                }
                case 2 -> { 
                    System.out.print("Enter the name of the hotel: ");
                    String hotelName = scanner.nextLine();
                    Hotel hotel = HRS.fetchHotel(hotelName);
                    if (hotel != null) {
                        hotel.viewHotel();
                    }
                    else {
                        System.out.printf("Hotel \"%s\" is not found\n", hotelName);
                    }
                }
                case 3 -> {
                    System.out.println("TODO"); // added placeholders
                    break;
                }
                case 4 -> {
                    System.out.println("TODO"); // added placeholders
                    break;
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    run = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
