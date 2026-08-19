package com.example;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.Entity.AppConfigNew;
import com.example.springcorelab.NotificationService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//Manual configuration & Explicit Bean
class Book {
    private final String title = "Wings of Fire";

    // Getter method
    public String getTitle() {
        return title;
    }
}

@Configuration
class AppConfig {
    @Bean
    public Book myFavBook() {
        return new Book();// Manually registered into the ApplicationContext
    }
}

// Dependancy Component
@Component
class Engine {
    // M1:Start
    public void Start() {
        System.out.println("Engine start Successfuly");
    }
}

@Component
class Car {
    private final Engine engine;

    // Constructor-Injection:spring automatically inject Engine bean here
    public Car(Engine engine) {
        this.engine = engine;
    }

    // method2:Drive
    public void Drive() {
        System.out.println("Car Drive successfully on the road");
    }
}

// Prototype:create new bean instance when bean requested,shared new beanobj
@Component
@Scope("prototype") // creates brand new bean instance obj when everytime bean gets requested
class PrototypeBean {
    // Constructor
    public PrototypeBean() {
        System.out.println("ProtoTypeBean Instance Created Successfully");
    }
}

// SpEL(SpringbootExpressionLang:for injecting things,refactoring booleanval
// dynamically(rumtime)
@Component
class SpelEx {
    // Injecting runtime sys_properties using spEL
    @Value("#{systemProperties['os.name']}")
    private String operatingSystem;

    // Evaluating a dynamic mathematical formula using SpEL
    @Value("#{T(java.lang.Math).random() * 1000}")
    private Double randomValue;

    // Method for Displaying value on console
    public void DisplayValue() {
        System.out.println("System Properties:" + operatingSystem);
        System.out.println("Random Value:" + randomValue);
    }
}

// Bean Lifecycle:init() and clean()
@Component
class LifecycleBean {
    // constructor
    public LifecycleBean() {
        System.out.println("Lifecycle object is Initiated");
    }

    @PostConstruct
    public void init() {
        System.out.println(" Dependancy Injected,ready for action");
    }

    @PreDestroy
    public void clean() {
        System.out.println("Container shuting down,cleaning up resources");
    }
}

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Start the Spring IOC Container & capture the ApplContext
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        // Manual configuration & Explicit Bean
        System.out.println("Manual Configuration & Explicit Bean");
        Book book = context.getBean(Book.class);
        System.out.println("My Favorite Book:" + book.getTitle());

        // Dependancy Injection & IOC
        System.out.println("Dependacy Injection & IOC");
        Car car = context.getBean(Car.class);
        car.Drive();

        // Prototype Scope contract to Singleton
        System.out.println("Requesting Prototype Bean #1");
        context.getBean(PrototypeBean.class);

        System.out.println("Requesting Prototype Bean #2");
        context.getBean(PrototypeBean.class);

        // Spel
        SpelEx spelEx = context.getBean(SpelEx.class);
        spelEx.DisplayValue();

        // Notitification Service Object
        System.out.println("----Notification Service--Testing");

        // initiating the ApplicationContext container
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(AppConfigNew.class);

        // register user
        com.example.springcorelab.UserController userController = context2.getBean("userController",
                com.example.springcorelab.UserController.class);
        userController.registerUser("Gaurav");

        // Testing @bean annotation retrieval
        String sysId = context2.getBean("customSystemId", String.class);
        System.out.println("Bean retrival custom id:" + sysId);

        // Sms NotificationService
        NotificationService sms1 = context2.getBean("smsService", NotificationService.class);
        NotificationService sms2 = context2.getBean("smsService", NotificationService.class);

        System.out.println("Are Prototype scope is working? " + (sms1 == sms2));

        // close the conttext
        context2.close();
    }
}