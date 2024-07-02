package HotelReservationSystem;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {

    /**
     * Created a font that can be used by the Java Swing GUI from a .ttf file
     * @param type the type (regular, bold, italic) of the font
     * @param pt the size of the font
     * @param isRegistered checker if the font is already registered in the local graphics Environment
     * @return the derived font with the approriate size
     */
    public static Font get(String type, float pt, boolean isRegistered) {
        // get the file name from the type
        String filename = "UbuntuMono-" + type + ".ttf";

        // make a new file with the filename
        File file = new File(filename);

        try {

            // create and register a font with the filename created
            Font font = Font.createFont(Font.TRUETYPE_FONT, file);

            if (!isRegistered) {
                register(font);
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
     * Registers the font in the local graphis environment
     * @param font the font to be registered
     */
    public static void register(Font font) {
        // Make instance of Graphics Environment (for loading in .ttf (font) files)
        GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Register the font
        graphEnv.registerFont(font);
    }
}
