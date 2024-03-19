package com.ironhack.repository;

import com.ironhack.model.Author;
import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    Author author;
    Book book;

    @BeforeEach
    void setUp() {
        author = new Author("Xavi","xavi@mail.com");
        authorRepository.save(author);
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }


    @Test
    public void createAuthor(){
        Optional<Author> authorFound = authorRepository.findById(author.getAuthorId());
        assertTrue(authorFound.isPresent());
        assertEquals(author.getAuthorId(),authorFound.get().getAuthorId());
    }
}