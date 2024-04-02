package com.ironhack.service;

import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import com.ironhack.repository.AuthorRepository;
import com.ironhack.repository.BookRepository;
import com.ironhack.repository.IssueRepository;
import com.ironhack.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    StudentRepository studentRepository;
    public void addBook(Book book){

    }

    public Optional<Book> searchBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }

    public List<Book> searchBookByCategory(Categories category){
        return bookRepository.findBookByCategory(category);
    }

    public List<Book> searchBookByAuthor(int author_id){
        return bookRepository.findBookByAuthorId(author_id);
    }
    public List<Book> searchAllBooks(){
        return bookRepository.findAll();
    }

    public void issueBook(String isbn , String usn){}

    public void returnBook(String isbn , String usn){}

    public List<Book> searchBooksByStudentString( String usn){
        return new ArrayList<>();
    }
}
