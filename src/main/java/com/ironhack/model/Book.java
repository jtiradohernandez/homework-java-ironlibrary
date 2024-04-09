package com.ironhack.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book implements InputValidator {
    @Id
    @Column(name = "isbn")
    private String isbn;

    private String title;

    @Enumerated(EnumType.STRING)
    private Categories category;

    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Author authorBook;

    public Book(String isbn, String title, Categories category, int quantity, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.quantity = quantity;
        this.authorBook = author;
    }


    @Override
    public boolean input_validation() {
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public void updateQuantity(int newQuantity) {
        this.quantity += newQuantity;
    }

    @Override
    public String toString() {
        return String.format("%nISBN: %s%n" +
                "Title: %s%n" +
                "Category: %s%n" +
                "No of Books: %d%n", isbn, title, category, quantity);
    }
}
