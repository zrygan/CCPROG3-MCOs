package HotelReservationSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 * A class that extends GUI that creates the HRS window
 */
public class GUI_MAIN extends GUI {
    /**
     * A flag to check if the window is being managed or not
     */
    private boolean windowChecker;

    /**
     * Getter for the window checker
     * 
     * @return the value of the window checker
     */
    public boolean getWindowChecker() {
        return windowChecker;
    }

    /**
     * Setter for the window checker
     * 
     * @param windowChecker the new value of the window checker
     */
    public void setWindowChecker(boolean windowChecker) {
        this.windowChecker = windowChecker;
    }

    /**
     * The constructor for GUI_MAIN
     * 
     * @param hrs           the hrs database
     * @param window_height the window height
     * @param window_width  the window width
     */
    public GUI_MAIN(HRS hrs, int window_height, int window_width) {
        super(hrs, window_height, window_width);
        windowChecker = false;
    }

    /**
     * {@inheritDoc}
     * 
     * This method overrides the {@code GUI_CONFIG_WINDOW_CLOSE} method in
     * {@code GUI}.
     * This is a empty method.
     * 
     * @see GUI#GUI_CONFIG_WINDOW_CLOSE()
     */
    @Override
    public void GUI_CONFIG_WINDOW_CLOSE() {
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
        setTitle("Hotel Reservation System [Ganituen, Jimenez]");
        setSize(window_width, window_height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels = Assets.ASSET_ADD_PANELS(3);

        /*
         * code of TOP PANEL (panel : 0)
         * contains: LEFT TITLE
         * RIGHT TITLE
         */
        panels.getFirst().setBounds(0, 0, window_width, 100); // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Left Title (Hotel Reservation System and Version)
        String[] title_left = new String[] { "<b>Hotel Reservation System</b>", "Version 2.1.1" };
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_left, "left", 450, 50));

        // [TITLE BOX] Right title (current time and All Rights Reserved)s
        String[] title_right_text = new String[] { Assets.UPDATE_CLOCK_TIME(), "All Rights Reserved" };
        JEditorPane title_right = Assets.ASSET_TITLE_BOX(title_right_text, "right", 200, 50);
        panels.getFirst().add(title_right);
        Timer timer_clock = new Timer(1000, (ActionEvent _) -> {
            title_right_text[0] = Assets.UPDATE_CLOCK_TIME();
            Assets.UPDATE_TITLE_BOX(title_right, title_right_text, "right");
        });
        timer_clock.start();

        /*
         * code of LEFT PANEL (panel : 1)
         * contains:
         */
        panels.get(1).setBounds(0, 100, window_width / 2, window_height); // panels[1] = left panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panels.get(1).add(Assets.ASSET_OUTPUT_BOX(350, 500, hrs));

        /*
         * code of RIGHT PANEL (panel : 2)
         * contains:
         */
        panels.get(2).setBounds(window_width / 2, 100, window_width / 2, window_height); // panels[2] = right panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // [BASIC BUTTON] Create Hotel Button
        JButton btn_create = Assets.ASSET_BASIC_BUTTON("Create Hotel");
        btn_create.addActionListener(_ -> {
            if (!getWindowChecker()) {
                GUI_CREATE create = new GUI_CREATE(hrs, 500, 500, this);
                create.init();
                setWindowChecker(true);
            }
        });
        panels.get(2).add(btn_create);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width / 2));

        JButton btn_view = Assets.ASSET_BASIC_BUTTON("View Hotel");
        btn_view.addActionListener(_ -> {
            if (!getWindowChecker()) {
                GUI_VIEW view = new GUI_VIEW(hrs, 600, 900, this);
                view.init();
                setWindowChecker(true);
            }
        });
        panels.get(2).add(btn_view);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width / 2));

        JButton btn_manage = Assets.ASSET_BASIC_BUTTON("Manage Hotel");
        btn_manage.addActionListener(_ -> {
            if (!getWindowChecker()) {
                GUI_MANAGE manage = new GUI_MANAGE(hrs, 600, 500, this);
                manage.init();
                setWindowChecker(true);
            }
        });
        panels.get(2).add(btn_manage);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width / 2));

        JButton btn_simbook = Assets.ASSET_ACCENT_BUTTON("Simulate Booking");
        btn_simbook.addActionListener(_ -> {
            if (!getWindowChecker()) {
                GUI_SIM_BOOK sim = new GUI_SIM_BOOK(hrs, 750, 500, this);
                sim.init();
                setWindowChecker(true);
            }
        });
        btn_simbook.setPreferredSize(new Dimension(150, 90));
        panels.get(2).add(btn_simbook);

        // set the background color and add the panels
        for (JPanel panel : panels) {
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
