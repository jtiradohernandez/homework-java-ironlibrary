package com.ironhack.service;

import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import com.ironhack.model.Issue;
import com.ironhack.model.Student;
import com.ironhack.repository.AuthorRepository;
import com.ironhack.repository.BookRepository;
import com.ironhack.repository.IssueRepository;
import com.ironhack.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    StudentRepository studentRepository;
    private void addBook(Book book){

    }

    private Optional<Book> searchBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }

    private List<Book> searchBookByCategory(Categories category){
        return bookRepository.findBookByCategory(category);
    }

    private List<Book> searchBookByAuthor(String author){
        return new ArrayList<>();
    }
    private List<Book> searchAllBooks(){
        return new ArrayList<>();
    }

    private void issueBook(String usn, String name, String isbn){
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String todayString = FORMATTER.format( today );

        LocalDateTime returnDate = today.plusDays(7);
        String returnDateString = FORMATTER.format( returnDate );

        Issue issue = new Issue(todayString, returnDateString);

        Optional<Student> student = studentRepository.findByUsn(usn);
        if(student.isEmpty()){
            throw new IllegalArgumentException("Student doesn't exist");
        }
        //check if book is already issued
        if(!isBookIssued(isbn)) {
            issue.setIssueStudent(student.get());

            Optional<Book> book = bookRepository.findByIsbn(isbn);
            if (book.isPresent()) {
                issue.setIssueBook(book.get());
            } else {
                throw new IllegalArgumentException("Book doesn't exist in library");
            }

            issueRepository.save(issue);

        } else{
            System.out.println("Book is already issued");
        }

    }

    public boolean isBookIssued(String isbn){
        Optional<Issue> issue = issueRepository.findIssueByIsbn(isbn);
        return issue.isPresent() && issue.get().getIssueBook().getIsbn().equals(isbn);
    }

    private void returnBook(String isbn , String usn){}

    private List<Issue> searchBooksByStudentString(String usn){
        return studentRepository.searchBooksByStudentString(usn);
    }
}
