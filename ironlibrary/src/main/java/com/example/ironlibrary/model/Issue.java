package com.example.ironlibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Issue {
    private int issueId;
    private String issueDate;
    private String returnDate;
    // To be used later.
    //private Student issueStudent;
    //private Book issueBook;

}
