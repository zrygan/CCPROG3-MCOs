package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GUI_SIM_BOOK extends GUI {
    private final GUI_MAIN mains;
    private String hotel_name;

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

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
        setLayout(null);
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

        // [MENU BAR] Hotel Name Menu Bar
        JMenuBar hotel_bar = Assets.ASSET_MENU_BAR();
        JMenu hotelMenu = Assets.createMenu("Hotel Name");
        hotel_bar.add(hotelMenu);
        for (Hotel hotel : hrs.getHotels()) {
            JMenuItem hotelList = Assets.createMenuItem(hotel.getName());
            hotelList.addActionListener(e -> {
                setHotel_name(hotel.getName());
                hotelMenu.setText(hotel.getName());
            });
            hotelMenu.add(hotelList);
        }
        hotel_bar.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(hotel_bar);

        /* code of MID-LEFT PANEL (panel : 2)
         * contains: In-Day text box
         */
        panels.get(2).setBounds( 0, 210, window_width/2, 100);        // panels[2] = mid-left panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 30));

        // [TEXT BOX] In-Day
        JTextField in_day = Assets.ASSET_TEXT_FIELD("In-Day");
        in_day.setPreferredSize(new Dimension(75, 45));
        panels.get(2).add(in_day);

        /* code of MID-RIGHT PANEL (panel : 3)
         * contains: Out-Day text box
         */
        panels.get(3).setBounds(window_width/2, 210, window_width, 100);
        panels.get(3).setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));

        // [TEXT BOX] In-Day
        JTextField out_day = Assets.ASSET_TEXT_FIELD("In-Day");
        out_day.setPreferredSize(new Dimension(75, 45));
        panels.get(3).add(out_day);

        /* code of MID-LOW PANEL (panel : 4)
         * contains: Room type Menu Bar, Discount Code text field
         */
        panels.get(4).setBounds( 0, 310, window_width, 130);
        panels.get(4).setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));


        // // [TEXT BOX] Discount Code
        // FIXME: hey ayusin mo toh
        // JTextField discount_code = Assets.ASSET_TEXT_FIELD("Discount Code");
        // discount_code.setPreferredSize(new Dimension(300, 45));
        // panels.get(4).add(discount_code);

        /* code of LOW PANEL (panel : 5)
         * contains: Book Room Button
         */
        panels.get(5).setBounds( 0, 440, window_width, 310);
        panels.get(5).setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        // [BUTTON] Book Room
        JButton book_room = Assets.ASSET_ACCENT_BUTTON("Book Room");
        //book_room.addActionListener(e -> {
            //String guest_name_str = guest_name.getText();
            //String hotel_name_str = ((JMenu) hotel_name.getSelectedItem()).getText();
            //int in_day_int = getInput(in_day);
            //int out_day_int = getInput(out_day);
            //String room_type_str = ((JMenu) room_type.getSelectedItem()).getText();
            //String discount_code_str = discount_code.getText();
            //hrs.simBooking(sc, guest_name_str, hotel_name_str, in_day_int, out_day_int, room_type_str, discount_code_str);
        //});
        book_room.setPreferredSize(new Dimension(300, 100));
        panels.get(5).add(book_room);

        for (JPanel panel : panels){
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
