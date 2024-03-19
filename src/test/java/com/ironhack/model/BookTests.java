package com.ironhack.model;

import com.ironhack.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookTests {
    @AfterEach
    public void tearDown(){
        bookRepository.deleteAll();
    }
    @Autowired
    BookRepository bookRepository;
//    @Test
//    public void bookCreationTest(){
//        Book sample_book = new Book("1234","my awesome book", Categories.ADVENTURE,3);
//        bookRepository.save(sample_book);
//        Optional<Book> db_book=bookRepository.findBookByTitle("my awesome book");
//        assertTrue(db_book.isPresent());
//        assertEquals(sample_book,db_book.get());
//    }
}
