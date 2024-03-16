package com.example.ironlibrary.repository;

import com.example.ironlibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByTitle(String title);
}
