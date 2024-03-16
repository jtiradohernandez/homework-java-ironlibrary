package com.example.ironlibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor  // Generates a no-args constructor for JPA
public class Issue implements InputValidator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issueId;
    private String issueDate;
    private String returnDate;

    // Constructor without issueId
    public Issue(String issueDate, String returnDate) {
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    @Override
    public boolean input_validation() {
        // Implement validation logic here
        // Return true if validation passes, false otherwise
        return true;
    }

    @OneToOne
    private Student issueStudent;
    @OneToOne
    private Book issueBook;
}



