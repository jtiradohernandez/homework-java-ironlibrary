package com.example.ironlibrary.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * Validates if the provided string is not null and not empty.
     */
    public static boolean stringValidator(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Validates if the given string is a valid email address.
     */
    public static boolean emailValidator(String email) {
        if (email == null) return false;
        String emailRegex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates ISBN numbers (ISBN-10 and ISBN-13).
     *
     * ISBN-10 Examples: "0-306-40615-2", "0306406152"
     * ISBN-13 Examples: "978-0-306-40615-7", "9780306406157"
     *
     * Note:
     * - ISBN-10 is a 10-digit number with or without optional hyphens and might end with an 'X' representing 10.
     * - ISBN-13 is a 13-digit number, prefixed by '978' or '979', with or without optional hyphens.
     *
     * @param isbn the ISBN string to validate.
     * @return true if the isbn is valid according to ISBN-10 or ISBN-13 standards, false otherwise.
     */
    public static boolean isbnValidator(String isbn) {
        if (isbn == null) return false;

        // ISBN Regex that matches ISBN-10 and ISBN-13 formats, taking into account optional hyphens
        String isbnRegex = "^(?:ISBN(?:-13)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

        Pattern isbnPattern = Pattern.compile(isbnRegex);
        Matcher matcher = isbnPattern.matcher(isbn);

        // Matches ISBN against the regex pattern
        return matcher.matches();
    }

    /**
     * Updates the quantity of an item, ensuring it cannot go below zero.
     */
public static int addQuantityUpdate(int currentQuantity, int change) {
    if (currentQuantity < 0 || change < 0) {
        throw new IllegalArgumentException("Both currentQuantity and change must be non-negative.");
    }
    return currentQuantity + change;
}

    /**
     * Calculates the difference in days between two dates.
     */
    public static long dateDifferenceCalculator(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            return ChronoUnit.DAYS.between(start, end);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'dd/mm/yyyy'.");
        }
    }

    /**
     * Displays data in a structured format.
     * This is a placeholder for what could be a more complex data presentation utility.
     */
    public static void dataDisplay(Object data) {
        System.out.println(data.toString());
    }

    /**
     * Generates a unique identifier, for example, for use with entities that don't have an auto-generated ID.
     */
    public static String uniqueIdGenerator() {
        return UUID.randomUUID().toString();
    }
}
