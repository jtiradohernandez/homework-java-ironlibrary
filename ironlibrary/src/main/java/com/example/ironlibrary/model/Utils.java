package com.example.ironlibrary.model;

import java.time.LocalDate;
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
     * Validates if the provided string matches the typical ISBN format.
     * Note: This is a simplified check and might not cover all ISBN variants.
     */
    public static boolean isbnValidator(String isbn) {
        if (isbn == null) return false;
        String isbnRegex = "^(?:ISBN(?:-13)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
        Pattern isbnPattern = Pattern.compile(isbnRegex);
        Matcher matcher = isbnPattern.matcher(isbn);
        return matcher.matches();
    }

    /**
     * Updates the quantity of an item, ensuring it cannot go below zero.
     */
    public static int quantityUpdate(int currentQuantity, int change) {
        return Math.max(0, currentQuantity + change);
    }

    /**
     * Calculates the difference in days between two dates.
     */
    public static long dateDifferenceCalculator(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return ChronoUnit.DAYS.between(start, end);
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
