package com.example.ironlibrary.repository;

import com.example.ironlibrary.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
    Issue findByIssueId(int issueId);
}
