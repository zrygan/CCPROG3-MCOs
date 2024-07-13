package HotelReservationSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        return b;
    }

    public JButton ASSET_ACCENT_BUTTON(String text){
        Font f = Fonts.get("Regular", 14);
        JButton b = new JButton(text);

        b.setFont(f);
        b.setForeground(Colors.getVividGreen());
        b.setBackground(Colors.getDarkGreen());

        Border bdr = BorderFactory.createLineBorder(Colors.getVividGreen(), 3);
        b.setBorder(bdr);

        b.setPreferredSize(new Dimension(100, 30));

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

    public void init(JFrame frm){
        Fonts.init();               // init the fonts

        setVisible(true);
        setTitle("Hotel Reservation System [Ganituen, Jimenez]");
        setSize(900, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Colors.getBlack());

        frm.add(panel);
    }

    public GUI_HOME(HRS hrs){
        this.hrs = hrs;             // init HRS
        this.window_checker= false; // init the window checker as false
    }
}
