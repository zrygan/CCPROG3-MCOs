import java.util.Scanner;

public class Driver{
    public static void main(String[] args) {
        HRS hrs = new HRS();
        boolean run = true;
        Scanner sc = new Scanner(System.in); // initialize scanner
        
        // Debugging Code [DC1]
        hrs.createHotel("max");
        Hotel max = hrs.fetchHotel("max");
        // max.newRoom(); 
        // max.newRoom(); 
        max.newRoom(); // there should be 3 rooms in `max` [room_0 to room_2]
        // max.getRooms().get(0).checkIn();  // check-in in one of the rooms
                                                // so now there should be some earnings in max
                                                // remove this if need to check for changing base price

        // [11.06.2024] Check View hotel        >>> NO      >> YES 
        // [20.06.2024] Check I/O               >>> NO      >> YES
        // [20.06.2024] Check: (a) change name  >>> YES
        //                     (b) add room     >>> YES
        //                     (c) delete room  >>> NO      >> YES
        //                     (d) change price  
        //                          reserved    >>> NO      >> NO
        //                          !reserved   >>> NO      >> YES

        while(run){
            hrs.showMenu();

            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine(); // consume new line

            switch (option) {
                case 1 -> hrs.createHotelUI(sc);
                case 2 -> hrs.viewHotelUI(sc);
                case 3 -> hrs.manageHotelUI(sc);
                case 4 -> hrs.simBookingUI(sc);
                case 0 -> {
                    System.out.println("Exiting the Hotel Reservation System...");
                    run = false;
                }
                case 9 -> hrs.showHotels();
            }
            sc.nextLine(); // consume new line
        }
    }
}
