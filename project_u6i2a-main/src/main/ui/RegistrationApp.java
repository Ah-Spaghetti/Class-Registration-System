package ui;

import model.Course;
import model.Courses;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//course registration application
public class RegistrationApp {
    private Scanner input;
    private Course cpsc100;
    private Course cpsc110;
    private Course cpsc210;
    private Course cpsc221;
    private Course cpsc320;
    private Course cpsc420;
    private Courses loc;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_FILE_DESTINATION = "./data/courses.json";


    //EFFECTS: begins the execution of the registration application
    public RegistrationApp() throws FileNotFoundException {

        runRegistration();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runRegistration() {
        boolean appStatus = true;
        String command = null;

        initialization();

        while (appStatus) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("Your total cost for tuition is $" + loc.tuitionCost());
                appStatus = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Goodbye!");
    }

    //MODIFIES: this
    //EFFECTS: processess user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddCourses();
        } else if (command.equals("r")) {
            doRemoveCourses();
        } else if (command.equals("s")) {
            saveCourses();
        } else if (command.equals("l")) {
            loadCourses();
        } else if (command.equals("p")) {
            printCurrentRegisteredCourses();
        } else {
            System.out.println("Selection unavailable");
        }
    }


    //MODIFIES: this
    //EFFECTS: load the saved courses
    private void loadCourses() {
        try {
            loc = jsonReader.read();
            System.out.println("Loaded registered courses from " + JSON_FILE_DESTINATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE_DESTINATION);
        }
    }

    //EFFECTS: save the current registered courses to JSON file
    private void saveCourses() {
        try {
            jsonWriter.open();
            jsonWriter.write(loc);
            jsonWriter.close();
            System.out.println("Saved currently registered courses to " + JSON_FILE_DESTINATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE_DESTINATION);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a course from the current registration
    private void doRemoveCourses() {
        if (loc.isEmpty()) {
            System.out.println("You currently have no registered courses");
        } else {
            printCurrentRegisteredCourses();
            System.out.println("\nPlease enter the number of the course that you would like to remove");
            int num = input.nextInt();

            if (num == 100) {
                loc.removeCourses(cpsc100);

            } else if (num == 110) {
                loc.removeCourses(cpsc110);

            } else if (num == 210) {
                loc.removeCourses(cpsc210);

            } else if (num == 221) {
                loc.removeCourses(cpsc221);

            } else if (num == 320) {
                loc.removeCourses(cpsc320);

            } else if (num == 420) {
                loc.removeCourses(cpsc420);
            } else {
                System.out.println("Please enter a valid CPSC course number!");
            }
            printCurrentRegisteredCourses();

        }


    }

    //MODIFIES: this
    //EFFECTS: adds a course to the current registration
    private void doAddCourses() {
        System.out.println("\nHere are the 2022 Computer Science courses available!");
        System.out.println("\n" + cpsc100.getCourseName() + ", " + cpsc110.getCourseName() + ", "
                + cpsc210.getCourseName() + ", " + cpsc221.getCourseName() + ", " + cpsc320.getCourseName() + ", "
                + cpsc420.getCourseName());
        System.out.println("\nPlease enter the number of the course that you would like to register.");
        int num = input.nextInt();
        if (num == 100) {
            loc.addCourses(cpsc100);

        } else if (num == 110) {
            loc.addCourses(cpsc110);

        } else if (num == 210) {
            loc.addCourses(cpsc210);

        } else if (num == 221) {
            loc.addCourses(cpsc221);

        } else if (num == 320) {
            loc.addCourses(cpsc320);

        } else if (num == 420) {
            loc.addCourses(cpsc420);
        } else {
            System.out.println("Please enter a valid CPSC course number!");
        }
        printCurrentRegisteredCourses();

    }

    //EFFECTS: prints the current registered courses
    private void printCurrentRegisteredCourses() {

        String courseStr = "";
        for (Course course : loc.getLoc()) {
            courseStr = courseStr + course.getCourseName() + " ";

        }
        System.out.println("Registered courses:[" + courseStr + "]");

    }

    //EFFECTS: displays menu options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the 2022 UBC course registration!");
        System.out.println("\nPlease select:");
        System.out.println("\ta ---> add courses ");
        System.out.println("\tr ---> remove courses");
        System.out.println("\tp ---> print registered courses");
        System.out.println("\tq ---> quit and calculate tuition");
        System.out.println("\ts ---> save courses");
        System.out.println("\tl ---> load saved courses");

    }


    //MODIFIES: this
    //EFFECTS: initialize courses available to choose as well as the needed elements for the application
    private void initialization() {
        cpsc100 = new Course("CPSC 100", 70);
        cpsc110 = new Course("CPSC 110", 80);
        cpsc210 = new Course("CPSC 210", 120);
        cpsc221 = new Course("CPSC 221", 100);
        cpsc320 = new Course("CPSC 320", 115);
        cpsc420 = new Course("CPSC 420", 90);
        loc = new Courses();
        jsonWriter = new JsonWriter(JSON_FILE_DESTINATION);
        jsonReader = new JsonReader(JSON_FILE_DESTINATION);
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

}

