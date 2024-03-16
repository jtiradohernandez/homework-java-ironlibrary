package com.example.ironlibrary.service;

import com.example.ironlibrary.model.Author;
import com.example.ironlibrary.model.Book;
import com.example.ironlibrary.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class LibraryServices {
    public void addBook(Book new_book){

    }

    public Book searchBookByTitle(String Title){
        Book new_book= new Book();
        return new_book;

    }

    public Book searchBookCategory(Categories Category){
        Book new_book= new Book();
        return new_book;
    }

    public Book searchBookByAuthor(Author Author){
        Book new_book= new Book();
        return new_book;

    }
    public List<Book> allBooksAndAuthor(){
        List<Book> book_list= new ArrayList<Book>();

        return book_list;
    }

    public boolean issueBookStudent(Student student, Book Book){
        return false;
    }

    public List<Book> ListBooksByUsn(String usn){
        List<Book> book_list= new ArrayList<Book>();

        return book_list;

    }
}
