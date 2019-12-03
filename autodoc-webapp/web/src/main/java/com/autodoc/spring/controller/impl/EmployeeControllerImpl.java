package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.spring.controller.contract.EmployeeController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/employees")
public class EmployeeControllerImpl extends GlobalController implements EmployeeController {

    // @Inject
    EmployeeManager employeeManager;

    private static Logger LOGGER = Logger.getLogger(EmployeeControllerImpl.class);

    public EmployeeControllerImpl(LibraryHelper helper, EmployeeManager employeeManager) {
        super(helper);
        this.employeeManager = employeeManager;
    }


    @GetMapping("")
    public ModelAndView employees(@Valid EmployeeForm employeeForm, BindingResult bindingResult) {
        LOGGER.info("retrieving employees");
        ModelAndView mv = checkAndAddEmployeeDetails("employees");
        System.out.println("helper: " + helper.getConnectedToken());
        System.out.println(employeeManager);
        if (bindingResult.hasErrors()) {
            return mv;
        }
//        employeeManager.getAll("gettall: "+helper.getConnectedToken());
        List<EmployeeDTO> employees = employeeManager.getAll(helper.getConnectedToken());
        System.out.println("employee: " + employees.size());
        System.out.println("emp: " + employees.get(0));
        mv.addObject("employees", employees);
        return mv;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView employeeById(@PathVariable Integer id, @Valid EmployeeDTO employeeForm, BindingResult bindingResult) {
        LOGGER.info("trying to get member with id " + id);
        LOGGER.info("employee: " + employeeForm);
        ModelAndView mv = checkAndAddEmployeeDetails("employees_details");
        // if(employeeForm.getLogin()==null) {
        System.out.println("employee is null");
        Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), id);
        LOGGER.info("phonennumber: " + employee.getPhoneNumber1());
        LOGGER.info("lastC: " + employee.getLastConnection());
        LOGGER.info("startDate: " + employee.getStartDate());
        mv.addObject("employeeForm", employee);
        mv.addObject("employee", employee);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid EmployeeForm employeeForm, BindingResult bindingResult) {
        LOGGER.info("trying to update member with id " + employeeForm.getId());
        ModelAndView mv = checkAndAddEmployeeDetails("employees_details");
        mv.addObject("employeeForm", new EmployeeForm());
        if (bindingResult.hasErrors()) {
            Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), employeeForm.getId());
            mv.addObject("employee", employee);
            mv.addObject("employeeForm", employeeForm);
            return mv;
        }
        // Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), employeeForm.getId());
        //if (employee==null)return mv;
        // EmployeeDTO dto = convertFormIntoDto(employeeForm);
        // Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), employeeForm.getId());
        LOGGER.info("employee retrieved: " + employeeForm);
       /* mv.addObject("employeeForm", new EmployeeForm());
        mv.addObject("employee", employee);*/
        employeeManager.update(helper.getConnectedToken(), employeeForm);
        return new ModelAndView("redirect:" + "/employees/" + employeeForm.getId());
    }

    private EmployeeDTO convertFormIntoDto(EmployeeForm employeeForm) {
        LOGGER.info("TODO");
        return null;
    }

}

