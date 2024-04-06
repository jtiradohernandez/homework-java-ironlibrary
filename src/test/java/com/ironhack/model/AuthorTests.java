package com.ironhack.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorTests {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAuthorCreation() {

        Author author = new Author("Xavi", "Xavi@mail.com");

        assertNotNull(author);

        assertEquals("Xavi", author.getName());

        assertEquals("Xavi@mail.com", author.getEmail());
    }
}
