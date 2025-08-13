package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.model.Employee;
import com.litmus7.employeemanager.model.Response;
import com.litmus7.employeemanager.service.EmployeeService;
import java.util.List;

public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();

    public Response<Employee> addEmployeeToDB(Employee emp) {
        try {
            boolean added = employeeService.addEmployeeToDb(emp);
            if (added) return Response.success("Employee added successfully", emp);
            else return Response.error("Failed to add employee");
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    public Response<List<Employee>> getAllEmployeesFromDB() {
        try {
            List<Employee> employees = employeeService.getAllEmployeesFromDB();
            return Response.success("Employees found", employees);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    public Response<Employee> getEmployeeByIdFromDB(int id) {
        try {
            Employee employee = employeeService.getEmployeeByIdFromDB(id);
            return Response.success("Employee found", employee);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    public Response<Employee> updateEmployeeInDB(Employee emp) {
        try {
            boolean updated = employeeService.updateEmployeeInDb(emp);
            if (updated) return Response.success("Employee updated successfully", emp);
            else return Response.error("Failed to update employee");
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    public Response<Void> deleteEmployeeFromDB(int id) {
        try {
            boolean deleted = employeeService.deleteEmployeeFromDB(id);
            if (deleted) return Response.success("Employee deleted successfully", null);
            else return Response.error("Failed to delete employee");
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
