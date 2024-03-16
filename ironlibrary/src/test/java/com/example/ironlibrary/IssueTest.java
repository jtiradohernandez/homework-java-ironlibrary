package com.example.ironlibrary;

import com.example.ironlibrary.model.Issue;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssueTest {
    @Test
    public void testInputValidation() {
        Issue issue = new Issue(1, "2022-03-01", "2022-03-31");
        assertTrue(issue.input_validation());
    }
}
