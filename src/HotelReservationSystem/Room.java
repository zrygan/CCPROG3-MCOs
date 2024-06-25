package HotelReservationSystem;

import java.util.Arrays;

/**
 * A class that represents an object that represents the rooms in a hotel.
 */
public class Room {

    // Variables
    private String name;
    private double basePrice;
    private int daysBooked;
    private final Hotel hotel;
    private boolean[] availability;
    private Reservation reservation;

    /**
     * Constructor for the Room object
     *
     * @param name the name of the room, must be different for each room
     * @param hotel the hotel of the room
     * @param basePrice the base price of the hotel the room is part of
     */
    public Room(String name, Hotel hotel, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.daysBooked = 0; // init as 0
        this.hotel = hotel;
        this.availability = new boolean[31];
        Arrays.fill(this.availability, Boolean.FALSE);
    }

    /**
     * Setter for the base price
     *
     * @param basePrice the base price of the room, always 1299.00 but can be
     * changed using the manage hotel method
     *
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Setter for the room name
     *
     * @param name the name of the room
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the days booked
     *
     * @param daysBooked the number of days the room was booked
     */
    public void setDaysBooked(int daysBooked) {
        this.daysBooked = daysBooked;
    }

    /**
     * Setter for the availability of the Room
     *
     * @param availability the availability of the room per day
     */
    public void setAvailability(boolean[] availability) {
        this.availability = availability;
    }

    /**
     * Setter for the reservation of the hotel
     *
     * @param reservation the reservation for the hotel
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Getter for the name of the room
     *
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the base price of the room
     *
     * @return the base price of the room
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Getter for the days booked in the room
     *
     * @return the amount of days booked in the room
     */
    public int getDaysBooked() {
        return daysBooked;
    }

    /**
     * Getter for the reservation assigned to the room
     *
     * @return the reservation of the room
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Getter for the hotel of the room
     *
     * @return the hotel of the room
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * determines the days the room is booked and make it's availability for
     * those days true and increment the number of days the room is booked by
     * the total book book length
     *
     * @param checkin day the customer checks in
     * @param checkout day the customer checks out
     *
     * @author Jaztin Jimenez
     */
    public void addBookRoom(int checkin, int checkout) {
        for (int i = checkin; i <= checkout - 1; i++) {
            this.availability[i - 1] = true;
        }

        this.daysBooked += checkout - checkin + 1; // increment days booked with the total book length
    }

    /**
     * removes the availability of the room and decrements the number of days
     * the room is booked
     *
     * @param checkin day the customer checks in
     * @param checkout day the customer checks out
     *
     * @author Jaztin Jimenez
     */
    public void remBookRoom(int checkin, int checkout) {
        for (int i = checkin; i <= checkout - 1; i++) {
            this.availability[i - 1] = false;
        }

        this.daysBooked -= checkout - checkin + 1; // increment days booked with the total book length
    }

    /**
     * a checker that determines if the room is a available for a range
     *
     * @param checkin day the customer checks in
     * @param checkout day the customer checks out
     *
     * @return {true} if the room is available, {false} if otherwise
     *
     * @author Jaztin Jimenez
     */
    public boolean isAvailable(int checkin, int checkout) {
        for (int i = checkin; i <= checkout; i++) {
            if (this.availability[i - 1]) {
                return false;
            }
        }

        return true; // assume true
    }

    /**
     * a checker that determines if the room is a available for some day
     *
     * @param day a specific day
     *
     * @return the availability of the day
     *
     * @author Jaztin Jimenez
     */
    public boolean isAvailable(int day) {
        return !this.availability[day - 1];
    }
}
