package src;
/* Room Class
 * An object that represents the rooms in a hotel.
 * 
 * @params:
 *  - name      : String  : the name of the room, must be different for each room
 *  - basePrice : int     : the base price of the room, always 1299.00 but can be changed using the manage hotel method
 *  - isBooked  : Boolean : determines if the room is currently booked 
 */
public class Room {
    // Variables
    private String name;
    private double basePrice;
    private boolean isBooked;

    // Constructor
    public Room(String name){
        this.name = name;
        this.basePrice = 1299.0;
        this.isBooked = false; // init the isBooked as false
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setBasePrice(double basePrice){
        this.basePrice = basePrice;
    }

    public void setIsBooked(boolean isBooked){
        this.isBooked = isBooked;
    }

    // Getters
    public String getName(){
        return name;
    }

    public double getBasePrice(){
        return basePrice;
    }

    public boolean getIsBooked(){
        return isBooked;
    }

    /* checkIn
     * function that sets the booking checker of the room to true
     * 
     * @params:
     *  - none
     * 
     * @author: Zhean Ganiuen
     */
    public void checkIn(){
        this.isBooked = true;
    }

    /* checkOut
     * function that sets the booking checker of the room to false
     * 
     * @params:
     *  - none
     * 
     * @author: Zhean Ganiuen
     */
    public void checkOut(){
        this.isBooked = false;
    }
}
