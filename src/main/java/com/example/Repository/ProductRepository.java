package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import com.example.Entity.Product;

import jakarta.persistence.QueryHint;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // QueryHints annotation used for storing ID(Pk) of resultant queries in Query
    // cache(L3)
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    List<Product> findByCategory(String category);

}
