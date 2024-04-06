package com.ironhack.service;

import com.ironhack.model.*;
import com.ironhack.repository.AuthorRepository;
import com.ironhack.repository.BookRepository;
import com.ironhack.repository.StudentRepository;
import com.ironhack.repository.IssueRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IssueRepository issueRepository;

    private Author author1;
    private Author author2;
    private Book book1;
    private Book book2;
    private Student student1;
    private Student student2;
    private Issue issue1;
    private Issue issue2;

    @BeforeEach
    void setUp() {
        author1 = new Author("Corey Schafer","corey@mail.com");
        author2 = new Author("Antonio Gámez","antonio@mail.com");
        book1 = new Book("978-1-123456-12-7","Java is awesome", Categories.HORROR,8,author1);
        book2 = new Book("978-1-123456-12-6","SLA Driven Governance of RESTful systems", Categories.SCIENCE,5,author2);
        authorRepository.saveAll(List.of(author1, author2));
        bookRepository.saveAll(List.of(book1, book2));
        student1 = new Student("12345678901", "Mosh");
        student2 = new Student("12345678902", "John");
        studentRepository.saveAll(List.of(student1, student2));
        issue1 = new Issue(LocalDate.now().toString(), LocalDate.now().plusMonths(1).toString());
        issue1.setIssueBook(book1);
        issue1.setIssueStudent(student1);

        issue2 = new Issue(LocalDate.now().toString(), LocalDate.now().plusMonths(1).toString());
        issue2.setIssueBook(book2);
        issue2.setIssueStudent(student2);

        issueRepository.saveAll(List.of(issue1, issue2));
    }

    @Test
    void addNewBook() {
        libraryService.addNewBook(book1);
        Optional<Book> optionalBook = bookRepository.findByIsbn(book1.getIsbn());
        assertTrue(optionalBook.isPresent());
        assertEquals(book1.getIsbn(), optionalBook.get().getIsbn());
    }

    @Test
    void searchBookByTitle() {
        Optional<Book> optionalBook = libraryService.searchBookByTitle("Java is awesome");

        assertTrue(optionalBook.isPresent());
        assertEquals("Java is awesome", optionalBook.get().getTitle());
    }

    @Test
    void searchBookByCategory() {
        // Use the searchBookByCategory method to retrieve the books
        List<Book> books = libraryService.searchBookByCategory(Categories.HORROR);

        // Assert that the books returned by the method are the same as the books you saved
        assertEquals(1, books.size());
        assertTrue(books.contains(book1));
    }

    @Test
    void searchBookByAuthor() {
        // Use the searchBookByAuthor method to retrieve the books
        List<Book> booksByAuthor1 = libraryService.searchBookByAuthor(author1.getAuthorId());
        List<Book> booksByAuthor2 = libraryService.searchBookByAuthor(author2.getAuthorId());

        // Assert that the books returned by the method are the same as the books you saved
        assertEquals(1, booksByAuthor1.size());
        assertTrue(booksByAuthor1.contains(book1));

        assertEquals(1, booksByAuthor2.size());
        assertTrue(booksByAuthor2.contains(book2));
    }

    @Test
    void searchBooksByStudentStringTest() {
        List<Issue> foundIssues = libraryService.searchBooksByStudentString(student1.getUsn());

        // Verify the correct book issues are retrieved
        assertNotNull(foundIssues, "The returned list of issues should not be null");
        assertEquals(1, foundIssues.size(), "Expected one issue to be found for student1");

        // Use getters in Issue for issueBook
        assertEquals(book1.getIsbn(), foundIssues.get(0).getIssueBook().getIsbn(),
                "The ISBN of the book in the found issue should match book1's ISBN");
    }

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
        bookRepository.deleteAll();
        studentRepository.deleteAll();
        authorRepository.deleteAll();
    }
}
