package com.litmus7.employeemanager.constants;

public class Constants {

    // regex
    public static final String NAME_REGEX = "^[A-za-z\\s'-]{2,50}$";
    public static final String MOBILE_NUMBER_REGEX = "^[1-9][0-9]{9}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|in|org|net)$";

    // JDBC constants
    public static final String DB_URL = "jdbc:mysql://localhost:3306/empdb";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "roottoor";

    // SQL statements
    public static final String INSERT_EMPLOYEE =
        "INSERT INTO employees (id, first_name, last_name, mobile_number, email, joining_date, active_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_EMPLOYEES =
        "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employees";
    public static final String SELECT_EMPLOYEE_BY_ID =
        "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employees WHERE id = ?";
    public static final String SELECT_EMPLOYEE_BY_EMAIL =
        "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employees WHERE email = ?";
    public static final String SELECT_EMPLOYEE_BY_MOBILE_NUMBER =
        "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employees WHERE mobile_number = ?";
    public static final String UPDATE_EMPLOYEE =
        "UPDATE employees SET first_name = ?, last_name = ?, mobile_number = ?, email = ?, joining_date = ?, active_status = ? WHERE id = ?";
    public static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";
}
