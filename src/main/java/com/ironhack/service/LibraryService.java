package com.ironhack.service;

import com.ironhack.model.Book;
import com.ironhack.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private void addBook(Book book){

    }

    private Book searchBookByTitle(String title){
        return new Book();
    }

    private List<Book> searchBookByCategory(Categories category){
        return new ArrayList<>();
    }

    private Book searchBookByAuthor(String author){
        return new Book();
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
