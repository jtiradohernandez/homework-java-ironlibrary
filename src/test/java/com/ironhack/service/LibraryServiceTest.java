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

import java.util.List;
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
    book = new Book("978-3-16-148410-0", "Test Book", Categories.ADVENTURE, 10, author);
    bookRepository.save(book);
    }

    @Test
    void addNewBook() {
        libraryService.addNewBook(book);
        Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        assertTrue(optionalBook.isPresent());
        assertEquals(book.getIsbn(), optionalBook.get().getIsbn());
    }

    @Test
    void searchBookByTitle() {
        Optional<Book> optionalBook = libraryService.searchBookByTitle("Test Book");

        assertTrue(optionalBook.isPresent());
        assertEquals("Test Book", optionalBook.get().getTitle());
    }

    @Test
    void searchBookByCategory() {
        // Save a few books to the repository
        Author author = new Author("Test Author", "test.author@example.com");
        Book book1 = new Book("978-3-16-148410-0", "Test Book 1", Categories.ADVENTURE, 10, author);
        Book book2 = new Book("978-3-16-148410-1", "Test Book 2", Categories.ADVENTURE, 10, author);
        Book book3 = new Book("978-3-16-148410-2", "Test Book 3", Categories.ROMANCE, 10, author);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // Use the searchBookByCategory method to retrieve the books
        List<Book> books = libraryService.searchBookByCategory(Categories.ADVENTURE);

        // Assert that the books returned by the method are the same as the books you saved
        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
}