package com.ironhack.model;
import com.ironhack.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testStudentCreation() {

        Student student = new Student("12345678901", "Juan");

        assertNotNull(student);

        assertEquals("12345678901", student.getUsn());

        assertEquals("Juan", student.getName());
    }
    @Test
    void testAddIssue() {
        Student student = new Student("12345678901", "John Doe");
        Issue issue = new Issue("2022-01-01", "2022-02-01");
        student.addIssue(issue);
        assertTrue(student.getIssues().contains(issue));
        assertEquals(student, issue.getIssueStudent());
    }
}