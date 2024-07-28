package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;


public class GUI_DPM extends GUI {
    private final GUI_MANAGE mains;
    private final String hotelName;

    public String getHotelName() {
        return hotelName;
    }

    public GUI_MANAGE getMains() {
        return mains;
    }

    public GUI_DPM(HRS hrs, int window_height, int window_width, GUI_MANAGE mains, String hotelName) {
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
    public void init() {
        setTitle("Discount Price Modifier");
        setSize(window_width, window_height); // width and height = 500
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels  = Assets.ASSET_ADD_PANELS(3);

        /* code of TOP PANEL (panel : 0)
         *  contains:   TOP TITLE
         */
        panels.getFirst().setBounds( 0, 0, window_width, 100);        // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] {"<b>Hotel Reservation System</b>", ">>> Modifying Discount Prices for " + hotelName};
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /* code of MIDDLE PANEL (panel : 1)
         *  contains:   Hotel Name Menu Bar
         */
        panels.get(1).setBounds(0, 100, window_width, 400);
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // [JMenu] Room selection
        JMenuBar room_choice = Assets.ASSET_MENU_BAR();
        JMenu room_pick = Assets.createMenu("Choose Room Option");
        room_choice.add(room_pick);
        JMenuItem one_room = Assets.createMenuItem("One Room");
        one_room.addActionListener(e -> {
            // FIXME: add
            room_pick.setText(one_room.getText());
        });
        JMenuItem all_rooms = Assets.createMenuItem("All Rooms");
        all_rooms.addActionListener(e -> {
            // FIXME: add
            room_pick.setText(all_rooms.getText());
        });
        room_pick.add(one_room);
        room_pick.add(all_rooms);
        panels.get(1).add(room_choice);

        // [JSpinner] 

        
}
