package com.example.ironlibrary.repository;

import com.example.ironlibrary.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    //Optional<Student> findByName(String name);
}
