/* Hotel Class
 * An object for the hotels in the system 
 * 
 * @params:
 *  - name         : String        : the name of the hotel
 *  - roomTotal    : int           : the current number of rooms in the hotel
 *  - roomCount    : int           : the actual total number of rooms in the hotel
 *  - rooms[]      : Room          : the rooms in the hotel, an array of Room objects, maximum of 50
 *  - earnings     : double        : the total earnings of the hotel through room bookings
 */

import java.util.*;

public class Hotel {

    // Variables
    private String name;
    private int roomCount;
    private List<Room> rooms; // potential maximum of 50 rooms
    private List<Reservation> reservations;
    private double earnings;

    // Constructor
    public Hotel(String name) {
        this.name = name;
        this.roomCount = 0; // initialize current numner of rooms as 0
        this.earnings = 0; // initialize total earnings as 0
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Setters
    // Since the name and rooms cannot be changed, don't include it here
    public void setReservation(Reservation reservation){
        this.reservations = reservation;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public double getEarnings() {
        return earnings;
    }

    // Methods

    /* newRoom
     * creates a new room in the hotel, if possible
     * 
     * @params:
     *  - none
     * 
     * @returns:
     *  - true  : boolen  : if a new room was created
     *  - false : boolean : if not 
     * 
     * @author: Zhean Ganituen
     */
    public boolean newRoom() {
        // check if a room can still be created in the hotel
        if (roomCount < 50) {
            // make a unique room name
            String roomName = name + "_Room_" + roomCount;

            // create a new room with the new room name
            Room newRoom = new Room(roomName, this); // WHAT WILL BE THE HOTEL PARAMETER HERE?
            // this

            // add the created room in the array of rooms
            rooms.add(newRoom);

            // increment the number of rooms
            roomCount++;

            return true;
        }

        return false;
    }

    /* fetchRoom
     * returns the room given the name of the room of a hotelName
     * 
     * @params:
     *  - name : String : name of the room
     * 
     * @returns:
     *  - room : Room : the room with the room name in the hotel
     *  - null : null : the room was not found
     * 
     * @author: Zhean Ganituen
     */
    public Room fetchRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }

        return null; // room not found
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
    public void viewHotel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to view high-level hotel information or 2 to view low-level hotel information: ");
        int level = scanner.nextInt();

        switch (level) {
            case 1 ->
                System.out.printf("Hotel \"%s\" with %d rooms has earned PHP %.2f", this.getName(), this.getRooms().size(), this.getEarnings());
            case 2 -> {
                System.out.print("Enter 1 to view available/booked rooms for a selected date or 2 to view details of a specific room or reservation: ");
                int option = scanner.nextInt();
                if (option == 1) {
                    System.out.print("Enter date (1-31): ");
                    int date = scanner.nextInt();
                    List<String> availableRooms = new ArrayList<>();
                    List<String> bookedRooms = new ArrayList<>();
                    for (Room room : this.getRooms()) {
                        if (room.isAvailable(date)) {
                            availableRooms.add(room.getName());
                        } else {
                            bookedRooms.add(room.getName());
                        }
                    }
                    System.out.println("Available Rooms on day " + date + ": " + availableRooms);
                    System.out.println("Booked Rooms on day " + date + ": " + bookedRooms);
                } else if (option == 2) {
                    System.out.print("Enter room number: ");
                    String roomName = scanner.nextLine(); // changed this to stirng because room names are Strings not int
                    // get room
                    Room roomQuery = this.fetchRoom(roomName);
                    if (roomQuery != null) {
                        // calculate availability
                        int availability = 31 - roomQuery.getDaysBooked();

                        System.out.printf("The Room \"%s\" in Hotel \"%s\" costs %.2f per night and is available for %d days of the month.\n", roomQuery.getName(), name, roomQuery.getBasePrice(), availability);
                    } else {
                        System.out.println("The room \"%s\" in Hotel \"%s\" does not exist.");
                    }
                }
            }
        }
        scanner.close(); // remember to close scanner
    }
}
