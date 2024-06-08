package src;
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
    private int roomTotal;
    private int roomCount;
    private Room[] rooms; // potential maximum of 50 rooms
    private double earnings;

    // Constructor
    public Hotel(String name, int roomTotal){
        this.name = name;
        this.roomTotal = roomTotal;
        this.roomCount = 0; // initialize current numner of rooms as 0
        this.rooms = new Room[50];
        this.earnings = 0; // initialize total earnings as 0
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setRoomTotal(int roomTotal){
        this.roomTotal = roomTotal;
    }

    public void setRoomCount(int roomCount){
        this.roomCount = roomCount;
    }

    public void setRooms(Room[] rooms){
        this.rooms = rooms;
    }

    public void setEarnings(double earnings){
        this.earnings = earnings;
    }

    // Getters
    public String getName(){
        return name;
    }

    public int getRoomTotal(){
        return roomTotal;
    }

    public int getRoomCount(){
        return roomCount;
    }

    public Room[] getRooms(){
        return rooms;
    }

    public double getEarnings(){
        return earnings;
    }

    // Methods

    /* create
     * creates a hotel with a name, specifies number of rooms and room names
     *
     * @params:
     *  - none
     *
     * @returns:
     *  - none
     *
     * @author: Zhean Ganituen
     */
    public void create(){
        // check if a room can still be created in the hotel
        if (roomTotal < 50){
            // make a unique room name
            String roomName = name + "_Room_" + roomCount;

            // create a new room with the new room name
            Room newRoom = new Room(roomName);

            // add the created room in the array of rooms
            rooms[roomCount] = newRoom;

            // increment the number of rooms
            roomCount++;
        }
    }

    /* manage
     * manages a hotel, allows the user to view information about the chosen hotel
     * shows the name, total number of rooms, earnings, available and booked rooms,
     * room names, price per night, availability, guest and room information, check-
     * in and -out dates, total price for the booking, and price breakdown per night.
     *
     * @params:
     *  -
     *
     * @returns:
     *  - none
     *
     * @author: Zhean Ganituen
     */
    public void view(){

    }
}