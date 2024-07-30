package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 * A class that extends GUI that creates the manage hotel window
 */
public class GUI_MANAGE extends GUI {
    /**
     * The main GUI component
     */
    private final GUI_MAIN mains;

    /**
     * The name of the hotel
     */
    private String hotel_name;

    /**
     * A flag to check if the window is being managed
     */
    private boolean windowChecker_manage;

    /**
     * The name of the new hotel
     */
    private String new_hotel;

    /**
     * The number of rooms
     */
    private int room_num;

    /**
     * The getter for the room number
     * 
     * @return the room number
     */
    public int getRoom_num() {
        return room_num;
    }

    /**
     * Setter for the room number
     * 
     * @param room_num the room number
     */
    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    /**
     * Getter for the new hotel name
     * 
     * @return new hotel name
     */
    public String getNew_hotel() {
        return new_hotel;
    }

    /**
     * Setter for the new hotel name
     * 
     * @param new_hotel the new hotel name
     */
    public void setNew_hotel(String new_hotel) {
        this.new_hotel = new_hotel;
    }

    /**
     * Getter for the hotel name
     * 
     * @return the name of the hotel
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
     * Getter for the main window of the subwindow
     * 
     * @return the GUI_MAIN component
     */
    public GUI_MAIN getMains() {
        return mains;
    }

    /**
     * Getter of window checker
     * 
     * @return the value of window checker
     */
    public boolean getWindowChecker_manage() {
        return windowChecker_manage;
    }

    /**
     * Setter for the window checker
     * 
     * @param windowChecker_manage the value of window checker
     */
    public void setWindowChecker_manage(boolean windowChecker_manage) {
        this.windowChecker_manage = windowChecker_manage;
    }

    /**
     * The constructor for GUI_MANAGE
     * 
     * @param hrs           the hrs database
     * @param window_height the height of the window
     * @param window_width  the width of the window
     * @param mains         the GUI_MAIN of the window
     */
    public GUI_MANAGE(HRS hrs, int window_height, int window_width, GUI_MAIN mains) {
        super(hrs, window_height, window_width);
        this.mains = mains;
        windowChecker_manage = false;
    }

    /**
     * {@inheritDoc}
     * 
     * This method overrides the {@code GUI_CONFIG_WINDOW_CLOSE} method in
     * {@code GUI}.
     * This sets the setWindowChecker attribute of the mains attribute of GUI_MANAGE
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
        setTitle("Hotel Reservation System: Managing a Hotel");
        setSize(window_width, window_height);
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels = Assets.ASSET_ADD_PANELS(4);

        /*
         * code of TOP PANEL (panel : 0)
         * contains: TOP TITLE
         */
        panels.getFirst().setBounds(0, 0, window_width, 100); // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] { "<b>Hotel Reservation System</b>", ">>> Manage a Hotel" };
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /*
         * code of TOP-MID PANEL (panel : 1)
         * contains: Hotel Name Menu Bar
         */
        panels.get(1).setBounds(0, 100, window_width, 65); // panels[1] = top-mid panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // [MENU BAR] Hotel Name Menu Bar
        JMenuBar hotel_bar = Assets.ASSET_MENU_BAR();
        JMenu hotelMenu = Assets.ASSET_MENU("Hotel Name");
        hotel_bar.add(hotelMenu);
        for (Hotel hotel : hrs.getHotels()) {
            JMenuItem hotelList = Assets.CREATE_MENU_ITEM(hotel.getName());
            hotelList.addActionListener(_ -> {
                setHotel_name(hotel.getName());
                hotelMenu.setText(hotel.getName());
            });
            hotelMenu.add(hotelList);
        }
        hotel_bar.setPreferredSize(new Dimension(400, 45));
        panels.get(1).add(hotel_bar);

        /*
         * code of MID-LEFT PANEL (panel : 2)
         * contains: New Hotel Name Text Field, Add Room Button, Room Number to Remove
         */
        panels.get(2).setBounds(0, 145, window_width / 2, 450); // panels[2] = mid-left panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));

        // [TEXT FIELD] New Hotel Name Text Field
        JTextField new_hot_name = Assets.ASSET_TEXT_FIELD("New Hotel Name");
        new_hot_name.addActionListener(_ -> {
            if (!getWindowChecker_manage()) {
                if (getHotel_name() != null) {
                    if (hrs.fetchHotel(new_hot_name.getText()) != null) {
                        Assets.ASSET_PANE(this, "Hotel Name already exists", "HRS");
                    } else {
                        Hotel hotel = hrs.fetchHotel(getHotel_name());
                        setNew_hotel(new_hot_name.getText());
                        hotel.setName(getNew_hotel());

                        Hotel changeName = hrs.fetchHotel(getNew_hotel());

                        changeName.changeRoomName(getNew_hotel());

                        new_hot_name.setText(getNew_hotel());
                        Assets.ASSET_PANE(this,
                                "Hotel " + getHotel_name() + " is changed to Hotel " + getNew_hotel(), "HRS");
                        setHotel_name(getNew_hotel());
                        hotelMenu.setText(getNew_hotel());
                        dispose();
                    }
                } else {
                    Assets.ASSET_PANE(this, "Select a hotel first.", "HRS: Error");
                }
            }
        });
        panels.get(2).add(new_hot_name);

        // [BUTTON] Add Room Button
        JButton add_room = Assets.ASSET_ACCENT_BUTTON("Add Room");
        add_room.addActionListener(_ -> {
            if (!getWindowChecker_manage()) {
                if (getHotel_name() != null) {
                    GUI_ROOM room = new GUI_ROOM(hrs, 600, 500, this, hotel_name);
                    room.init();
                    setWindowChecker_manage(true);
                } else {
                    Assets.ASSET_PANE(this, "Select a hotel first.", "HRS: Error");
                }
            }
        });
        panels.get(2).add(add_room);

        // [TEXT FIELD] Room Number to Remove
        JTextField room_rem = Assets.ASSET_TEXT_FIELD("Room # to Remove");
        room_rem.addActionListener(_ -> {
            if (!getWindowChecker_manage()) {
                if (getHotel_name() != null) {
                    Hotel hotel = hrs.fetchHotel(getHotel_name());
                    int index = Integer.parseInt(room_rem.getText());
                    if (hotel.getRoomCount() > 1) {
                        if (room_rem.getText() != null && (index < 0 || index > 51)) {
                            Assets.ASSET_PANE(this, "Invalid Room Number.", "HRS: Error");
                        } else {
                            // format the name
                            String roomName = hotel.getName() + "_Room_" + index;
    
                            // get the room
                            Room room = hotel.fetchRoom(roomName);
    
                            // check if the room exists
                            if (room != null) {
                                if (room.getReservation() == null) {
                                    hotel.delRoom(index);
                                    Assets.ASSET_PANE(this, roomName + " is removed.", "HRS");
                                } else {
                                    Assets.ASSET_PANE(this,
                                            "Room " + roomName + " is reserved by " + room.getReservation().getGuest(), "HRS: Error");
                                }
                            } else {
                                Assets.ASSET_PANE(this, "Room " + roomName + " does not exist.", "HRS: Error");
                            }
                        }
                    } else {
                        Assets.ASSET_PANE(this, "Hotel must have at least 2 rooms.", "HRS: Error");
                    }
                } else {
                    Assets.ASSET_PANE(this, "Select a hotel first.", "HRS: Error");
                }
            }
        });
        panels.get(2).add(room_rem);

        /*
         * code of MID-RIGHT PANEL (panel: 3)
         * contains: Delete Hotel Button, New DPM Text Field, Remove Reservation Text
         * Field
         */
        panels.get(3).setBounds(window_width / 2, 145, window_width / 2, 450); // panels[3] = mid-right panel
        panels.get(3).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));

        // [BUTTON] Delete Hotel Button
        JButton delete_hot = Assets.ASSET_ACCENT_BUTTON("Delete Hotel");
        delete_hot.addActionListener(_ -> {
            if (!getWindowChecker_manage()) {
                if (getHotel_name() != null) {
                    Hotel hotel = hrs.fetchHotel(getHotel_name());
                    hotel.prepareForRemoval();
                    hrs.getHotels().remove(hotel);
                    Assets.ASSET_PANE(this, "Hotel " + getHotel_name() + " is deleted.", "HRS");
                    dispose();
                } else {
                    Assets.ASSET_PANE(this, "Select a hotel first.", "HRS: Error");
                }
            }
        });
        panels.get(3).add(delete_hot);

        // [TEXT FIELD] New DPM Text Field
        JButton new_DPM = Assets.ASSET_ACCENT_BUTTON("New DPM");
        new_DPM.addActionListener(_ -> {
            if (!getWindowChecker_manage()) {
                if (getHotel_name() != null) {
                    GUI_DPM dpm = new GUI_DPM(hrs, 600, 500, this, getHotel_name());
                    dpm.init();
                    setWindowChecker_manage(true);
                } else {
                    Assets.ASSET_PANE(this, "Select a hotel first.", "HRS: Error");
                }
            }
        });
        panels.get(3).add(new_DPM);

        // [JButton] Remove Reservation Text Field
        JButton rem_res = Assets.ASSET_ACCENT_BUTTON("Remove Reservation");
        rem_res.addActionListener(_ -> {
            if (!getWindowChecker_manage()) {
                if (getHotel_name() != null) {
                    GUI_RESERVATION res = new GUI_RESERVATION(hrs, 600, 500, this, hotel_name);
                    res.init();
                    setWindowChecker_manage(true);
                } else {
                    Assets.ASSET_PANE(this, "Select a hotel first.", "HRS: Error");
                }
            }
        });
        panels.get(3).add(rem_res);

        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
