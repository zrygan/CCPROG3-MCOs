package HotelReservationSystem;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * A class that handles fonts for the GUI of the Hotel Reservation System.
 */
public class Fonts {

    /**
     * Default empty constructor
     */
    public Fonts() {

    }

    /**
     * Created a font that can be used by the Java Swing GUI from a .ttf file
     * 
     * @param type         the type (regular, bold, italic) of the font
     * @param pt           the size of the font
     * @param isRegistered checker if the font is already registered in the local
     *                     graphics Environment
     * @return the derived font with the approriate size and type
     */
    public static Font get(String type, float pt, boolean isRegistered) {
        // get the file name from the type
        String filename = "/HotelReservationSystem/fonts/UbuntuMono-" + type + ".ttf";

        try {
            InputStream stream = Fonts.class.getResourceAsStream(filename);

            if (stream == null) {
                throw new IOException("Error. Font file not found!");
            }
            // create and register a font with the filename created
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream);

            if (!isRegistered) {
                // Make instance of Graphics Environment (for loading in .ttf (font) files)
                GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

                // Register the font
                graphEnv.registerFont(font);
            }

            // return the font with the font size
            return font.deriveFont(pt);
        } catch (IOException | FontFormatException e) {
            System.out.printf("\nError. Font in file '%s' is not found.", filename);

            // return some default font with a default font size
            return new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        }
    }

    /**
     * Method overloading of get() without isRegistered parameter
     * 
     * @param type the type (regular, italic, bold) of the font
     * @param pt   the size of the font
     * @return the font with the appropriate size and type
     */
    public static Font get(String type, float pt) {
        return get(type, pt, false);
    }

    /**
     * Initializes the fonts and the types.
     */
    public static void init() {
        get("Regular", 14, false);
        get("Bold", 14, false);
        get("Italic", 14, false);
    }
}
