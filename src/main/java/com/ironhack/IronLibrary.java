package com.ironhack;

import com.ironhack.viewer.LibraryMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class IronLibrary implements CommandLineRunner {
    @Autowired
    private LibraryMenu libraryMenu;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(IronLibrary.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!Arrays.asList(environment.getActiveProfiles()).contains("test")){
            libraryMenu.displayMenu();
        }
    }
}
