package HotelReservationSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

/*
* ========== DESIGN SPECS for Main_GUI ==========
*
* Dimensions:
*      home HRS
*          W   900 (px)
*          H   800 (px)
*
*      create hotel
*          W   500 (px)
*          H   500 (px)
*
*      view hotel
*          W   900 (px)
*          H   800 (px)
*
*      manage hotel
*          W   500 (px)
*          H   800 (px)
*
*      sim booking
*          W   900 (px)
*          H   800 (px)
*
*      create room
*          W   500 (px)
*          H   600 (px)
*
* Fonts:
*      Ubuntu Mono Regular     UbuntuMono-Regular.ttf  https://fonts.google.com/specimen/Ubuntu+Mono
*      Ubuntu Mono Italic      UbuntuMono-Italic.ttf   https://fonts.google.com/specimen/Ubuntu+Mono
*      Ubuntu Mono Bold        UbuntuMono-Bold.ttf     https://fonts.google.com/specimen/Ubuntu+Mono
*
* Colors: https://color.adobe.com/Pip-Boy-Theme-color-theme-1ec50007-9a2e-4434-a972-8b398274ffe5/
*      Dark Forest Green   #022601     button primary background color,
*                                      button secondary text color.
*
*      Vivid Green         #22F21B     button primary text color,
*                                      button secondary background color.
*
*      Radical Green       #11790E     placeholder text color for input fields.
*
*      Woodsome            #0d0d0d     window fill color,
*                                      button tertiary text color
*
*/

/**
 * The GUI of the HRS
 */
class GUIs extends JFrame {
    private final HRS hrs;
    private boolean isOpen;
    private final int window_height;
    private final int window_width;

    /**
     * The constructor of the GUI 
     * @param hrs the Hotel Reservation System database
     */
    public GUIs(HRS hrs){
        this.hrs = hrs;
        this.isOpen = false; // init the window checker as false
        this.window_height = 800;
        this.window_width = 900;
        init();
        Fonts.init();
    }

    /**
     * Getter for the isOpen flag
     * @return the boolean value of isOpen
     */
    public boolean getIsOpen(){
        return isOpen;
    }

    /**
     * Setter for the isOpen flag
     * @param isOpen the new boolean value of isOpen
     */
    public void setIsOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    /**
     * Handles closing of windows and duplicate windows.
     */
    private void CONFIG_AT_CLOSE(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setIsOpen(false);
            }
        });
    }

    /**
     * The main GUI window.
     */
    private void init(){
        setTitle("Hotel Reservation System [Ganituen, Jimenez]");
        setSize(window_width, window_height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels  = Assets.ASSET_ADD_PANELS(3);

        /* code of TOP PANEL (panel : 0)
            *  contains:   LEFT TITLE
            *              RIGHT TITLE
            */
        panels.getFirst().setBounds( 0, 0, window_width, 100);        // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Left Title (Hotel Reservation System and Version)
        String[] title_left = new String[] {"<b>Hotel Reservation System</b>", "Version 2.1.1"};
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_left, "left", 450, 35));

        // [TITLE BOX] Right title (current time and All Rights Reserved)s
        String[] title_right_text = new String[] {Assets.UPDATE_CLOCK_TIME(), "All Rights Reserved"};
        JEditorPane title_right = Assets.ASSET_TITLE_BOX(title_right_text, "right", 200, 35);
        panels.getFirst().add(title_right);
        Timer timer_clock = new Timer(1000, (ActionEvent _) ->{
            title_right_text[0] = Assets.UPDATE_CLOCK_TIME();
            Assets.UPDATE_TITLE_BOX(title_right, title_right_text, "right");
        });
        timer_clock.start();

        /* code of LEFT PANEL (panel : 1)
            *  contains:
            */
        panels.get(1).setBounds( 0, 100, window_width/2, window_height); // panels[1] = left panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
        panels.get(1).add(Assets.ASSET_OUTPUT_BOX(350,500, hrs));

        /* code of RIGHT PANEL (panel : 2)
            *  contains:
            */
        panels.get(2).setBounds( window_width/2,100, window_width/2, window_height); // panels[2] = right panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // [BASIC BUTTON] Create Hotel Button
        JButton btn_create = Assets.ASSET_BASIC_BUTTON("Create Hotel");
        btn_create.addActionListener(_ -> {
            if (!getIsOpen()) {
                GUI_create_hotel();
                setIsOpen(true);
            }
        });
        panels.get(2).add(btn_create);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width/2));

        JButton btn_view = Assets.ASSET_BASIC_BUTTON("View Hotel");
        btn_view.addActionListener(_ -> {
            if (!getIsOpen()) {
                GUI_view_hotel();
                setIsOpen(true);
            }
        });
        panels.get(2).add(btn_view);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width/2));

        JButton btn_manage = Assets.ASSET_BASIC_BUTTON("Manage Hotel");
        btn_manage.addActionListener(_ -> {
            if (!getIsOpen()) {
                GUI_manage_hotel();
                setIsOpen(true);
            }
        });
        panels.get(2).add(btn_manage);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width/2));

        JButton btn_simbook = Assets.ASSET_ACCENT_BUTTON("Simulate Booking");
        btn_simbook.addActionListener(_ -> {
            if (!getIsOpen()) {
                GUI_sim_book();
                setIsOpen(true);
            }
        });
        btn_simbook.setPreferredSize(new Dimension(150, 90));
        panels.get(2).add(btn_simbook);

        // set the background color and add the panels
        for (JPanel panel : panels){
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }

    /**
     * The GUI for create hotel
     */
    private void GUI_create_hotel(){
        JFrame GUI = new JFrame("HRS: Create Hotel");
        GUI.setSize(500,500);
        GUI.setVisible(true);
        CONFIG_AT_CLOSE();
    }

    /**
     * The GUI for view hotel
     */
    private void GUI_view_hotel(){
        JFrame GUI = new JFrame("HRS: View Hotel");
        GUI.setSize(900,800);
        GUI.setVisible(true);
        CONFIG_AT_CLOSE();
    }

    /**
     * The GUI for manage hotel
     */
    private void GUI_manage_hotel(){
        JFrame GUI = new JFrame("HRS: Manage Hotel");
        GUI.setSize(500,800);
        GUI.setVisible(true);
        CONFIG_AT_CLOSE();
    }

    /**
     * The GUI for simulate booking
     */
    private void GUI_sim_book(){
        JFrame GUI = new JFrame("HRS: Simulate Booking");
        GUI.setSize(900,800);
        GUI.setVisible(true);
        CONFIG_AT_CLOSE();
    }

    /**
     * The GUI for creating a room. Can only be called by GUI_manage_hotel
     */
    private void GUI_create_room(){
        JFrame GUI = new JFrame("HRS: Create Room");
        GUI.setSize(500,600);
        GUI.setVisible(true);
        CONFIG_AT_CLOSE();
    }
}
