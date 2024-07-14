package HotelReservationSystem;

public class GUI_MANAGE extends GUI_MAIN {
    private void init(){
        setTitle("Hotel Reservation System : Managing a Hotel");
        setSize(window_width, window_height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        setVisible(true);
        CONFIG_AT_CLOSE();

    }

    public GUI_MANAGE(HRS hrs) {
        super(hrs, 800, 500);
        init();
    }
}
