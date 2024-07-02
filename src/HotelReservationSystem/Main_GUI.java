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
 * Colors: https://color.adobe.com/Pip-Boy-Theme-color-theme-cf4447b6-dfc5-41e6-a20f-ca988f1f5447/
 *      COLOR_NAME  HEX_CODE    DESC (is it for text, background, highlight, button?) 
 *      cName       #022601         
 *      cName       #22F21B         
 *      cName       #094007         
 *      cName       #1A8C16         
 *      cName       #0D0D0D         
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
