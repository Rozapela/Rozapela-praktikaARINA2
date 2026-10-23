package com.github.rozapela.hr.repository;

import com.github.rozapela.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    List<Employee> findByFullNameContainingIgnoreCase(String fullName);
    List<Employee> findByActive(Boolean active);
    List<Employee> findByFullNameContainingIgnoreCaseAndActive(String fullName, Boolean active);
}