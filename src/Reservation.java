/* Reservation Class
 * An object for the reservation of a room
 *
 * @params:
 *  - guest     : final String : the guest name
 *  - checkin   : final int    : the check-in date (DD)
 *  - checkout  : final int    : the check-out date (DD)
 *  - total     : double       : the total cost of the booking
 *  - room      : final Room   : the room to be reserved
 */

public class Reservation {

    // Variables
    private final String guest;
    private final int checkin;
    private final int checkout;
    private final double total;
    private final Room room;
    // the breakdown of the reservation will be calculated later as total / (checkin
    // - checkout)

    // Constructor
    public Reservation(String guest, int checkin, int checkout, Room room) {
        this.room = room;
        this.guest = guest;
        this.checkin = checkin;
        this.checkout = checkout;
        this.total = room.getBasePrice() * (checkout - checkin); // calculate total
    }

    // Setters
    // no setters since all are FINAL

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

    public Room getRoom() {
        return room;
    }

    /*
     * isRoomAvailable
     * to check if the room is available for the day
     * 
     * @params:
     * - room : Room : the room to check the avaulability
     * - checkInDay : int : the day of check in, 1 < checkInDay > 31 (can't check in
     * on day 31)
     * - checkOutDay : int : the day of check out, 1 < checkOutDay > 31 (can't check
     * out on day 1)
     * 
     * @returns:
     * - false : Boolean : if room is not available
     * - true : Boolean : if room is available
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

    /*
     * breakdown
     * gets the total costs of booking the reservation
     * 
     * @params:
     * - none
     * 
     * @return:
     * - cost per day : double
     */
    public double breakdown() {
        return total / (
            - checkin);
    }
}
