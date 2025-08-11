package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.model.Employee;
import com.litmus7.employeemanager.service.EmployeeService;
import java.util.List;

public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();

    public void addEmployeeToDB(Employee emp) {
        employeeService.addEmployeeToDb(emp);
    }

    public List<Employee> getAllEmployeesFromDB() {
        return employeeService.getAllEmplyeesFromDB();
    }

    public Employee getEmployeeByIdFromDB(int id) {
        return employeeService.getEmployeeByIdFromDB(id);
    }

    public boolean updateEmployeeInDB(Employee emp) {
        return employeeService.updateEmployeeInDb(emp);
    }

    public boolean deleteEmployeeFromDB(int id) {
        return employeeService.deleteEmployeeFromDB(id);
    }
}
