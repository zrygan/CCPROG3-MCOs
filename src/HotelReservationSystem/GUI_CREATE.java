package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A class that extends GUI that creates the new hotel window
 */
public class GUI_CREATE extends GUI {
    /**
     * The main GUI component
     */
    private final GUI_MAIN mains;

    /**
     * The name of the hotel
     */
    private String hotel_name;

    /**
     * The type of room (e.g. single, double, suite, etc.)
     */
    private int type_num;

    /**
     * The total number of rooms
     */
    private int room_tot;

    /**
     * Getter for the total number of rooms
     * 
     * @return the total number of rooms
     */
    public int getRoom_tot() {
        return room_tot;
    }

    /**
     * Setter for the total number of rooms
     * 
     * @param room_tot the total number of rooms
     */
    public void setRoom_tot(int room_tot) {
        this.room_tot = room_tot;
    }

    /**
     * Getter for the room type number
     * 
     * @return the room type number
     */
    public int getType_num() {
        return type_num;
    }

    /**
     * Setter for the room type number
     * 
     * @param type_num the room type number
     */
    public void setType_num(int type_num) {
        this.type_num = type_num;
    }

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
     * @param hotel_name the hotel name
     */
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    /**
     * Getter for the main GUI component
     * 
     * @return the main GUI component
     */
    public GUI_MAIN getMains() {
        return mains;
    }

    /**
     * The constructor for GUI_CREATE
     * 
     * @param hrs           the hrs database
     * @param window_height the window height
     * @param window_width  the window width
     * @param mains         the main window
     */
    public GUI_CREATE(HRS hrs, int window_height, int window_width, GUI_MAIN mains) {
        super(hrs, window_height, window_width);
        this.mains = mains;
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
        setTitle("Hotel Reservation System: Creating a hotel");
        setSize(window_width, window_height);
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels = Assets.ASSET_ADD_PANELS(2);

        /*
         * code of TOP PANEL (panel : 0)
         * contains: TOP TITLE
         */
        panels.getFirst().setBounds(0, 0, window_width, 100); // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] { "<b>Hotel Reservation System</b>", ">>> Creating a Hotel" };
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /*
         * code of Bottom PANEL (panel : 1)
         * contains:
         */
        panels.get(1).setBounds(0, 100, window_width, 400); // panels[1] = bottom panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

        // [MENU BAR] Hotel Name Menu Bar
        JTextField hotel_name = Assets.ASSET_TEXT_FIELD("Hotel Name");
        hotel_name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setHotel_name(hotel_name.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setHotel_name(hotel_name.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text fields
            }
        });
        hotel_name.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(hotel_name);

        panels.get(1).add(Assets.ASSET_SEPARATOR(window_width / 2));

        JSpinner num_room = Assets.ASSET_SPINNER(1, 50);
        num_room.setValue(1);
        setRoom_tot((int) num_room.getValue());
        num_room.setPreferredSize(new Dimension(300, 45));
        num_room.addChangeListener(e -> {
            setRoom_tot((int) num_room.getValue());
        });
        panels.get(1).add(num_room);

        panels.get(1).add(Assets.ASSET_SEPARATOR(window_width / 2));

        // [MENU BAR] Room Type Menu Bar
        JMenuBar room_type = Assets.ASSET_MENU_BAR();
        JMenu type_menu = Assets.createMenu("Room Type");
        setType_num(0);
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
        panels.get(1).add(room_type);

        JButton createButton = Assets.ASSET_ACCENT_BUTTON("Create Hotel");
        createButton.addActionListener(e -> {
            if (getHotel_name() != null || getRoom_tot() == 0 || getType_num() == 0) { // FIXME: WTF IS WRONG WITH THIS
                Assets.ASSET_PANE(this, "Please fill out all required fields.", "HRS: Error");
            } else {
                if (hrs.fetchHotel(getHotel_name()) != null) {
                    Assets.ASSET_PANE(this, "Hotel name already exists.", "HRS: Error");
                } else {
                    hrs.createHotel(getHotel_name(), getType_num(), getRoom_tot());
                    Assets.ASSET_PANE(this, "Hotel created successfully.", "HRS");
                    mains.setWindowChecker(false);
                    dispose();
                }
            }
        });
        panels.get(1).add(createButton);

        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }

}