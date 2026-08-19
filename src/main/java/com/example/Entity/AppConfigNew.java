package com.example.Entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.springcorelab")
public class AppConfigNew {
    // Method-level @bean annotation
    @Bean(name = "customSystemId")
    public String systemIdGenerator() {
        return "SYS-PROD-9988X";
    }

}
