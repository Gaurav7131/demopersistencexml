package com.example;

import java.sql.Date;

import com.example.Entity.EmployeeEntity;

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
        // EmployeeID empId = new EmployeeID(111L, 222L);
        // Employee newEmp = new Employee(empId, "Rahul", EmployeeStatus.ACTIVE, new
        // Date(0));

        EmployeeID mynewempId = new EmployeeID(111L, 222L);
        // Transaction begin:
        try {
            // State:New/transient->managed
            em.getTransaction().begin();// start transaction

            // State:transient(new) just a normal java object,db does nothing
            EmployeeEntity newEmp = new EmployeeEntity(mynewempId, "Gaurav", EmployeeStatus.ACTIVE, new Date(0));
            System.out.println("Saving Employee to db");

            // State:Managed:jpa not tracking this object
            em.persist(newEmp);// Add persistence context

            em.getTransaction().commit();// saved permantly
            System.out.println("Saved Employee Successfully");

            // Phase2:Managed
            // Select & Update(Dirty Checking)
            em.getTransaction().begin();

            // State:Manage:fetched from DB,jpa watching it
            EmployeeEntity findEmp = em.find(EmployeeEntity.class, newEmp);
            System.out.println("Fetched name:" + findEmp.getName());

            // It is managed state we must used setter method not em.persist()
            findEmp.setName("Gaurav Thakare");// dirty changes

            em.getTransaction().commit();
            System.out.println("Successfully Updated changes using Dirty Changes\n");

            // State:Unamanged or Deattached & Merge(manage -> deattach -> manage)
            em.getTransaction().begin();

            // state:manage
            EmployeeEntity trackemp = em.find(EmployeeEntity.class, findEmp);
            System.out.println("Fetched Tracked Emp" + trackemp.getName());

            // State:Deatached(Jpa stop watching from now)
            em.detach(trackemp);
            trackemp.setName("Gaurav Thakare Deatched");// This change wont saved

            // To save the deatached entity ,we must reatached to existing trans(trackemp).
            // used merge
            EmployeeEntity reatachEmployee = em.merge(trackemp);// State:manage again

            em.getTransaction().commit();
            System.out.println(" Merged deattached Changes Sucessfully\n");

            // State:Removal/Deletion
            em.getTransaction().begin();

            // to remove entity it must be in managed state so fetched it
            EmployeeEntity empToDelete = em.find(EmployeeEntity.class, reatachEmployee);

            // state:Delete (Remove)
            em.remove(empToDelete);

            em.getTransaction().commit();
            System.out.println("Delete Employee Sucessfully\n");

        } catch (Exception e) {
            em.getTransaction().rollback();// undo trans. if something went wrong (ACID)
        } finally {
            em.close();
            emf.close();
        }
    }
}