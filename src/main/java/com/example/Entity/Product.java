package com.example.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Cacheable // Jakarta std annotation to mrk this entity as eligible for L2 cache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) // update changes,prevent dirty reads,safed cache lock
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    // default no-args constructor
    public Product() {
    }

    // Constructor for our use
    public Product(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
