package com.litmus7.employeemanager.util;

import java.time.LocalDate;

public class ValidationUtil {

    public static boolean isValidId(int id) {
        if (id <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isBlank() && name.matches("^[A-za-z\\s'-]{2,50}$");
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) return false;
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|in|org|net)$");
    }

    public static boolean isValidMobileNo(String number) {
        if (number == null) return false;
        return number.matches("^[1-9][0-9]{9}$");
    }

    public static boolean isValidJoiningDate(LocalDate date) {
        if (date == null) return false;
        LocalDate lowerBound = LocalDate.of(2009, 7, 1);
        LocalDate today = LocalDate.now();
        return date.isAfter(lowerBound) && date.isBefore(today);
    }
}
