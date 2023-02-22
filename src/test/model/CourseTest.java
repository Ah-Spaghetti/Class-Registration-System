package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course testCourse1;
    private Course testCourse2;
    private Course testCourse3;
    private Course testCourse4;
    private Course testCourse5;

    @BeforeEach
    void runBefore() {
        testCourse1 = new Course("CPSC 210", 120);
        testCourse2 = new Course("CPSC 210", 120);
        testCourse3 = new Course("CPSC 110", 70);
        testCourse4 = new Course("CPSC 210", 100);
        testCourse5 = new Course("CPSC 110", 120);
    }


    @Test
    void testConstructor() {
        assertEquals("CPSC 210", testCourse1.getCourseName());
        assertEquals(120, testCourse1.getCourseCost());
    }

    @Test
    void testConstructorNegCost() {
        testCourse1 = new Course("MATH 100", -20);
        assertEquals("MATH 100", testCourse1.getCourseName());
        assertEquals(0, testCourse1.getCourseCost());
    }

    @Test
    void testEqualsFalse() {
        assertFalse(testCourse1.equals(""));
    }

    @Test
    void testEqualsTrue() {
        assertTrue(testCourse1.equals(testCourse2));
        assertFalse(testCourse1.equals(testCourse3));
        assertFalse(testCourse1.equals(testCourse4));
        assertFalse(testCourse1.equals(testCourse5));


    }

}
