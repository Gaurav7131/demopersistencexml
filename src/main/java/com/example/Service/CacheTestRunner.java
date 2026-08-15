package com.example.Service;

import org.springframework.stereotype.Component;

import com.example.Entity.Product;
import com.example.Repository.ProductRepository;

@Component
public class CacheTestRunner implements org.springframework.boot.CommandLineRunner {
    private ProductRepository productRepository;

    public CacheTestRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Saved mock product to DB
        productRepository.save(new Product(1L, "Webcamp", "Electronics"));

        System.out.println("Fetching first time(Will hit DB");
        productRepository.findById(1L);

        System.out.println("Fetching second time(will hit cache,not DB");
        productRepository.findById(1L);

        // Query-cache relevance
        System.out.println("Fetching first time by category(Willhit DB");
        productRepository.findByCategory("Electronics");

        System.out.println("Fetching product by categories(Will hit cache,not DB");
        productRepository.findByCategory("Electronics");
    }
}
