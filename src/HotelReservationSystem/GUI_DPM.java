package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class GUI_DPM extends GUI {
    private final GUI_MANAGE mains;
    private final String hotelName;
    private int room_opt;
    private String room_num;
    private int day_num;
    private double price_mod;
    
    public double getPrice_mod() {
        return price_mod;
    }

    public void setPrice_mod(double price_mod) {
        this.price_mod = price_mod;
    }

    public int getDay_num() {
        return day_num;
    }

    public void setDay_num(int day_num) {
        this.day_num = day_num;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }


    public int getRoom_opt() {
        return room_opt;
    }

    public void setRoom_opt(int room_opt) {
        this.room_opt = room_opt;
    }

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
        String[] title_top = new String[] {"<b>Hotel Reservation System</b>", ">>> Modifying Date Prices for " + hotelName};
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
            setRoom_opt(1);
            room_pick.setText(one_room.getText());
        });
        JMenuItem all_rooms = Assets.createMenuItem("All Rooms");
        all_rooms.addActionListener(e -> {
            setRoom_opt(2);
            room_pick.setText(all_rooms.getText());
        });
        room_pick.add(one_room);
        room_pick.add(all_rooms);
        panels.get(1).add(room_choice);

        // [JSpinner] Day selection
        JSpinner day_spinner = Assets.ASSET_SPINNER(1, 31);
        day_spinner.setValue(1);
        setDay_num((int) day_spinner.getValue());
        day_spinner.setPreferredSize(new Dimension(75, 45));
        day_spinner.addChangeListener(e -> {
            setDay_num((int) day_spinner.getValue());
        });
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
        panels.get(1).add(room_number);

        // [JTextField] Price modifier
        JTextField price_modifier = Assets.ASSET_TEXT_FIELD("Enter Price Modifier");
        price_modifier.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setPrice_mod(Double.parseDouble(price_modifier.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setPrice_mod(Double.parseDouble(price_modifier.getText()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text fields
            }
        });
        panels.get(1).add(price_modifier);

        // [JButton] DPM Button
        JButton dpm_button = Assets.ASSET_ACCENT_BUTTON("Apply DPM");
        dpm_button.addActionListener(e -> {
            if((getRoom_opt() == 1 || getRoom_opt() == 2) && getPrice_mod() > 0) {
                Hotel hotel = hrs.fetchHotel(getHotelName());
                if (getRoom_opt() == 1) {
                    if (!getRoom_num().isEmpty()) {
                        Room room = hotel.fetchRoom(hotel.getName() + "_Room_" + getRoom_num());
                        if (room!= null) {
                            room.changeDPM(getDay_num() - 1, getPrice_mod());
                            JOptionPane.showMessageDialog(null, "Discount Price Modifier applied successfully.");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Room not found.");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please fill out all required fields.");
                    }
                }
                else if (getRoom_opt() == 2) {
                    hotel.changeDPMs(getDay_num() - 1, getPrice_mod());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill out all required fields.");
            }
        });
        panels.get(1).add(dpm_button);

        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}