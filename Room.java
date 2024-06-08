/* Room Class
 * An object that represents the rooms in a hotel.
 * 
 * @params:
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

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setBasePrice(double basePrice){
        this.basePrice = basePrice;
    }

    // Getters
    public String getName(){
        return name;
    }

    public double getBasePrice(){
        return basePrice;
    }
}
