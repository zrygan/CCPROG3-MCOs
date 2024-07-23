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
*          H   600 (px)
*
*      manage hotel
*          W   800 (px)
*          H   500 (px)
*
*      sim booking
*          W   500 (px)
*          H   750 (px)
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

package HotelReservationSystem;

import javax.swing.*;

public abstract class GUI extends JFrame {
    protected final HRS hrs;
    protected final int window_height;
    protected final int window_width;

    public GUI(HRS hrs, int window_height, int window_width){
        this.hrs = hrs;
        this.window_height = window_height;
        this.window_width = window_width;
    }
    
    public abstract void init();

    public abstract void GUI_CONFIG_WINDOW_CLOSE();
}