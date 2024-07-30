package HotelReservationSystem;

/**
 * A class that represents standard room extends Room
 */
public class RoomSTD extends Room {

    /**
     * Constructor for the Room object
     *
     * @param name      the name of the room, must be different for each room
     * @param hotel     the hotel of the room
     * @param basePrice the base price of the room
     */
    public RoomSTD(String name, Hotel hotel, double basePrice) {
        super(name, hotel, basePrice);
    }

    /**
     * {@inheritDoc}
     *
     * This method overrides the {@code getRoomType} method in
     * {@code Room}.
     * This returns the correct room type
     *
     * @see Room#getRoomType()
     */
    @Override
    public int getRoomType() {
        return 1;
    }

}
