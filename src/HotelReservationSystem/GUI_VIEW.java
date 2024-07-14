package HotelReservationSystem;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_VIEW extends GUI_MAIN {
    private void init(){
        setTitle("Hotel Reservation System : Viewing a Hotel");
        setSize(window_width, window_height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        setVisible(true);
        CONFIG_AT_CLOSE();
    }

    public GUI_VIEW(HRS hrs) {
        super(hrs, 800, 900);
        init();
    }
}