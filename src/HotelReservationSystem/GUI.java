package HotelReservationSystem;

import javax.swing.*;

public abstract class GUI extends JFrame {
    protected final HRS hrs;
    protected final int window_height;
    protected final int window_width;

    public GUI(HRS hrs, int window_height, int window_width){
        this.hrs = hrs;
        this.window_height = window_height;
        this.window_width = window_width;
    }
    
    public abstract void init();

    public abstract void GUI_CONFIG_WINDOW_CLOSE();
}