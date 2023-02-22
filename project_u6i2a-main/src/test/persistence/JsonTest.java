package persistence;


import model.Course;
import model.Courses;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkCourse(Course course, String courseName, int courseCost) {
        assertEquals(courseName, course.getCourseName());
        assertEquals(courseCost, course.getCourseCost());
    }
}
