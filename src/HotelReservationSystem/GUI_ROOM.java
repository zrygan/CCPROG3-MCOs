package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 * A class that extends GUI that creates the new room window under manage hotel
 */
public class GUI_ROOM extends GUI {
    /**
     * The main GUI component for managing hotel operations
     */
    private final GUI_MANAGE mains;

    /**
     * The name of the hotel
     */
    private final String hotelName;

    /**
     * The number of rooms available
     */
    private int num_room;

    /**
     * The type number of the room
     */
    private int type_num;

    /**
     * Getter for the room Type
     * 
     * @return the room type
     */
    public int getType_num() {
        return type_num;
    }

    /**
     * Setter for the room type
     * 
     * @param type_num the room type
     */
    public void setType_num(int type_num) {
        this.type_num = type_num;
    }

    /**
     * Getter for num_room
     * 
     * @return the value of num_room
     */
    public int getNum_room() {
        return num_room;
    }

    /**
     * Setter for num_room
     * 
     * @param num_room the value of num_room
     */
    public void setNum_room(int num_room) {
        this.num_room = num_room;
    }

    /**
     * The getter of the root GUI
     * 
     * @return the GUI_MAIN component
     */
    public GUI_MANAGE getMains() {
        return mains;
    }

    /**
     * The getter of the hotel name
     * 
     * @return the hotel name
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Constructor for the GUI room
     * 
     * @param hrs           the hrs database
     * @param window_height the window height
     * @param window_width  the window width
     * @param mains         the GUI_MAIN of the sub gui
     * @param hotelName     the name of the hotel
     */
    public GUI_ROOM(HRS hrs, int window_height, int window_width, GUI_MANAGE mains, String hotelName) {
        super(hrs, window_height, window_width);
        this.mains = mains;
        this.hotelName = hotelName;
    }

    /**
     * {@inheritDoc}
     * 
     * This method overrides the {@code GUI_CONFIG_WINDOW_CLOSE} method in
     * {@code GUI}.
     * This sets the setWindowChecker attribute of the mains attribute of GUI_ROOM
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
        setTitle("Hotel Reservation System: Adding a Room in " + getHotelName());
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
        String[] title_top = new String[] { "<b>Hotel Reservation System</b>", ">>> Adding a Room in " + hotelName };
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /*
         * code of MID PANEL (panel : 1)
         * contains: num_rooms, room_type, add_room button
         */
        panels.get(1).setBounds(0, 100, window_width, 500); // panels[1] = mid panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 50, 60));

        // [JSpinner] num_rooms
        JSpinner num_rooms = Assets.ASSET_SPINNER(1, 50);
        num_rooms.setValue(1);
        setNum_room((int) num_rooms.getValue());
        num_rooms.addChangeListener(_ -> setNum_room((int) num_rooms.getValue()));
        num_rooms.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(num_rooms);

        // [MENU BAR] Room Type Menu Bar
        JMenuBar room_type = Assets.ASSET_MENU_BAR();
        JMenu type_menu = Assets.ASSET_MENU("Room Type");
        setType_num(0);
        room_type.add(type_menu);
        JMenuItem std_type = Assets.CREATE_MENU_ITEM("Standard Room");
        std_type.addActionListener(_ -> {
            setType_num(1);
            type_menu.setText(std_type.getText());
        });
        JMenuItem del_type = Assets.CREATE_MENU_ITEM("Deluxe Room");
        del_type.addActionListener(_ -> {
            setType_num(2);
            type_menu.setText(del_type.getText());
        });
        JMenuItem ex_type = Assets.CREATE_MENU_ITEM("Executive Room");
        ex_type.addActionListener(_ -> {
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

        // [JButton] Add Room Button
        JButton add_room = Assets.ASSET_ACCENT_BUTTON("Add Room");
        add_room.addActionListener(_ -> {
            if (getType_num() != 0) {
                Hotel hotel = hrs.fetchHotel(getHotelName());
                if (hotel.getRoomCount() + num_room < 51) {
                    for (int i = 0; i < num_room; i++) {
                        Room newRoom = hotel.newRoom(getType_num());
                    }
                    hotel.changeRoomName(hotelName);
                    Assets.ASSET_PANE(this, "Successfully added " + num_room + " rooms.", "HRS");
                    dispose();
                } else {
                    Assets.ASSET_PANE(this, "Hotel cannot accommodate more than 50 rooms.", "HRS: Error");
                }
            } else {
                Assets.ASSET_PANE(this, "Fill out the required boxes.", "HRS: Error");
            }

        });
        add_room.setPreferredSize(new Dimension(300, 90));
        panels.get(1).add(add_room);

        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
