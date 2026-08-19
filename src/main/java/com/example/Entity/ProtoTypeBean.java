package com.example.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ProtoTypeBean {
    private final Long instanceId = System.currentTimeMillis();

    // method
    public void showId() {
        System.out.println("Prototype Bean Instance ID:" + instanceId);
    }

}
