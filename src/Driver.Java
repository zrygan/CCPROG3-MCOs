public class Driver {
    public static void main(String[] args) {
        Hotel a = new Hotel("MyHotel", 10);

        /// create a new room
        a.create();

        System.out.println("Total rooms in hotel: " + a.getRoomTotal());
    }
}
