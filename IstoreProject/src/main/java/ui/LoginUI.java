package ui;

import infrastructure.*;

import java.util.Scanner;

public class LoginUI {
    private final Scanner scanner;

    public LoginUI() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        System.out.println("***********LOGIN*********");
        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        if (UserDAO.loginAdmin(email, password)) {
            new AdminMenuUI().show();
        } else if (UserDAO.login(email, password)){
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }
}
