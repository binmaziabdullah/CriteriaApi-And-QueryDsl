package com.criteriaandquerydsl.CriteriaApiAndQueryDsl;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee){
        Employee employee1s = employeeService.save(employee);
        return employee;
    }

    @GetMapping
    public List<Employee> getAllEmp(){
        return employeeService.getAllEmpByCriteriaApi()
                ;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmp(id);
    }

    @GetMapping("/{empName}")
    public List<Employee> getEmployeeByName(@PathVariable String empName){
        return employeeService.getEmpByName(empName);
    }

}

