/* Reservation Class
 * An object for the reservation of a room
 *
 * @params:
 *  - guest     : final String : the guest name
 *  - checkin   : final int    : the check-in date (DD)
 *  - checkout  : final int    : the check-out date (DD)
 *  - total     : double       : the total cost of the booking
 *  - breakdown : double       : the breakdown of the cost, price per day
 *  - room      : final Room   : the room to be reserved
 */

public class Reservation {

    // Variables
    private final String guest;
    private final int checkin;
    private final int checkout;
    private double total;
    private double breakdown;
    private final Room room;

    // Constructor
    public Reservation(String guest, int checkin, int checkout, double total, double breakdown, Room room) {
        this.room = room;
        this.guest = guest;
        this.checkin = checkin;
        this.checkout = checkout;
        this.total = total;
        this.breakdown = breakdown;
    }

    // Setters

    public void setTotal(double total) {
        this.total = total;
    }

    public void setBreakdown(double breakdown) {
        this.breakdown = breakdown;
    }

    // Getters
    public String getGuest() {
        return guest;
    }

    public int getCheckin() {
        return checkin;
    }

    public int getCheckout() {
        return checkout;
    }

    public double getTotal() {
        return total;
    }

    public double getBreakdown() {
        return breakdown;
    }

    public Room getRoom() {
        return room;
    }

    /* isRoomAvailable
     * to check if the room is available for the day
     * 
     * @params:
     *  - room        : Room : the room to check the avaulability
     *  - checkInDay  : int  : the day of check in, 1 < checkInDay > 31 (can't check in on day 31)
     *  - checkOutDay : int  : the day of check out, 1 < checkOutDay > 31 (can't check out on day 1)
     * 
     * @returns:
     *  - false : Boolean : if room is not available
     *  - true  : Boolean : if room is available
     * 
     * @author: Jaztin Jimenez 
     */
    public boolean isRoomAvailable(Room room, int checkInDay, int checkOutDay) {
        for (int i = checkInDay; i < checkOutDay; i++) {
            if (!room.isAvailable(i)) {
                return false;
            }
        }
        return true;
    }
}
