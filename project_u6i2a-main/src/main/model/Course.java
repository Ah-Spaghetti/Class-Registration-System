package model;


// a representation of a course with the name of the course and how much the course costs

import org.json.JSONObject;
import persistence.Writable;

public class Course implements Writable {
    private String courseName;          //the name of the course
    private int courseCost;             //cost of registering in the course

    //REQUIRES: cost > 0
    //EFFECTS: creates a new course with name and status
    public Course(String name, int cost) {
        courseName = name;
        if (cost >= 0) {
            courseCost = cost;
        } else {
            courseCost = 0;
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCost() {
        return courseCost;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseName", courseName);
        json.put("courseCost", courseCost);
        return json;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course c = (Course) obj;
            return courseName.equals(c.getCourseName()) && courseCost == c.getCourseCost();
        }
        return super.equals(obj);

    }

}
