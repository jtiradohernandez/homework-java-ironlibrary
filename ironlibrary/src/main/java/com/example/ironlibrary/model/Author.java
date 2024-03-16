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
@Table(name = "author")
public class Author implements InputValidator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name="book_id")
    private Book authorBook;

    public Author(String name, String email, Book authorBook) {
        setName(name);
        setEmail(email);
        setAuthorBook(authorBook);
    }


    @Override
    public boolean input_validation() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && authorBook.equals(author.authorBook) && Objects.equals(name, author.name) && email == author.email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, email, authorBook);
    }
}
