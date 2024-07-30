package HotelReservationSystem;

/**
 * This class represents an object Reservation of a room for the system
 */
public class Reservation {

    // Variables
    private final String guest;
    private final int checkin;
    private final int checkout;
    private final double total;
    private final Room room;
    private final int reservationNumber;

    /**
     * Constructor for the Reservation class
     *
     * @param guest             the guest name
     * @param checkin           the day of check in
     * @param checkout          the day of check out
     * @param room              the room of the reservation
     * @param reservationNumber the number of the reservation
     * @param total             the total cost of the reservation
     */
    public Reservation(String guest, int checkin, int checkout, Room room, int reservationNumber, double total) {
        this.room = room;
        this.guest = guest;
        this.checkin = checkin;
        this.checkout = checkout;
        this.total = total;
        this.reservationNumber = reservationNumber;
    }

    /**
     * Getter for the guest name
     *
     * @return the guest name of the reservation
     */
    public String getGuest() {
        return guest;
    }

    /**
     * Getter for the check in day
     *
     * @return the check in day of the reservation
     */
    public int getCheckin() {
        return checkin;
    }

    /**
     * Getter for the check out day
     *
     * @return the check out day of the reservation
     */
    public int getCheckout() {
        return checkout;
    }

    /**
     * Getter for the room of the reservation
     *
     * @return the room of the reservation
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Getter for the total cost of the reservation
     *
     * @return the total cost
     */
    public double getTotal() {
        return total;
    }

    /**
     * Getter for the total cost of the reservation
     *
     * @return the reservation number
     */
    public int getReservationNumber() {
        return reservationNumber;
    }
}

