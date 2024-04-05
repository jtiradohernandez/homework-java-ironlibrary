package com.ironhack;

import com.ironhack.service.LibraryService;
import com.ironhack.viewer.LibraryMenu;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IronLibrary {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IronLibrary.class, args);
//		LibraryService libraryService = context.getBean(LibraryService.class);
//		LibraryMenu libraryMenu = new LibraryMenu(libraryService);
        LibraryMenu libraryMenu = context.getBean(LibraryMenu.class);
        libraryMenu.displayMenu();
        context.close();
//		SpringApplication.run(IronLibrary.class, args);
    }


}
