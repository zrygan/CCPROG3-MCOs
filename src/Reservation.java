/* Reservation Class
 * An object for the reservation of a room
 *
 * @params:
 *  - guest     : String : the guest name
 *  - checkin   : int    : the check-in date (DD)
 *  - checkout  : int    : the check-out date (DD)
 *  - total     : double : the total cost of the booking
 *  - breakdown : double : the breakdown of the cost, price per day
 */
public class Reservation {

    // Variables
    private String guest;
    private int checkin;
    private int checkout;
    private double total;
    private double breakdown;
    private Room room;

    // Constructor
    public Reservation(String guest, int checkin, int checkout, double total, double breakdown) {
        this.guest = guest;
        this.checkin = checkin;
        this.checkout = checkout;
        this.total = total;
        this.breakdown = breakdown;
        room.bookLength(checkin, checkout);
    }

    // Setters
    public void setGuest(String guest) {
        this.guest = guest;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(int checkout) {
        this.checkout = checkout;
    }

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

    public boolean isRoomAvailable(Room room, int checkInDay, int checkOutDay) {
        for (int i = checkInDay; i < checkOutDay; i++) {
            if (!room.isAvailable(i)) {
                return false;
            }
        }
        return true;
    }
}
