package com.litmus7.employeemanager.util;

import com.litmus7.employeemanager.constants.Constants;
import java.time.LocalDate;

public class ValidationUtil {

    public static boolean isValidId(int id) {
        if (id <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isBlank() && name.matches(Constants.NAME_REGEX);
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) return false;
        return email.matches(Constants.EMAIL_REGEX);
    }

    public static boolean isValidMobileNo(String number) {
        if (number == null) return false;
        return number.matches(Constants.MOBILE_NUMBER_REGEX);
    }

    public static boolean isValidJoiningDate(LocalDate date) {
        if (date == null) return false;
        LocalDate lowerBound = LocalDate.of(2009, 7, 1);
        LocalDate today = LocalDate.now();
        return date.isAfter(lowerBound) && date.isBefore(today);
    }
}
