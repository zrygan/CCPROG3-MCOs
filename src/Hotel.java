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
    private final String name;
    private int roomCount;
    private List<Room> rooms; // potential maximum of 50 rooms
    private List<Reservation> reservations; // list of reservations
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
    public void setReservation(List<Reservation> reservation) {
        this.reservations = reservation;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
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
    public void newRoom() {
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
        }
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
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter 1 to view high-level hotel information or 2 to view low-level hotel information: ");
            String level = scanner.nextLine();

            switch (level) {
                case "1" ->
                    System.out.printf("Hotel \"%s\" with %d rooms has earned PHP %.2f.\n", this.getName(), this.getRooms().size(), this.getEarnings());

                case "2" -> {
                    System.out.println("Enter 1 to view available/booked rooms for a selected date or 2 to view details of a specific room or reservation: ");

                    String option = scanner.nextLine();
                    

                    if ("1".equals(option)) {
                        System.out.print("Enter date (1-31): ");

                        String date = scanner.nextLine();
                        int dateInt = Integer.parseInt(date);

                        List<String> availableRooms = new ArrayList<>();
                        List<String> bookedRooms = new ArrayList<>();
                        
                        for (Room room : this.getRooms()) {
                            if (room.isAvailable(dateInt)) {
                                availableRooms.add(room.getName());
                            } else {
                                bookedRooms.add(room.getName());
                            }
                        }

                        System.out.println("Available Rooms on day " + date + ": " + availableRooms);
                        System.out.println("Booked Rooms on day " + date + ": " + bookedRooms);

                    } else if ("2".equals(option)) {
                        System.out.println("Enter room number: ");
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
        } catch (InputMismatchException e){
            System.out.println("ERROR. Invalid option, input mismatch. Please try again. ");
        } 
    }

    /* changePrice
     * changes the price of all rooms in the hotel, if and only if there are no reseravations
     * 
     * @params:
     *  - newPrice : double : the new price, constraint: newPrice >= 100
     * 
     * @returns:
     *  - none
     * 
     * @author: Zhean Ganituen
     */
    public void changePrice(double newPrice) {
        // check if a reservation is empty
        // if reseravation is empty (then no reseravation is made yet)
        // and if newPrice is greater than the minimum amount: 100
        if (!this.reservations.isEmpty() && newPrice >= 100) {
            // iterate through the rooms and set the price to newPrice
            for (Room room : this.rooms) {
                room.setBasePrice(newPrice);
            }
        }
    }
}
