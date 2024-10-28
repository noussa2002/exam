package com.example.exam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    Optional<Employee> findById(Long id);
    void deleteById(Long id);

}
