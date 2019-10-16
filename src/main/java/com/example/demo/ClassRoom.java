package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name1;
    private String professor;
    private String credit;
    private String description;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Student> students;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
