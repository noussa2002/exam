package com.example.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LocalizationService localizationService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee, Locale locale) {


        employeeRepository.save(employee);
        throw new RuntimeException(localizationService.getMessage("employee.added", locale));
    }

    public void updateEmployee(Employee employee, Local locale) {
        Employee existingEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new RuntimeException(localizationService.getMessage("employee.not.found", locale)));

        existingEmployee.setAge(employee.getAge());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setFname(employee.getFname());
        existingEmployee.setLname(employee.getLname());
        existingEmployee.setPhone(employee.getPhone());

        employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void deleteEmployee(Long id, Locale locale) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException(localizationService.getMessage("employee.not.found", locale));
        }
        employeeRepository.deleteById(id);
    }
}
