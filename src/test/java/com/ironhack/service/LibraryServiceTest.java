package com.ironhack.service;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {

    Author author = new Author("Xavi Moreno", "xavi.moreno@example.com");
    book = new Book("978-3-16-148410-0", "The Book", Categories.ADVENTURE, 10, author);
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void addNewBook() {
        libraryService.addNewBook(book);
        Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        assertTrue(optionalBook.isPresent());
        assertEquals(book.getIsbn(), optionalBook.get().getIsbn());
    }
}