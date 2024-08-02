package HotelReservationSystem;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A class that extends GUI that creates a reservation under manage hotel
 */
public class GUI_RESERVATION extends GUI {
    /**
     * The main GUI component for managing hotel operations
     */
    private final GUI_MANAGE mains;

    /**
     * The name of the hotel
     */
    private final String hotelName;

    /**
     * The name of the guest
     */
    private String guestName;

    /**
     * The day of the check in
     */
    private int checkin;

    /**
     * The getter of the check in
     * 
     * @return check in day
     */
    public int getCheckin() {
        return checkin;
    }

    /**
     * The setter of the check in
     * 
     * @param checkin the check in day
     */
    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }

    /**
     * The getter of the guest name
     * 
     * @return the guest name
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * The setter of the guest name
     * 
     * @param guestName the guest name
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
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
    public GUI_RESERVATION(HRS hrs, int window_height, int window_width, GUI_MANAGE mains, String hotelName) {
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

    @Override
    public void init() {
        setTitle("Hotel Reservation System: Deleting a Reservation in " + getHotelName());
        setSize(window_width, window_height); // window_width = 500 & window_height = 600
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
        String[] title_top = new String[] { "<b>Hotel Reservation System</b>",
                ">>> Deleting a Reservation in " + getHotelName() };
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));

        /*
         * code of MID PANEL (panel : 1)
         * contains: guest_name, checkinDate, delete reservation button
         */
        panels.get(1).setBounds(0, 100, window_width, 500); // panels[1] = mid panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 50, 60));

        // [JTextField] Guest Name
        JTextField guest_name = Assets.ASSET_TEXT_FIELD("Guest Name");
        guest_name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setGuestName(guest_name.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setGuestName(guest_name.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // empty
            }
        });
        guest_name.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(guest_name);

        // [JSpinner] checkInDay
        JSpinner checkinDate = Assets.ASSET_SPINNER(1, 30);
        checkinDate.setValue(1);
        setCheckin((int) checkinDate.getValue());
        checkinDate.addChangeListener(_ -> setCheckin((int) checkinDate.getValue()));
        checkinDate.setPreferredSize(new Dimension(300, 45));
        panels.get(1).add(checkinDate);

        // [JButton] Delete Reservation Button
        JButton delete_button = Assets.ASSET_ACCENT_BUTTON("Delete Reservation");
        delete_button.addActionListener(_ -> {
            Hotel hotel = hrs.fetchHotel(getHotelName());
            if (getGuestName() == null) {
                Assets.ASSET_PANE(this, "Please fill in all fields", "HRS: error");
            } else if (hotel.removeReservation(getGuestName(), getCheckin())) {
                Assets.ASSET_PANE(this, "Successfully removed reservation.", "HRS");
                dispose();
            } else {
                boolean temp = hotel.removeReservation(getGuestName(), getCheckin());
                if (temp) {
                    Assets.ASSET_PANE(this, "Successfully removed reservation", "HRS");
                } else {
                    Assets.ASSET_PANE(this, "Unsuccesfully removed reservation", "HRS: Error");
                }
            }
        });
        delete_button.setPreferredSize(new Dimension(300, 90));
        panels.get(1).add(delete_button);

        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
