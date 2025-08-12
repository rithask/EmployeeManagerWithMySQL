package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.exception.ValidationException;
import com.litmus7.employeemanager.model.Employee;
import com.litmus7.employeemanager.util.ValidationUtil;
import java.util.List;

public class EmployeeService {

    EmployeeDAO employeeDAO = new EmployeeDAO();

    public boolean addEmployeeToDb(Employee emp) {
        if (emp == null) throw new ValidationException("Employee cannot be null");
        if (employeeDAO.employeeExistsByEmail(emp.getEmail())) throw new ValidationException(
            "An employee with same e-mail ID already exists"
        );
        if (employeeDAO.employeeExistsByMobileNumber(emp.getMobileNo())) throw new ValidationException(
            "An employee with same mobile number already exists"
        );
        validateEmployee(emp);

        return employeeDAO.createEmployee(emp);
    }

    public List<Employee> getAllEmployeesFromDB() {
        if (employeeDAO.getAllEmployees().isEmpty()) {
            throw new ValidationException("No employees found");
        }

        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeByIdFromDB(int id) {
        if (id <= 0) throw new ValidationException("ID must be a positive integer");

        Employee emp = employeeDAO.getEmployeeById(id);
        if (emp == null) throw new ValidationException("Employee does not exist");

        return emp;
    }

    public boolean updateEmployeeInDb(Employee emp) {
        validateEmployee(emp);
        return employeeDAO.updateEmployee(emp);
    }

    public boolean deleteEmployeeFromDB(int id) {
        if (id <= 0) throw new ValidationException("ID must be a positive integer");
        if (!employeeDAO.employeeExistsById(id)) throw new ValidationException("Employee does not exist");

        return employeeDAO.deleteEmployee(id);
    }

    public void validateEmployee(Employee emp) {
        if (!ValidationUtil.isValidId(emp.getId())) {
            throw new ValidationException("ID must be a positive integer");
        }
        if (!ValidationUtil.isValidName(emp.getFirstName())) {
            throw new ValidationException("First name should not be empty");
        }
        if (!ValidationUtil.isValidName(emp.getLastName())) {
            throw new ValidationException("Last name should not be empty");
        }
        if (!ValidationUtil.isValidMobileNo(emp.getMobileNo())) {
            throw new ValidationException("Mobile number should only consist of 10-digits");
        }
        if (!ValidationUtil.isValidEmail(emp.getEmail())) {
            throw new ValidationException("Email ID should be in the form of 'user@domain.com'");
        }
        if (!ValidationUtil.isValidJoiningDate(emp.getJoiningDate())) {
            throw new ValidationException("Joining date should not be a future date");
        }
    }
}
