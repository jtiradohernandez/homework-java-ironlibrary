package com.ironhack.repository;

import com.ironhack.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    Author author;

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    public void findAuthorByIdTest() {
        author = new Author("Xavi", "xavi@mail.com");
        authorRepository.save(author);

        Optional<Author> authorFound = authorRepository.findById(author.getAuthorId());

        assertTrue(authorFound.isPresent());
        assertEquals(author.getAuthorId(), authorFound.get().getAuthorId());
    }
}