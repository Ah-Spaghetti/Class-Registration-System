package persistence;

import model.Course;
import model.Courses;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Courses courses = new Courses();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:filename.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCourses() {
        try {
            Courses courses = new Courses();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourses.json");
            writer.open();
            writer.write(courses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourses.json");
            courses = reader.read();
            assertEquals(0, courses.tuitionCost());
            assertTrue(courses.isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCourses() {
        try {
            Courses courses = new Courses();
            courses.addCourses(new Course("CPSC 210", 120));
            courses.addCourses(new Course("CPSC 221", 100));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourses.json");
            writer.open();
            writer.write(courses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourses.json");
            courses = reader.read();
            assertEquals(220, courses.tuitionCost());
            List<Course> c = courses.getLoc();
            assertEquals(2, courses.getSize());
            checkCourse(courses.getIndex(0), "CPSC 210", 120);
            checkCourse(courses.getIndex(1), "CPSC 221", 100);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
