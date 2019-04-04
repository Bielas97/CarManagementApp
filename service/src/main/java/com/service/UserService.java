package com.service;

import com.exception.MyException;
import com.service.enums.SortCriterium;

import java.util.Scanner;

public class UserService {
    private Scanner scanner = new Scanner(System.in);

    public int getInt(String message) {
        System.out.println(message);

        String text = scanner.nextLine();
        if (!text.matches("\\d+")) {
            throw new MyException("Int value is not correct: " + text);
        }
        return Integer.parseInt(text);
    }

    public SortCriterium getSortCriterium() {
        System.out.println("1 - price");
        System.out.println("2 - mileage");
        System.out.println("3 - color");
        System.out.println("4 - model");
        System.out.println("Enter sort criterium option");
        String text = scanner.nextLine();

        if (!text.matches("[1-4]")) {
            throw new MyException("Sort criterium option is not valid: " + text);
        }
        return SortCriterium.values()[Integer.parseInt(text) - 1];
    }

    public boolean getBoolean(String message) {
        System.out.println(message + " [ y / n ]:");
        return scanner.nextLine().charAt(0) == 'y';
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
}
