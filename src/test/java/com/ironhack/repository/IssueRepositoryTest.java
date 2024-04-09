package com.ironhack.repository;

import com.ironhack.model.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Test
    void testIssueCreationAndRetrieval() {
        // Create and save an Issue
        Issue savedIssue = issueRepository.save(new Issue("2023-01-01", "2023-01-10"));

        // Retrieve the saved Issue by ID
        Optional<Issue> retrievedIssue = issueRepository.findById(savedIssue.getIssueId());

        // Verify the retrieval
        assertTrue(retrievedIssue.isPresent(), "Issue should be found by ID");
        assertEquals("2023-01-01", retrievedIssue.get().getIssueDate(), "Issue dates should match");
    }

    @Test
    void testFindByIssueId() {
        // Create and save an Issue
        Issue savedIssue = issueRepository.save(new Issue("2023-02-01", "2023-02-10"));

        // Use the custom findByIssueId method
        Issue foundIssue = issueRepository.findByIssueId(savedIssue.getIssueId());

        // Verify the result
        assertNotNull(foundIssue, "Issue should be found with custom query");
        assertEquals("2023-02-01", foundIssue.getIssueDate(), "Issue dates should match in custom query");
    }
}
