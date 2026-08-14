package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Relationship.OneToMany.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
