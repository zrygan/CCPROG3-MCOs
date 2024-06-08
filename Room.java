/**Room Class
 * An object that represents the rooms in a hotel.
 * 
 * Variables:
 *  - name      : String : the name of the room, must be different for each room
 *  - basePrice : int    : the base price of the room, always 1299.00 but can be changed using the manage hotel method 
 */
public class Room {
    // Variables
    private String name;
    private double basePrice;

    // Constructor
    public Room(String name){
        this.name = name;
        this.basePrice = 1299.0;
    }
}
