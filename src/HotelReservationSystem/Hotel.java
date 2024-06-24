package HotelReservationSystem;

import java.util.*;

/**
 * This class represents a Hotel object in the system
 */
public class Hotel {

    // Variables
    private String name;
    private int roomCount;
    private ArrayList<Room> rooms; // potential maximum of 50 rooms
    private ArrayList<Reservation> reservations; // list of reservations
    private double earnings;

    /**
     * Constructor for the Hotel object
     * 
     * @param name Name of the Hotel
     */
    public Hotel(String name) {
        this.roomCount = 1; // initialize at 1 because we want to start at room_1 not room_0
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.earnings = 0.0; // initialize as 0

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
     * Sets the reservation of the Hotel
     * 
     * @param reservation The reservation in the Hotel
     */
    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservations = reservation;
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
     * Sets the earnings of the Hotel
     * 
     * @param earnings The earnings of the Hotel
     */
    public void setEarnings(double earnings) {
        this.earnings += earnings;
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
     * Getter for the number of rooms in the Hotel
     * 
     * @return The number of rooms in the Hotel
     */
    public int getRoomCount() {
        return roomCount - 1; // -1 because we start at 1 not 0
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
     * creates a new room in the hotel, if possible
     * 
     * @return {true} if a new room is created, {false} if otherwise
     * 
     * @author Zhean Ganituen
     */
    public Room newRoom() {
        // check if a room can still be created in the hotel
        if (roomCount < 51) {
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

    /**
     * Deletes a specific room given a room number
     * 
     * @param num the room number
     */
    public void delRoom(int num) {
        // check if index is within bounds
        if (num < 1 || num > 51) {
            System.out.printf("\n\033[31mError. Entered number out of range. From 1 to 50 only.\033[37m\n");
        } else {
            String roomName = this.name + "_Room_" + num;
            Room room = fetchRoom(roomName);

            // check if the rooms exists
            if (room != null) {
                this.rooms.remove(num);
                System.out.printf("Room number %d in hotel '%s' has been successfully deleted.", num, this.getName());
                roomCount--;
            } else {
                System.out.printf("\n\033[31mError. Room number %d not found.\033[37m\n", num);
        }
        }
    }

    /**
     * Books a room and makes a reservation, if possible
     * 
     * @param guestName the guests name
     * @param checkIn   date of checking in
     * @param checkOut  date of checking out
     * 
     * @return {true} if room booking is successful, {false} if room booking is not
     *         successful
     * 
     * @author Zhean Ganituen, Jaztin Jimenez
     */
    public boolean bookRoom(String guestName, int checkIn, int checkOut) {
        // iterate through all the rooms in hotel
        for (Room room : this.rooms) {
            // look for a room that is available for the entire duration of the reservation
            if (!room.isAvailable(checkIn, checkOut)) {
                // add reservation
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

    /**
     * return the room given the name of the room of a hotelName
     * 
     * @param name name of the room
     * @return {room} the room with the room name in the hotel, {null} the room was
     *         not found
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
     * views the hotel information, checks either high-level information or
     * low-level information from the hotel.
     * 
     * @param sc The scanner object
     * 
     * @author Zhean Ganituen
     * @author Jaztin Jimenez
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
            case 1 -> {
                System.out.printf("You selected to \033[34mview high-level information\033[37m for hotel '%s'.\n",
                        this.getName());
                System.out.printf("\n\033[33mHotel '%s' with %d rooms has earned PHP %.2f.\033[37m\n", this.getName(),
                        this.rooms.size(), this.getEarnings());
            }
            case 2 -> {
                System.out.printf("You selected to \033[34mview low-level information\033[37m on hotel '%s'.\n",
                        this.getName());
                System.out.printf("\n=========================== OPTIONS ===========================\n");
                System.out.printf("\33[33m1\33[37m\t:\tview available/booked rooms for a selected date\n");
                System.out.printf("\33[33m2\33[37m\t:\tview details of a specific room or reservation\n");
                System.out.printf("===============================================================");

                System.out.printf("\nChoose an option: ");

                int option = sc.nextInt();

                if (1 == option) {
                    System.out.printf("You selected to \033[34mview booked rooms for a date\033[37m for hotel '%s'.\n",
                            this.getName());

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
                    if (availableRooms.isEmpty()) {
                        System.out.printf("Available rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n",
                                this.getName(), date);
                    } else {
                        System.out.printf("Available rooms of hotel '%s' on day %d:\n", this.getName(), date);
                        for (String room : availableRooms) {
                            System.out.printf("\t\033[33m%s\033[37m\n", room);
                        }
                    }

                    // print booked rooms with proper formatting and handling
                    if (bookedRooms.isEmpty()) {
                        System.out.printf("\nBooked rooms of hotel '%s' on day %d: \033[31mNONE\033[37m.\n",
                                this.getName(), date);
                    } else {
                        System.out.printf("\nBooked rooms of hotel '%s' on day %d:\n", this.getName(), date);
                        for (String room : bookedRooms) {
                            System.out.printf("\t\033[33m%s\033[37m\n", room);
                        }
                    }

                } else if (2 == option) {
                    System.out.printf("You selected to \033[34mview a room reservation\033[37m for hotel '%s'.\n",
                            this.getName());

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

                        System.out.printf(
                                "\n\033[34mThe Room '%s' in Hotel '%s' costs %.2f per night and is available for %d days of the month.\033[37m\n",
                                roomQuery.getName(), this.name, roomQuery.getBasePrice(), days);
                    } else {
                        System.out.printf(
                                "\n\033[31mError. Sorry! But the room name '%s' in Hotel '%s' does not exist.\033[37m\n",
                                roomName, this.getName());
                    }
                }
            }
        }
    }

    /**
     * changes the price of all rooms in the hotel, if and only if there are no
     * reservations
     * 
     * @param newPrice the new price, constraint: newPrice >= 100
     * @return {true} if the base price is successfully changed, {false} if
     *         otherwise
     * 
     * @author Zhean Ganituen
     */
    public boolean changePrice(double newPrice) {
        // check if a reservation is empty
        // if reservation is empty (then no reservation is made yet)
        // and if newPrice is greater than the minimum amount: 100
        if (this.reservations.isEmpty() && newPrice >= 100) {
            // iterate through the rooms and set the price to newPrice
            for (Room room : this.rooms) {
                room.setBasePrice(newPrice);
            }
            return true;
        }

        return false;
    }

    /**
     * Adds a specific number of rooms to a hotel.
     * 
     * @param sc the scanner object
     * 
     * @author Jaztin Jimenez
     */
    public void addRoom(Scanner sc) {
        System.out.printf("Enter number of rooms to create: ");
        int num = sc.nextInt();
        sc.nextLine();

        System.out.println(); // empty new line

        for (int i = 0; i < num; i++) {

            Room newRoom = this.newRoom();

            if (newRoom != null) {

                System.out.printf("A new room '%s' has been added in hotel '%s'.\n", newRoom.getName(), this.getName());

            } else {
                System.out.printf("A new room cannot be created since there are 50 rooms in hotel '%s' already.\n",
                        this.getName());
            }
        }
    }

    /**
     * User I/O for deleting room. Uses `delRoom`
     * 
     * @param sc the scanner object
     * 
     * @author Zhean Ganituen
     */
    public void delRoomUI(Scanner sc) {
        if (roomCount > 1) {
            System.out.printf("\nEnter room number to delete: ");
            int index = sc.nextInt();
            sc.nextLine();
            this.delRoom(index);
        } else {
            System.out.printf("\n\033[31mError. Entered number out of range. From 1 to 50 only.\033[37m\n");
        }
    }

    /**
     * User I/O for changing the base prices of all rooms. Uses `changePrice`
     * 
     * @param sc the scanner object
     * 
     * @author Zhean Ganituen
     * @author Jaztin Jimenez
     */
    public void changePriceUI(Scanner sc) {
        System.out.print("Enter the new price for the rooms of the hotel: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();

        if (this.changePrice(newPrice)) {
            System.out.printf("The rooms of hotel '%s' have been changed to %.2f.\n", this.getName(),
                    this.rooms.get(0).getBasePrice());
        } else {
            System.out.printf(
                    "The base price of hotel '%s' has not been changed because there's an ongoing reservation.",
                    this.getName());
        }
    }

    /**
     * Removes the reservation from a hotel room.
     * 
     * @param sc Scanner object
     * 
     * @author Zhean Ganituen, Jazatin Jimenez
     */
    public void removeReservationUI(Scanner sc) {
        System.out.print("Enter guest name for reservation removal: ");
        String guestName = sc.nextLine();
        System.out.print("Enter check-in date of the reservation to remove (1-31): ");
        int checkInDate = sc.nextInt();
        sc.nextLine(); // Consume newline

        Reservation reservationToRemove = null;
        for (Reservation reservation : reservations) { // go through all the reservations within the hotel
            if (reservation.getGuest().equals(guestName) && reservation.getCheckin() == checkInDate) { // checks if the
                // guest name and
                // the check-in
                // date of the
                // reservation is
                // valid
                reservationToRemove = reservation;
                break;
            }
        }
        if (reservationToRemove != null) { // removes the reservation if valid
            Room removeRoom = reservationToRemove.getRoom();
            removeRoom.removeAvailability(reservationToRemove.getCheckin(), reservationToRemove.getCheckout()); // makes
            // the
            // dates
            // within
            // the
            // reservation
            // available
            // for
            // booking
            setEarnings(-(removeRoom.getBasePrice()
                    * (reservationToRemove.getCheckout() - reservationToRemove.getCheckin())));
            this.reservations.remove(reservationToRemove);
            System.out.println("HotelReservationSystem.Reservation removed successfully.");

        } else { // doesn't cancel the reservation if invalid
            System.out.println("HotelReservationSystem.Reservation not found.");
        }
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
        System.out.println("Hotel data cleared.");
    }
}