package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GUI_MANAGE extends GUI {
    private final GUI_MAIN mains;
    private String hotel_name;
    private boolean windowChecker_manage;
    private String new_hotel;
    private int room_num;

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public String getNew_hotel() {
        return new_hotel;
    }

    public void setNew_hotel(String new_hotel) {
        this.new_hotel = new_hotel;
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

    public boolean getWindowChecker_manage(){
        return windowChecker_manage;
    }

    public void setWindowChecker_manage(boolean windowChecker_manage){
        this.windowChecker_manage = windowChecker_manage;
    }

    public GUI_MANAGE(HRS hrs, int window_height, int window_width, GUI_MAIN mains){
        super(hrs, window_height, window_width);
        this.mains = mains;
        windowChecker_manage = false;
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
    public void init() {
        setTitle("Hotel Reservation System: Managing a Hotel");
        setSize(window_width, window_height);
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels  = Assets.ASSET_ADD_PANELS(5);

        /* code of TOP PANEL (panel : 0)
        *  contains:   TOP TITLE
        */
        panels.getFirst().setBounds( 0, 0, window_width, 100);        // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] {"<b>Hotel Reservation System</b>", ">>> Manage a Hotel"};
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /* code of TOP-MID PANEL (panel : 1)
        *  contains: Hotel Name Menu Bar
        */
        panels.get(1).setBounds( 0, 100, window_width, 65);        // panels[1] = top-mid panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 20,  10));

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
        hotel_bar.setPreferredSize(new Dimension(400, 45));
        panels.get(1).add(hotel_bar);

        /* code of MID-LEFT PANEL (panel : 2)
         * contains: New Hotel Name Text Field, Add Room Button, Room Number to Remove
         */
        panels.get(2).setBounds( 0, 145, window_width/2, 400);        // panels[2] = mid-left panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));

        // [TEXT FIELD] New Hotel Name Text Field
        JTextField new_hot_name = Assets.ASSET_TEXT_FIELD("Enter New Hotel Name");
        new_hot_name.addActionListener(e -> {
            if (hrs.fetchHotel(new_hot_name.getText()) == null) {
                JOptionPane.showMessageDialog(this, "Hotel Name already exists.");
            }
            else {
                Hotel hotel = hrs.fetchHotel(getHotel_name());
                setNew_hotel(new_hot_name.getText());
                hotel.setName(getNew_hotel());
                new_hot_name.setText(getNew_hotel());
                JOptionPane.showMessageDialog(this, "Hotel " + getHotel_name() + " is changed to Hotel " + getNew_hotel());
                // FIXME: Find a way to Update hotel menu after the changes
                // I think rerun the for loop for the hotel hotel : getHotels() stuff
                setHotel_name(getNew_hotel());
                hotelMenu.setText(getNew_hotel());
            }
        });
        panels.get(2).add(new_hot_name);

        // [BUTTON] Add Room Button
        JButton add_room = Assets.ASSET_ACCENT_BUTTON("Add Room");
        add_room.addActionListener(_ -> {
            if (!getWindowChecker_manage()){
                if (getHotel_name() != null) {
                    GUI_ROOM room = new GUI_ROOM(hrs, 600, 500, this, hotel_name);
                    room.init();
                    setWindowChecker_manage(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Select a hotel first.");
                }
            }
        });
        panels.get(2).add(add_room);
        
        // [TEXT FIELD] Room Number to Remove
        JTextField room_rem = Assets.ASSET_TEXT_FIELD("Room # to Remove");
        room_rem.addActionListener(e -> {
            if (getHotel_name() != null) {
                Hotel hotel = hrs.fetchHotel(getHotel_name());
                int index = Integer.parseInt(room_rem.getText());
                if (hotel.getRoomCount() > 1) {
                    if (room_rem.getText() != null && (index < 0 || index > 51)) {
                        JOptionPane.showMessageDialog(this, "Invalid Room Number.");
                    }
                    else {
                        // format the name
                        String roomName = hotel.getName() + "_Room_" + index;
    
                        // get the room
                        Room room = hotel.fetchRoom(roomName);
    
                        // check if the room exists
                        if (room!= null) {
                            if (room.getReservation() == null) {
                                hotel.delRoom(index);
                                JOptionPane.showMessageDialog(this, roomName + " is removed.");
                            }
                            else {
                                JOptionPane.showMessageDialog(this, "Room " + roomName + " is reserved by " + room.getReservation().getGuest());
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Room " + roomName + " does not exist.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Hotel must have at least 2 rooms.");
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Select a hotel first.");
            }
            
        });
        panels.get(2).add(room_rem);

        /* code of MID-RIGHT PANEL (panel: 3)
         * contains: Delete Hotel Button, New DPM Text Field, Remove Reservation Text Field
         */
        panels.get(3).setBounds(window_width/2, 145,window_width/2, 400);        // panels[3] = mid-right panel
        panels.get(3).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));

        // [BUTTON] Delete Hotel Button
        JButton delete_hot = Assets.ASSET_ACCENT_BUTTON("Delete Hotel");
        delete_hot.addActionListener(_ -> {
            if (getHotel_name()!= null) {
                Hotel hotel = hrs.fetchHotel(getHotel_name());
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Hotel " + getHotel_name() + "?", "Confirm Delete Hotel", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    hotel.prepareForRemoval();
                    hrs.getHotels().remove(hotel);
                    JOptionPane.showMessageDialog(this, "Hotel " + getHotel_name() + " is deleted.");
                    // FIXME: Find a way to update the Hotel MenuItem List
                    setHotel_name(null);
                    hotelMenu.setText("Hotel Name");
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Select a hotel first.");
            }
        });
        panels.get(3).add(delete_hot);

        // FIXME: Skipped for now because i think it might be necessary to have a whole new window for this function
        // [TEXT FIELD] New DPM Text Field
        JButton new_DPM = Assets.ASSET_ACCENT_BUTTON("New DPM");
        new_DPM.addActionListener(e -> {
            if (!getWindowChecker_manage()){
                if (getHotel_name() != null) {
                    GUI_DPM dpm = new GUI_DPM(hrs, 600, 500, this, getHotel_name());
                    dpm.init();
                    setWindowChecker_manage(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Select a hotel first.");
                }
        });
        panels.get(3).add(new_DPM);

        // [TEXT FIELD] Remove Reservation Text Field
        // FIXME: necessary for another text field so maybe add a new window for this too
        JTextField rem_res = Assets.ASSET_TEXT_FIELD("Reservation Guest Name to Remove");
        rem_res.addActionListener(e -> {
            if (getHotel_name()!= null) {
                Hotel hotel = hrs.fetchHotel(getHotel_name());
                String reservationName = rem_res.getText();
            }
        });
        panels.get(3).add(rem_res);

        /* code of BOTTOM PANEL (panel: 4)
        *  contains:   FOOTER
        */
        panels.get(4).setBounds( 0, 545, window_width, 255);        // panels[4] = bottom panel
        panels.get(4).setLayout(new FlowLayout(FlowLayout.LEFT, 50, 60));

        // [OUTPUX BOX] Output box
        // FIXME: add an actionlistener for the text area box
        JTextArea output_box = Assets.ASSET_OUTPUT_BOX(window_width, 50, "HELLO");
        output_box.setPreferredSize(new Dimension(window_width-100, 45));
        panels.get(4).add(output_box);

        for (JPanel panel : panels){
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
