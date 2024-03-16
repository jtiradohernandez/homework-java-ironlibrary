package com.example.ironlibrary;

import com.example.ironlibrary.model.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void testStringValidator() {
        Assertions.assertTrue(Utils.stringValidator("Hello World"));
        Assertions.assertFalse(Utils.stringValidator(""));
        Assertions.assertFalse(Utils.stringValidator(null));
    }

    @Test
    void testEmailValidator() {
        Assertions.assertTrue(Utils.emailValidator("example@example.com"));
        Assertions.assertFalse(Utils.emailValidator("example@"));
        Assertions.assertFalse(Utils.emailValidator("example.com"));
        Assertions.assertFalse(Utils.emailValidator(null));
    }

    @Test
    void testIsbnValidator() {
        Assertions.assertTrue(Utils.isbnValidator("978-3-16-148410-0"));
        Assertions.assertTrue(Utils.isbnValidator("0-306-40615-2"));
        Assertions.assertFalse(Utils.isbnValidator("1234567890123"));
        Assertions.assertFalse(Utils.isbnValidator(null));
    }

    @Test
    void testQuantityUpdate() {
        Assertions.assertEquals(10, Utils.quantityUpdate(8, 2));
        Assertions.assertEquals(0, Utils.quantityUpdate(5, -10));
    }

    @Test
    void testDateDifferenceCalculator() {
        Assertions.assertEquals(1, Utils.dateDifferenceCalculator("2023-01-01", "2023-01-02"));
        Assertions.assertEquals(-1, Utils.dateDifferenceCalculator("2023-01-02", "2023-01-01"));
    }

    // Assuming Utils.dataDisplay() only prints the data, no need to unit test as it doesn't return any value.
    // Instead, focus on methods with clear input/output behaviors.

    @Test
    void testUniqueIdGenerator() {
        String uniqueId1 = Utils.uniqueIdGenerator();
        String uniqueId2 = Utils.uniqueIdGenerator();
        Assertions.assertNotEquals(uniqueId1, uniqueId2);
    }
}
