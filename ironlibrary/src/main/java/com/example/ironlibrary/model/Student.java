package com.example.ironlibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor

public class Student implements InputValidator{
    @Id
    private String usn;
    private String name;

//    @OneToOne(mappedBy="issueStudent")
//    private Issue issue;

    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn; //uniqueIdGenerator() de Utils

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean input_validation() {
        //añadir logica de validacion name
        return false;
    }
}
