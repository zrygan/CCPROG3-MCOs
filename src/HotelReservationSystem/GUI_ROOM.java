package HotelReservationSystem;

public class GUI_ROOM extends GUI_MAIN {
    private final String hotel;

    public String getHotel(){
        return hotel;
    }

   private void init(){
        setTitle("Hotel Reservation System : Creating a Room in Hotel " + hotel);
        setSize(window_width, window_height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        setVisible(true);
        CONFIG_AT_CLOSE();
    }

    public GUI_ROOM(HRS hrs, String hotel) {
        super(hrs, 500, 600);
        this.hotel = hotel;
        init();
    }
}