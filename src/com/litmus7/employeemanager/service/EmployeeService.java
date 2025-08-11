package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.model.Employee;
import java.util.List;

public class EmployeeService {

    EmployeeDAO employeeDAO = new EmployeeDAO();

    public boolean addEmployeeToDb(Employee emp) {
        return employeeDAO.createEmployee(emp);
    }

    public List<Employee> getAllEmplyeesFromDB() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeByIdFromDB(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public boolean updateEmployeeInDb(Employee emp) {
        return employeeDAO.updateEmployee(emp);
    }

    public boolean deleteEmployeeFromDB(int id) {
        return employeeDAO.deleteEmployee(id);
    }
}
