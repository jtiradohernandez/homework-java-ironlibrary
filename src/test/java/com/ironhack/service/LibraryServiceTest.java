package com.ironhack.service;

import com.ironhack.model.*;
import com.ironhack.repository.AuthorRepository;
import com.ironhack.repository.BookRepository;
import com.ironhack.repository.IssueRepository;
import com.ironhack.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LibraryServiceTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private StudentRepository studentRepository;
    Author author1;
    Author author2;
    Book book1;
    Book book2;
    Student student1;
    Student student2;
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

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
        bookRepository.deleteAll();
        studentRepository.deleteAll();
        authorRepository.deleteAll();
    }
    @Test
    void isBookIssuedTest(){
        Author author3 = new Author("original author", "newemail@mail.com");
        authorRepository.save(author3);
        Book bookZeroQuantity = new Book("123-5-567890-00-0", "No books lefts", Categories.ADVENTURE, 0, author3);
        bookRepository.save(bookZeroQuantity);
        assertTrue(libraryService.isBookIssued(bookZeroQuantity.getIsbn()));
        assertFalse(libraryService.isBookIssued(book1.getIsbn()));
    }

    @Test
    void issueBookTestValid(){
        Author author4 = new Author("another author", "newermail@mail.com");
        authorRepository.save(author4);
        Book bookToIssue = new Book("123-5-567890-12-3", "Book to issue", Categories.ADVENTURE, 5, author4);
        bookRepository.save(bookToIssue);
        String returnDate = libraryService.issueBook("12345678901", "Book to issue", "123-5-567890-12-3");
        List<Issue> results = libraryService.findIssueByIsbn("978-1-123456-12-7");
        assertNotNull(results);
        assertEquals("Java is awesome", results.getFirst().getIssueBook().getTitle());
        assertEquals("12345678901", results.getFirst().getIssueStudent().getUsn());
        assertNotNull(returnDate);
    }

    @Test
    void issueBookTest_OutOfStock(){
        Author author5 = new Author("nuevo", "email@mail.com");
        authorRepository.save(author5);
        Book outOfStockBook = new Book("978-1-123456-10-5","Libro agotado2", Categories.FANTASY,0,author5);
        bookRepository.save(outOfStockBook);
        String returnDate = libraryService.issueBook("12345678901", "Libro agotado2", "978-1-123456-10-5");
        assertNull(returnDate);
    }
}