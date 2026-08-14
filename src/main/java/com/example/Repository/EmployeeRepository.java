package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Relationship.OneToMany.Employee;
import com.example.Repository.EmployeeRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
