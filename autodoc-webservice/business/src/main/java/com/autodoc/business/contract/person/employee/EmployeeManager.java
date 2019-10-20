package com.autodoc.business.contract.person.employee;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.person.employee.Employee;

public interface EmployeeManager extends IGenericManager {

    boolean exist(String login);


    EmployeeDTO getEmployeeByLogin(String login);

    EmployeeDTO getEmployeeByToken(String token);

    Employee getByLogin(String login);

    Employee getByToken(String token);
}
