package HotelReservationSystem;

import java.awt.*;                          // for general awt stuff
import java.awt.event.ActionEvent;          // for ActionEvent (used in dynamic clock)
import java.awt.event.WindowAdapter;        // for handling window closing
import java.awt.event.WindowEvent;          // for handling window closing
import java.time.LocalDateTime;             // for clock, get time
import java.time.format.DateTimeFormatter;  // for clock, time format
import javax.swing.*;                       // for general swing stuff
import javax.swing.border.Border;           // for borders

/*
 * ========== DESIGN SPECS for Main_GUI ==========
 * 
 * Dimensions:
 *      home HRS
 *          W   900 (px)
 *          H   1000 (px)
 * 
 *      create hotel
 *          W   500 (px)
 *          H   500 (px)
 * 
 *      view hotel
 *          W   900 (px)
 *          H   750 (px)
 * 
 *      manage hotel
 *          W   500 (px)
 *          H   750 (px)
 * 
 *      sim booking
 *          W   900 (px)
 *          H   750 (px)
 * 
 *      create room
 *          W   500 (px)
 *          H   550 (px)
 *   
 * Fonts: 
 *      Ubunto Mono Regular     UbuntuMono-Regular.ttf  https://fonts.google.com/specimen/Ubuntu+Mono
 *      Ubunto Mono Italic      UbuntuMono-Italic.ttf   https://fonts.google.com/specimen/Ubuntu+Mono
 *      Ubunto Mono Bold        UbuntuMono-Bold.ttf     https://fonts.google.com/specimen/Ubuntu+Mono
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
 * This class handles all GUIs of the Hotel Reservation System.
 */
public final class GUI extends JFrame {

    /**
     * the timer for the clock
     */
    private final Timer timer_clock;

    /**
     * the timer for the outputBox
     */
    private final Timer timer_outputBox;

    /**
     * the HRS
     */
    private final HRS hrs;

    /**
     * the checker if a window is already open 
     */
    private boolean window;

    /**
     * getter for the widow checker
     * @return the window checker value
     */
    public boolean getWindow(){
        return window;
    }

    /**
     * setter for window checker
     * @param window new window checker value
     */
    public void setWindow(boolean window){
        this.window = window;
    }

    /**
     * The constructor for the GUI of the HRS.
     *
     * @param hrs the Hotel Reservation System
     */
    public GUI(HRS hrs) {
        initFonts();         // initialize the fonts
        this.hrs = hrs;      // the HRS database
        this.window = false; // checker if there's a subwindow

        JPanel panel = new JPanel(new GridBagLayout());            // the panel
        GridBagConstraints grid = new GridBagConstraints();

        /* 
            ----------------------
            ----- COMPONENTS -----
            ----------------------
         */
        // [TEXT BOX] LEFT TITLE
        // :: "Hotel Reservation System"
        // :: Version Number
        JEditorPane titleLeft = new JEditorPane();
        titleLeft.setContentType("text/html"); // set text in JEditorPane as HTML
        titleLeft.setFont(Fonts.get("Regular", 14));
        titleLeft.setForeground(getVividGreen());
        titleLeft.setBackground(getBlack());
        titleLeft.setEditable(false); // Make it non-editable
        titleLeft.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        titleLeft.setText("<html><body style='text-align: left;'><b>Hotel Reservation System</b><br>Version 2.1.0</body></html>");
        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 5;
        grid.anchor = GridBagConstraints.NORTHEAST;
        titleLeft.setPreferredSize(new Dimension(260, 90));
        panel.add(titleLeft, grid);

        // [TEXT BOX] RIGHT TITLE
        // :: The date today
        // :: "All Rights Reserved."
        JEditorPane titleRight = new JEditorPane();
        titleRight.setContentType("text/html"); // set text in JEditorPane as HTML
        titleRight.setFont(Fonts.get("Regular", 14));
        titleRight.setForeground(getVividGreen());
        titleRight.setBackground(getBlack());
        titleRight.setEditable(false); // Make it non-editable
        titleRight.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        grid.gridx = 10;
        grid.gridy = 0;

        // make the clock dynamically update based on the actual time
        timer_clock = new Timer(1000, (ActionEvent e) -> {
            clock(titleRight);
        });
        timer_clock.start();
        titleRight.setPreferredSize(new Dimension(240, 90));
        panel.add(titleRight, grid);

        // [SEPARATOR] Horizontal line separator
        // FIXME not appearing
        JSeparator horiLine = new JSeparator(SwingConstants.HORIZONTAL);
        horiLine.setBackground(getVividGreen());
        grid.gridx = 1;
        grid.gridy = 1;
        grid.gridwidth = GridBagConstraints.REMAINDER; // span accross the entire width of the window
        panel.add(horiLine, grid);

        // [TEXT BOX] HOTEL List
        // :: the list of HotelReservationSystem
        // FIXME make it scrollable
        JTextArea outputBox = new JTextArea();
        outputBox.setEditable(false);
        Border outputBox_border = BorderFactory.createLineBorder(getVividGreen(), 2);
        outputBox.setBorder(outputBox_border);
        grid.gridx = 0;
        grid.gridy = 3;
        grid.gridheight = 20;
        grid.gridwidth = 5;
        // make the output box dynamically update for Hotel in HRS.getHotels()
        timer_outputBox = new Timer(1000, (ActionEvent e) -> {
            updateOutputBox(outputBox);
        });
        timer_outputBox.start();
        outputBox.setPreferredSize(new Dimension(260, 400));
        panel.add(outputBox, grid); // make it scrollable

        // [BUTTON] CREATE Hotel
        JButton button_createHotel = ASSET_BASIC_BUTTON("Create Hotel");
        button_createHotel.addActionListener(e -> {
            if (!this.window) {
                CREATE_HOTEL_UI();
                this.window = true;
            }
        });
        grid.gridx = 8;
        grid.gridy = 3;
        grid.gridheight = 3;
        grid.gridwidth = 7;
        panel.add(button_createHotel, grid);

        // [BUTTON] VIEW Hotel
        JButton button_viewHotel = ASSET_BASIC_BUTTON("View Hotel");
        button_viewHotel.addActionListener(e -> {
            if (!this.window) {
                VIEW_HOTEL_UI();
                this.window = true;
            }
        });
        grid.gridy = 6;
        panel.add(button_viewHotel, grid);

        // [BUTTON] MANAGE Hotel
        JButton button_manageHotel = ASSET_BASIC_BUTTON("Manage Hotel");
        button_manageHotel.addActionListener(e -> {
            if (!this.window) {
                MANAGE_HOTEL_UI();
                this.window = true;
            }
        });
        grid.gridy = 9;
        panel.add(button_manageHotel, grid);

        // [BUTTON] SIMULATE Booking
        JButton button_simBooking = ASSET_ACCENT_BUTTON("<html>Simulate<br>Booking</html>");
        button_simBooking.setPreferredSize(new Dimension(100, 60));
        button_simBooking.addActionListener(e -> {
            if (!this.window) {
                SIM_BOOKING_UI();
                this.window = true;
            }
        });
        grid.gridy = 12;
        grid.gridheight = 6;
        panel.add(button_simBooking, grid);

        // initialize frame (this should be at the end)
        panel.setBackground(getBlack());
        add(panel);
        setSize(900, 1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * makes the default asset: "accent button"
     *
     * @param text the text in the button
     * @return the accent button created
     */
    public JButton ASSET_ACCENT_BUTTON(String text) {
        Font f = Fonts.get("Regular", 14);
        JButton btn = new JButton(text);

        btn.setFont(f);
        btn.setForeground(getDarkGreen());
        btn.setBackground(getVividGreen());

        return btn;
    }

    /**
     * makes the default asset: "basic button"
     *
     * @param text the text in the button
     * @return the basic button created
     */
    public JButton ASSET_BASIC_BUTTON(String text) {
        Font f = Fonts.get("Regular", 14);
        JButton btn = new JButton(text);

        btn.setFont(f);
        btn.setForeground(getVividGreen());
        btn.setBackground(getDarkGreen());

        // add the vivid green border with thickness 3 units
        Border border = BorderFactory.createLineBorder(getVividGreen(), 3);
        btn.setBorder(border);

        // FIXME: add rounded borders (if needed)
        // but the non rounded border also looks nice (more in line with the inspo theme)
        // set default size, can be changed after button is created
        btn.setPreferredSize(new Dimension(100, 30));

        return btn;
    }

    /**
     * returns the vivid green color from the hex code
     *
     * @return color from the vivid green hex code
     */
    public final Color getVividGreen() {
        return Color.decode("#22F21B");
    }

    /**
     * returns the dark green color from the hex code
     *
     * @return color from the dark green hex code
     */
    public final Color getDarkGreen() {
        return Color.decode("#022601");
    }

    /**
     * returns the black color from the hex code
     *
     * @return color from the black hex code
     */
    public final Color getBlack() {
        return Color.decode("#0d0d0d");
    }

    /**
     * returns the black color from the hex code
     *
     * @return color from the black hex code
     */
    public final Color getNormGreen() {
        return Color.decode("#11790E");
    }

    /**
     * function that initializes the fonts that will be used by the GUI.
     */
    public void initFonts() {
        // load and register fonts    
        Fonts.get("Regular", 12f, false);
        Fonts.get("Italic", 12f, false);
        Fonts.get("Bold", 12f, false);
    }

    /**
     * adds the clock in the home GUI's right title box.
     *
     * @param titleRight the JEditorPane that contains the text of the right
     * title
     */
    private void clock(JEditorPane titleRight) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = now.format(format);
        titleRight.setText("<html><body style='text-align: right;'>" + formattedDate + "<br>All Rights Reserved.</body></html>");
    }

    /**
     * updates the list of hotels in the database in the home GUI's output box
     *
     * @param outputBox the JTextArea that contains the text of the list of
     * hotels
     */
    private void updateOutputBox(JTextArea outputBox) {

        StringBuilder output = new StringBuilder(); // use StringBuilder so we can use append
        for (Hotel hotel : hrs.getHotels()) {
            output.append("\n     ").append(hotel.getName());
        }
        outputBox.setText(output.toString()); // convert it first to String 
        // since it is a StringBuilder

        outputBox.setFont(Fonts.get("Regular", 18));
        outputBox.setForeground(getVividGreen());
        outputBox.setBackground(getBlack());
    }

    /**
     * Method that handles window closing and makes another window available for openning
     * 
     * @param frame the frame to be closed
     */
    private void atClose(JFrame frame){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                setWindow(false);
            }
        });
    }

    /**
     * GUI class that makes the user-interface for the create hotel window
     */
    private void CREATE_HOTEL_UI() {
        JFrame GUI = new JFrame("HRS: Create Hotel");
        JPanel panel = new JPanel(new GridBagLayout());

        // components
        JLabel label = new JLabel("Hello, World!");
        panel.add(label);

        // initialize frame (this should be at the end)
        panel.setBackground(getBlack());
        GUI.add(panel);
        GUI.setSize(500, 500);
        GUI.setVisible(true);

        atClose(GUI);
    }

    /**
     * GUI class that makes the user-interface for the view hotel window
     */
    private void VIEW_HOTEL_UI() {
        JFrame GUI = new JFrame("HRS: View Hotel");
        JPanel panel = new JPanel(new GridBagLayout());

        // components
        // initialize frame (this should be at the end)
        panel.setBackground(getBlack());
        GUI.add(panel);
        GUI.setSize(900, 750);
        GUI.setVisible(true);

        atClose(GUI);
    }

    /**
     * GUI class that makes the user-interface for the manage hotel window
     */
    private void MANAGE_HOTEL_UI() {
        JFrame GUI = new JFrame("HRS: Manage Hotel");
        JPanel panel = new JPanel(new GridBagLayout());

        // components
        // initialize frame (this should be at the end)
        panel.setBackground(getBlack());
        GUI.add(panel);
        GUI.setSize(500, 750);
        GUI.setVisible(true);

        atClose(GUI);
    }

    /**
     * GUI class that makes the user-interface for the simulate booking window
     */
    private void SIM_BOOKING_UI() {
        JFrame GUI = new JFrame("HRS: Booking Simulation");
        JPanel panel = new JPanel(new GridBagLayout());

        // components
        // initialize frame (this should be at the end)
        panel.setBackground(getBlack());
        GUI.add(panel);
        GUI.setSize(900, 750);
        GUI.setVisible(true);

        atClose(GUI);
    }

    /**
     * GUI class that makes the user-interface for the create room window
     *
     * @param hotelName the name of the hotel where the room is going to be
     * created
     */
    private void CREATE_ROOM_UI(String hotelName) {
        String title = "HRS: Create Room in " + hotelName;
        JFrame GUI = new JFrame(title);
        JPanel panel = new JPanel(new GridBagLayout());

        // components
        // initialize frame (this should be at the end)
        panel.setBackground(getBlack());
        GUI.add(panel);
        GUI.setSize(500, 550);
        GUI.setVisible(true);

        atClose(GUI);
    }



    // [ZRY]    Commented this out because idk what it's for?
    //          I remember having a reason to add this, but I think i changed the implementation
    //          Left it as a comment if needed
    // /**
    //  * Method that handles closing the "sub-windows" without terminating the
    //  * entire application
    //  *
    //  * @param GUI The GUI itself
    //  */
    // private void onCloseSubWindow(JFrame GUI) {
    //     // make it so that when the user closes THIS window the entire app does
    //     // not close
    //     GUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    //     GUI.addWindowListener(new WindowAdapter() {
    //         // if the close button is pressed only hide the window
    //         // so, override the windowClosing function by just disposing it
    //         @Override
    //         public void windowClosing(WindowEvent e) {
    //             GUI.dispose();
    //         }
    //     });
    // }
}
