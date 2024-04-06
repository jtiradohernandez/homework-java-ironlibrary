package com.ironhack.repository;

import com.ironhack.model.Author;
import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    Author author;
    Book sample_book;

    @BeforeEach
    void setUp() {
        author = new Author("Xavi","xavi@mail.com");
        sample_book = new Book("1234","my awesome book", Categories.ADVENTURE,3,author);
        authorRepository.save(author);
        bookRepository.save(sample_book);


    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
//    @Test
//    public void bookCreationTest(){
//        Optional<Book> db_book=bookRepository.findBookByTitle("my awesome book");
//        assertTrue(db_book.isPresent());
//        assertEquals(sample_book,db_book.get());
//    }
@Test
void findAllBooksWithAuthor() {
    Author author1 = new Author("JK Rowling", "jkrowling@mail.com");
    Author author2 = new Author("George Orwell", "georgeorwell@mail.com");
    authorRepository.save(author1);
    authorRepository.save(author2);
    Book book1 = new Book("978-3-16-148410-0", "Harry Potter", Categories.FANTASY, 1, author1);
    Book book2 = new Book("978-3-16-148434-0", "1984", Categories.FICTION, 2, author2);
    bookRepository.save(book1);
    bookRepository.save(book2);
    assertEquals(author1, book1.getAuthorBook());

}

}
