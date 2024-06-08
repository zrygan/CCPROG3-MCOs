/* Room Class
 * An object that represents the rooms in a hotel.
 * 
 * @params:
 *  - name       : String  : the name of the room, must be different for each room
 *  - basePrice  : int     : the base price of the room, always 1299.00 but can be changed using the manage hotel method
 *  - isBooked   : Boolean : determines if the room is currently booked
 *  - daysBooked : int     : the number of days the room was booked
 *  - hotel      : Hotel   : the hotel of the room
 * */
public class Room {
    // Variables
    private String name;
    private double basePrice;
    private boolean isBooked;
    private int daysBooked;
    private Hotel hotel;

    // Constructor
    public Room(String name, Hotel hotel){
        this.name = name;
        this.basePrice = 1299.0;
        this.isBooked = false; // init the isBooked as false
        this.daysBooked = 0; // init as 0
        this.hotel = hotel;
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

    public void setDaysBooked(int daysBooked){this.daysBooked = daysBooked;}

    public void setHotel(Hotel hotel){ this.hotel = hotel;}

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

    public int getDaysBooked(){ return daysBooked;}

    public Hotel getHotel(){ return hotel; }

    /* checkIn
     * function that sets the booking checker of the room to true
     * 
     * @params:
     *  - none
     * 
     * @author: Zhean Ganituen
     */
    public void checkIn(){
        this.isBooked = true;

        // increment daysBooked
        this.daysBooked++;

        // update earnings as the current earnings + basePrice of the room
        hotel.setEarnings(hotel.getEarnings() + basePrice);
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
