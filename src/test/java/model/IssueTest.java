package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IssueTest {

    @Test
    void testConstructorAssignsValuesCorrectly() {
        // Given
        String expectedIssueDate = "2023-01-01";
        String expectedReturnDate = "2023-01-10";

        // When
        Issue issue = new Issue(expectedIssueDate, expectedReturnDate);

        // Then
        assertEquals(expectedIssueDate, issue.getIssueDate(), "IssueDate should match the constructor input");
        assertEquals(expectedReturnDate, issue.getReturnDate(), "ReturnDate should match the constructor input");
    }

    @Test
    void testInputValidation() {
        // Given
        Issue issue = new Issue("2023-01-01", "2023-01-10");

        // When
        boolean isValid = issue.input_validation();

        // Then
        assertTrue(isValid, "input_validation should return true");
    }
}
