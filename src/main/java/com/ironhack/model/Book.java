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
    private Categories category;

    private int quantity;

    @OneToOne(cascade = CascadeType.PERSIST)
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
        if (!(o instanceof Book book)) return false;
        return quantity == book.quantity && Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) && category == book.category &&
                authorBook.getAuthorId() == book.authorBook.getAuthorId() && Objects.equals(authorBook.getName(), book.authorBook.getName()) &&
                book.authorBook.getEmail().equals(authorBook.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, category, quantity, authorBook);
    }

    public void updateQuantity(int newQuantity){
        this.quantity+=newQuantity;
    }
}
