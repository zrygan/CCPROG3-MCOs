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

    //@FIXME: REMOVE ALL THE ARRAYLIST GETTERS AND CHANGE THE getRooms() to just getRoomsSize()
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
        if (roomCount + 1 < 50) {
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
    public Room newRoom(int count) {
        // check if a room can still be created in the hotel
        if (roomCount + count < 50) {
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

    /* delRoom
     * Deletes a specific room given a room number
     * 
     * @params:
     *  - num : int : the room number
     * 
     * @returns:
     *  - none
     */
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

    /* bookRoom
     * Books a room and makes a reservation, if possible
     
     * 
     * @param: 
     *  - guestName : String : the guests name 
     *  - checkIn   : int    : date of checking in
     *  - checkOut  : int    : date of checking out
     * 
     * @return
     *  - true  : boolean : if room booking is successful
     *  - false : boolean : if room booking is not successful
     * 
     * @Author: Zhean Ganituen, Jaztin Jimenez
     */
    public boolean bookRoom(String guestName, int checkIn, int checkOut) {
        // iterate through all the rooms in hotel
        for (Room room : this.rooms) {
            // look for a room that is available for the entire duration of the reservation
            if (!room.isAvailable(checkIn, checkOut)) {
                // add reseravation
                Reservation newReservation = new Reservation(guestName, checkIn, checkOut, room);
                this.reservations.add(newReservation);
                System.out.printf("Room booked successfully for %s.\n", guestName);
                setEarnings(room.getBasePrice() * (checkOut - checkIn)); 
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
        System.out.printf("\n==================== OPTIONS ====================\n");
        System.out.printf("\33[33m1\33[37m\t:\tview high-level hotel information\n");
        System.out.printf("\33[33m2\33[37m\t:\tview low-level hotel information\n");
        System.out.printf("\33[31m0\33[37m\t:\texit\n");
        System.out.printf("=================================================");

        System.out.printf("\nChoose an option: ");

        int level = sc.nextInt();
        sc.nextLine(); // consume new line

        switch (level) {
            case 0 ->
                System.out.println("\nReturning to main menu.");
            case 1 ->{
                System.out.printf("You selected to \033[34mview high-level information\033[37m for hotel '%s'.\n", this.getName());
                System.out.printf("\n\033[33mHotel '%s' with %d rooms has earned PHP %.2f.\033[37m\n", this.getName(), this.getRooms().size(), this.getEarnings());
            }
            case 2 -> {
                System.out.printf("You selected to \033[34mview low-level information\033[37m on hotel '%s'.\n", this.getName());
                System.out.printf("\n\n=========================== OPTIONS ===========================\n");
                System.out.printf("\33[33m1\33[37m\t:\tview available/booked rooms for a selected date\n");
                System.out.printf("\33[33m2\33[37m\t:\tview details of a specific room or reservation\n");
                System.out.printf("===============================================================");

                System.out.printf("\nChoose an option: ");

                int option = sc.nextInt();

                if (1 == option) {
                    System.out.printf("You selected to \033[34mview booked rooms for a date\033[37m for hotel '%s'.\n", this.getName());
                    
                    System.out.print("\nEnter date (1-31): ");

                    int date = sc.nextInt();
                    sc.nextLine();

                    ArrayList<String> availableRooms = new ArrayList<>();
                    ArrayList<String> bookedRooms = new ArrayList<>();
                    

                    for (Room room : this.rooms) {
                        if (room.isAvailable(date)) {
                            availableRooms.add(room.getName());
                        } else {
                            bookedRooms.add(room.getName());
                        }
                    }

                    // print available rooms with proper formatting and handling
                    if (availableRooms.isEmpty()){
                        System.out.printf("Available rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n", this.getName(), date);
                    } else{
                        System.out.printf("Available rooms of hotel '%s' on day %d:\n", this.getName(), date);
                        for (String room : availableRooms){
                            System.out.printf("\t\033[33m%s\033[37m\n", room);
                        }
                    }

                    // print booked rooms with proper formatting and handling
                    if (bookedRooms.isEmpty()){
                        System.out.printf("\nBooked rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n", this.getName(), date);
                    } else{
                        System.out.printf("\nBooked rooms of hotel '%s' on day %d:\n", this.getName(), date);
                        for (String room : bookedRooms){
                            System.out.printf("\t\033[33m%s\033[37m\n", room);
                        }
                    }

                } else if (2 == option) {
                    System.out.printf("You selected to \033[34mview a room reservation\033[37m for hotel '%s'.\n", this.getName());
                    
                    System.out.printf("\nEnter room number: ");
                    int roomNum = sc.nextInt();
                    sc.nextLine();

                    String roomName = name + "_Room_" + roomNum; // reformat the name

                    // get room
                    Room roomQuery = this.fetchRoom(roomName);

                    // if room exists
                    if (roomQuery != null) {
                        // calculate availability
                        int days = 31 - roomQuery.getDaysBooked();

                        System.out.printf("\n\033[34mThe Room '%s' in Hotel '%s' costs %.2f per night and is available for %d days of the month.\033[37m\n", roomQuery.getName(), this.name, roomQuery.getBasePrice(), days);
                    } else {
                        System.out.printf("\n\033[31mError. Sorry! But the room name '%s' in Hotel '%s' does not exist.\033[37m\n", roomName, this.getName());
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

    /* changeRoomName
     * Changes the room name.
     * 
     * @param:
     *  - sc : Scanner : Scanner object for user input
     * 
     * @returns:
     *  - none
     * 
     * @Author: Jaztin Jimenez
     */
    public void changeHotelName(Scanner sc) {
        System.out.println("Enter new hotel name: ");

        String oldName = this.getName();

        String newName = sc.nextLine();

        this.setName(newName); // set the name to the new name

        System.out.printf("Hotel '%s' has been renamed to '%s'.\n", oldName, this.getName());
    }

    /* addRoom
     * Adds a specific number of rooms to a hotel.
     * 
     * @param:
     *  - sc : Scanner : Scanenr object
     * 
     * @returns:
     *  - none
     * 
     * @Author: Jaztin Jimenez
     */
    public void addRoom(Scanner sc) {
        System.out.printf("Enter number of rooms to create: ");
        int num = sc.nextInt();

        for (int i = 0; i < num; i++) {

            Room newRoom = this.newRoom();

            if (newRoom != null) {

                System.out.printf("A new room '%s' has been added in hotel '%s'.\n", newRoom.getName(), this.getName());

            } else {
                System.out.printf("A new room cannot be created since there are 50 rooms in hotel '%s' already.\n", this.getName());
            }
        }
    }

    /* delRoomUI
     * User I/O for deleting room. Uses `delRoom`
     * 
     * @param:
     *  - sc : Scanner : Scanner object
     * 
     * @returns: 
     *  - None
     * 
     * @authors: Zhean Ganituen
     */
    public void delRoomUI(Scanner sc) {
        if (roomCount > 1) {
            System.out.print("Enter room number to delete: ");
            int index = sc.nextInt();
             sc.nextLine();
             this.delRoom(index);
        } else {
            System.out.printf("Delete cancelled. There is only one room left.\n");
        }
    }

    /* changePriceUI
     * User I/O for changing the base prices of all rooms. Uses `changePrice`
     * 
     * @param:
     *  - sc : Scanner : Scanner object
     * 
     * @returns:
     *  - none
     * 
     * @Authors: Zhean Ganituen, Jaztin Jimenez
     */
    public void changePriceUI(Scanner sc) {
        System.out.print("Enter the new price for the rooms of the hotel: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();

        if (this.changePrice(newPrice)) {
            System.out.printf("The rooms of hotel '%s' have been changed to %.2f.\n", this.getName(), this.getRooms().get(0).getBasePrice());
        } else {
            System.out.printf("The base price of hotel '%s' has not been changed because there's an ongoing reservation.", this.getName());
        }
    }

    /* removeReservationUI
     * Removes the reservation from a hotel room.
     * 
     * @param:
     *  - sc : Scanner : Scanner object.
     * 
     * @returns:
     *  - none
     * 
     * @author: Zhean Ganituen, Jaztin Jimenez
     */
    public void removeReservationUI(Scanner sc) {
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

    /* prepareForRemoval
     * Prepares a hotel for removal by resetting all its variables
     * 
     * @params: 
     *  - none
     * 
     * @returns:
     *  - none 
     * 
     * @author: Jaztin Jimenez
     */
    public void prepareForRemoval() {
        this.name = null;
        this.rooms.clear();
        this.reservations.clear();
        this.earnings = 0.0;
        System.out.println("Hotel data cleared.");
    }

}
