package com.autodoc.controllers.contract.person.employee;

import com.autodoc.model.models.person.employee.Employee;
import org.springframework.http.ResponseEntity;

public interface SkillController {


    ResponseEntity getAll();

    Employee getEmployeeByName(String name);

    String getEmployeeById(int id);

    String addEmployee(Employee employee);

    String updateEmployee();


    String deleteEmployee(int employeeId);
}
