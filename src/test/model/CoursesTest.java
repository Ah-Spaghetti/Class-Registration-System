package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CoursesTest {
    private Courses testCourses1;
    private Course testCourse1;
    private Course testCourse2;
    private Course testCourse3;

    @BeforeEach
    void runBefore() {
        testCourses1 = new Courses();
        testCourse1 = new Course("CPSC 210", 120);
        testCourse2 = new Course("CPSC 110", 80);
        testCourse3 = new Course("CPSC 100", 70);
    }

    @Test
    void testConstructor() {
        assertTrue(testCourses1.isEmpty());
    }

    @Test
    void testGetLoc() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        assertEquals(2, testCourses1.getLoc().size());
        assertEquals(testCourse1, testCourses1.getLoc().get(0));
        assertEquals(testCourse2, testCourses1.getLoc().get(1));
    }


    @Test
    void testIsEmpty() {
        assertTrue(testCourses1.isEmpty());
        testCourses1.addCourses(testCourse1);
        assertFalse(testCourses1.isEmpty());
    }

    @Test
    void testClearCourses() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        testCourses1.clearCourses();
        assertEquals(0,testCourses1.getSize());
    }

    @Test
    void testContainsCourses() {
        testCourses1.addCourses(testCourse1);
        assertTrue(testCourses1.containsCourses(testCourse1));
    }

    @Test
    void testContainsCoursesInManyCourses() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        testCourses1.addCourses(testCourse3);
        assertTrue(testCourses1.containsCourses(testCourse2));
    }

    @Test
    void testGetSize() {
        assertEquals(0, testCourses1.getSize());
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        assertEquals(2, testCourses1.getSize());

    }

    @Test
    void testGetIndex() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        assertEquals(testCourse1, testCourses1.getIndex(0));
        assertEquals(testCourse2, testCourses1.getIndex(1));
    }

    @Test
    void testAddCourses() {
        testCourses1.addCourses(testCourse1);
        assertTrue(testCourses1.containsCourses(testCourse1));
        assertEquals(1, testCourses1.getSize());

    }

    @Test
    void testAddCoursesFull() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        testCourses1.addCourses(testCourse3);
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        testCourses1.addCourses(testCourse3);
        assertEquals(3, testCourses1.getSize());
        assertEquals(testCourse1, testCourses1.getIndex(0));
        assertEquals(testCourse2, testCourses1.getIndex(1));
        assertEquals(testCourse3, testCourses1.getIndex(2));

    }


    @Test
    void testAddCoursesFailed() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse1);
        assertEquals(1, testCourses1.getSize());
    }

    @Test
    void testAddCoursesMultipleTimes() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        assertEquals(2, testCourses1.getSize());
        assertEquals(testCourse1, testCourses1.getIndex(0));
        assertEquals(testCourse2, testCourses1.getIndex(1));
    }

    @Test
    void testRemoveCourses() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        testCourses1.removeCourses(testCourse1);
        assertEquals(1, testCourses1.getSize());
        assertEquals(testCourse2, testCourses1.getIndex(0));
    }

    @Test
    void testRemoveCoursesFailed() {
        testCourses1.addCourses(testCourse1);
        testCourses1.removeCourses(testCourse2);
        assertEquals(1, testCourses1.getSize());
        assertEquals(testCourse1, testCourses1.getIndex(0));
    }

    @Test
    void testRemoveCoursesEmptyList() {
        testCourses1.removeCourses(testCourse1);
        assertEquals(0, testCourses1.getSize());
        assertTrue(testCourses1.isEmpty());
    }

    @Test
    void testRemoveCoursesMultipleTimes() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        testCourses1.addCourses(testCourse3);
        testCourses1.removeCourses(testCourse1);
        testCourses1.removeCourses(testCourse2);
        assertEquals(1, testCourses1.getSize());
    }

    @Test
    void testTuitionCost() {
        testCourses1.addCourses(testCourse1);
        testCourses1.addCourses(testCourse2);
        assertEquals(200, testCourses1.tuitionCost());
    }


}
