package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GUI_SIM_BOOK extends GUI {
    private final GUI_MAIN mains;

    public GUI_MAIN getMains(){
        return mains;
    }

    public GUI_SIM_BOOK(HRS hrs, int window_height, int window_width, GUI_MAIN mains){
        super(hrs, window_height, window_width);
        this.mains = mains;
    }

    @Override
    public void GUI_CONFIG_WINDOW_CLOSE() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mains.setWindowChecker(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                mains.setWindowChecker(false);
            }
        });
    }

    @Override
    public void init(){
        setTitle("Hotel Reservation System: Simulate Booking");
        setSize(window_width, window_height); // width = 500 (px) height = 750 (px)
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(getLayout());
        setResizable(false);

        ArrayList<JPanel> panels = Assets.ASSET_ADD_PANELS(6);

        /* code of TOP PANEL (panel : 0)
        *  contains:   TOP TITLE
        */
        panels.getFirst().setBounds( 0, 0, window_width, 100);        // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] {"<b>Hotel Reservation System</b>", ">>> Simulate Booking"};
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /* code of TOP-MID PANEL (panel : 1)
         *  contains: Guest Name Text box and Hotel Name Menu Bar
         */
        panels.get(1).setBounds( 0, 100, window_width, 110);        // panels [1] = top-mid panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 20,  10));

        // [TEXT BOX] Guest Name
        JTextField guest_name = Assets.ASSET_TEXT_FIELD("Guest Name");
        guest_name.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(guest_name);
        
        panels.add(Assets.ASSET_SEPARATOR(window_width));

        // [MENU BAR] Hotel Name Menu Bar
        JMenuBar hotel_name = Assets.ASSET_MENU_BAR(hrs, "Hotel Name");
        hotel_name.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(hotel_name);

        /* code of MID-LEFT PANEL (panel : 2)
         * contains: In-Day text box
         */
        panels.get(2).setBounds( 0, 210, window_width/2, 100);        // panels[2] = mid-left panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 50));

        // [TEXT BOX] In-Day
        JTextField in_day = Assets.ASSET_TEXT_FIELD("In-Day");
        in_day.setPreferredSize(new Dimension(75, 45));
        panels.get(2).add(in_day);

        /* code of MID-RIGHT PANEL (panel : 3)
         * contains: Out-Day text box
         */
        panels.get(3).setBounds(window_width/2, 210, window_width, 100);
        panels.get(3).setLayout(new FlowLayout(FlowLayout.LEFT, 10, 50));

        // [TEXT BOX] In-Day
        JTextField out_day = Assets.ASSET_TEXT_FIELD("In-Day");
        out_day.setPreferredSize(new Dimension(75, 45));
        panels.get(2).add(out_day);

        for (JPanel panel : panels){
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
