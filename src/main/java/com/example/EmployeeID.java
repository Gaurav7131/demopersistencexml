//Purpose of this class is Resolve conflict of EmpId & DeptId
package com.example;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // Marks this class as a reusable component for a Primary Key
public class EmployeeID implements Serializable {

    private Long deptId;
    private Long empId;

    public EmployeeID() {
    } // Default constructor is mandatory

    // Constrctor for our use
    public EmployeeID(Long deptId, Long empId) {
        this.deptId = deptId;
        this.empId = empId;
    }

    // We MUST implement equals() and hashCode() for Composite Keys
    // 1.equals()
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmployeeID that = (EmployeeID) o;
        return Objects.equals(deptId, that.deptId) && Objects.equals(empId, that.empId);
    }

    // 2.HashCode()
    @Override
    public int hashCode() {
        return Objects.hash(deptId, empId);
    }
}