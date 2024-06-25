# CCPROG3 MCO1 Presentation

Zhean Ganituen and Jaztin Jimenez

## Member Contribution

```java
/**
 * Removes the reservation from a hotel room.
 *
 * @param guestName the name of the guest
 * @param checkInDate the date of the guest's check in
 *
 * @return {true} if the reservation was removed, {false} otherwise
 *
 * @author Zhean Ganituen, Jaztin Jimenez <- attributiong are here
 */
```

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
