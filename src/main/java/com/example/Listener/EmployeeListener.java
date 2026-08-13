package com.example.Listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class EmployeeListener {
    @PrePersist
    public void BeforeSave(Object entity) {
        System.out.println("Before saving:Formalities");
    }

    @PostPersist
    public void AfterSave(Object entity) {
        System.out.println("AFter saving:Accessing condn");
    }

    @PreUpdate
    public void BeforeUpdate(Object entity) {
        System.out.println("After updating chnages");
    }

    @PreRemove
    public void BeforeRemove(Object entity) {
        System.out.println("BEfore wiping out");
    }
}
