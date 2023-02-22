package ui.popups;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AlreadyAdded extends JFrame {
    //error message
    private JLabel msg;
    //error picture
    private JLabel error;

    //EFFECTS: constructs a popup window displaying image and informing user course is already added
    public AlreadyAdded() {
        super("Error");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
        msg = new JLabel("Course already added, cannot add the same course again!");
        error = new JLabel();
        addImageToLabel(error);
        msg.setBounds(25,0,400,50);
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
