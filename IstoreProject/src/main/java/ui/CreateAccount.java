package ui;

import java.util.Scanner;

public class CreateAccount {
    private final Scanner scanner;

    public CreateAccount() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        System.out.println("***********Create an account*********");
        System.out.print("Email: ");
        String mail = scanner.next();

        System.out.print("Enter Password: ");
        String password = scanner.next();

        System.out.print("Enter password again: ");
        String password2 = scanner.next();

        if (password.equals(password2)) {
            System.out.println("Successfully created account");
        } else {
            System.out.println("Passwords do not match. Please try again.");
        }
    }

}
