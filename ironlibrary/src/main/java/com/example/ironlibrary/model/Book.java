package com.example.ironlibrary.model;

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

    public Book(String isbn, String title, Categories category, int quantity) {
        this.isbn = isbn;
        this.title = title;
        Category = category;
        this.quantity = quantity;
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
        return quantity == book.quantity && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Category == book.Category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, Category, quantity);
    }
}
