/* Hotel Class
 * An object for the hotels in the system 
 * 
 * @params:
 *  - name         : String        : the name of the hotel
 *  - roomTotal    : int           : the current number of rooms in the hotel
 *  - roomCount    : int           : the actual total number of rooms in the hotel
 *  - rooms[]      : Room          : the rooms in the hotel, an array of Room objects, maximum of 50
 *  - earnings     : double        : the total earnings of the hotel through room bookings
 */

import java.util.*;

public class Hotel {

    // Variables
    private String name;
    private int roomCount;
    private ArrayList<Room> rooms; // potential maximum of 50 rooms
    private ArrayList<Reservation> reservations; // list of reservations
    private double earnings;

    // Constructor
    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.earnings = 0.0; // initialize as 0

    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservations = reservation;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public double getEarnings() {
        return earnings;
    }

    // Methods

    /* newRoom
     * creates a new room in the hotel, if possible
     * 
     * @params:
     *  - none
     * 
     * @returns:
     *  - true  : boolen  : if a new room was created
     *  - false : boolean : if not 
     * 
     * @author: Zhean Ganituen
     */
    public Room newRoom() {
        // check if a room can still be created in the hotel
        if (roomCount < 50) {
            // make a unique room name
            String roomName = name + "_Room_" + roomCount;

            // create a new room with the new room name
            Room newRoom = new Room(roomName, this);

            // add the created room in the array of rooms
            rooms.add(newRoom);

            // increment the number of rooms
            roomCount++;

            return newRoom;
        }

        return null;
    }

    public void delRoom(int num) {
        // check if index is within bounds
        if (num < 0 || num > 50) {
            System.out.println("Index out of bounds.");
        } else {
            String roomName = this.name + "_Room_" + num;
            Room room = fetchRoom(roomName);

            // check if the rooms exists
            if (room != null) {
                Room removedRoom = rooms.remove(num);
                System.out.printf("Room '%s' has been removed from hotel '%s'.\n", removedRoom.getName(), this.getName());
                roomCount--;
            } else {
                System.out.printf("Room with the name '%s' is not found.", roomName);
            }
        }
    }

    public boolean bookRoom(String guestName, int checkIn, int checkOut) {
        // iterate through all the rooms in hotel
        for (Room room : this.rooms) {
            // look for a room that is available for the entire duration of the reservation
            if (!room.isAvailable(checkIn, checkOut)) {
                // add reseravation
                this.getRooms();
                this.reservations.add(new Reservation(guestName, checkIn, checkOut, room));
                System.out.printf("Room booked successfully for %s.\n", guestName);
                room.checkIn(); // check in the room
                room.bookLength(checkIn, checkOut);
                return true;
            }
        }

        System.out.println("There are currently no available rooms for the selected dates");
        return false;
    }

    /* fetchRoom
     * returns the room given the name of the room of a hotelName
     * 
     * @params:
     *  - name : String : name of the room
     * 
     * @returns:
     *  - room : Room : the room with the room name in the hotel
     *  - null : null : the room was not found
     * 
     * @author: Zhean Ganituen
     */
    public Room fetchRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }

        return null; // room not found
    }

    /* viewHotel
     * views the hotel information, checks either high-level information or low-level information from
     * the hotel.
     * 
     * @params:
     *  - none
     *
     * @returns:
     *  - none
     *
     * @author: Zhean Ganituen and Jaztin Jimenez
     */
    public void viewHotel(Scanner sc) {
        System.out.println("1\t:\tto view high-level hotel information");
        System.out.println("2\t:\tto view low-level hotel information");
        System.out.print("Choice: ");
        System.out.println(); // add new line
        int level = sc.nextInt();
        sc.nextLine(); // consume new line

        switch (level) {
            case 1 ->
                System.out.printf("Hotel '%s' with %d rooms has earned PHP %.2f.\n", this.getName(), this.getRooms().size(), this.getEarnings());

            case 2 -> {
                System.out.println("1\t:\tto view available/booked rooms for a selected date");
                System.out.println("2\t:\tto view details of a specific room or reservation");

                int option = sc.nextInt();

                if (1 == option) {
                    System.out.print("Enter date (1-31): ");

                    int date = sc.nextInt();
                    sc.nextLine();

                    ArrayList<String> availableRooms = new ArrayList<>();
                    ArrayList<String> bookedRooms = new ArrayList<>();

                    for (Room room : this.getRooms()) {
                        if (room.isAvailable(date)) {
                            availableRooms.add(room.getName());
                        } else {
                            bookedRooms.add(room.getName());
                        }
                    }

                    System.out.println("Available Rooms on day " + date + ": " + availableRooms);
                    System.out.println("Booked Rooms on day " + date + ": " + bookedRooms);

                } else if (2 == option) {
                    System.out.println("Enter room number: ");
                    String roomName = sc.nextLine();
                    roomName = name + "_Room_" + roomName; // reformat the name

                    // get room
                    Room roomQuery = this.fetchRoom(roomName);

                    // if room exists
                    if (roomQuery != null) {
                        // calculate availability
                        int days = 31 - roomQuery.getDaysBooked();

                        System.out.printf("The Room '%s' in Hotel '%s' costs %.2f per night and is available for %d days of the month.\n", roomQuery.getName(), this.name, roomQuery.getBasePrice(), days);
                    } else {
                        System.out.println("The room '%s' in Hotel '%s' does not exist.");
                    }
                }
            }
        }
    }

    /* changePrice
     * changes the price of all rooms in the hotel, if and only if there are no reseravations
     * 
     * @params:
     *  - newPrice : double : the new price, constraint: newPrice >= 100
     * 
     * @returns:
     *  - none
     * 
     * @author: Zhean Ganituen
     */
    public boolean changePrice(double newPrice) {
        // check if a reservation is empty
        // if reseravation is empty (then no reseravation is made yet)
        // and if newPrice is greater than the minimum amount: 100
        if (!this.reservations.isEmpty() && newPrice >= 100) {
            // iterate through the rooms and set the price to newPrice
            for (Room room : this.rooms) {
                room.setBasePrice(newPrice);
            }
            return true;
        }

        return false;
    }

    public void changeHotelName(Scanner sc) {
        System.out.println("Enter new hotel name: ");

        String oldName = this.getName();

        String newName = sc.nextLine();

        this.setName(newName); // set the name to the new name

        System.out.printf("Hotel '%s' has been renamed to '%s'.\n", oldName, this.getName());
    }

    public void addRoom(Scanner sc) {
        Room newRoom = this.newRoom();
        if (newRoom != null) {
            System.out.printf("A new room '%s' has been added in hotel '%s'.\n", newRoom.getName(), this.getName());
        } else {
            System.out.printf("A new room cannot be created since there are 50 rooms in hotel '%s' already.\n", this.getName());
        }
    }

    public void removeRoom(Scanner sc) {
        System.out.print("Enter room number to delete: ");
        int index = sc.nextInt();
        sc.nextLine();

        this.delRoom(index);
    }

    public void updateRoomBasePrice(Scanner sc) {
        System.out.print("Enter the new price for the rooms of the hotel: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();

        if (this.changePrice(newPrice)) {
            System.out.printf("The rooms of hotel '%s' have been changed to %.2f.\n", this.getName(), this.getRooms().get(0).getBasePrice());
        } else {
            System.out.printf("The base price of hotel '%s' has not been changed because there's an ongoing reservation.", this.getName());
        }
    }

    public void removeReservation(Scanner sc) {
        System.out.print("Enter guest name for reservation removal: ");
        String guestName = sc.nextLine();
        System.out.print("Enter check-in date of the reservation to remove (1-31): ");
        int checkInDate = sc.nextInt();
        sc.nextLine(); // Consume newline

        Reservation reservationToRemove = null;
        for (Reservation reservation : reservations) { // go through all the reservations within the hotel
            if (reservation.getGuest().equals(guestName) && reservation.getCheckin() == checkInDate) { // checks if the guest name and the check-in date of the reservation is valid
                reservationToRemove = reservation;
                break;
            }
        }
        if (reservationToRemove != null) { // removes the reservation if valid
            reservations.remove(reservationToRemove);
            System.out.println("Reservation removed successfully.");
        } else { // doesn't cancel the reservation if invalid
            System.out.println("Reservation not found.");
        }
    }

    public void prepareForRemoval() {
        this.name = null;
        this.rooms.clear();
        this.reservations.clear();
        this.earnings = 0.0;
        System.out.println("Hotel data cleared.");
    }

}
