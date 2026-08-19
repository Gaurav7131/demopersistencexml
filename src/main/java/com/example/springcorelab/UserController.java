package com.example.springcorelab;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("userController")
public class UserController {
    private final NotificationService notificationService;

    // constructor injection using @Qualifier with autowired to reduced ambiguilty
    // betn emialNotificationService & smsNotificationService
    public UserController(@Qualifier("emailService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Method for registering user
    public void registerUser(String username) {
        System.out.println("user registered successfully:" + username);
        System.out.println("Welcome to our Platform:" + username + " Hii.. ");

    }
}
