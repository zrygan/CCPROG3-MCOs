package HotelReservationSystem;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A class that extends GUI that creates the change DPM window under manage hotel
 */
public class GUI_DPM extends GUI {
    /**
     * The main GUI component for managing
     */
    private final GUI_MANAGE mains;

    /**
     * The name of the hotel
     */
    private final String hotelName;

    /**
     * The room option chosen
     */
    private int room_opt;

    /**
     * The number of rooms
     */
    private String room_num;

    /**
     * The number of days
     */
    private int day_num;

    /**
     * The price modifier
     */
    private double price_mod;

    /**
     * Getter for the price modifier
     * 
     * @return the price modifier
     */
    public double getPrice_mod() {
        return price_mod;
    }

    /**
     * Setter for the price modifier
     * 
     * @param price_mod the price modifier value
     */
    public void setPrice_mod(double price_mod) {
        this.price_mod = price_mod;
    }

    /**
     * Getter for the day number
     * 
     * @return the day number
     */
    public int getDay_num() {
        return day_num;
    }

    /**
     * Setter for the day number
     * 
     * @param day_num the day number value
     */
    public void setDay_num(int day_num) {
        this.day_num = day_num;
    }

    /**
     * Getter for the room number
     * 
     * @return the room number
     */
    public String getRoom_num() {
        return room_num;
    }

    /**
     * Setter for the room number
     * 
     * @param room_num the room number value
     */
    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    /**
     * Getter for the room option
     * 
     * @return the room option
     */
    public int getRoom_opt() {
        return room_opt;
    }

    /**
     * Setter for the room option
     * 
     * @param room_opt the room option value
     */
    public void setRoom_opt(int room_opt) {
        this.room_opt = room_opt;
    }

    /**
     * Getter for the hotel name
     * 
     * @return the hotel name
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Getter for the main GUI component
     * 
     * @return the main GUI component
     */
    public GUI_MANAGE getMains() {
        return mains;
    }

    /**
     * The constructor for GUI_DPM
     * 
     * @param hrs           the hrs database
     * @param window_height the window height
     * @param window_width  the window width
     * @param mains         the main window
     * @param hotelName     the hotel name
     */
    public GUI_DPM(HRS hrs, int window_height, int window_width, GUI_MANAGE mains, String hotelName) {
        super(hrs, window_height, window_width);
        this.mains = mains;
        this.hotelName = hotelName;
    }

    /**
     * {@inheritDoc}
     * 
     * This method overrides the {@code GUI_CONFIG_WINDOW_CLOSE} method in
     * {@code GUI}.
     * This sets the setWindowChecker attribute of the mains attribute of GUI_DPM
     * as false.
     * 
     * @see GUI#GUI_CONFIG_WINDOW_CLOSE()
     */
    @Override
    public void GUI_CONFIG_WINDOW_CLOSE() {
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
        setTitle("Discount Price Modifier");
        setSize(window_width, window_height); // width and height = 500
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels = Assets.ASSET_ADD_PANELS(2);

        /*
         * code of TOP PANEL (panel : 0)
         * contains: TOP TITLE
         */
        panels.getFirst().setBounds(0, 0, window_width, 100); // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] { "<b>Hotel Reservation System</b>",
                ">>> Modifying Date Prices for " + hotelName };
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /*
         * code of MIDDLE PANEL (panel : 1)
         * contains: Hotel Name Menu Bar
         */
        panels.get(1).setBounds(0, 100, window_width, 500);
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // [JMenu] Room selection
        JMenuBar room_choice = Assets.ASSET_MENU_BAR();
        JMenu room_pick = Assets.createMenu("Choose Room Option");
        room_choice.add(room_pick);
        JMenuItem one_room = Assets.createMenuItem("One Room");
        one_room.addActionListener(_ -> {
            setRoom_opt(1);
            room_pick.setText(one_room.getText());
        });
        JMenuItem all_rooms = Assets.createMenuItem("All Rooms");
        all_rooms.addActionListener(_ -> {
            setRoom_opt(2);
            room_pick.setText(all_rooms.getText());
        });
        room_pick.add(one_room);
        room_pick.add(all_rooms);
        room_choice.setPreferredSize(new Dimension(300, 45));
        room_pick.setPreferredSize(new Dimension(300, 45));
        one_room.setPreferredSize(new Dimension(300, 45));
        all_rooms.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(room_choice);

        // [JSpinner] Day selection
        JSpinner day_spinner = Assets.ASSET_SPINNER(1, 31);
        day_spinner.setValue(1);
        setDay_num((int) day_spinner.getValue());
        day_spinner.addChangeListener(_ -> setDay_num((int) day_spinner.getValue()));
        day_spinner.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(day_spinner);

        // [JTextField] Room number selection
        JTextField room_number = Assets.ASSET_TEXT_FIELD("Enter Room Number");
        room_number.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setRoom_num(room_number.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setRoom_num(room_number.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text fields
            }
        });
        room_number.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(room_number);

        // [JTextField] Price modifier
        JTextField price_modifier = Assets.ASSET_TEXT_FIELD("Enter Price Modifier");
        price_modifier.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Not used
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                updatePriceModifier();
            }

            private void updatePriceModifier() {
                String text = price_modifier.getText();
                if (text.isEmpty()) {
                    // Don't try to parse if the field is empty
                    return;
                }
                try {
                    setPrice_mod(Double.parseDouble(text));
                } catch (NumberFormatException ex) {
                    // Handle the exception, for example, clear the field or show an error message
                    price_modifier.setText("");
                    Assets.ASSET_PANE(getMains(), "Please enter a valid number.", "HRS: Error");
                }
            }
        });
        price_modifier.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(price_modifier);

        // [JButton] DPM Button
        JButton dpm_button = Assets.ASSET_ACCENT_BUTTON("Apply DPM");
        dpm_button.addActionListener(_ -> {
            if ((getRoom_opt() == 1 || getRoom_opt() == 2) && getPrice_mod() > 0) {
                Hotel hotel = hrs.fetchHotel(getHotelName());
                if (getRoom_opt() == 1) {
                    if (!getRoom_num().isEmpty()) {
                        Room room = hotel.fetchRoom(hotel.getName() + "_Room_" + getRoom_num());
                        if (room != null) {
                            room.changeDPM(getDay_num() - 1, getPrice_mod());
                            Assets.ASSET_PANE(this, "Discount Price Modifier applied successfully.", "HRS");
                        } else {
                            Assets.ASSET_PANE(this, "Room not found.", "HRS: Error");
                        }
                    } else {
                        Assets.ASSET_PANE(this, "Please fill out all required fields.", "HRS: Error");
                    }
                } else if (getRoom_opt() == 2) {
                    hotel.changeDPMs(getDay_num() - 1, getPrice_mod());
                    Assets.ASSET_PANE(this, "Discount Price Modifier applied successfully for all rooms.", "HRS");
                }
            } else {
                Assets.ASSET_PANE(this, "Please fill out all required fields.", "HRS: Error");
            }
        });
        dpm_button.setPreferredSize(new Dimension(300, 90));
        panels.get(1).add(dpm_button);

        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}