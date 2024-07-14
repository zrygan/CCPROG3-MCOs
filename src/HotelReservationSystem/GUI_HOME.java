    package HotelReservationSystem;

    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
    import javax.swing.*;
    import javax.swing.border.Border;
    import javax.swing.border.EmptyBorder;
    import javax.swing.event.DocumentEvent;
    import javax.swing.event.DocumentListener;

    /*
     * ========== DESIGN SPECS for Main_GUI ==========
     *
     * Dimensions:
     *      home HRS
     *          W   900 (px)
     *          H   1000 (px)
     *
     *      create hotel
     *          W   500 (px)
     *          H   500 (px)
     *
     *      view hotel
     *          W   900 (px)
     *          H   750 (px)
     *
     *      manage hotel
     *          W   500 (px)
     *          H   750 (px)
     *
     *      sim booking
     *          W   900 (px)
     *          H   750 (px)
     *
     *      create room
     *          W   500 (px)
     *          H   550 (px)
     *
     * Fonts:
     *      Ubunto Mono Regular     UbuntuMono-Regular.ttf  https://fonts.google.com/specimen/Ubuntu+Mono
     *      Ubunto Mono Italic      UbuntuMono-Italic.ttf   https://fonts.google.com/specimen/Ubuntu+Mono
     *      Ubunto Mono Bold        UbuntuMono-Bold.ttf     https://fonts.google.com/specimen/Ubuntu+Mono
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

    class GUI_HOME extends JFrame {
        private final HRS hrs;
        private boolean window_checker;
        private final int window_height;
        private final int window_width;

        public boolean getWindow_checker(){
            return window_checker;
        }

        public void setWindow_checker(boolean window_checker){
            this.window_checker = window_checker;
        }

        public void close_handler(JFrame frame){
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                   setWindow_checker(true);
                }
            }));
        }


        public JButton ASSET_BASIC_BUTTON(String text){
            Font f = Fonts.get("Regular", 14);
            JButton b = new JButton(text);

            b.setFont(f);
            b.setForeground(Colors.getVividGreen());
            b.setBackground(Colors.getDarkGreen());

            Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 3);
            b.setBorder(bdr);

            b.setPreferredSize(new Dimension(150, 45));
            b.setMargin(new Insets(10, 0, 10, 0));

            return b;
        }

        public JButton ASSET_ACCENT_BUTTON(String text){
            Font f = Fonts.get("Bold", 14);
            JButton b = new JButton(text);

            b.setFont(f);
            b.setForeground(Colors.getDarkGreen());
            b.setBackground(Colors.getVividGreen());

            Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 3);
            b.setBorder(bdr);

            b.setPreferredSize(new Dimension(150, 45));
            b.setMargin(new Insets(10, 0, 10, 0));

            return b;
        }

        public JTextField ASSET_TEXT_FIELD(String dummy){
            Font f = Fonts.get("Regular", 14);
            JTextField t = new JTextField();

            t.setFont(f);

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

            t.setBackground(Colors.getDarkGreen());
            t.setToolTipText(dummy);

            t.setPreferredSize(new Dimension(150, 45));
            t.setMargin(new Insets(10, 0, 10, 0));

            return t;
        }

        public JTextArea ASSET_OUTPUT_BOX(int width, int height){
            Font f = Fonts.get("Regular", 16);
            JTextArea a = new JTextArea();
            a.setEditable(false);
            a.setForeground(Colors.getVividGreen());
            a.setBackground(Colors.getBlack());
            a.setFont(f);

            Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 2);
            a.setBorder(bdr);

            a.setPreferredSize(new Dimension(width, height));

            Timer timer_outputBox = new Timer(1000, (ActionEvent e) ->{
                a.setText(UPDATE_OUTPUT_BOX().toString());
            });
            timer_outputBox.start();

            return a;
        }

        public JEditorPane ASSET_TITLE_BOX(String[] contents, String alignment, int width, int height){
            JEditorPane ep = new JEditorPane();
            StringBuilder s = new StringBuilder();

            ep.setContentType("text/html");
            ep.setFont(Fonts.get("Regular", 14));
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

        public static JPanel ASSET_SEPARATOR(int width){
            JPanel p = new JPanel();
            p.setBackground(Colors.getBlack());
            p.setPreferredSize(new Dimension(width, 30));
            p.setBorder(new EmptyBorder(5,0,5,0));
            return p;
        }

        public JEditorPane UPDATE_TITLE_BOX(JEditorPane ep, String[] contents, String alignment){
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

            return ep;
        }

        public StringBuilder UPDATE_OUTPUT_BOX(){
            // Note to jaztin:
            // for other output boxes or status boxes
            // just override this method not the ASSET_OUTPUT_BOX
            StringBuilder out = new StringBuilder();
            for (Hotel hotel : hrs.getHotels()){
                out.append("\n     ").append(hotel.getName());
            }

            return out;
        }

        public String ASSET_CLOCK_TIME(){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
            return now.format(format);
        }

        public ArrayList<JPanel> ADD_PANELS(int count){
            ArrayList<JPanel> panels = new ArrayList<>();

            for (int i = 0; i < count; i++){ panels.add(new JPanel());}

            return panels;
        }

        private void init(){
            Fonts.init();               // init the fonts

            setTitle("Hotel Reservation System [Ganituen, Jimenez]");
            setSize(window_width, window_height);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(null);
            setResizable(false);

            ArrayList<JPanel> panels  = ADD_PANELS(3);

            /* code of TOP PANEL (panel : 0)
             *  contains:   LEFT TITLE
             *              RIGHT TITLE
             */
            panels.getFirst().setBounds( 0, 0, window_width, 100);        // panels[0] = top panel
            panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

            // [TITLE BOX] Left Title (Hotel Reservation System and Version)
            String[] title_left = new String[] {"<b>Hotel Reservation System</b>", "Version 2.1.1"};
            panels.getFirst().add(ASSET_TITLE_BOX(title_left, "left", 450, 35));

            // [TITLE BOX] Right title (current time and All Rights Reserved)s
            String[] title_right_text = new String[] {ASSET_CLOCK_TIME(), "All Rights Reserved"};
            JEditorPane title_right = ASSET_TITLE_BOX(title_right_text, "right", 200, 35);
            panels.getFirst().add(title_right);
            Timer timer_clock = new Timer(1000, (ActionEvent e) ->{
                title_right_text[0] = ASSET_CLOCK_TIME();
                UPDATE_TITLE_BOX(title_right, title_right_text, "right");
            });
            timer_clock.start();

            /* code of LEFT PANEL (panel : 1)
             *  contains:
             */
            panels.get(1).setBounds( 0, 100, window_width/2, window_height); // panels[1] = left panel
            panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
            panels.get(1).add(ASSET_OUTPUT_BOX(350,500));

            /* code of RIGHT PANEL (panel : 2)
             *  contains:
             */
            panels.get(2).setBounds( window_width/2,100, window_width/2, window_height); // panels[2] = right panel
            panels.get(2).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

            // [BASIC BUTTON] Create Hotel Button
            panels.get(2).add(ASSET_BASIC_BUTTON("Create Hotel"));

            panels.get(2).add(ASSET_SEPARATOR(window_width/2));
            panels.get(2).add(ASSET_BASIC_BUTTON("View Hotel"));

            panels.get(2).add(ASSET_SEPARATOR(window_width/2));
            panels.get(2).add(ASSET_BASIC_BUTTON("Manage Hotel"));

            panels.get(2).add(ASSET_SEPARATOR(window_width/2));
            JButton simButton = ASSET_ACCENT_BUTTON("Simulate Booking");
            simButton.setPreferredSize(new Dimension(150, 90));
            panels.get(2).add(simButton);

            // set the bgcolor and add the panels
            for (JPanel panel : panels){
                panel.setBackground(Colors.getBlack());
                add(panel);
            }

            setVisible(true);
        }

        public GUI_HOME(HRS hrs){
            this.hrs = hrs;              // init HRS
            this.window_checker = false; // init the window checker as false
            this.window_height = 800;
            this.window_width = 900;
            init();
        }
    }
