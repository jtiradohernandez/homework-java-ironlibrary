package com.ironhack.service;

import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import com.ironhack.repository.AuthorRepository;
import com.ironhack.repository.BookRepository;
import com.ironhack.repository.IssueRepository;
import com.ironhack.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

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

    private void issueBook(String isbn , String usn){}

    private void returnBook(String isbn , String usn){}

    private List<Book> searchBooksByStudentString( String usn){
        return new ArrayList<>();
    }
}
