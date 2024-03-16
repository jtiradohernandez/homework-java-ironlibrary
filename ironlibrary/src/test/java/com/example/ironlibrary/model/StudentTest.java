package com.example.ironlibrary.model;

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

        Student student = new Student("abcd", "Juan");

        assertNotNull(student);

        assertEquals("abcd", student.getUsn());

        assertEquals("Juan", student.getName());
    }
}