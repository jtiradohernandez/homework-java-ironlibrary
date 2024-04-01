package com.ironhack.viewer;

import com.ironhack.model.Book;
import com.ironhack.model.Categories;
import com.ironhack.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryMenu {
    @Autowired
    private LibraryService libraryService;

    private Optional<Book> one_book=null;
    private List<Book> books;

    public LibraryMenu(LibraryService libraryService) {
        this.libraryService = libraryService;
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
            System.out.println("5. Search All Books");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Search Books By Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Book
                    break;
                case 2:
                    System.out.print("Enter title to search: ");
                    String title = scanner.nextLine();
                    one_book=libraryService.searchBookByTitle(title);
                    if(one_book.isPresent()) {
                        System.out.println(one_book.get().getIsbn());
                        System.out.println(one_book.get().getTitle());
                    }
                    break;
                case 3:
                    System.out.print("Enter a Category to search: ");
                    String category = scanner.nextLine();
                    try{
                        books = libraryService.searchBookByCategory(Categories.valueOf(category));
                        for(Book book: books){
                            System.out.println(book.getIsbn());
                            System.out.println(book.getTitle());
                        }
                    }
//                    catch (InputMismatchException imm){
//                        System.out.print("Wrong Category ");
//                    }
                    catch (IllegalArgumentException iae){
                        System.out.println("Wrong Category");
                    }

                    break;
                case 4:
                    System.out.print("Enter an Author ID:");
                    String author_id = scanner.nextLine();
                    try{
                        //TODO change to isbn validation
                        int authorid = Integer.parseInt(author_id);
                        books = libraryService.searchBookByAuthor(authorid);
                        for(Book book: books){
                            System.out.println(book.getIsbn());
                            System.out.println(book.getTitle());
                        }
                    }catch (IllegalArgumentException iae){
                        System.out.println("Author ID should be numeric");
                    }

                    break;
                case 5:
                    // Search All Books
                    books = libraryService.searchAllBooks();
                    for(Book book: books){
                        System.out.println(book.getIsbn());
                        System.out.println(book.getTitle());
                    }
                    break;
                case 6:
                    // Issue Book
                    break;
                case 7:
                    // Return Book
                    break;
                case 8:
                    // Search Books By Student
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

    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        LibraryMenu libraryMenu = new LibraryMenu(libraryService);
        libraryMenu.displayMenu();
    }
}