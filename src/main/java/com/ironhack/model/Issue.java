package com.ironhack.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor  // Generates a no-args constructor for JPA
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issueId;
    private String issueDate;
    private String returnDate;
    @ManyToOne
    @JoinColumn(name="issue_student")
    private Student issueStudent;
    @OneToOne
    @JoinColumn(name="issue_book")
    private Book issueBook;

    public Issue(String issueDate, String returnDate) {
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
}



