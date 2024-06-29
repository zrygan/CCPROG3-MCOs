package HotelReservationSystem;

import javax.swing.*;

/*
 * ========== DESIGN SPECS for Main_GUI ==========
 * 
 * Dimensions:
 *      W   450 (px)
 *      H   500 (px)
 * 
 * Fonts:
 *      FONT_NAME   TTF_FILENAME    GOOGLE_FONTS_LINK
 *      ...
 * 
 * Colors: ADOBE_COLORS_LINK
 *      COLOR_NAME  HEX_CODE    DESC (is it for text, background, highlight, button?)
 *      ...
 * 
 */

public class Main_GUI extends JFrame {

    public Main_GUI() {
        super("Hotel Reservation System (Ganituen, Jimenez)");
        setSize(450, 500);
        initMain();
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMain() {

    }
}
