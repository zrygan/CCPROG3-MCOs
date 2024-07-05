package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.Border;

/*
 * ========== DESIGN SPECS for Main_GUI ==========
 * 
 * Dimensions:
 *      home HRS
 *          W   450 (px)
 *          H   500 (px)
 * 
 *      create hotel
 *          W   250 (px)
 *          H   250 (px)
 * 
 *      view hotel
 *          W   450 (px)
 *          H   375 (px)
 * 
 *      manage hotel
 *          W   250 (px)
 *          H   375 (px)
 * 
 *      sim booking
 *          W   450 (px)
 *          H   375 (px)
 * 
 *      create room
 *          W   250 (px)
 *          H   225 (px)
 *   
 * Fonts: 
 *      Ubunto Mono Regular     UbuntuMono-Regular.ttf  https://fonts.google.com/specimen/Ubuntu+Mono
 *      Ubunto Mono Italic      UbuntuMono-Italic.ttf   https://fonts.google.com/specimen/Ubuntu+Mono
 *      Ubunto Mono Bold        UbuntuMono-Bold.ttf     https://fonts.google.com/specimen/Ubuntu+Mono
 * 
 * Colors: https://color.adobe.com/Pip-Boy-Theme-color-theme-1ec25007-9a2e-4434-a972-8b398274ffe5/
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

public final class GUI {
    public GUI(){
        initFonts();

        JFrame GUI = new JFrame("Hotel Reservation System");
        JPanel panel = new JPanel();

        // Components
        JButton button_createHotel = ASSET_BASIC_BUTTON("Create Hotel");
        button_createHotel.addActionListener(e -> CREATE_HOTEL_UI());

        panel.add(button_createHotel);

        JButton button_viewHotel = ASSET_BASIC_BUTTON("View Hotel");   
        button_viewHotel.addActionListener(e -> VIEW_HOTEL_UI());
        panel.add(button_viewHotel);

        JButton button_manageHotel = ASSET_BASIC_BUTTON("Manage Hotel");   
        button_manageHotel.addActionListener(e -> MANAGE_HOTEL_UI());
        panel.add(button_manageHotel);

        JButton button_simBooking = ASSET_ACCENT_BUTTON("<html>Simulate<br>Booking</html>");
        button_simBooking.setPreferredSize(new Dimension(100, 60));
        button_simBooking.addActionListener(e -> SIM_BOOKING_UI());
        panel.add(button_simBooking);


        // initialize frame (this should be at the end)
        panel.setBackground(getBlack());
        GUI.add(panel);
        GUI.setSize(450, 500);
        GUI.setVisible(true);
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton ASSET_ACCENT_BUTTON (String text){
        Font f = Fonts.get("Regular", 12);
        JButton btn = new JButton(text);
        
        btn.setFont(f);
        btn.setForeground(getDarkGreen());
        btn.setBackground(getVividGreen());

        return btn;
    }

    public JButton ASSET_BASIC_BUTTON (String text){
        Font f = Fonts.get("Regular", 12);
        JButton btn = new JButton(text);

        btn.setFont(f);
        btn.setForeground(getVividGreen());
        btn.setBackground(getDarkGreen());

        // add the vivid green border with thickness 3 units
        Border border = BorderFactory.createLineBorder(getVividGreen(), 3);
        btn.setBorder(border);

        // set default size, can be changed after button is created
        btn.setPreferredSize(new Dimension(100, 30));

        return btn;
    }

    

    /**
     * returns the vivid green color from the hex code
     * @return color from the vivid green hex code
     */
    public final Color getVividGreen(){
        return Color.decode("#22F21B");
    }

    /**
     * returns the dark green color from the hex code
     * @return color from the dark green hex code
     */
    public final Color getDarkGreen(){
        return Color.decode("#022601");
    }

    /**
     * returns the black color from the hex code
     * @return color from the black hex code
     */
    public final Color getBlack(){
        return Color.decode("#0d0d0d");
    }

    /**
     * returns the black color from the hex code
     * @return color from the black hex code
     */
    public final Color getNormGreen(){
        return Color.decode("#11790E");
    }

    /**
     * function that initializes the fonts that will be used by the GUI.
     */
    public void initFonts(){
        // load and register fonts    
        Fonts.get("Regular", 12f, false);    
        Fonts.get("Italic", 12f, false);
        Fonts.get("Bold", 12f, false);
    }

    /**
     * GUI class that makes the user-interface for the create hotel window
     */
    private void CREATE_HOTEL_UI(){
        JFrame GUI = new JFrame("HRS: Create Hotel");

        // components

        // initialize frame (this should be at the end)
        GUI.setSize(250, 250);
        GUI.setVisible(true);

        onCloseSubWindow(GUI);
    }

    private void VIEW_HOTEL_UI(){
        JFrame GUI = new JFrame("HRS: View Hotel");
        
        // components
        
        // initialize frame (this should be at the end)
        GUI.setSize(450, 375);
        GUI.setVisible(true);

        onCloseSubWindow(GUI);
    }

    private void MANAGE_HOTEL_UI(){
        JFrame GUI = new JFrame("HRS: Manage Hotel");
        
        // components
        
        // initialize frame (this should be at the end)
        GUI.setSize(250, 375);
        GUI.setVisible(true);

        onCloseSubWindow(GUI);
    }

    private void SIM_BOOKING_UI(){
        JFrame GUI = new JFrame("HRS: Booking Simulation");
        
        // components
        
        // initialize frame (this should be at the end)
        GUI.setSize(450, 375);
        GUI.setVisible(true);

        onCloseSubWindow(GUI);
    }

    private void CREATE_ROOM_UI(String hotelName){
        String title = "HRS: Create Room in " + hotelName;
        JFrame GUI = new JFrame("HRS: Create Room");
        
        // components
        
        // initialize frame (this should be at the end)
        GUI.setSize(250, 225);
        GUI.setVisible(true);

        onCloseSubWindow(GUI);
    }

    private void onCloseSubWindow(JFrame GUI){
        // make it so that when the user closes THIS window the entire app does
        // not close
        GUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        GUI.addWindowListener(new WindowAdapter(){
            // if the close button is pressed only hide the window
            // so, override the windowClosing function by just disposing it
            @Override
            public void windowClosing(WindowEvent e){
                GUI.dispose();
            }
        });
    }
} 
