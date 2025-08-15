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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeDAO {

    private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

    public boolean createEmployee(Employee employee) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_EMPLOYEE)
        ) {
            logger.debug("Executing SQL query: " + Constants.INSERT_EMPLOYEE);

            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getMobileNumber());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setObject(6, employee.getJoiningDate());
            preparedStatement.setBoolean(7, employee.isActive());
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                logger.info("Inserted employee with ID {}", employee.getId());
                return true;
            } else {
                logger.warn("No rows inserted for employee with ID {}", employee.getId());
                return false;
            }
        } catch (SQLException e) {
            logger.error(
                "Failed to insert employee with ID {} using query [{}]",
                employee.getId(),
                Constants.INSERT_EMPLOYEE,
                e
            );
            return false;
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_ALL_EMPLOYEES)
        ) {
            logger.debug("Executing SQL query [{}]", Constants.SELECT_ALL_EMPLOYEES);

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
            logger.info("Fetched {} employees from database", employees.size());
        } catch (SQLException e) {
            logger.error("Error fetching all employees using query [{}]", Constants.SELECT_ALL_EMPLOYEES, e);
        }

        return employees;
    }

    public Employee getEmployeeById(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_EMPLOYEE_BY_ID)
        ) {
            logger.debug("Executing SQL query [{}] with id={}", Constants.SELECT_EMPLOYEE_BY_ID, id);

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                logger.info("Found employee with ID {}", id);
                return new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("mobile_number"),
                    rs.getString("email"),
                    rs.getObject("joining_date", LocalDate.class),
                    rs.getBoolean("active_status")
                );
            } else {
                logger.warn("No employee found with ID {}", id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error fetching employee with ID {} using query [{}]", id, Constants.SELECT_EMPLOYEE_BY_ID, e);
        }
        return null;
    }

    public boolean updateEmployee(Employee employee) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_EMPLOYEE)
        ) {
            logger.debug("Executing SQL query [{}] to update employee: {}", Constants.UPDATE_EMPLOYEE, employee);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getMobileNumber());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setObject(5, employee.getJoiningDate());
            preparedStatement.setBoolean(6, employee.isActive());
            preparedStatement.setInt(7, employee.getId());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                logger.info("Updated employee with ID {}", employee.getId());
                return true;
            } else {
                logger.warn("No employee updated for ID {}", employee.getId());
                return false;
            }
        } catch (SQLException e) {
            logger.error(
                "Error updating employee with ID {} using query [{}]",
                employee.getId(),
                Constants.UPDATE_EMPLOYEE,
                e
            );
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.DELETE_EMPLOYEE)
        ) {
            logger.debug("Executing SQL query [{}] to delete employee with ID {}", Constants.DELETE_EMPLOYEE, id);
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                logger.info("Deleted employee with ID {}", id);
                return true;
            } else {
                logger.warn("No employee deleted with ID {}", id);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error deleting employee with ID {} using query [{}]", id, Constants.DELETE_EMPLOYEE, e);
            return false;
        }
    }

    public boolean employeeExistsById(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_EMPLOYEE_BY_ID)
        ) {
            logger.debug("Executing SQL query: " + Constants.SELECT_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            logger.error("Error executing SQL query:  " + e);
            return false;
        }
    }

    public boolean employeeExistsByEmail(String email) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_EMPLOYEE_BY_EMAIL)
        ) {
            logger.debug("Executing SQL query: " + Constants.SELECT_EMPLOYEE_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            logger.error("Error executing SQL query:  " + e);
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
            logger.debug("Executing SQL query: " + Constants.SELECT_EMPLOYEE_BY_MOBILE_NUMBER);
            preparedStatement.setString(1, mobileNumber);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            logger.error("Error executing SQL query:  " + e);
            return false;
        }
    }
}
