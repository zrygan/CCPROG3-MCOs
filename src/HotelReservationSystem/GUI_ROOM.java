package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GUI_ROOM extends GUI {
    private final GUI_MANAGE mains;
    private final String hotelName;
    private int num_room;
    private int type_num;

    public int getType_num() {
        return type_num;
    }

    public void setType_num(int type_num) {
        this.type_num = type_num;
    }

    public int getNum_room() {
        return num_room;
    }

    public void setNum_room(int num_room) {
        this.num_room = num_room;
    }

    public GUI_MANAGE getMains(){
        return mains;
    }

    public String getHotelName(){
        return hotelName;
    }

    public GUI_ROOM(HRS hrs, int window_height, int window_width, GUI_MANAGE mains, String hotelName){
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
    public void init(){
        setTitle("Hotel Reservation System: Adding a Room in " + hotelName);
        setSize(window_width, window_height);
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels = Assets.ASSET_ADD_PANELS(2);

        /* code of TOP PANEL (panel : 0)
        *  contains:   TOP TITLE
        */
        panels.getFirst().setBounds( 0, 0, window_width, 100);        // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
       
        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] {"<b>Hotel Reservation System</b>", ">>> Adding a Room in " + hotelName};
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /* code of MID PANEL (panel : 1)
         *  contains: num_rooms, room_type, add_room button
         */
         panels.get(1).setBounds( 0, 100, window_width, 400);        // panels[1] = mid panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // [JSpinner] num_rooms
        JSpinner num_rooms = Assets.ASSET_SPINNER(1, 50);
        num_rooms.setValue(1);
        setNum_room((int) num_rooms.getValue());
        num_rooms.addChangeListener(e -> {
            setNum_room((int) num_rooms.getValue());
        });
        panels.get(1).add(num_rooms);

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
        panels.get(1).add(room_type);

        // [JButton] Add Room Button
        JButton add_room = Assets.ASSET_ACCENT_BUTTON("Add Room");
        add_room.addActionListener(e -> {
            Hotel hotel = hrs.fetchHotel(getHotelName());
            if (hotel.getRoomCount() + num_room < 51) {
                for(int i = 0; i < num_room; i++){
                    Room newRoom = hotel.newRoom(getType_num());
                }
                JOptionPane.showMessageDialog(this, "Successfully added " + num_room + " rooms.");
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Hotel cannot accommodate more than 50 rooms.");
            }
        });
        panels.get(1).add(add_room);


        setVisible(true);
    }
}
