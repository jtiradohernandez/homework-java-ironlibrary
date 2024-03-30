package com.ironhack.repository;


import com.ironhack.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("avcbdhdhd", "Juan");
        student = studentRepository.save(student);
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
        studentRepository.flush();
    }

    @Test
    void searchBooksByStudentString(){
        Author author1 = new Author("JK Rowling", "jkrowling@mail.com");
        Author author2 = new Author("George Orwell", "georgeorwell@mail.com");
        authorRepository.save(author1);
        authorRepository.save(author2);
        Book book1 = new Book("978-3-16-148410-0", "Harry Potter", Categories.FANTASY, 1, author1);
        Book book2 = new Book("978-3-16-148434-0", "1984", Categories.FICTION, 2, author2);
        bookRepository.save(book1);
        bookRepository.save(book2);
        Issue issue1 = new Issue("2022-08-01 17:09:38", "2022-08-07 17:09:38");
        Issue issue2 = new Issue("2022-10-01 17:09:38", "2022-10-07 17:09:38");

        issue1.setIssueBook(book1);
        issue1.setIssueStudent(student);
        student.addIssue(issue1);
        issueRepository.save(issue1);
        issue2.setIssueBook(book2);
        issue2.setIssueStudent(student);

        student.addIssue(issue2);
        issueRepository.save(issue2);
        List<Issue> results = studentRepository.searchBooksByStudentString("12345678901");

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("Harry Potter", results.get(0).getIssueBook().getTitle());
        assertEquals("2022-08-07 17:09:38", results.get(0).getReturnDate());

    }

//    @Test
//    void findByName() {
//        Optional<Student> student = studentRepository.findByName("Juan");
//        assertTrue(student.isPresent());
//        assertEquals("Juan", student.get().getName());
//    }
}