package com.example.demo.reviews.repo;

import com.example.demo.commonentity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
