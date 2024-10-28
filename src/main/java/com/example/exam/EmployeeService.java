package com.example.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
        @Autowired
        private EmployeeRepository employeeRepository;

        public List<Employee> getAllEmployees() {
            return employeeRepository.findAll();
        }

        public void addEmployee(Employee employee) {
            employeeRepository.save(employee);
        }

        public void updateEmployee (Employee employee){
            Employee oldemployee=employeeRepository.findById(employee.getId());

            oldemployee.setAge(employee.getAge());
            oldemployee.setEmail(employee.getEmail());
            oldemployee.setFname(employee.getFname());
            oldemployee.setLname(employee.getLname());
            oldemployee.setPhone(employee.getPhone());

            employeeRepository.save(oldemployee);
        }

        @Transactional
        public void deleteEmployee (Long id){
            employeeRepository.deleteById(id);

        }
    }

