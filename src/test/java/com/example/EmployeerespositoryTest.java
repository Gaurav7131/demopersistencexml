package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Relationship.OneToMany.Department;
import com.example.Relationship.OneToMany.Employee;
import com.example.Repository.DepartmentRepository;
import com.example.Repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.*;

// 1. Tell Spring Boot to open the "Sandbox"
@DataJpaTest
public class EmployeerespositoryTest {

    // 2. Bring in the Repositories we want to test
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // 3. Write the actual test case
    @Test
    public void testSaveAndFetchEmployee() {
        // --- ARRANGE (Set up the data) ---
        Department dept = new Department();
        dept.setName("Engineering");
        // Save the department first so it has an ID
        departmentRepository.save(dept);

        Employee emp = new Employee();
        emp.setName("Gaurav");
        emp.setDepartment(dept);// Link the employee to the department

        // ACT (Perform the action we are testing) ---
        Employee savedEmployee = employeeRepository.save(emp);

        // Fetch it back from the database to prove it worked
        Employee fetchedEmployee = employeeRepository.findById(savedEmployee.getId()).orElse(null);

        // ASSERT (Verify the results) ---
        assertNotNull(fetchedEmployee, "Employee should not be null!");
        assertEquals("Gaurav", fetchedEmployee.getName(), "Name should match!");
        assertEquals("Engineering", fetchedEmployee.getDepartment().getName(), "Department name should match!");

        System.out.println(" Test Passed! Employee " + fetchedEmployee.getName() +
                " was successfully saved to " + fetchedEmployee.getDepartment().getName());
    }
}