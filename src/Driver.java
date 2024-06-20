import java.util.Scanner;

public class Driver{
    public static void main(String[] args) {
        HRS hrs = new HRS();
        boolean run = true;
        Scanner sc = new Scanner(System.in); // initialize scanner
        
        // // Debugging code: 11.06.2024
        // hrs.createHotel("max");
        // Hotel max = hrs.fetchHotel("max");
        // max.newRoom(); 
        // max.newRoom(); 
        // max.newRoom(); // there should be 3 rooms in `max`
        // max.getRooms().get(0).checkIn();  // check-in in one of the rooms
        //                                         // so now there should be some earnings in max

        // // END      11.06.2024

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
                case 5 -> {
                    System.out.println("Exiting the Hotel Reservation System...");
                    run = false;
                }
            }
            sc.nextLine(); // consume new line
        }
    }
}
