package com.litmus7.employeemanager.model;

import java.time.LocalDate;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private LocalDate joiningDate;
    private boolean active;

    public Employee() {}

    public Employee(
        int id,
        String firstName,
        String lastName,
        String mobileNumber,
        String email,
        LocalDate joiningDate,
        boolean active
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.joiningDate = joiningDate;
        this.active = active;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getJoiningDate() {
        return this.joiningDate;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return String.format(
            "ID:\t\t%d%n" +
            "First Name:\t%s%n" +
            "Last Name:\t%s%n" +
            "Mobile Number:\t%s%n" +
            "Email:\t\t%s%n" +
            "Joining Date:\t%s%n" +
            "Active Status:\t%b%n",
            id,
            firstName,
            lastName,
            mobileNumber,
            email,
            joiningDate,
            active
        );
    }
}
