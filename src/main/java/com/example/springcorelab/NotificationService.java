package com.example.springcorelab;

//import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Simple Notification service
public interface NotificationService {

    void sendNotification(String message);
}

// Service:sending Email
@Component("emailService")
class sendEmail implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email:" + message);
    }
}

// Service:sending sms
@Component("smsService")
@Scope("prototype") // new instance of obj when everytime u requested bean
// @Primary // :If u want this tobe "default fallback"
class sendSMS implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS:" + message);
    }

}
