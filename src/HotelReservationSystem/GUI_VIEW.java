package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 * A class that extends GUI that creates the view hotel window
 */
public class GUI_VIEW extends GUI {
    /**
     * The main GUI component
     */
    private final GUI_MAIN mains;

    /**
     * The name of the hotel
     */
    private String hotel_name;

    /**
     * Getter for the hotel name
     * 
     * @return the hotel name
     */
    public String getHotel_name() {
        return hotel_name;
    }

    /**
     * Setter for the hotel name
     * 
     * @param hotel_name the name of the hotel
     */
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    /**
     * Getter for the GUI_MAIN window
     * 
     * @return the GUI_MAIN component
     */
    public GUI_MAIN getMains() {
        return mains;
    }

    /**
     * Constructor for GUI_VIEW
     * 
     * @param hrs           the hrs database
     * @param window_height the height of the window
     * @param window_width  the width of the window
     * @param mains         the GUI_MAIN of the window
     */
    public GUI_VIEW(HRS hrs, int window_height, int window_width, GUI_MAIN mains) {
        super(hrs, window_height, window_width);
        this.mains = mains;
    }

    /**
     * {@inheritDoc}
     * 
     * This method overrides the {@code GUI_CONFIG_WINDOW_CLOSE} method in
     * {@code GUI}.
     * This sets the setWindowChecker attribute of the mains attribute of GUI_VIEW
     * as false.
     * 
     * @see GUI#GUI_CONFIG_WINDOW_CLOSE()
     */
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

    /**
     * {@inheritDoc}
     * 
     * This method overrides the {@code init} method in {@code GUI}.
     * This method initializes the GUI.
     * 
     * @see GUI#init()
     */
    @Override
    public void init() {
        setTitle("Hotel Reservation System: Viewing a hotel");
        setSize(window_width, window_height);
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels = Assets.ASSET_ADD_PANELS(3);

        /*
         * code of TOP PANEL (panel : 0)
         * contains: TOP TITLE
         */
        panels.getFirst().setBounds(0, 0, window_width, 100); // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] { "<b>Hotel Reservation System</b>", ">>> Viewing a Hotel" };
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /*
         * code of LEFT PANEL (panel : 1)
         * contains:
         */
        panels.get(1).setBounds(0, 100, window_width / 2 + 200, window_height); // panels[1] = left panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        JTextArea output = Assets.ASSET_OUTPUT_BOX();
        output.setPreferredSize(new Dimension(500, 400));
        panels.get(1).add(output);

        /*
         * code of RIGHT PANEL (panel : 2)
         * contains:
         */
        panels.get(2).setBounds(window_width / 2 + 200, 100, window_width / 2 - 200, window_height); // panels[2] =
                                                                                                     // right panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // [MENU BAR] Hotel Name Button
        JMenuBar view_name = Assets.ASSET_MENU_BAR();
        JMenu hotelMenu = Assets.createMenu("Hotel Name");
        view_name.add(hotelMenu);
        for (Hotel hotel : hrs.getHotels()) {
            JMenuItem hotelList = Assets.createMenuItem(hotel.getName());
            hotelList.addActionListener(_ -> {
                setHotel_name(hotel.getName());
                hotelMenu.setText(hotel.getName());
            });
            hotelMenu.add(hotelList);
        }
        panels.get(2).add(view_name);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width / 2));

        JButton high_lvl_info = Assets.ASSET_ACCENT_BUTTON("High Level Info");
        high_lvl_info.addActionListener(_ -> {
            StringBuilder out = new StringBuilder();
            Hotel hotel = hrs.fetchHotel(getHotel_name());
            if (hotel != null) {
                out.append("\n     Hotel: ").append(hotel.getName());
                out.append("\n     \thas ").append(hotel.roomTypeCount()[0]).append(" Standard rooms");
                out.append("\n     \thas ").append(hotel.roomTypeCount()[1]).append(" Deluxe rooms");
                out.append("\n     \thas ").append(hotel.roomTypeCount()[2]).append(" Executive rooms");
                out.append("\n     \thas earned ").append(hotel.getEarnings());
                out.append("\n     \tRoom base price range from ").append(hotel.getBasePrice()).append(" to ")
                        .append(hotel.getBasePrice() + (hotel.getBasePrice() * 0.35));
                out.append("\n     \thas average date price modifier ").append(hotel.getAverageDPM());
                output.setText(out.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Hotel not found!");
            }
        });
        panels.get(2).add(high_lvl_info);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width / 2));

        JTextField ent_day = Assets.ASSET_TEXT_FIELD("Enter Day");
        ent_day.addActionListener(_ -> {
            StringBuilder out = new StringBuilder();
            int day = Integer.parseInt(ent_day.getText());
            Hotel hotel = hrs.fetchHotel(getHotel_name());
            if (getHotel_name() != null) {
                if (hotel.fetchAvails(1, day).isEmpty()) {
                    out.append("\n     Available rooms of hotel ").append(hotel.getName()).append(" on day ")
                            .append(day).append(": NONE");
                } else {
                    out.append("\n     Available rooms of hotel ").append(hotel.getName()).append(" on day ")
                            .append(day).append(": \n");
                    out.append("     \t");
                    for (String room : hotel.fetchAvails(1, day)) {
                    String roomNum = room.replace(hotel.getName() + "_Room_", "");
                       out.append(roomNum).append(" ");
                    }
                }
                if (hotel.fetchAvails(0, day).isEmpty()) {
                    out.append("\n     Booked rooms of hotel ").append(hotel.getName()).append(" on day ").append(day)
                            .append(": NONE");
                } else {
                    out.append("\n     Booked rooms of hotel ").append(hotel.getName()).append(" on day ").append(day)
                            .append(": \n");
                    out.append("     \t");
                    int index2 = 0;
                    for (String room : hotel.fetchAvails(0, day)) {
                        index2++;
                        if (index2 % 16 == 0) out.append("\n\t");
                        String roomNum = room.replace(hotel.getName() + "_Room_", "");
                        out.append(roomNum).append(" ");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hotel not found!");
            }
            output.setText(out.toString());
        });
        panels.get(2).add(ent_day);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width / 2));

        JTextField res_name = Assets.ASSET_TEXT_FIELD("Guest Name");
        res_name.addActionListener(_ -> {
            StringBuilder out = new StringBuilder();
            String reservation = res_name.getText();
            Hotel hotel = hrs.fetchHotel(getHotel_name());
            if (hotel != null) {
                for (Reservation guest_res : hotel.getReservations()) {
                    if (guest_res.getGuest().equals(reservation)) {
                        out.append("\n\tReservation ").append(guest_res.getReservationNumber()).append(" under guest ")
                                .append(guest_res.getGuest()).append("\n");
                        out.append("\n\t\t===== RECEIPT =====");
                        out.append("\n\t\tname:\t").append(guest_res.getGuest());
                        out.append("\n\t\thtl :\thotel ").append(hotel.getName());
                        out.append("\n\t\troom:\t").append(guest_res.getRoom().getName());
                        out.append("\n\t\tin  :\t%d").append(guest_res.getCheckin());
                        out.append("\n\t\tout :\t%d").append(guest_res.getCheckout());
                        out.append("\n\t\tcost\tPHP ").append(guest_res.getTotal());
                        out.append("\n\t\t===================\n");
                    }
                }
                output.setText(out.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Hotel not found!");
            }

        });
        panels.get(2).add(res_name);

        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}