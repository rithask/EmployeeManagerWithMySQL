package com.litmus7.employeemanager.app;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.model.Employee;
import com.litmus7.employeemanager.model.Response;
import com.litmus7.employeemanager.util.InputUtil;
import com.litmus7.employeemanager.util.ValidationUtil;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagerApp {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        int tempId = 0;
        Employee tempEmp = null;

        EmployeeController employeeController = new EmployeeController();

        while (userChoice != 6) {
            System.out.println();
            System.out.println("1. Add an employee");
            System.out.println("2. Get all employees");
            System.out.println("3. Get one employee");
            System.out.println("4. Update an employee");
            System.out.println("5. Delete an employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                userChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                continue;
            }

            System.out.println();
            switch (userChoice) {
                case 1:
                    tempEmp = readDataFromUser(scanner);
                    Response<Employee> employeeAddResponse = employeeController.addEmployeeToDB(tempEmp);
                    if (employeeAddResponse.isSuccess()) {
                        System.out.println("Employee added successfully");
                    } else {
                        System.out.println(employeeAddResponse.getMessage());
                    }
                    break;
                case 2:
                    Response<List<Employee>> employeesFetchResponse = employeeController.getAllEmployeesFromDB();
                    if (employeesFetchResponse.isSuccess()) {
                        employeesFetchResponse.getData().forEach(System.out::println);
                    } else {
                        System.out.println(employeesFetchResponse.getMessage());
                    }
                    break;
                case 3:
                    tempId = InputUtil.readInt(scanner, "Enter the ID of the employee you want to fetch: ");
                    Response<Employee> employeeFetchResponse = employeeController.getEmployeeByIdFromDB(tempId);
                    if (employeeFetchResponse.isSuccess()) {
                        System.out.println(employeeFetchResponse.getData());
                    } else {
                        System.out.println(employeeFetchResponse.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Enter the details of the employee you want to update...");
                    tempEmp = readDataFromUser(scanner);
                    Response<?> employeeUpdateResponse = employeeController.updateEmployeeInDB(tempEmp);
                    if (employeeUpdateResponse.isSuccess()) {
                        System.out.println("Employee updated successfully");
                    } else {
                        System.out.println(employeeUpdateResponse.getMessage());
                    }
                    break;
                case 5:
                    tempId = InputUtil.readInt(scanner, "Enter the ID of the employee you want to delete: ");
                    Response<?> employeeDeleteResponse = employeeController.deleteEmployeeFromDB(tempId);
                    System.out.println(employeeDeleteResponse.getMessage());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static Employee readDataFromUser(Scanner sc) {
        int id = 0;
        do {
            System.out.print("Enter the ID: ");
            try {
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID should be a number");
                continue;
            }
        } while (!ValidationUtil.isValidId(id));

        String firstName;
        do {
            System.out.print("Enter the first name: ");
            firstName = sc.nextLine();
        } while (!ValidationUtil.isValidName(firstName));

        String lastName;
        do {
            System.out.print("Enter the last name: ");
            lastName = sc.nextLine();
        } while (!ValidationUtil.isValidName(lastName));

        String mobileNo = null;
        do {
            System.out.print("Enter the 10-digit mobile number: ");
            try {
                mobileNo = sc.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Mobile number should only consist of digits");
                continue;
            }
        } while (!ValidationUtil.isValidMobileNo(mobileNo));

        String email;
        do {
            System.out.print("Enter the email: ");
            email = sc.nextLine();
        } while (!ValidationUtil.isValidEmail(email));

        LocalDate joiningDate = null;
        do {
            System.out.print("Enter the joining date (YYYY-MM-DD): ");
            try {
                joiningDate = LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date");
                continue;
            }
        } while (!ValidationUtil.isValidJoiningDate(joiningDate));

        Boolean activeStatus = null;
        while (activeStatus == null) {
            System.out.print("Is the employee active (y/n): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("y")) activeStatus = true;
            else if (input.equals("n")) activeStatus = false;
            else System.out.println("Invalid input. Please enter y or n.");
        }

        return new Employee(id, firstName, lastName, mobileNo, email, joiningDate, activeStatus);
    }
}
