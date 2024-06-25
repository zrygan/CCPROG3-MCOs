# CCPROG3 MCO1 Presentation

Zhean Ganituen and Jaztin Jimenez

## Member Contribution

- Java Program
    - Jaztin Jimenez and Zhean Ganituen
- UML
    - Jaztin Jimenez and Zhean Ganituen
- Test script
    - Zhean Ganituen
- Javadoc
    - Jaztin Jimenez and Zhean Ganituen

## Creating a new room

```java
/**
 * creates a new room in the hotel, if possible
 *
 * @return {Room} if a new room is created, {null} if otherwise
 *
 * @author Zhean Ganituen
 */
public Room newRoom() {
    for (int roomNumber = 1; roomNumber < 51; roomNumber++) {
        String roomName = name + "_Room_" + roomNumber;
        // the first room that doesnt exist yet make it
        if (fetchRoom(roomName) == null) {
            Room newRoom = new Room(roomName, this, this.basePrice);
            rooms.add(newRoom);
            roomCount++;
            return newRoom;
        }
    }

    return null;
}
```


## Creating a reservation

```java
/**
 * Books a room and makes a reservation, if possible
 *
 * @param guestName the guests name
 * @param checkIn date of checking in
 * @param checkout date of checking out
 *
 * @return {true} if room booking is successful, {false} if room booking is
 * not successful
 *
 * @author Zhean Ganituen, Jaztin Jimenez
 */
public boolean bookRoom(String guestName, int checkin, int checkout) {
    // iterate through all the rooms in hotel
    for (Room room : this.rooms) {
        // look for a room that is available for the entire duration of the reservation
        if (room.isAvailable(checkin, checkout)) {
            // add reservation
            Reservation newReservation = new Reservation(guestName, checkin, checkout, room);
            System.out.println(newReservation);
            this.reservations.add(newReservation);
            System.out.printf("\n\033[33mRoom booked successfully for %s.\033[37m\n", guestName);
            System.out.printf("\n\033[33m===== RECEIPT =====\033[37m");
            System.out.printf("\n\033[33mname\033[37m:\t%s", guestName);
            System.out.printf("\n\033[33mhtl \033[37m:\thotel %s", this.name);
            System.out.printf("\n\033[33mroom\033[37m:\t%s", room.getName());
            System.out.printf("\n\033[33min  \033[37m:\t%d", checkin);
            System.out.printf("\n\033[33mout \033[37m:\t%d", checkout);
            System.out.printf("\n\033[33mcost\033[37m:\tPHP %.2f", room.getBasePrice() * (checkout - checkin));
            System.out.printf("\n\033[33m===================\033[37m\n");
            setEarnings(room.getBasePrice() * (checkout - checkin));
            room.setReservation(newReservation);
            room.addBookRoom(checkin, checkout);
            return true;
        }
    }

    System.out.printf("\n\033[31mError. There are currently no available rooms in hotel '%s' for the selected dates.\033[37m\n", this.getName());
    return false;
}
```

## Manage Hotel Feature

```java
    /**
     * User I/O for managing a hotel
     *
     * @param sc Scanner object
     *
     * @author Zhean Ganituen
     */
    public void manageHotel(Scanner sc) {
        System.out.printf("You selected to \033[34mmanage\033[37m a hotel!\n");

        System.out.print("\nEnter the name of the hotel: ");
        String name = sc.nextLine();
        Hotel hotel = fetchHotel(name);

        if (hotel != null) {
            System.out.printf("\n=================== OPTIONS ===================\n");
            System.out.printf("\33[33m1\33[37m\t:\t change the name of hotel '%s'\n", name);
            System.out.printf("\33[33m2\33[37m\t:\t add a new room\n");
            System.out.printf("\33[33m3\33[37m\t:\t remove a room\n");
            System.out.printf("\33[33m4\33[37m\t:\t update the base price\n");
            System.out.printf("\33[33m5\33[37m\t:\t remove a reservation\n");
            System.out.printf("\33[33m6\33[37m\t:\t remove hotel '%s'\n", name);
            System.out.printf("\33[31m0\33[37m\t:\t exit\n");
            System.out.printf("===============================================");

            System.out.printf("\nChoose an option: ");

            int choice = getInput(sc);

            switch (choice) {
                case 0 -> // exit to main menu
                    System.out.println("\nReturning to main menu.");
                case 1 -> {
                    // change name 

                    System.out.printf("You selected to \033[34mchange the name of hotel '%s'\033[37m.\n", name);
                    System.out.printf("\nEnter new hotel name: ");

                    String oldName = hotel.getName();

                    String newName = sc.nextLine();

                    if (fetchHotel(newName) != null) {
                        System.out.printf("\n\033[31mError. Sorry! But that hotel name '%s' already exists.\033[37m\n",
                                newName);

                    } else {
                        hotel.setName(newName); // set the name to the new name
                        System.out.printf("\n\33[33mHotel '%s' has been successfully renamed to '%s'.\33[37m\n",
                                oldName, hotel.getName());
                        Hotel changeName = fetchHotel(newName);

                        changeName.changeRoomName(newName);
                    }

                }
                case 2 -> {
                    // add room 

                    System.out.printf("You selected to \033[34madd a room\033[37m in hotel '%s'.\n", name);

                    System.out.printf("\nEnter number of rooms to create: ");

                    int num = getInput(sc);

                    if (num > 0 && hotel.getRoomCount() + num < 51) {
                        for (int i = 0; i < num; i++) {

                            Room newRoom = hotel.newRoom();

                            if (newRoom != null) {

                                System.out.printf(
                                        "\n\033[33mA new room '%s' has been successfully added in hotel '%s'.\033[37m\n",
                                        newRoom.getName(), hotel.getName());

                            } else {
                                System.out.printf(
                                        "\n\033[31mError. A new room cannot be created since there are 50 rooms in hotel '%s' already.\033[37m\n",
                                        hotel.getName());
                            }
                        }
                    } else if (num <= 0) {
                        System.out.printf("\n\033[31mError. Invalid number of rooms to create (0).\033[37m\n",
                                hotel.getName());
                    } else {
                        System.out.printf(
                                "\n\033[31mError. Entered number %d will cause the number of rooms in hotel '%s' to overflow.\nThere are %d rooms in hotel '%s', maximum rooms that can be added is %d.\033[37m\n",
                                num, name, hotel.getRoomCount(), name, 50 - hotel.getRoomCount());
                    }
                }
                case 3 -> {
                    // delete a room

                    System.out.printf("You selected to \033[34mdelete a room\033[37m in hotel '%s'.\n", name);

                    // hotel.delRoomUI(sc);
                    if (hotel.getRoomCount() > 1) {
                        System.out.printf("\nEnter room number to delete: ");

                        int index = getInput(sc);

                        // check if index is within bounds
                        if (index < 0 || index > 51) {
                            System.out.printf("\n\033[31mError. Entered number out of range. From 1 to 50 only.\033[37m\n");
                        } else {
                            // format the name
                            String roomName = hotel.getName() + "_Room_" + index;

                            // get the room
                            Room room = hotel.fetchRoom(roomName);

                            // check if the rooms exists
                            if (room != null) {

                                if (room.getReservation() == null) { // checks if there are reservation
                                    hotel.delRoom(index); // run delRoomUI
                                    System.out.printf(
                                            "\n\033[33mRoom number %d in hotel '%s' has been successfully deleted.\033[37m\n",
                                            index, hotel.getName());
    
                                    // when we delete a room we need to move the names of the rooms back by one
                                    // so say we removed index = 10, we rename all rooms index = 10 + n with the decrement of its room number
                                }
                                else {
                                    System.out.printf("\n\033[31mError. Room number %d has an active reservation.\033[37m\n", index);
                                }
                               
                            } else {
                                System.out.printf("\n\033[31mError. Room number %d not found.\033[37m\n", index);
                            }
                        }
                    } else {
                        System.out.printf(
                                "\n\033[31mError. Cannot delete room. There is only 1 room left in hotel '%s'.\033[37m\n",
                                hotel.getName());
                    }
                }
                case 4 -> {
                    // change price

                    System.out.printf("You selected to \033[34mchange the price\033[37m of hotel '%s'.\n", name);
                    System.out.printf("\nEnter the new price for the rooms of the hotel: ");

                    double newPrice = getInputDBL(sc);

                    if (hotel.changePrice(newPrice)) {
                        System.out.printf("\033[33m\nThe rooms of hotel '%s' have been changed to %.2f.\033[37m\n", hotel.getName(),
                                hotel.getBasePrice());
                    } else if (newPrice <= 100) {
                        System.out.printf(
                                "\n\033[31mError. The base price of hotel '%s' has not been changed because the new price is too low. It must be greater than or equal to 100.\033[37m\n",
                                hotel.getName());
                    } else {
                        System.out.printf(
                                "\n\033[31mError. The base price of hotel '%s' has not been changed because there's an ongoing reservation.\033[37m\n",
                                hotel.getName());
                    }
                }

                case 5 -> {
                    System.out.printf("You selected to \033[34mremove a reseravation\033[37m in hotel '%s'.\n", name);

                    // get the name
                    System.out.print("Enter guest name for reservation removal: ");
                    String guestName = sc.nextLine();

                    // get the check-in date
                    System.out.print("Enter check-in date of the reservation to remove (1-31): ");
                    int checkinDate = getInput(sc);

                    if (checkinDate < 0 || checkinDate > 31){
                        System.out.printf("\n\033[31mError. Check-in date %d not within this month.\033[37m\n", checkinDate);
                    }
                    else if (hotel.removeReservation(guestName, checkinDate)) {
                        // if hotel reservation is removed or true
                        System.out.printf("\n\033[33mReservation removed successfully.\033[37m\n");
                    } else {
                        // doesn't cancel the reservation if invalid
                        // if the remove reservation does not remove anything
                        System.out.printf("\n\033[31mError. Reservation with guest '%s' and check-in date %d not found.\033[37m\n", guestName, checkinDate);
                    }
                }
                case 6 -> {
                    System.out.printf("You selected to \033[34mdelete the hotel '%s'\033[37m.\n", name);
                    System.out.printf("\n\033[33mPreparing hotel '%s' for removal..\033[37m\n", hotel.getName());

                    hotel.prepareForRemoval();

                    System.out.printf("\n\033[33mHotel data cleared..\033[37m\n");

                    hotels.remove(hotel);

                    System.out.println("Hotel removed successfully.");

                    return; // Exit after removal
                }
                default ->
                    System.out.printf("\n\033[31mError. Invalid choice. Try again.\033[37m\n");
            }
        } else {
            System.out.printf("\n\033[31mError. Hotel '%s' is not found.\033[37m\n", name);
        }
    }
```