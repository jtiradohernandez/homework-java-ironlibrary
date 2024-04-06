package com.ironhack.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
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
     * Validates ISBNs (ISBN-10 and ISBN-13).
     * ISBN-10 Examples: "0-306-40615-2", "0306406152"
     * ISBN-13 Examples: "978-0-306-40615-7", "9780306406157"
     * Note:
     * - ISBN-10 is a 10-digit number with or without optional hyphens and might end with an 'X' representing 10.
     * - ISBN-13 is a 13-digit number, prefixed by '978' or '979', with or without optional hyphens.
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
            System.out.println("Both currentQuantity and change must be non-negative.");
            return currentQuantity; // Return the original quantity unchanged as an error signal
        }
        return currentQuantity + change;
    }

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Validates if the provided date string follows the "dd/MM/yyyy" format.
     *
     * @param date the date string to validate.
     * @return true if the date is in the correct format, false otherwise.
     */
    public static boolean validateDateFormat(String date) {
        try {
            LocalDate.parse(date, formatter);
            return true; // Date was successfully parsed, indicating valid format
        } catch (DateTimeParseException e) {
            return false; // Parsing failed, indicating invalid format
        }
    }

    /**
     * Calculates the difference in days between two dates provided in "dd/MM/yyyy" format.
     * Assumes both dates have been validated and are in the correct format.
     *
     * @param startDate the start date in "dd/MM/yyyy" format.
     * @param endDate the end date in "dd/MM/yyyy" format.
     * @return an Optional containing the difference in days between the start and end dates,
     *         or an empty Optional if either date is in an invalid format.
     */
    public static Optional<Long> dateDifferenceCalculator(String startDate, String endDate) {
        if (!validateDateFormat(startDate) || !validateDateFormat(endDate)) {
            // If either date is in invalid format, return an empty Optional
            return Optional.empty();
        }

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        long daysBetween = ChronoUnit.DAYS.between(start, end);

        return Optional.of(daysBetween);
    }





        /**
         * Generates a unique identifier, for example, for use with entities that don't have an auto-generated ID.
         */
        public static String uniqueIdGenerator() {
            return UUID.randomUUID().toString();
        }

    public static boolean usnValidator(String usn) {
        if (usn == null) return false;

        // USN Regex that matches exactly 13 numeric characters
        String usnRegex = "^[0-9]{11}$";

        Pattern usnPattern = Pattern.compile(usnRegex);
        Matcher matcher = usnPattern.matcher(usn);

        // Matches USN against the regex pattern
        return matcher.matches();
    }
}
