package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.exception.ValidationException;
import com.litmus7.employeemanager.model.Employee;
import com.litmus7.employeemanager.util.ValidationUtil;
import java.util.List;

public class EmployeeService {

    EmployeeDAO employeeDAO = new EmployeeDAO();

    public boolean addEmployeeToDb(Employee employee) {
        if (employee == null) throw new ValidationException("Employee cannot be null");
        if (employeeDAO.employeeExistsById(employee.getId())) throw new ValidationException(
            "An employee with ID " + employee.getId() + " already exists"
        );
        if (employeeDAO.employeeExistsByEmail(employee.getEmail())) throw new ValidationException(
            "An employee with e-mail " + employee.getEmail() + " already exists"
        );
        if (employeeDAO.employeeExistsByMobileNumber(employee.getMobileNumber())) throw new ValidationException(
            "An employee with mobile number " + employee.getMobileNumber() + " already exists"
        );
        validateEmployee(employee);

        return employeeDAO.createEmployee(employee);
    }

    public List<Employee> getAllEmployeesFromDB() {
        if (employeeDAO.getAllEmployees().isEmpty()) {
            throw new ValidationException("No employees found");
        }

        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeByIdFromDB(int id) {
        if (id <= 0) throw new ValidationException("ID must be a positive integer");

        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee == null) throw new ValidationException("No employee found with ID: " + id);

        return employee;
    }

    public boolean updateEmployeeInDb(Employee employee) {
        if (employee == null) throw new ValidationException("Employee cannot be null");
        if (!employeeDAO.employeeExistsById(employee.getId())) throw new ValidationException(
            "Cannot update. No employee found with ID: " + employee.getId()
        );

        validateEmployee(employee);
        return employeeDAO.updateEmployee(employee);
    }

    public boolean deleteEmployeeFromDB(int id) {
        if (id <= 0) throw new ValidationException("ID must be a positive integer");
        if (!employeeDAO.employeeExistsById(id)) throw new ValidationException(
            "Cannot delete. No employee found with ID: " + id
        );

        return employeeDAO.deleteEmployee(id);
    }

    public void validateEmployee(Employee employee) {
        if (!ValidationUtil.isValidId(employee.getId())) {
            throw new ValidationException("ID must be a positive integer");
        }
        if (!ValidationUtil.isValidName(employee.getFirstName())) {
            throw new ValidationException("First name should not be empty");
        }
        if (!ValidationUtil.isValidName(employee.getLastName())) {
            throw new ValidationException("Last name should not be empty");
        }
        if (!ValidationUtil.isValidMobileNo(employee.getMobileNumber())) {
            throw new ValidationException("Mobile number should only consist of 10-digits");
        }
        if (!ValidationUtil.isValidEmail(employee.getEmail())) {
            throw new ValidationException("Email ID should be in the form of 'user@domain.com'");
        }
        if (!ValidationUtil.isValidJoiningDate(employee.getJoiningDate())) {
            throw new ValidationException("Joining date should not be a future date");
        }
    }
}
