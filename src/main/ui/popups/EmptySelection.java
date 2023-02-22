package ui.popups;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EmptySelection extends JFrame {
    //an error message
    private JLabel msg;
    //error picture
    private JLabel error;

    //EFFECTS: constructs an error popup window where the user did not choose a course to commit a manipulation on
    public EmptySelection() {
        super("Error");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
        msg = new JLabel("No selected course.");
        msg.setBounds(25, 0, 400, 50);
        error = new JLabel();
        addImageToLabel(error);
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
