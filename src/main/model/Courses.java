package model;

import model.eventlogging.Event;
import model.eventlogging.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


//represents a list of courses
public class Courses implements Writable {
    public static final String JSON_FILE_DESTINATION = "./data/courses.json";

    private ArrayList<Course> loc;
    //private int totalCost;

    //EFFECTS: creates a new list, containing courses
    public Courses() {
        loc = new ArrayList<>();
        //totalCost = 0;
    }

    //EFFECTS: returns the list of courses
    public ArrayList<Course> getLoc() {
        return loc;
    }


    //EFFECTS: to check the emptiness of the list
    public boolean isEmpty() {
        if (loc.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    //EFFECTS: get the size of the list of courses
    public int getSize() {
        return loc.size();
    }

    //EFFECTS: get the course at the given index
    public Course getIndex(int i) {
        return loc.get(i);
    }


    //EFFECTS: returns true if given course is in list of courses
    //         returns false if given course is not in list of courses
    public boolean containsCourses(Course course) {
        return loc.contains(course);
    }

    //REQUIRES: given course is not already in the list of courses
    //MODIFIES: this
    //EFFECTS: add a course to the list of registered courses
    public void addCourses(Course course) {
        if (!loc.contains(course)) {
            loc.add(course);
            EventLog.getInstance().logEvent(new Event("Added " + course.getCourseName() + "."));
        }
    }

    //REQUIRES: given course is in the list of courses
    //MODIFIES: this
    //EFFECTS: remove a course from the list of registered courses
    public void removeCourses(Course course) {
        if (loc.contains(course)) {
            loc.remove(course);
            EventLog.getInstance().logEvent(new Event("Removed " + course.getCourseName() + "."));
        }
    }

    //MODIFIES: totalCost
    //EFFECTS: calculates the total cost of all registered courses
    public int tuitionCost() {
        int totalCost = 0;
        for (Course course: loc) {
            totalCost = totalCost + course.getCourseCost();

        }
        EventLog.getInstance().logEvent(new Event("Calculated tuition, total is: " + totalCost + "."));
        return totalCost;
    }

    //MODIFIES: this
    //EFFECTS: clears the courses
    public void clearCourses() {
        loc.removeAll(loc);
        EventLog.getInstance().logEvent(new Event("Cleared courses."));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("totalCost", this.tuitionCost());
        json.put("courses", coursesToJson());
        return json;
    }

    //EFFECTS: return things in this workroom as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course course : loc) {
            jsonArray.put(course.toJson());
        }

        return jsonArray;
    }

}
