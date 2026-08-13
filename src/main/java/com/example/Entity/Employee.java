//Purpose of this class for Temporal annotation and transient(offrods invisible calculations)
package com.example.Entity;

import java.sql.Date;

import com.example.EmployeeID;
import com.example.EmployeeStatus;
import com.example.Listener.EmployeeListener;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "employees")
@EntityListeners(EmployeeListener.class) // Attached EmplooyeeListener(Listener) class
public class Employee {

    @EmbeddedId // 1. COMPOSITE KEY used here empId & DeptId
    private EmployeeID id;
    private String name;

    @Enumerated(EnumType.STRING) // ENUMERATED type (saves "ACTIVE" as a String , not 0(integer))
    private EmployeeStatus status;

    @Temporal(TemporalType.DATE) // TEMPORAL type (saves only the Day/Month/Year)
    private Date joinDate;

    // @Transient // TRANSIENT (Calculated in Java, NEVER saved to the database colm
    // invisible cauclations)
    // private double calculatedBonus

    public Employee() {
    }

    public Employee(EmployeeID id, String name, EmployeeStatus status, Date joinDate) {
        this.id = id;
        this.status = status;
        this.joinDate = joinDate;
        // this.calculatedBonus = 5000.0; // We can calculate this, but it won't be
        // saved
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
