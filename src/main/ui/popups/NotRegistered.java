package ui.popups;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NotRegistered extends JFrame {
    //the error message
    private JLabel msg;
    //the error picture
    private JLabel error;

    //EFFECTS: constructs a popup window displaying the error that remove course encountered error
    public NotRegistered() {
        super("Error");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
        msg = new JLabel("Selected course is not already registered!");
        error = new JLabel();
        addImageToLabel(error);
        msg.setBounds(25, 0, 400, 50);
        add(msg);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //MODIFIES: error
    //EFFECTS: constructs a scaled version of the error picture
    private void addImageToLabel(JLabel error) {
        ImageIcon imageIcon = new ImageIcon("./data/xmark.jpg");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(350,350,Image.SCALE_SMOOTH);
        error.setIcon(new ImageIcon(newImage));
        error.setBounds(20,25,380,380);
        add(error);
    }
}
