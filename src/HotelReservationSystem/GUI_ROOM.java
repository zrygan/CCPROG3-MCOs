package HotelReservationSystem;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_ROOM extends GUI {
    private final GUI_MANAGE mains;
    private final String hotelName;

    public GUI_MANAGE getMains(){
        return mains;
    }

    public String getHotelName(){
        return hotelName;
    }

    public GUI_ROOM(HRS hrs, int window_height, int window_width, GUI_MANAGE mains, String hotelName){
        super(hrs, window_height, window_width);
        this.mains = mains;
        this.hotelName = hotelName;
    }

    @Override
    public void GUI_CONFIG_WINDOW_CLOSE(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mains.setWindowChecker_manage(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                mains.setWindowChecker_manage(false);
            }
        });
    }

    @Override
    public void init(){
        setTitle("Hotel Reservation System: Adding a Room in " + hotelName);
        setSize(window_width, window_height);
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        setVisible(true);
    }
}
