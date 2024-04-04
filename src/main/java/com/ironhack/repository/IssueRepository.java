package com.ironhack.repository;

import com.ironhack.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
    Issue findByIssueId(int issueId);

    @Query("SELECT i FROM Issue i JOIN i.issueBook b WHERE b.isbn = ?1")
    Optional<Issue> findByIsbn(String isbn);

}
