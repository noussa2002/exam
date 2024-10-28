package com.example.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LocalizationService localizationService; // Service to fetch localized messages

    @GetMapping("/Employees")
    public List<Employee> getAllEmployees(@RequestHeader(name = "Accept-Language", required = false) String localeStr) {

        // You can use the locale to format the employee data if needed, but it won't change the response structure
        return employeeService.getAllEmployees();
    }

    @PostMapping("/Employees")
    public void addEmployee(@RequestBody Employee employee,Locale locale) {
        employeeService.addEmployee(employee,locale);
    }

    @PatchMapping("/Employees")
    public void updateEmployee(@RequestBody Employee employee, Local locale) {
        employeeService.updateEmployee(employee,locale);
    }

    @DeleteMapping("/Employees/{id}")
    public void deleteEmployee(@PathVariable long id,Locale locale) {
        employeeService.deleteEmployee(id,locale);
    }
}
