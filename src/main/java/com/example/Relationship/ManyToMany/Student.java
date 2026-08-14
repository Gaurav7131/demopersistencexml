package com.example.Relationship.ManyToMany;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Many to Many requires middle table(junction table for hidden colmn to link
    // it) @Jointable tells the jpa the name of middle table & colmn use to link
    @ManyToMany
    @JoinTable(name = "student_course_registration", // middle table
            joinColumns = @JoinColumn(name = "student_id"), // key for this class
            inverseJoinColumns = @JoinColumn(name = "course_id")// key for other(course) class
    )
    private List<Course> courses;

}
