package ui;


import model.Course;
import model.Courses;
import model.eventlogging.EventLog;
import model.eventlogging.LogPrinter;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.popups.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.eventlogging.Event;
import model.eventlogging.EventLog;

import static model.Courses.JSON_FILE_DESTINATION;

public class GUI extends JFrame implements ActionListener {
    //a label to declare the registered courses
    private JLabel labelRegisteredCourses;
    //a label to declare the available courses to choose from
    private JLabel labelAvailableCourses;
    //JList to represent the registered courses that are displayed on GUI
    private JList<String> listRegisteredCourses;
    //JList to represent the available courses that are displayed on GUI
    private JList<String> listAvailableCourses;
    //DefaultListModel to represent the list of registered courses in String
    private DefaultListModel<String> defaultListRegisteredCourses;
    //DefaultListModel to represent the list of available courses in String
    private DefaultListModel<String> defaultListAvailableCourses;
    //represents the registered courses of type Courses, for JSON to Courses to DefaultListModel<String> transfer
    private Courses trackingRegisteredCourses;
    //A button that adds a selected course
    private JButton addCourse;
    //A button that removes a selected course
    private JButton removeCourse;
    //A button that saves the current registered courses
    private JButton save;
    //A button that loads the saved courses
    private JButton load;
    //A button to terminate the program
    private JButton quit;
    //A button to calculate the total tuition of all registered courses
    private JButton tuition;
    //A button to clear the registered courses
    private JButton clear;

    //courses to be used and stored
    private Course cpsc100;
    private Course cpsc110;
    private Course cpsc210;
    private Course cpsc221;
    private Course cpsc320;
    private Course cpsc420;

    //tracks tuition
    private int currTuition;

    //JsonReader and JsonWriter for save and load
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    //EFFECTS: constructs a Graphics User Interface
    public GUI() {
        super("Class Registration");
        initializeCourses();
        initializeJFrame();
        placeJFrame();
        addLabels();
        addButtons();
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(840, 700));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    //EFFECTS: initializes all required JFrame
    public void initializeJFrame() {
        labelRegisteredCourses = new JLabel("Registered Courses: ");
        labelAvailableCourses = new JLabel("Available Courses: (select course to add or remove)");
        defaultListRegisteredCourses = new DefaultListModel<>();
        listRegisteredCourses = new JList<>(defaultListRegisteredCourses);
        defaultListAvailableCourses = new DefaultListModel<>();

        defaultListAvailableCourses.addElement("CPSC 100");
        defaultListAvailableCourses.addElement("CPSC 110");
        defaultListAvailableCourses.addElement("CPSC 210");
        defaultListAvailableCourses.addElement("CPSC 221");
        defaultListAvailableCourses.addElement("CPSC 320");
        defaultListAvailableCourses.addElement("CPSC 420");
        listAvailableCourses = new JList<>(defaultListAvailableCourses);
        addCourse = new JButton("Add Course");
        removeCourse = new JButton("Remove Course");
        save = new JButton("Save");
        load = new JButton("Load");
        quit = new JButton("Quit Application");
        tuition = new JButton("Calculate Tuition");
        clear = new JButton("Clear registered courses");


    }

    //EFFECTS: places all JFrame on the GUI
    private void placeJFrame() {
        labelRegisteredCourses.setBounds(10, 0, 300, 20);
        labelAvailableCourses.setBounds(450, 0, 500, 20);
        listRegisteredCourses.setBounds(10, 25, 400, 350);
        listRegisteredCourses.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        listAvailableCourses.setBounds(420, 25, 400, 350);
        listAvailableCourses.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addCourse.setBounds(10, 400, 250, 100);
        removeCourse.setBounds(280, 400, 250, 100);
        tuition.setBounds(550, 400, 250, 100);
        save.setBounds(10, 550, 250, 100);
        load.setBounds(280, 550, 250, 100);
        quit.setBounds(550, 550, 250, 100);
        clear.setBounds(280, 510, 250, 25);
    }

    //EFFECTS: add the labels onto the GUI
    private void addLabels() {
        add(labelRegisteredCourses);
        add(labelAvailableCourses);
        add(listRegisteredCourses);
        add(listAvailableCourses);
    }

    //EFFECTS: add the buttons onto the GUI
    private void addButtons() {
        add(addCourse);
        addCourse.setActionCommand("ADD");
        addCourse.addActionListener(this);
        add(removeCourse);
        removeCourse.setActionCommand("REMOVE");
        removeCourse.addActionListener(this);
        add(save);
        save.setActionCommand("SAVE");
        save.addActionListener(this);
        add(load);
        load.setActionCommand("LOAD");
        load.addActionListener(this);
        add(quit);
        quit.setActionCommand("QUIT");
        quit.addActionListener(this);
        add(tuition);
        tuition.setActionCommand("TUITION");
        tuition.addActionListener(this);
        add(clear);
        clear.setActionCommand("CLEAR");
        clear.addActionListener(this);
    }

    //EFFECTS: initialize courses and all relative fields
    public void initializeCourses() {
        cpsc100 = new Course("CPSC 100", 70);
        cpsc110 = new Course("CPSC 110", 80);
        cpsc210 = new Course("CPSC 210", 120);
        cpsc221 = new Course("CPSC 221", 100);
        cpsc320 = new Course("CPSC 320", 115);
        cpsc420 = new Course("CPSC 420", 90);
        trackingRegisteredCourses = new Courses();
        currTuition = 0;
        jsonWriter = new JsonWriter(JSON_FILE_DESTINATION);
        jsonReader = new JsonReader(JSON_FILE_DESTINATION);
    }

    //EFFECTS: performs an assigned tasked based on button press
    //         AddCourse | removeCourse | calculateTuition |
    //         quit | save | load | clear registered courses
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("ADD")) {
            addCourse();
        } else if (action.equals("REMOVE")) {
            removeCourse();
        } else if (action.equals("TUITION")) {
            calculateTuition();
        } else if (action.equals("QUIT")) {
            this.printLog(EventLog.getInstance());
            dispose();
        } else if (action.equals("SAVE")) {
            saveFile();
        } else if (action.equals("LOAD")) {
            loadFile();
        } else if (action.equals("CLEAR")) {
            clearCourses();
        }
    }

    //MODIFIES: defaultListRegisteredCourses, listRegisteredCourses, trackingRegisteredCourses
    //EFFECTS: clears courses
    private void clearCourses() {
        defaultListRegisteredCourses.removeAllElements();
        listRegisteredCourses.removeAll();
        trackingRegisteredCourses.clearCourses();
    }

    //MODIFIES: listRegisteredCourses, trackingRegisteredCourses
    //EFFECTS: load JSON file onto GUI
    private void loadFile() {
        DefaultListModel<String> emptyList = new DefaultListModel<>();
        try {
            listRegisteredCourses = new JList<>(emptyList);
            trackingRegisteredCourses = jsonReader.read();
            coursesToDefault();
        } catch (IOException e) {
            new FileNotFound();
        }
    }

    //EFFECTS: save registered courses onto JSON file
    private void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(trackingRegisteredCourses);
            jsonWriter.close();
        } catch (IOException e) {
            new FileNotFound();
        }
    }

    //EFFECTS: add the course selected by user, if no course is selected produce a popup error
    private void addCourse() {
        String selected1 = listAvailableCourses.getSelectedValue();
        if (listAvailableCourses.isSelectionEmpty()) {
            new EmptySelection();
        }
        if (!defaultListRegisteredCourses.contains(selected1)) {
            defaultListRegisteredCourses.addElement(selected1);
            addAvailableCourses();
        } else if (defaultListRegisteredCourses.contains(selected1)) {
            new AlreadyAdded();
        }
    }

    //EFFECTS: removes the course selected by user, if no course is selected produce a popup error
    private void removeCourse() {
        String selected2 = listAvailableCourses.getSelectedValue();

        if (listAvailableCourses.isSelectionEmpty()) {
            new EmptySelection();
        } else if (!defaultListRegisteredCourses.contains(selected2)) {
            new NotRegistered();
        }
        if (defaultListRegisteredCourses.contains(selected2)) {
            defaultListRegisteredCourses.removeElement(selected2);
            removeRegisteredCourses();
        }

    }

    //EFFECTS: add selected course to Courses tracker
    private void addAvailableCourses() {
        String selected = listAvailableCourses.getSelectedValue();
        if (selected == "CPSC 100") {
            trackingRegisteredCourses.addCourses(cpsc100);
        } else if (selected == "CPSC 110") {
            trackingRegisteredCourses.addCourses(cpsc110);
        } else if (selected == "CPSC 210") {
            trackingRegisteredCourses.addCourses(cpsc210);
        } else if (selected == "CPSC 221") {
            trackingRegisteredCourses.addCourses(cpsc221);
        } else if (selected == "CPSC 320") {
            trackingRegisteredCourses.addCourses(cpsc320);
        } else if (selected == "CPSC 420") {
            trackingRegisteredCourses.addCourses(cpsc420);
        }
    }

    //EFFECTS: remove selected course from Courses tracker
    private void removeRegisteredCourses() {
        String selected = listAvailableCourses.getSelectedValue();
        if (selected == "CPSC 100") {
            trackingRegisteredCourses.removeCourses(cpsc100);
        } else if (selected == "CPSC 110") {
            trackingRegisteredCourses.removeCourses(cpsc110);
        } else if (selected == "CPSC 210") {
            trackingRegisteredCourses.removeCourses(cpsc210);
        } else if (selected == "CPSC 221") {
            trackingRegisteredCourses.removeCourses(cpsc221);
        } else if (selected == "CPSC 320") {
            trackingRegisteredCourses.removeCourses(cpsc320);
        } else if (selected == "CPSC 420") {
            trackingRegisteredCourses.removeCourses(cpsc420);
        }

    }

    //EFFECTS: calculates the tuition of the current registered courses
    public void calculateTuition() {
        currTuition = trackingRegisteredCourses.tuitionCost();
        new CalculateTuition(currTuition);
    }

    //EFFECTS: override current registered Courses with loaded Courses
    public void coursesToDefault() {
        defaultListRegisteredCourses.removeAllElements();
        for (Course course : trackingRegisteredCourses.getLoc()) {

            defaultListRegisteredCourses.addElement(course.getCourseName());
        }
    }

    //EFFECTS: prints the event log
    private void printLog(EventLog el) {
        String msg = "";
        for (Event next : el) {
            msg = msg + next.toString() + "\n\n";
        }
        System.out.println(msg);
    }



}
