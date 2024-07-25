package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUI_SIM_BOOK extends GUI {
    private final GUI_MAIN mains;
    private String hotel_name;
    private int in_Day;
    private int out_Day;
    private String discount;
    private String guest_name;
    private int type_num;

    public int getType_num() {
        return type_num;
    }

    public void setType_num(int type_num) {
        this.type_num = type_num;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getOut_Day() {
        return out_Day;
    }

    public void setOut_Day(int out_Day) {
        this.out_Day = out_Day;
    }

    public int getIn_Day() {
        return in_Day;
    }

    public void setIn_Day(int in_Day) {
        this.in_Day = in_Day;
    }

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
        guest_name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setGuest_name(guest_name.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setGuest_name(guest_name.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text fields
            }
            
        });
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
                hotelList.setPreferredSize(new Dimension(300, 45));
            });
            hotelMenu.add(hotelList);
        }
        hotel_bar.setPreferredSize(new Dimension(300, 45));
        hotelMenu.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(hotel_bar);

        /* code of MID-LEFT PANEL (panel : 2)
         * contains: In-Day text box
         */
        panels.get(2).setBounds( 0, 210, window_width/2, 100);        // panels[2] = mid-left panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 30));

        // [TEXT BOX] In-Day
        JSpinner in_day = Assets.ASSET_SPINNER(1, 31);
        in_day.setValue(1);
        setIn_Day((int) in_day.getValue());
        in_day.setPreferredSize(new Dimension(75, 45));
        in_day.addChangeListener(e -> {
            setIn_Day((int) in_day.getValue());
        });
        panels.get(2).add(in_day);

        /* code of MID-RIGHT PANEL (panel : 3)
         * contains: Out-Day text box
         */
        panels.get(3).setBounds(window_width/2, 210, window_width, 100);
        panels.get(3).setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));

        // [TEXT BOX] In-Day
        JSpinner out_day = Assets.ASSET_SPINNER(1, 31);
        out_day.setValue(1);
        setOut_Day((int) out_day.getValue());
        out_day.setPreferredSize(new Dimension(75, 45));
        out_day.addChangeListener(e -> {
            setOut_Day((int) out_day.getValue());
        });
        panels.get(3).add(out_day);

        /* code of MID-LOW PANEL (panel : 4)
         * contains: Room type Menu Bar, Discount Code text field
         */
        panels.get(4).setBounds( 0, 310, window_width, 130);
        panels.get(4).setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));


        // [TEXT BOX] Discount Code
        JTextField discount_code = Assets.ASSET_TEXT_FIELD("Discount Code");
        discount_code.setPreferredSize(new Dimension(300, 45));
        discount_code.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String temp = hrs.verifyDiscount(discount_code.getText());
                setDiscount(temp);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String temp = hrs.verifyDiscount(discount_code.getText());
                setDiscount(temp);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text fields
            }
        });
        panels.get(4).add(discount_code);

        // [MENU BAR] Room Type Menu Bar
        JMenuBar room_type = Assets.ASSET_MENU_BAR();
        JMenu type_menu = Assets.createMenu("Room Type");
        room_type.add(type_menu);
        JMenuItem std_type = Assets.createMenuItem("Standard Room");
        std_type.addActionListener(e -> {
            setType_num(1);
            type_menu.setText(std_type.getText());
        });
        JMenuItem del_type = Assets.createMenuItem("Deluxe Room");
        del_type.addActionListener(e -> {
            setType_num(2);
            type_menu.setText(del_type.getText());
        });
        JMenuItem ex_type = Assets.createMenuItem("Executive Room");
        ex_type.addActionListener(e -> {
            setType_num(3);
            type_menu.setText(ex_type.getText());
        });
        type_menu.add(std_type);
        type_menu.add(del_type);
        type_menu.add(ex_type);
        room_type.setPreferredSize(new Dimension(300, 45));
        type_menu.setPreferredSize(new Dimension(300, 45));
        std_type.setPreferredSize(new Dimension(300, 45));
        del_type.setPreferredSize(new Dimension(300, 45));
        ex_type.setPreferredSize(new Dimension(300, 45));
        panels.get(4).add(room_type);

        /* code of LOW PANEL (panel : 5)
         * contains: Book Room Button
         */
        panels.get(5).setBounds( 0, 440, window_width, 310);
        panels.get(5).setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        // [BUTTON] Book Room
        JButton book_room = Assets.ASSET_ACCENT_BUTTON("Book Room");
        book_room.addActionListener(e -> {
            Hotel hotel = hrs.fetchHotel(getHotel_name());
            // [DEBUGGING] Print out all the values
            System.out.println("Guest Name: " + getGuest_name());
            System.out.println("Hotel Name: " + getHotel_name());
            System.out.println("In-Day: " + getIn_Day());
            System.out.println("Out-Day: " + getOut_Day());
            System.out.println("Room Type: " + getType_num());
            System.out.println("Discount Code: " + getDiscount());
            hotel.bookRoom(getGuest_name(), getIn_Day(), getOut_Day(), getType_num(), getDiscount());
        });
        book_room.setPreferredSize(new Dimension(300, 100));
        panels.get(5).add(book_room);

        for (JPanel panel : panels){
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
