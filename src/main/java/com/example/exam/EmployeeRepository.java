package com.example.exam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    Employee findById(long id);
    void deleteById(Long id);

}
