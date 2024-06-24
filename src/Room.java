/* Room Class
 * An object that represents the rooms in a hotel.
 * 
 * @params:
 *  - name         : String      : the name of the room, must be different for each room
 *  - basePrice    : int         : the base price of the room, always 1299.00 but can be changed using the manage hotel method
 *  - daysBooked   : int         : the number of days the room was booked
 *  - hotel        : Hotel       : the hotel of the room
 *  - availability : Boolean     : the availability of the room per day
 *  - reservation  : Reservation : the reservation for the hotel
 */

import java.util.Arrays;

public class Room {

    // Variables
    private final String name;
    private double basePrice;
    private int daysBooked;
    private final Hotel hotel;
    private boolean[] availability;
    private Reservation reservation;

    // Constructor
    public Room(String name, Hotel hotel) {
        this.name = name;
        this.basePrice = 1299.0;
        this.daysBooked = 0; // init as 0
        this.hotel = hotel;
        this.availability = new boolean[31];
        Arrays.fill(this.availability, Boolean.FALSE);
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setDaysBooked(int daysBooked) {
        this.daysBooked = daysBooked;
    }

    public void setAvailability(boolean[] availability) {
        this.availability = availability;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
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

    public Reservation reservation() {
        return reservation;
    }

    /*
     * bookLength
     * determines the days the room is booked and make it's availability for those
     * days false
     * and increment the number of days the room is booked by the total book book
     * length
     * 
     * @params:
     * - checkin : int : day the customer checks in
     * - checkout : int : day the customer checks out
     * 
     * @author: Jaztin Jimenez
     */
    public void bookLength(int checkin, int checkout) {
        for (int i = checkin; i <= checkout - 1; i++) {
            this.availability[i - 1] = true;
        }

        this.daysBooked += checkout - checkin + 1; // increment days booked with the total book length
    }

    // FIXME: CODE DOCS
    public void removeAvailability(int checkin, int checkout) {
        for (int i = checkin; i <= checkout - 1; i++) {
            this.availability[i - 1] = false;
        }

        this.daysBooked -= checkout - checkin + 1; // increment days booked with the total book length
    }

    /*
     * isAvailable
     * a checker that determines if the room is a available for a range
     * 
     * @params:
     * - checkin : int : day the customer checks in
     * - checkout : int : day the customer checks out
     * 
     * @author: Jaztin Jimenez
     */
    public boolean isAvailable(int checkIn, int checkOut) {
        for (int i = checkIn; i <= checkOut; i++) {
            if (!this.availability[i - 1]) {
                return false;
            }
        }

        return true; // assume true
    }

    /*
     * isAvailable
     * a checker that determines if the room is a available for some day
     * 
     * @params:
     * - day : int : a specific day
     * 
     * 
     * @author: Jaztin Jimenez
     */
    public boolean isAvailable(int day) {
        return !this.availability[day - 1];
    }
}
