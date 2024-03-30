package com.ironhack.repository;


import com.ironhack.model.Issue;
import com.ironhack.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByName(String name);

    Optional<Student> findByUsn(String usn);

    @Query("SELECT i FROM Issue i JOIN i.issueStudent s WHERE s.usn = ?1")
    List<Issue> searchBooksByStudentString(String usn);
}
