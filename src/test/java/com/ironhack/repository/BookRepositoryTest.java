package com.ironhack.repository;

import com.ironhack.model.Author;
import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import com.ironhack.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    Author author;
    Book sample_book;

    @BeforeEach
    void setUp() {
        author = new Author("Xavi","xavi@mail.com");
        sample_book = new Book("1234","my awesome book", Categories.ADVENTURE,3,author);
        authorRepository.save(author);
        bookRepository.save(sample_book);


    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
//    @Test
//    public void bookCreationTest(){
//        Optional<Book> db_book=bookRepository.findBookByTitle("my awesome book");
//        assertTrue(db_book.isPresent());
//        assertEquals(sample_book,db_book.get());
//    }
}
