package HotelReservationSystem;

public class GUI_BOOKING extends GUI_MAIN {
    private void init(){
        setTitle("Hotel Reservation System : Booking Simulation");
        setSize(window_width, window_height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        setVisible(true);
        CONFIG_AT_CLOSE();
    }

    public GUI_BOOKING(HRS hrs) {
        super(hrs, 800, 900);
        init();
    }
}