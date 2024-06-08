/**Hotel Class
 * An object for the hotels in the system 
 * 
 * Variables:
 *  - name    : String : the name of the hotel
 *  - rooms[] : Room   : the rooms in the hotel, an array of Room objects, maximum of 50
 */
public class Hotel{
    // Variables
    private String name;
    private Room[] rooms = new Room[50]; // maximum of 50 rooms

    // Constructor
    public Room(String name, Room[] rooms){
        this.name = name;
        this.rooms = rooms;
    }
}