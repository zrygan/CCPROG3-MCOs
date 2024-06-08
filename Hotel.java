/* Hotel Class
 * An object for the hotels in the system 
 * 
 * @params:
 *  - name         : String        : the name of the hotel
 *  - roomCount    : int           : the actual total number of rooms in the hotel
 *  - rooms[]      : Room          : the rooms in the hotel, an array of Room objects, maximum of 50
 *  - earnings     : double        : the total earnings of the hotel through room bookings
 */
public class Hotel{
    // Variables
    private String name;
    private int roomCount;
    private Room[] rooms = new Room[50]; // potential maximum of 50 rooms
    private double earnings;
    /*  TODO: availability
        make a way that we can set the availability so that if we
        availability[1].booked this returns the booked rooms for
        day 1. So it's like a Array struct in C
     */

    /*  TODO: reservation
        also make a Array struct for this.
        reservation[1] is a reservation and we can get other info
        by
            .guest      : guest information of the reservation
            .in         : check-in information
            .out        : check-out information
            .price      : price information
            .breakdown  : price breakdown (price per night)
     */

    // Constructor
    public Room(String name, int roomCount, Room[] rooms, double earnings){
        this.name = name;
        this.roomCount = roomCount;
        this.rooms = rooms;
        this.earnings = earnings;
    }

    // Setters
    public void setName(String name){
        this.name = name;
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
     *  -
     *
     * @returns:
     *  - none
     *
     * @author: Zhean Ganituen
     */
    public void create(){

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