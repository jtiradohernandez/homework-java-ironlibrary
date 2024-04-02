package com.ironhack.model;

import com.ironhack.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTests {


    @Test
    public void testBookCreation(){
        Author sample_author = new Author("Jose Martinez","josep@gmail.com");
        Book book = new Book("123456789","Best Book Ever" ,Categories.ADVENTURE, 2, sample_author);
        assertEquals(book.getAuthorBook(),sample_author);
        assertEquals(book.getCategory(),Categories.ADVENTURE);
        assertEquals(book.getIsbn(),"123456789");
        assertEquals(book.getTitle(),"Best Book Ever");
        assertEquals(book.getQuantity(),2);

    }

}
