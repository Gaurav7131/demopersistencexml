package com.example;

import java.sql.Date;

import com.example.Entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // create EntityFactoryManager >heavy,create once/ db or appln
        // "my-jpa-unit" matches the name in persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-jpa-unit");

        // create EntityManager > lightwt,create /request(Crud)
        EntityManager em = emf.createEntityManager();

        // create object for composite key
        EmployeeID empId = new EmployeeID(111L, 222L);
        Employee newEmp = new Employee(empId, "Rahul", EmployeeStatus.ACTIVE, new Date(0));

        // Transaction begin:
        try {
            em.getTransaction().begin();// start transaction

            System.out.println("Saving Employee to db");
            em.persist(newEmp);// Add persistence context

            em.getTransaction().commit();// saved permantly
            System.out.println("Saved Successfully");

        } catch (Exception e) {
            em.getTransaction().rollback();// undo trans. if something went wrong (ACID)
        } finally {
            em.close();
            emf.close();
        }
    }
}