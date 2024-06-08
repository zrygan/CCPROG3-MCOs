public class Driver {
    public static void main(String[] args) {
        Hotel a = new Hotel("MyHotel", 10);

        // create a new room
        a.newRoom();

        a.getRooms()[0].checkIn();
        a.getRooms()[0].checkIn();
        a.getRooms()[0].checkIn();

        a.manage("ROOM", 0);
    }
}
