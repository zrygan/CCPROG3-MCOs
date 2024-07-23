package HotelReservationSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Class that contains all the assets of the GUI implementation of the HRS.
 */
public class Assets {

    /**
     * Assets empty constructor
     */
    public Assets(){

    }

    /**
     * Makes a basic button
     * @param text the text inside the button
     * @return the button created
     */
    public static JButton ASSET_BASIC_BUTTON(String text){
        Font f = Fonts.get("Regular", 14);
        JButton b = new JButton(text);

        b.setFont(f);
        b.setForeground(Colors.getVividGreen());
        b.setBackground(Colors.getDarkGreen());

        Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 3);
        b.setBorder(bdr);

        b.setPreferredSize(new Dimension(150, 45));
        b.setMargin(new Insets(10, 0, 10, 0));

        b.setHorizontalAlignment(JButton.CENTER);

        return b;
    }

    /**
     * Makes a bright accented button
     * @param text the text inside the button
     * @return the button created
     */
    public static JButton ASSET_ACCENT_BUTTON(String text){
        Font f = Fonts.get("Bold", 14);
        JButton b = new JButton(text);

        b.setFont(f);
        b.setForeground(Colors.getDarkGreen());
        b.setBackground(Colors.getVividGreen());

        Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 3);
        b.setBorder(bdr);

        b.setPreferredSize(new Dimension(150, 45));
        b.setMargin(new Insets(10, 0, 10, 0));

        b.setHorizontalAlignment(JButton.CENTER);

        return b;
    }

    /**
 * Makes a text field for the user to fill in
 * @param dummy the dummy text inside the text field
 * @return the text field created
 */
public static JTextField ASSET_TEXT_FIELD(String dummy){
    Font f = Fonts.get("Regular", 14);
    JTextField t = new JTextField();

    t.setText(dummy);
    t.setFont(f);
    
    //add temporary text field for the dummy text field
    t.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (t.getText().equals(dummy)) {
                t.setText("");
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (t.getText().isEmpty()) {
                t.setText(dummy);
            }
        }
    });

    t.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            ASSET_TEXT_FIELD_UPDATE();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ASSET_TEXT_FIELD_UPDATE();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ASSET_TEXT_FIELD_UPDATE();
        }

        private void ASSET_TEXT_FIELD_UPDATE(){
            String text = t.getText().trim();
            Color color = text.isEmpty() ? Colors.getNormGreen() : Colors.getVividGreen();
            t.setForeground(color);
        }
    });

    Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 3);
    t.setBorder(bdr);

    t.setForeground(Colors.getNormGreen());
    t.setBackground(Colors.getDarkGreen());
    t.setToolTipText(dummy);

    t.setPreferredSize(new Dimension(150, 45));
    t.setMargin(new Insets(10, 0, 10, 0));

    t.setHorizontalAlignment(JTextField.CENTER);

    return t;
}

    /**
     * Not meant to be called. Creates the preliminaries for the ASSET_OUTPUT_BOX
     * @return a JTextArea of the output box with preliminary design
     */
    public static JTextArea ASSET_OUTPUT_BOX(){
        Font f = Fonts.get("Regular", 16);
        JTextArea a = new JTextArea();
        a.setEditable(false);
        a.setForeground(Colors.getVividGreen());
        a.setBackground(Colors.getBlack());
        a.setFont(f);

        Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 2);
        a.setBorder(bdr);

        return a;
    }

    /**
     * Creates a dynamically updating output box. For the hotel list
     *
     * @param width the width of the box
     * @param height the height of the box
     * @param hrs the hotel reservation system
     * @return the output box created
     */
    public static JTextArea ASSET_OUTPUT_BOX(int width, int height, HRS hrs){
        JTextArea a = ASSET_OUTPUT_BOX();

        a.setPreferredSize(new Dimension(width, height));

        Timer timer_outputBox = new Timer(1000, (ActionEvent _) -> a.setText(UPDATE_OUTPUT_BOX(hrs).toString()));
        timer_outputBox.start();

        return a;
    }

    /**
     * Creates a dynamically updating output box. For display messages.
     * @param width the width of the box
     * @param height the height of the box
     * @param display the display message
     * @return the output box created
     */
    public static JTextArea ASSET_OUTPUT_BOX(int width, int height, String display) {
        JTextArea a = ASSET_OUTPUT_BOX();

        a.setPreferredSize(new Dimension(width, height));

        a.setText(display);

        a.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTextArea();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTextArea();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTextArea();
            }

            private void updateTextArea() {
                SwingUtilities.invokeLater(() -> {
                    a.setText(display);
                });
            }
        });

        return a;
    }

    /**
     * Creates a title box.
     * @param contents the contents of the title box, as an array of strings.
     * @param alignment the alignment of the text
     * @param width the width of the box
     * @param height the height of the box
     * @return the created title box
     */
    public static JEditorPane ASSET_TITLE_BOX(String[] contents, String alignment, int width, int height){
        JEditorPane ep = new JEditorPane();
        StringBuilder s = new StringBuilder();

        ep.setContentType("text/html");
        ep.setFont(Fonts.get("Regular", 18));
        ep.setForeground(Colors.getVividGreen());
        ep.setBackground(Colors.getBlack());
        ep.setEditable(false);
        ep.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

        s.append("<html><body style=\"text-align:".concat(alignment).concat(";\">"));

        for (int i = 0; i < contents.length; i++){
            s.append(contents[i]);

            if (i < contents.length - 1) {
                s.append("<br>");
            }
        }

        s.append("</body></html>");

        ep.setText(s.toString());
        ep.setPreferredSize(new Dimension(width, height));

        return ep;
    }

    /**
     * Creates a separator for the buttons.
     * @param width the width of the buttons (the entire width of the JPanel)
     * @return the created separator.
     */
    public static JPanel ASSET_SEPARATOR(int width){
        JPanel p = new JPanel();
        p.setBackground(Colors.getBlack());
        p.setPreferredSize(new Dimension(width, 30));
        p.setBorder(new EmptyBorder(5,0,5,0));
        return p;
    }

    /**
     * Created an array of panels.
     * @param count the number of panels to be created.
     * @return an ArrayList of the panels that were created.
     */
    public static ArrayList<JPanel> ASSET_ADD_PANELS(int count){
        ArrayList<JPanel> panels = new ArrayList<>();

        for (int i = 0; i < count; i++){ panels.add(new JPanel());}

        return panels;
    }

    /**
     * Updates the text inside the title box. For the clock.
     *
     * @param ep the editor pane of the title box
     * @param contents the contents of the title box
     * @param alignment the alignment of the text
     */
    public static void UPDATE_TITLE_BOX(JEditorPane ep, String[] contents, String alignment){
        StringBuilder s = new StringBuilder();

        s.append("<html><body style=\"text-align:".concat(alignment).concat(";\">"));

        for (int i = 0; i < contents.length; i++){
            s.append(contents[i]);

            if (i < contents.length - 1) {
                s.append("<br>");
            }
        }

        s.append("</body></html>");

        ep.setText(s.toString());
    }

    /**
     * Specifically for the hotel list. Updates the list of hotels inside the output box
     *
     * @param hrs the hotel reservation system
     *
     * @return a StringBuilder of the list of hotels
     */
    public static StringBuilder UPDATE_OUTPUT_BOX(HRS hrs){
        StringBuilder out = new StringBuilder();
        for (Hotel hotel : hrs.getHotels()){
            out.append("\n     ").append(hotel.getName());
        }

        return out;
    }

    /**
     * Specifically for the status bar. Updates the message inside the output box.
     *
     * @param out the string to put in the status bar
     * @return the string
     */
    public static String UPDATE_OUTPUT_BOX(String out){
        return out;
    }

    /**
     * Gets the current time and formats it.
     * @return the formatted time.
     */
    public static String UPDATE_CLOCK_TIME(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(format);
    }

    /**
     * Creates a JMenuBar for the dropdown buttons
     * @return the created JMenuBar
     */
    public static JMenuBar ASSET_MENU_BAR() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Colors.getDarkGreen());
        menuBar.setBorder(BorderFactory.createLineBorder(Colors.getVividGreen(), 2));

        menuBar.setPreferredSize(new Dimension(150, 45));
        menuBar.setMargin(new Insets(10, 0, 10, 0));
    
        return menuBar;
    }

    // /**
    //  * Creates a dynamically updating menu bar for the hotel list
    //  *
    //  * @param hrs the hotel reservation system
    //  * @return the menu bar created
    //  * FIXME: REMOVE THIS 
    //  */
    // public static JMenuBar ASSET_MENU_BAR(HRS hrs , String dummy){
    //     JMenuBar menuBar = ASSET_MENU_BAR();
        
        JMenu hotelMenu = createMenu(dummy);
        menuBar.add(hotelMenu);
        for (Hotel hotel : hrs.getHotels()) {
            JMenuItem hotelList = createMenu(hotel.getName());
            hotelMenu.add(hotelList);
        }


    //     menuBar.setBackground(Colors.getDarkGreen());
    //     menuBar.setBorder(BorderFactory.createLineBorder(Colors.getVividGreen(), 2));
    //     menuBar.setPreferredSize(new Dimension(150, 45));
    //     menuBar.setMargin(new Insets(10, 0, 10, 0));
    //     return menuBar;
    // }
    
    /**
     * Creates the options of the menu bar
     * @param name the name of the menu
     * @return the created JMenu
     */
    public static JMenu createMenu(String name) {
        JMenu menu = new JMenu(name);
        menu.setForeground(Colors.getVividGreen());
        menu.setFont(Fonts.get("Regular", 18));

        menu.setPreferredSize(new Dimension(150, 45));
        menu.setMargin(new Insets(10, 0, 10, 0));
    
        menu.setHorizontalAlignment(JMenu.CENTER);

        return menu;
    }
    
    /**
     * Creates a JMenuItem and their actions listeners
     * @param name the name of the menu item
     * @return the created JMenuItem
     */
    public static JMenuItem createMenuItem(String name) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setBackground(Colors.getDarkGreen());
        menuItem.setForeground(Colors.getVividGreen());
        menuItem.setFont(Fonts.get("Regular", 18));
        menuItem.setPreferredSize(new Dimension(150, 45));

        menuItem.setHorizontalAlignment(JMenuItem.CENTER);
    
        return menuItem;
    }
}