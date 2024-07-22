package HotelReservationSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GUI_CREATE extends GUI{
    private final GUI_MAIN mains;

    public GUI_MAIN getMains(){
        return mains;
    }

    public GUI_CREATE(HRS hrs, int window_height, int window_width, GUI_MAIN mains){
        super(hrs, window_height, window_width);
        this.mains = mains;
    }

    @Override
    public void GUI_CONFIG_WINDOW_CLOSE() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mains.setWindowChecker(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                mains.setWindowChecker(false);
            }
        });
    }

    @Override
    public void init(){
        setTitle("Hotel Reservation System: Creating a hotel");
        setSize(window_width, window_height);
        GUI_CONFIG_WINDOW_CLOSE();
        setLayout(null);
        setResizable(false);

        ArrayList<JPanel> panels  = Assets.ASSET_ADD_PANELS(3);

        /* code of TOP PANEL (panel : 0)
         *  contains:   TOP TITLE
         */
        panels.getFirst().setBounds( 0, 0, window_width, 100);        // panels[0] = top panel
        panels.getFirst().setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // [TITLE BOX] Top Title (Hotel Reservation System and Version)
        String[] title_top = new String[] {"<b>Hotel Reservation System</b>", ">>> Viewing a Hotel"};
        panels.getFirst().add(Assets.ASSET_TITLE_BOX(title_top, "left", 450, 50));


        /* code of LEFT PANEL (panel : 1)
         *  contains:
         */
        panels.get(1).setBounds( 0, 100, window_width/2, window_height); // panels[1] = left panel
        panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
        panels.get(1).add(Assets.ASSET_OUTPUT_BOX(350,500, hrs));

        /* code of RIGHT PANEL (panel : 2)
         *  contains:
         */
        panels.get(2).setBounds( window_width/2,100, window_width/2, window_height); // panels[2] = right panel
        panels.get(2).setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // [MENU BAR] Create Hotel Button
        JMenuBar view_hot_name = Assets.ASSET_MENU_BAR(hrs, "Hotel Name");
        // ADD ACTION LISTENER
        panels.get(2).add(view_hot_name);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width/2));

        JButton high_lvl_info = Assets.ASSET_ACCENT_BUTTON("Hight Level Info");
        panels.get(2).add(high_lvl_info);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width/2));

        JTextField ent_day = Assets.ASSET_TEXT_FIELD("Enter Day");
        // ADD ACTION LISTENER
        panels.get(2).add(ent_day);

        panels.get(2).add(Assets.ASSET_SEPARATOR(window_width/2));

        JTextField res_name = Assets.ASSET_TEXT_FIELD("Reservation Name");
        // ADD ACTION LISTENER
        panels.get(2).add(res_name);

        for (JPanel panel : panels){
            panel.setBackground(Colors.getBlack());
            add(panel);
        }

        setVisible(true);
    }
}
