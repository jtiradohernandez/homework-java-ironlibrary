package com.ironhack;

import com.ironhack.service.LibraryService;
import com.ironhack.viewer.LibraryMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IronLibrary {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(IronLibrary.class, args);
		LibraryService libraryService = context.getBean(LibraryService.class);
		LibraryMenu libraryMenu = new LibraryMenu(libraryService);
		libraryMenu.displayMenu();
		context.close();
		//SpringApplication.run(IronLibrary.class, args);
	}

}
