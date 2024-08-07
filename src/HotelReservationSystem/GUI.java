/*
* ========== DESIGN SPECS for GUI ==========
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
*          H   600 (px)
*
*      manage hotel
*          W   500 (px)
*          H   600 (px)
*      
*      sim booking
*          W   500 (px)
*          H   750 (px)
*
*      create room
*          W   500 (px)
*          H   600 (px)
*
*      new DPM
*          W   600 (px)
*          H   500 (px)
*      remove reservation
*          W   600 (px)
*          H   500 (px)
*      add room
*          W   600 (px)
*          H   500 (px)
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

package HotelReservationSystem;

import javax.swing.*;

/**
 * An abstract class for creating Graphical User Interfaces (GUIs) for the Hotel
 * Reservation System (HRS).
 */
public abstract class GUI extends JFrame {
    /**
     * The Hotel Reservation System (HRS) instance
     */
    protected final HRS hrs;

    /**
     * The height of the GUI window
     */
    protected final int window_height;

    /**
     * The width of the GUI window
     */
    protected final int window_width;

    /**
     * Constructor for the GUI class
     * 
     * @param hrs           The Hotel Reservation System (HRS) instance
     * @param window_height The height of the GUI window
     * @param window_width  The width of the GUI window
     */
    public GUI(HRS hrs, int window_height, int window_width) {
        this.hrs = hrs;
        this.window_height = window_height;
        this.window_width = window_width;
    }

    /**
     * Initializes the GUI components
     */
    public abstract void init();

    /**
     * Configures the GUI window close operation
     */
    public abstract void GUI_CONFIG_WINDOW_CLOSE();
}