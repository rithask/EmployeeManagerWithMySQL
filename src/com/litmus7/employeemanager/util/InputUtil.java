package com.litmus7.employeemanager.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class InputUtil {

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            return scanner.nextLine();
        }
    }

    public static LocalDate readDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Use YYY-MM-DD");
            }
        }
    }

    public static boolean readBoolean(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String inputString = scanner.nextLine().trim().toLowerCase();
            if (inputString == "y") return true;
            if (inputString == "n") return false;
            System.out.println("Enter 'y' or 'n'");
        }
    }
}
