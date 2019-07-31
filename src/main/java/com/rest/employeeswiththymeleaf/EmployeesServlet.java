package com.rest.employeeswiththymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        Map<String, String> atributesMap = new HashMap<>();
        atributesMap.put("id", id.toString());
        atributesMap.put("first_name", repository.getOne(id).getFirst_name());
        atributesMap.put("last_name", repository.getOne(id).getLast_name());
        atributesMap.put("email", repository.getOne(id).getEmail());

        model.addAllAttributes(atributesMap);

        return "singleEmployee";
//        return repository.findById(id);
    }

    @PostMapping
    public Employees addEmployee(@RequestBody Employees employee) {
        return repository.save(employee);
    }

    @DeleteMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        final Logger logger = LoggerFactory.getLogger(EmployeesServlet.class);
        try {
            repository.deleteById(id);
            return "Deleted employee with id: " + id;
        } catch(RuntimeException e) {
            logger.warn("There is no employee with id " + id + ". Delete is impossible.");
        }
        return null;
    }

    @PutMapping("/employees/update")
    public Employees updateEmployee(@RequestBody Employees employee) {
        return repository.save(employee);
    }



}
