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
    import javax.swing.event.DocumentEvent;
    import javax.swing.event.DocumentListener;

    class GUI_HOME extends JFrame {
        private final HRS hrs;
        private boolean window_checker;

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

            b.setPreferredSize(new Dimension(100, 30));
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

            b.setPreferredSize(new Dimension(100, 30));
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

            t.setPreferredSize(new Dimension(100, 30));
            t.setMargin(new Insets(10, 0, 10, 0));

            return t;
        }

        public JTextArea ASSET_OUTPUT_BOX(int width, int height){
            JTextArea a = new JTextArea();
            a.setEditable(false);

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
            setBackground(Colors.getBlack());
            setSize(900, 1000);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(null);

            ArrayList<JPanel> panels  = ADD_PANELS(3);

            /* code of TOP PANEL (panel : 0)
             *  contains:   LEFT TITLE
             *              RIGHT TITLE
             */
            panels.getFirst().setBounds( 0, 0, 900, 50);        // panels[0] = top panel
            panels.getFirst().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

            // [TITLE BOX] LEFT TITLE
            String[] title_left = new String[] {"<b>Hotel Reservation System</b>", "Version 2.1.1"};
            panels.getFirst().add(ASSET_TITLE_BOX(title_left, "left", 260, 180));

            // [TITLE BOX] RIGHT TITLE
            String[] title_right_text = new String[] {ASSET_CLOCK_TIME(), "All Rights Reserved"};
            JEditorPane title_right = ASSET_TITLE_BOX(title_right_text, "right", 260, 180);
            panels.getFirst().add(title_right);
            Timer timer_clock = new Timer(1000, (ActionEvent e) ->{
                title_right_text[0] = ASSET_CLOCK_TIME();
                UPDATE_TITLE_BOX(title_right, title_right_text, "right");
            });
            timer_clock.start();

            /* code of LEFT PANEL (panel : 1)
             *  contains:
             */
            panels.get(1).setBounds( 0, 50, 450, 950);      // panels[1] = left panel

            /* code of RIGHT PANEL (panel : 2)
             *  contains:
             */

            // set the bgcolor and add the panels
            for (JPanel panel : panels){
                panel.setBackground(Colors.getBlack());
                add(panel);
            }
            panels.get(2).setBounds( 450, 50, 450, 950);    // panels[2] = right panel

            setVisible(true);
        }

        public GUI_HOME(HRS hrs){
            this.hrs = hrs;              // init HRS
            this.window_checker = false; // init the window checker as false
            init();
        }
    }
