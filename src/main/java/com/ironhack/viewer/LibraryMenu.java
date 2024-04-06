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
                    //llamar a getIsbn() do While
                    System.out.print("Enter isbn: ");
                    String isbnBook = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String titleBook = scanner.nextLine();
                    System.out.print("Enter category (Available categories: HORROR, SCIENCE, ROMANCE, FICTION, FANTASY, ADVENTURE, BIOGRAPHY, MISTERY, OTHERS");
                    String categoryInput = scanner.nextLine().toUpperCase();
                    Categories categoryBook;
                    try {
                        categoryBook = Categories.valueOf(categoryInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category. Please enter a valid category.");
                        break;
                    }
                    System.out.print("Enter Author name: ");
                    String authorName = scanner.nextLine();
                    System.out.print("Enter Author mail: ");
                    String authorMail = scanner.nextLine();
                    System.out.print("Enter number of books: ");
                    int quantity = scanner.nextInt();
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
                        System.out.println("error");
                    }
                    System.out.println("Book was successfully added to library");
                    break;
                case 2:
                    System.out.print("Enter title to search: ");
                    String title = scanner.nextLine();
                    Optional<Book> requestedBook = libraryService.searchBookByTitle(title);
                    if (requestedBook.isPresent()) {
                        System.out.println(requestedBook.get());
                    } else {
                        System.out.println("Book not found");
                    }
                    break;
                case 3:
                    System.out.print("Enter a Category to search: ");
                    String category = scanner.nextLine();
                    try {
                        List<Book> books = libraryService.searchBookByCategory(Categories.valueOf(category));
                        for (Book book : books) {
                            System.out.println(book.getIsbn());
                            System.out.println(book.getTitle());
                        }
                    } catch (InputMismatchException imm) {
                        System.out.print("Wrong Category ");
                    }
//                    catch (IllegalArgumentException iae) {
//                        System.out.println("Wrong Category");
//                    }

                    break;
                case 4:
                    System.out.print("Enter an Author ID:");
                    String author_id = scanner.nextLine();
                    try {
                        //TODO change to isbn validation
                        int authorid = Integer.parseInt(author_id);
                        List<Book> books = libraryService.searchBookByAuthor(authorid);
                        for (Book book : books) {
                            System.out.println(book.getIsbn());
                            System.out.println(book.getTitle());
                        }
                    } catch (IllegalArgumentException iae) {
                        System.out.println("Author ID should be numeric");
                    }
                    break;
                case 5:
                    libraryService.printBooks(libraryService.searchAllBooks());
                    break;
                case 6:
                    System.out.print("Enter usn:");
                    String usn = scanner.nextLine();
                    System.out.print("Enter name:");
                    String name = scanner.nextLine();
                    System.out.print("Enter book ISBN:");
                    String isbn = scanner.nextLine();
                    try {
                        //check if student and book exist. Comprobar nombre?????
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
                    System.out.println("Enter usn:");
                    String usnSearch = scanner.nextLine();
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