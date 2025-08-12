package com.litmus7.employeemanager.dao;

import com.litmus7.employeemanager.constants.Constants;
import com.litmus7.employeemanager.model.Employee;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public boolean createEmployee(Employee emp) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_EMPLOYEE)
        ) {
            preparedStatement.setInt(1, emp.getId());
            preparedStatement.setString(2, emp.getFirstName());
            preparedStatement.setString(3, emp.getLastName());
            preparedStatement.setString(4, emp.getMobileNo());
            preparedStatement.setString(5, emp.getEmail());
            preparedStatement.setObject(6, emp.getJoiningDate());
            preparedStatement.setBoolean(7, emp.isActive());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_ALL_EMPLOYEES)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String mobile_number = rs.getString("mobile_number");
                String email = rs.getString("email");
                LocalDate joining_date = rs.getObject("joining_date", LocalDate.class);
                boolean active_status = rs.getBoolean("active_status");
                employees.add(
                    new Employee(id, first_name, last_name, mobile_number, email, joining_date, active_status)
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employees;
    }

    public Employee getEmployeeById(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_EMPLOYEE_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("mobile_number"),
                    rs.getString("email"),
                    rs.getObject("joining_date", LocalDate.class),
                    rs.getBoolean("active_status")
                );
            } else return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateEmployee(Employee emp) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_EMPLOYEE)
        ) {
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getMobileNo());
            preparedStatement.setString(4, emp.getEmail());
            preparedStatement.setObject(5, emp.getJoiningDate());
            preparedStatement.setBoolean(6, emp.isActive());
            preparedStatement.setInt(7, emp.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.DELETE_EMPLOYEE)
        ) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean employeeExistsById(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_EMPLOYEE_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean employeeExistsByEmail(String email) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_EMPLOYEE_BY_EMAIL)
        ) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean employeeExistsByMobileNumber(String mobileNumber) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                Constants.SELECT_EMPLOYEE_BY_MOBILE_NUMBER
            )
        ) {
            preparedStatement.setString(1, mobileNumber);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
