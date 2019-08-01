package com.rest.employeeswiththymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeesServlet {

    private EmployeesRepository repository;

    public EmployeesServlet(EmployeesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public String findAllEmployees(Model model) {
        List<Employees> allEmployees = repository.findAll();
        model.addAttribute("employeesList", allEmployees);
        return "employees";
    }

    @GetMapping("/employees/{id}")
    public String findEmployeeById(@PathVariable Integer id, Model model) {

        try {
//            Just wanted to try model.addAllAtributes methhod.
            Map<String, String> atributesMap = new HashMap<>();
            atributesMap.put("id", id.toString());
            atributesMap.put("first_name", repository.getOne(id).getFirst_name());
            atributesMap.put("last_name", repository.getOne(id).getLast_name());
            atributesMap.put("email", repository.getOne(id).getEmail());

            model.addAllAttributes(atributesMap);

            return "singleEmployee";
        } catch(EntityNotFoundException e) {
            return "employeeNotExists";
        }
    }

    @PostMapping
    public String addEmployee(@RequestBody Employees employee, Model model) {
        repository.save(employee);
        model.addAttribute("firstName", employee.getFirst_name());
        model.addAttribute("lastName", employee.getLast_name());
        model.addAttribute("email", employee.getEmail());
        return "newEmployee";

    }

    @DeleteMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        try {
            model.addAttribute("firstName", repository.getOne(id).getFirst_name());
            model.addAttribute("lastName", repository.getOne(id).getLast_name());
            repository.deleteById(id);
            return "deletedEmployee";
        } catch(EntityNotFoundException e) {
            return "employeeNotExists";
        }

    }

    @PutMapping("/employees/update")
    public String updateEmployee(@RequestBody Employees employee, Model model) {
        model.addAttribute("firstName", repository.getOne(employee.getId()).getFirst_name());
        model.addAttribute("lastName", repository.getOne(employee.getId()).getLast_name());
        model.addAttribute("email", repository.getOne(employee.getId()).getEmail());
        model.addAttribute("newFirstName", employee.getFirst_name());
        model.addAttribute("newLastName", employee.getLast_name());
        model.addAttribute("newEmail", employee.getEmail());
        repository.save(employee);
        return "employeeUpdated";
    }



}
