/* Room Class
 * An object that represents the rooms in a hotel.
 * 
 * @params:
 *  - name         : String  : the name of the room, must be different for each room
 *  - basePrice    : int     : the base price of the room, always 1299.00 but can be changed using the manage hotel method
 *  - isBooked     : Boolean : determines if the room is currently booked
 *  - daysBooked   : int     : the number of days the room was booked
 *  - hotel        : Hotel   : the hotel of the room
 *  - availability : Boolean : the availability of the room per day
 */
public class Room {

    // Variables
    private final String name;
    private double basePrice;
    private boolean isBooked;
    private int daysBooked;
    private final Hotel hotel;
    private boolean[] availability;

    // Constructor
    public Room(String name, Hotel hotel) {
        this.name = name;
        this.basePrice = 1299.0;
        this.isBooked = false; // init the isBooked as false
        this.daysBooked = 0; // init as 0
        this.hotel = hotel;
        this.availability = new boolean[31];
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public void setDaysBooked(int daysBooked) {
        this.daysBooked = daysBooked;
    }

    public void setAvailability(boolean[] availability) {
        this.availability = availability;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public int getDaysBooked() {
        return daysBooked;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public boolean[] getAvailability() {
        return availability;
    }

    /* checkIn
     * function that sets the booking checker of the room to true
     * 
     * @params:
     *  - none
     * 
     * @author: Zhean Ganituen
     */
    public void checkIn() {
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
    public void checkOut() {
        this.isBooked = false;
    }

    /* bookLength
     * determines the days the room is booked and make it's availability for those days false
     * and increment the number of days the room is booked by the total book book length
     * 
     * @params:
     *  - checkin  : int : day the customer checks in
     *  - checkout : int : day the customer checks out
     * 
     * @author: Jaztin Jimenez
     */
    public void bookLength(int checkin, int checkout) {
        for (int i = checkin; i <= checkout; i++) {
            this.availability[i] = true;
        }

        this.daysBooked += checkout - checkin; // increment days booked with the total book length
    }

    /* isAvailable
     * a checker that determines if the room is a available for some day 
     * 
     * @params:
     *  - checkin  : int : day the customer checks in
     *  - checkout : int : day the customer checks out
     * 
     * @fixme: since we have this already, do we need getters and setters for availability?
     * 
     * @author: Jaztin Jimenez
     */
    public boolean isAvailable(int day) {
        return !availability[day - 1];
    }

}
