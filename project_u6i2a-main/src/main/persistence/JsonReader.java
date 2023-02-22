package persistence;

import model.Course;
import model.Courses;
import model.eventlogging.Event;
import model.eventlogging.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//represents a reader that reads Courses from JSON data stored
public class JsonReader {
    private String source;

    //EFFECTS:constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads courses from file and returns it
    //         throws IOException if an error occurs reading data from file
    public Courses read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourses(jsonObject);

    }


    //EFFECTS: read source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        EventLog.getInstance().logEvent(new Event("Loaded file."));

        return contentBuilder.toString();

    }

    //EFFECTS: parse courses from JSON object and returns it
    private Courses parseCourses(JSONObject jsonObject) {
        Courses courses = new Courses();
        addingCourses(courses, jsonObject);
        return courses;
    }

    //MODIFIES: Courses
    //EFFECTS: parses course from JSON object and add them to courses
    private void addingCourses(Courses courses, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(courses, nextCourse);
        }
    }

    //MODIFIES: Courses
    //EFFECTS: parses course from JSON object and adds it to courses
    private void addCourse(Courses courses, JSONObject jsonObject) {
        String name = jsonObject.getString("courseName");
        int cost = jsonObject.getInt("courseCost");
        Course course = new Course(name, cost);
        courses.addCourses(course);
    }

}
