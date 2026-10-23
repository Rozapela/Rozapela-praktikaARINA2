package com.github.rozapela.hr.service;

import com.github.rozapela.hr.model.Employee;
import com.github.rozapela.hr.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> search(String name, String active) {
        boolean hasName = (name != null && !name.isBlank());

        if (active == null || Objects.equals(active, "all")) {
            return hasName ? employeeRepository.findByFullNameContainingIgnoreCase(name) : employeeRepository.findAll();
        }

        boolean isActive = Objects.equals(active, "true");
        if (hasName) {
            return employeeRepository.findByFullNameContainingIgnoreCaseAndActive(name, isActive);
        }
        return employeeRepository.findByActive(isActive);
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник с таким ID не найден"));
    }

    public Employee saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                throw new RuntimeException("Email уже занят");
            }
        } else {
            if (employeeRepository.existsByEmailAndIdNot(employee.getEmail(), employee.getId())) {
                throw new RuntimeException("Email уже занят другим сотрудником");
            }
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}