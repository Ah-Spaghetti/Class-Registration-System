package persistence;

import model.Course;
import model.Courses;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Courses courses = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCourses() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourses.json");
        try {
            Courses courses = reader.read();
            assertTrue(courses.isEmpty());
            assertEquals(0, courses.tuitionCost());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCourses() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourses.json");
        try {
            Courses courses = reader.read();
            assertEquals(270, courses.tuitionCost());
            List<Course> c = courses.getLoc();
            assertEquals(3, c.size());
            checkCourse(c.get(0), "CPSC 100", 70);
            checkCourse(c.get(1), "CPSC 110", 80);
            checkCourse(c.get(2), "CPSC 210", 120);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
