package com.ironhack.viewer;

import com.ironhack.model.*;
import com.ironhack.repository.AuthorRepository;
import com.ironhack.service.LibraryService;
import org.springframework.stereotype.Component;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class LibraryMenu {
    private final LibraryService libraryService;
    private final AuthorRepository authorRepository;
    public LibraryMenu(LibraryService libraryService, AuthorRepository authorRepository) {
        this.libraryService = libraryService;
        this.authorRepository = authorRepository;
    }
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book By Title");
            System.out.println("3. Search Book By Category");
            System.out.println("4. Search Book By Author");
            System.out.println("5. Show All Books");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Search Books By Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException ime) {
                choice = 9;
                scanner.nextLine();
            }
            switch (choice) {
                case 1:
                    // Add Book
                    String isbnBook = libraryService.getIsbn(scanner);
                    String titleBook = libraryService.getString(scanner, "Enter title: ", "Title can not be empty. Please try again.");
                    Categories categoryBook = libraryService.getCategory(scanner);
                    String authorName = libraryService.getString(scanner, "Enter author name: ", "Author name can not be empty. Please try again.");
                    String authorMail = libraryService.getAuthorEmail(scanner);
                    int quantity = libraryService.getNumber(scanner, "Enter number of books: ", "Invalid input. Please enter a positive quantity.");
                    Author author;
                    Optional<Author> optionalAuthor = libraryService.findAuthorByName(authorName);
                    if (optionalAuthor.isPresent()) {
                        author = optionalAuthor.get();
                    } else {
                        author = new Author(authorName, authorMail);
                        authorRepository.save(author);
                    }
                    Book newBook = new Book(isbnBook, titleBook, categoryBook, quantity, author);
                    try {
                        libraryService.addNewBook(newBook);
                    } catch (IllegalArgumentException iae) {
                        System.out.println("Something went wrong" + iae.getMessage());
                    }
                    System.out.println("Book was successfully added to library");
                    break;
                case 2:
                    String title = libraryService.getString(scanner, "Enter title: ", "Title can not be empty. Please try again.");
                    Optional<Book> requestedBook = libraryService.searchBookByTitle(title);
                    if (requestedBook.isPresent()) {
                        System.out.println(requestedBook.get());
                    } else {
                        System.out.println("Book not found");
                    }
                    break;
                case 3:
                    String category = libraryService.getCategory(scanner).name();
                    try {
                        List<Book> books = libraryService.searchBookByCategory(Categories.valueOf(category));
                        libraryService.printBooksByCategoryOrAuthor(books);
                    } catch (InputMismatchException imm) {
                        System.out.print("Wrong Category ");
                    }
//                    catch (IllegalArgumentException iae) {
//                        System.out.println("Wrong Category");
//                    }

                    break;
                case 4:
                    int author_id = libraryService.getNumber(scanner, "Enter Author ID: ", "Invalid input. Please enter a positive numeric value.");
                    List<Book> books = libraryService.searchBookByAuthor(author_id);
                    libraryService.printBooksByCategoryOrAuthor(books);
                    break;
                case 5:
                    libraryService.printBooks(libraryService.searchAllBooks());
                    break;
                case 6:
                    String usn = libraryService.getUsn(scanner);
                    String name = libraryService.getString(scanner, "Enter student name: ", "Student name can not be empty. Please try again.");
                    String isbn = libraryService.getIsbn(scanner);
                    try {
                        //check if student and book exist.
                        Optional<Student> studentOptional = libraryService.findStudentByUsn(usn);
                        Optional<Book> bookOptional = libraryService.findBookByIsbn(isbn);
                        if (studentOptional.isPresent() && bookOptional.isPresent()) {
                            Book book = bookOptional.get();
                            if (book.getQuantity() > 0) {
                                String returnDate = libraryService.issueBook(usn, name, isbn);
                                System.out.println("\n");
                                System.out.println("Book issued. Return date : " + returnDate);
                            } else {
                                System.out.println("There aren't any copies left.");
                            }
                        } else {
                            System.out.println("Student or book does not exist.");
                        }
                    } catch (IllegalArgumentException iae) {
                        System.out.println("An exception occurred: " + iae.getMessage());
                    }
                    break;
                case 7:
                    // Return Book
                    break;
                case 8:
                    // Search Books By Student
                    String usnSearch = libraryService.getUsn(scanner);
                    try {
                        Optional<Student> studentOptional = libraryService.findStudentByUsn(usnSearch);
                        if (studentOptional.isPresent()) {
                            List<Issue> issueList = libraryService.searchBooksByStudentString(usnSearch);
                            if (issueList.isEmpty()) {
                                System.out.println("No books found for the specified student.");
                                break;
                            }
                            System.out.println("Book Title          Student Name    Return date");
                            for (Issue issue : issueList) {
                                String bookTitle = issue.getIssueBook().getTitle();
                                String studentName = issue.getIssueStudent().getName();
                                String returnDate = issue.getReturnDate();
                                System.out.printf("%-20s %-15s %s%n", bookTitle, studentName, returnDate);
                            }
                        } else {
                            System.out.println("Student does not exist");
                        }
                    } catch (IllegalArgumentException iae) {
                        System.out.println("An exception occurred: " + iae.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}