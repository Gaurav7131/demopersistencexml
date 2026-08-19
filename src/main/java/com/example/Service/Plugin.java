package com.example.Service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public interface Plugin {
    void execute();
}

@Component
@Order(1) // order matters in List
class FirstPlugin implements Plugin {
    @Override
    public void execute() {
        System.out.println("First Plugin Executed(Priority High)");
    }

}

@Component
@Order(2)
class SecondPlugin implements Plugin {
    @Override
    public void execute() {
        System.out.println("Second Plugin Executed(Priority Low)");
    }

}
