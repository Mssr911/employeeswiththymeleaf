package com.rest.employeeswiththymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeesServlet {

    @GetMapping("/psita")
    public String findAllEmployees() {
        return "employees";
    }




}
