import java.util.*;
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
public class Hotel{
    // Variables
    private String name;
    private int roomCount;
    private List<Room> rooms; // potential maximum of 50 rooms
    private List<Reservation> reservations;
    private double earnings;

    // Constructor
    public Hotel(String name){
        this.name = name;
        this.roomCount = 0; // initialize current numner of rooms as 0
        this.earnings = 0; // initialize total earnings as 0
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setRoomCount(int roomCount){
        this.roomCount = roomCount;
    }

    public void setRooms(List<Room> rooms){
        this.rooms = rooms;
    }

    public void setEarnings(double earnings){
        this.earnings = earnings;
    }

    // Getters
    public String getName(){
        return name;
    }

    public int getRoomCount(){
        return roomCount;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public double getEarnings(){ return earnings; }

    // Methods

    /* newRoom
     * creates a new room in the hotel, if possible
     * 
     * @params:
     *  - none
     * 
     * @returns:
     *  - boolean
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
}