package com.ironhack.service;

import com.ironhack.model.*;
import com.ironhack.repository.AuthorRepository;
import com.ironhack.repository.BookRepository;
import com.ironhack.repository.IssueRepository;
import com.ironhack.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ironhack.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    StudentRepository studentRepository;

    public void addNewBook(Book book) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        if (optionalBook.isPresent()) {
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
        } else {
            bookRepository.save(book);
        }
    }

    public Optional<Book> searchBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    public List<Book> searchBookByCategory(Categories category) {
        return bookRepository.findBookByCategory(category);
    }

    public List<Book> searchBookByAuthor(int author_id) {
        return bookRepository.findBookByAuthorId(author_id);
    }

    public List<Book> searchAllBooks() {
        return bookRepository.findAll();
    }

    public String issueBook(String usn, String name, String isbn) {
        LocalDateTime issueDate = LocalDateTime.now();
        LocalDateTime returnDate = issueDate.plusDays(7);
        String issueDateString = Utils.formatter.format(issueDate);
        String returnDateString = Utils.formatter.format(returnDate);
        Issue issue = new Issue(issueDateString, returnDateString);
        Optional<Student> student = studentRepository.findByUsn(usn);
        //check if book is already issued
        if (!isBookIssued(isbn)) {
            issue.setIssueStudent(student.get());
            Optional<Book> book = bookRepository.findByIsbn(isbn);
            issue.setIssueBook(book.get());
            issueRepository.save(issue);
            //restar un ejemplar a libro
            book.get().updateQuantity(-1);
            bookRepository.save(book.get());
            return returnDateString;
        } else {
            return null;
        }
    }

    public boolean isBookIssued(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        return book.get().getQuantity() == 0;
    }

    public List<Issue> searchBooksByStudentString(String usn) {
        return studentRepository.searchBooksByStudent(usn);
    }

    public List<Issue> findIssueByIsbn(String isbn) {
        return issueRepository.findByIsbn(isbn);
    }

    public List<Issue> findIssueByUsn(String usn) {
        return issueRepository.findByUsn(usn);
    }

    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Optional<Student> findStudentByUsn(String usn) {
        return studentRepository.findByUsn(usn);
    }

    public List<Book> findAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthor();
    }

    //añadido hoy
    public Optional<Author> findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Book ISBN           Book Title      Category      No of Books     Author name           Author mail ");
            for (Book book : books) {
                System.out.printf("%-20s %-15s %-12s %-15s %-20s %s%n",
                        book.getIsbn(),
                        book.getTitle(),
                        book.getCategory(),
                        book.getQuantity(),
                        book.getAuthorBook().getName(),
                        book.getAuthorBook().getEmail());
            }
        }
    }

    public void printBook(Book book) {
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println("Book ISBN           Book Title      Category      No of Books");
            System.out.printf("%-20s %-15s %-12s %-15s %-20s %s%n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());
        }
    }

    public String getIsbn(Scanner scanner) {
        String isbn;
        do {
            System.out.print("Enter isbn: ");
            isbn = scanner.nextLine();
            if (!Utils.isbnValidator(isbn)) {
                System.out.println("Invalid ISBN. Please try again.");
            }
        } while (!Utils.isbnValidator(isbn));
        return isbn;
    }

    public String getTitle(Scanner scanner) {
        String title;
        do {
            System.out.print("Enter title: ");
            title = scanner.nextLine();
            if (!Utils.stringValidator(title)) {
                System.out.println("Title can not be empty. Please try again.");
            }
        } while (!Utils.stringValidator(title));
        return title;
    }

    public String getAuthorName(Scanner scanner) {
        String name;
        do {
            System.out.print("Enter author name: ");
            name = scanner.nextLine();
            if (!Utils.stringValidator(name)) {
                System.out.println("Author can not be empty. Please try again.");
            }
        } while (!Utils.stringValidator(name));
        return name;
    }

    public String getAuthorEmail(Scanner scanner) {
        String email;
        do {
            System.out.print("Enter author email: ");
            email = scanner.nextLine();
            if (!Utils.emailValidator(email)) {
                System.out.println("Author can not be empty. Please try again.");
            }
        } while (!Utils.emailValidator(email));
        return email;
    }

    public int getQuantity(Scanner scanner) {
        int quantity;
        do {
            System.out.print("Enter number of books: ");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                if (quantity >= 0) {
                    break;
                } else {
                    System.out.println("Number of books can't be negative. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Consume invalid input
            }
        } while (true);
        return quantity;
    }

    public Categories getCategory(Scanner scanner) {
        Categories category = null;
        while (true) {
            System.out.print("Enter category (Available categories: HORROR, SCIENCE, ROMANCE, FICTION, FANTASY, ADVENTURE, BIOGRAPHY, MISTERY, OTHERS): ");
            String categoryInput = scanner.nextLine().toUpperCase();
            for (Categories c : Categories.values()) {
                if (c.name().equals(categoryInput)) {
                    category = c;
                    break;
                }
            }
            if (category != null) {
                break;
            } else {
                System.out.println("Invalid category. Please enter a valid category.");
            }
        }
        return category;
    }

    public String getUsn(Scanner scanner) {
        String usn;
        do {
            System.out.print("Enter USN: ");
            usn = scanner.nextLine();
            if (!Utils.usnValidator(usn)) {
                System.out.println("Invalid USN. Please try again.");
            }
        } while (!Utils.usnValidator(usn));
        return usn;
    }
}
