package com.example.ironlibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Issue implements InputValidator{
    private int issueId;
    private String issueDate;
    private String returnDate;
    // To be used later.
    //private Student issueStudent;
    //private Book issueBook;

    @Override
    public boolean input_validation() {
        // Implement validation logic here
        // Return true if validation passes, false otherwise
        return true;
    }

}
