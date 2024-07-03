package HotelReservationSystem;

import java.awt.Color;
import javax.swing.*;

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
 *      create hotel
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

public class GUI extends JFrame {
    public GUI() {

        super("Hotel Reservation System (Ganituen, Jimenez)");
        setSize(450, 500);
        initMain();
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMain() {

    }

    /**
     * returns the vivid green color from the hex code
     * @return color from the vivid green hex code
     */
    public Color getVividGreen(){
        return Color.decode("#22F21B");
    }

    /**
     * returns the dark green color from the hex code
     * @return color from the dark green hex code
     */
    public Color getDarkGreen(){
        return Color.decode("#022601");
    }

    /**
     * returns the black color from the hex code
     * @return color from the black hex code
     */
    public Color getBlack(){
        return Color.decode("#0d0d0d");
    }

    /**
     * returns the black color from the hex code
     * @return color from the black hex code
     */
    public Color getNormGreen(){
        return Color.decode("#11790E");
    }
}
