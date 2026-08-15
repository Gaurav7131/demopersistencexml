package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "`app_user`")
// Defining NamedQuery on the top of the entity to avoid messsy query string
// scattering
@NamedQuery(name = "Usernew.findByEmailNamed", query = "select u from Usernew u where u.email=:email")
public class Usernew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;

    // default no-args constructor
    public Usernew() {
    }

    // constructor for our used
    public Usernew(String name, String email, Integer age) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getter & Setter method
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;

    }
}
