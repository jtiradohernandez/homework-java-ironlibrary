package com.ironhack.repository;


import com.ironhack.model.Author;
import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByTitle(String title);
    List<Book> findBookByCategory(Categories category);

    Optional<Book> findByIsbn(String isbn);

}
