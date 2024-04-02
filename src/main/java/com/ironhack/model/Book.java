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
public class Book implements InputValidator{
    @Id
    @Column(name="isbn")
    private String isbn;

    private String title;

    @Enumerated(EnumType.STRING)
    private Categories Category;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author authorBook;

    public Book(String isbn, String title, Categories category, int quantity, Author author) {
        this.isbn = isbn;
        this.title = title;
        Category = category;
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
        return authorBook.equals(book.authorBook) && quantity == book.quantity && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Category == book.Category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, Category, quantity, authorBook);
    }

    public void updateQuantity(int newQuantity){
        this.quantity+=newQuantity;
    }
}
