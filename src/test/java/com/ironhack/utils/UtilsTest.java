package com.ironhack.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testStringValidator() {
        assertTrue(Utils.stringValidator("Hello World"));
        assertFalse(Utils.stringValidator(""));
        assertFalse(Utils.stringValidator(null));
    }
    @Test
    void testEmailValidator() {
        assertTrue(Utils.emailValidator("example@example.com"));
        assertFalse(Utils.emailValidator("example@"));
        assertFalse(Utils.emailValidator("example.com"));
        assertFalse(Utils.emailValidator(null));
    }

    @Test
    void testIsbnValidator() {
        assertTrue(Utils.isbnValidator("978-3-16-148410-0"));
        assertTrue(Utils.isbnValidator("0-306-40615-2"));

        assertFalse(Utils.isbnValidator("1234567890123"));
        assertFalse(Utils.isbnValidator(null));
    }

    @Test
    void testQuantityUpdate() {
        // Test for successful quantity update
        assertEquals(10, Utils.addQuantityUpdate(8, 2), "The sum should be 10 for valid inputs.");

        // Test for unsuccessful quantity update
        assertEquals(5, Utils.addQuantityUpdate(5, -10), "Negative changes should result in no update.");
        assertEquals(-5, Utils.addQuantityUpdate(-5, 10), "Initial negative quantities should behave normally.");
    }

    @Test
    void testValidateDateFormat() {
        // Test valid date formats
        assertTrue(Utils.validateDateFormat("01/01/2023"), "Date should be considered valid");
        assertTrue(Utils.validateDateFormat("31/12/2023"), "Date should be considered valid");

        // Test invalid date formats
        assertFalse(Utils.validateDateFormat("2023-01-01"), "Date should be considered invalid");
        assertFalse(Utils.validateDateFormat("01/2023"), "Date should be considered invalid");
        assertFalse(Utils.validateDateFormat("test"), "Date should be considered invalid");
    }

    @Test
    void testDateDifferenceCalculator() {
        // Test valid date inputs
        assertEquals(Optional.of(1L), Utils.dateDifferenceCalculator("01/01/2023", "02/01/2023"),
                "The difference should be 1 day for valid date inputs");
        assertEquals(Optional.of(-1L), Utils.dateDifferenceCalculator("02/01/2023", "01/01/2023"),
                "The difference should be -1 days for valid date inputs");

        // Test invalid date inputs
        assertEquals(Optional.empty(), Utils.dateDifferenceCalculator("2023-01-01", "01/01/2023"),
                "Should return Optional.empty() for invalid date format");
        assertEquals(Optional.empty(), Utils.dateDifferenceCalculator("01/01/2023", "2023-01-02"),
                "Should return Optional.empty() for invalid date format");
    }


    @Test
    void testUniqueIdGenerator() {
        String uniqueId1 = Utils.uniqueIdGenerator();
        String uniqueId2 = Utils.uniqueIdGenerator();
        Assertions.assertNotEquals(uniqueId1, uniqueId2);
    }

    @Test
    void usnValidator(){
        assertTrue(Utils.usnValidator("12345678901"));
        assertTrue(Utils.usnValidator("98765432101"));

        assertFalse(Utils.usnValidator("123"));
        assertFalse(Utils.usnValidator(null));
    }
}
