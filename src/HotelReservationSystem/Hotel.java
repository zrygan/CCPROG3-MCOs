package HotelReservationSystem;

import java.util.*;

/**
 * This class represents a Hotel object in the system
 */
public class Hotel {

    // Variables
    private String name;
    private int roomCount;
    private int reservationCount;
    private ArrayList<Room> rooms; // potential maximum of 50 rooms
    private ArrayList<Reservation> reservations; // list of reservations
    private double earnings;
    private double basePrice;

    /**
     * Constructor for the Hotel object
     *
     * @param name Name of the Hotel
     */
    public Hotel(String name) {
        this.roomCount = 1; // initialize at 1 because we want to start at room_1 not room_0
        this.reservationCount = 0;
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.earnings = 0.0; // initialize as 0
        this.basePrice = 1299.0;

    }

    /**
     * Getter for the base price of the Hotel
     *
     * @return base price of the Hotel
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price of the Hotel
     *
     * @param basePrice base price of the hotel
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Getter for the name of the Hotel
     *
     * @return The name of the Hotel
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Hotel
     *
     * @param name The name of the Hotel
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the ArrayList of reservation objects in the Hotel
     * 
     * @return the ArrayList of Reservation objects 
     */
    public ArrayList<Reservation> getReservations(){
        return reservations;
    }

    /**
     * Sets the reservation of the Hotel
     *
     * @param reservation The reservation in the Hotel
     */
    public void setReservations(ArrayList<Reservation> reservation) {
        this.reservations = reservation;
    }

    /**
     * Getter for the Arraylist of Room objects in the Hotel
     *
     * @return the ArrayList of Room objects
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Sets the rooms in the Hotel
     *
     * @param rooms The rooms in the Hotel
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Getter for the earnings of the Hotel
     *
     * @return The earnings of the Hotel
     */
    public double getEarnings() {
        return earnings;
    }

    /**
     * Sets the earnings of the Hotel
     *
     * @param earnings The earnings of the Hotel
     */
    public void setEarnings(double earnings) {
        this.earnings += earnings;
    }

    /**
     * Getter for the number of rooms in the Hotel
     *
     * @return The number of rooms in the Hotel
     */
    public int getRoomCount() {
        return roomCount - 1; // -1 because we start at 1 not 0
    }

    /**
     * Setter for the number of rooms in the Hotel
     * 
     * @param roomCount The number of rooms in the hotel
     */
    public void setRoomCount(int roomCount){
        this.roomCount = roomCount;
    }

     /**
     * Getter for the number of reservations in the Hotel
     *
     * @return The number of reservations in the Hotel
     */
    public int getReservationCount() {
        return reservationCount;
    }

    /**
     * Setter for the number of reservations in the Hotel
     * 
     * @param reservationCount The number of reservations in the hotel
     */
    public void setReservationCount(int reservationCount){
        this.reservationCount = reservationCount;
    }

    /**
     * creates a new room in the hotel, if possible
     *
     * @param type the type of room
     * 
     * @return {Room} if a new room is created, {null} if otherwise
     *
     * @author Zhean Ganituen
     */
    public Room newRoom(int type) {
        for (int roomNumber = 1; roomNumber < 51; roomNumber++) {
            String roomName = name + "_Room_" + roomNumber;
            // the first room that doesnt exist yet make it
            if (fetchRoom(roomName) == null) {
                Room newRoom = new Room(roomName, this, this.basePrice, type);
                rooms.add(newRoom);
                roomCount++;
                return newRoom;
            }
        }

        return null;
    }

    /**
     * Books a room and makes a reservation, if possible
     *
     * @param guestName the guests name
     * @param checkin date of checking in
     * @param checkout date of checking out
     * @param type the type of room the user wants to book
     *
     * @return {true} if room booking is successful, {false} if room booking is
     * not successful
     *
     * @author Zhean Ganituen, Jaztin Jimenez
     */
    public boolean bookRoom(String guestName, int checkin, int checkout, int type) {
        // iterate through all the rooms in hotel
        for (Room room : this.rooms) {
            // look for a room that is available for the entire duration of the reservation
            if (room.isAvailable(checkin, checkout) && room.getType() == type) {
                // add reservation
                int reservationNumber = this.getReservationCount() + 1;
                double total = room.calcPrice(checkin, checkout);

                Reservation newReservation = new Reservation(guestName, checkin, checkout, room, reservationNumber, total);

                this.reservations.add(newReservation);

                // Print Receipt
                System.out.printf("\n\033[33mRoom booked successfully for %s.\033[37m\n", guestName);
                System.out.printf("\n\033[33m===== RECEIPT =====\033[37m");
                System.out.printf("\n\033[33mname\033[37m:\t%s", guestName);
                System.out.printf("\n\033[33mhtl \033[37m:\thotel %s", this.name);
                System.out.printf("\n\033[33mroom\033[37m:\t%s", room.getName());
                System.out.printf("\n\033[33min  \033[37m:\t%d", checkin);
                System.out.printf("\n\033[33mout \033[37m:\t%d", checkout);
                System.out.printf("\n\033[33mcost\033[37m:\tPHP %.2f", total);
                System.out.printf("\n\033[33m===================\033[37m\n");

                setEarnings(total);

                room.setReservation(newReservation);
                room.addBookRoom(checkin, checkout);
                this.setReservationCount(reservationNumber);
                return true;
            }
        }

        System.out.printf("\n\033[31mError. There are currently no available rooms in hotel '%s' for the selected dates.\033[37m\n", this.getName());
        return false;
    }

    /**
     * return the room given the name of the room of a hotelName
     *
     * @param name name of the room
     * @return {room} the room with the room name in the hotel, {null} the room
     * was not found
     *
     * @author Zhean Ganituen
     */
    public Room fetchRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }

        return null; // room not found
    }

    /**
     * return an array of the names of the available or booked for a day
     *
     * @param type {1} method gets the available rooms, {otherwise} method gets
     * the booked rooms
     * @param date the date
     * @return {avails} the names of the rooms that are available on the date,
     * {booked} the names of the rooms that are booked on the date
     */
    public ArrayList<String> fetchAvails(int type, int date) {
        ArrayList<String> avails = new ArrayList<>();
        ArrayList<String> booked = new ArrayList<>();

        for (Room room : this.rooms) {
            if (room.isAvailable(date)) {
                avails.add(room.getName());
            } else {
                booked.add(room.getName());
            }
        }
        // if type is 1 then return available rooms
        // otherwise return the booked rooms
        if (type == 1) {
            return avails;
        } else {
            return booked;
        }
    }

    /**
     * changes the price of all rooms in the hotel, if and only if there are no
     * reservations
     *
     * @param newPrice the new price, constraint: newPrice >= 100
     * @return {true} if the base price is successfully changed, {false} if
     * otherwise
     *
     * @author Zhean Ganituen
     */
    public boolean changePrice(double newPrice) {
        // check if a reservation is empty
        // if reservation is empty (then no reservation is made yet)
        // and if newPrice is greater than the minimum amount: 100
        if (this.reservations.isEmpty() && newPrice >= 100) {
            // iterate through the rooms and set the price to newPrice

            this.setBasePrice(newPrice); // change base price of the hotel itself

            // then iterare through the rooms to change their base price
            for (Room room : this.rooms) {
                room.setBasePrice(newPrice);

            }
            return true;
        }

        return false;
    }

    /**
     * User I/O for deleting room. Uses `delRoom`
     *
     * @param index the room number to delete
     *
     * @author Zhean Ganituen
     */
    public void delRoom(int index) {
        if (index >= 1 && index <= rooms.size()) {
            this.rooms.remove(index - 1); // minus 1 this because we start naming at 1 but indexing still
            // starts at 0
            roomCount--;
        }
    }

    /**
     * Changes the name of the rooms in the hotel
     *
     * @param name name of the hotel
     *
     * @author Jaztin Jimenez
     */
    public void changeRoomName(String name) {
        roomCount = 1;
        for (Room room : this.rooms) {
            room.setName(name + "_Room_" + roomCount);
            roomCount++;
        }
    }

    /**
     * Removes the reservation from a hotel room.
     *
     * @param guestName the name of the guest
     * @param checkinDate the date of the guest's check in
     *
     * @return {true} if the reservation was removed, {false} otherwise
     *
     * @author Zhean Ganituen, Jaztin Jimenez
     */
    public boolean removeReservation(String guestName, int checkinDate) {
        Reservation reservationToRemove = null; // assume not found
        
        // check if checkinDate is out of bounds
        if(checkinDate < 0 || checkinDate > 31){
            return false;
        }
        
        // go through all the reservations within the hotel
        for (Reservation reservation : reservations) {
            // checks if the guest name and the check-in date of the reservation is valid
            if (reservation.getGuest().equals(guestName) && reservation.getCheckin() == checkinDate) {
                reservationToRemove = reservation;
                break;
            }
        }

        if (reservationToRemove != null) { // removes the reservation if valid
            Room removeRoom = reservationToRemove.getRoom();
            removeRoom.remBookRoom(reservationToRemove.getCheckin(), reservationToRemove.getCheckout()); // makes
            // the dates within the reservation available for booking
            setEarnings(-(removeRoom.getBasePrice()
                    * (reservationToRemove.getCheckout() - reservationToRemove.getCheckin())));
            this.reservations.remove(reservationToRemove);
            return true;
        }

        return false;
    }

    /**
     * Prepares a hotel for removal by resetting all its variables
     *
     * @author Jaztin Jimenez
     */
    public void prepareForRemoval() {
        this.name = null;
        this.rooms.clear();
        this.reservations.clear();
        this.earnings = 0.0;
    }

    /**
     * Gets the average DPC of all the rooms in the hotel
     * 
     * @return the average DPC
     */
    public double getAverageDPC(){
        double total = 0;
        for (Room room : rooms){
            for (int i = 0; i < 31; i++) {
                total += room.getDPM()[i]; // get the DPM of the room at the ith day
            }
        }
        return total / 31 / this.getRoomCount();
    }

    public void changeDPMs(int day, double newDPM){
        for (Room room : this.getRooms()){
            room.changeDPM(day, newDPM);
        }
    }

    public int[] roomTypeCount(){
        int[] roomTypes = new int[3];

        // set up counters
        roomTypes[0] = 0; // counter for standard rooms
        roomTypes[1] = 0; // counter for deluxe rooms
        roomTypes[2] = 0; // counter for executive rooms

        for (Room room : this.getRooms()){
            switch (room.getType()) {
                case 1 -> roomTypes[0] += 1;
                case 2 -> roomTypes[1] += 1;
                case 3 -> roomTypes[2] += 1;                    
            }
        }

        return roomTypes;
    }
}
