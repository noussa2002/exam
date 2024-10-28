package com.example.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LocalizationService localizationService; // Service to fetch localized messages

    @GetMapping("/Employees")
    public Map<String, Object> getAllEmployees(@RequestHeader(name = "Accept-Language", required = false) String localeStr) {
        Locale locale = localizationService.getLocaleFromHeader(localeStr); // Method to determine Locale

        // Localized labels
        String firstNameLabel = localizationService.getMessage("employee.fname", locale);
        String lastNameLabel = localizationService.getMessage("employee.lname", locale);
        String ageLabel = localizationService.getMessage("employee.age", locale);
        String emailLabel = localizationService.getMessage("employee.email", locale);
        String phoneLabel = localizationService.getMessage("employee.phone", locale);

        // Fetch the list of employees
        List<Employee> employees = employeeService.getAllEmployees();

        // Create a response map
        Map<String, Object> response = new HashMap<>();
        response.put("firstNameLabel", firstNameLabel);
        response.put("lastNameLabel", lastNameLabel);
        response.put("ageLabel", ageLabel);
        response.put("emailLabel", emailLabel);
        response.put("phoneLabel", phoneLabel);
        response.put("employees", employees);

        return response;
    }

    @PostMapping("/Employees")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PatchMapping("/Employees")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/Employees/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }
}
