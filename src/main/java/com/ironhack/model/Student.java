package com.ironhack.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor

public class Student implements InputValidator{
    @Id
    private String usn;
    private String name;

    @OneToMany(mappedBy="issueStudent")
//    @OneToMany(mappedBy="issueStudent", cascade = CascadeType.ALL)
    private List<Issue> issues;

    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }
    public List<Issue> getIssues(){
        return this.issues;
    }

    public void addIssue(Issue issue){
        if (issues == null) {
            issues = new ArrayList<>();
        }
        issues.add(issue);
        issue.setStudent(this);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(usn, student.usn) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usn, name);
    }

    @Override
    public boolean input_validation() {
        //añadir logica de validacion name
        return false;
    }
}
