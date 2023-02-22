package ui.popups;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculateTuition extends JFrame  {
    //message
    private JLabel msg;

    //EFFECTS: constructs a popup window displaying the tuition cost
    public CalculateTuition(int tuition) {
        super("Tuition cost");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(100, 100));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
        msg = new JLabel("Tuition is: " + tuition + "$");
        msg.setBounds(0,0,100,100);
        add(msg);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

}
