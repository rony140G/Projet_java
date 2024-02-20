package ui;

import java.util.Scanner;
import usecase.EmailValidator;
import infrastructure.WhiteListDao;
import usecase.PasswordHashing;
import infrastructure.UserDAO;

public class CreateAccount {
    private final Scanner scanner;

    public CreateAccount() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        System.out.println("***********Create an account*********");

        String email = "";
        boolean isValidEmail = false;
        boolean whitelisted = false;

        while (!(isValidEmail && whitelisted)) {
            System.out.print("Email: ");
            email = scanner.next();
            isValidEmail = EmailValidator.isValidEmail(email);
            whitelisted = WhiteListDao.isWhitelisted(email);

            if (!isValidEmail) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            } else if (!whitelisted) {
                System.out.println("Email not whitelisted. You can't create an account.");
                return;
            }
        }

        System.out.print("Enter Password: ");
        String password = scanner.next();
        System.out.print("Enter password again: ");
        String password2 = scanner.next();
        String hashedPassword = PasswordHashing.hashPassword(password);

        if (password.equals(password2)) {
            if (UserDAO.insertUser(email, hashedPassword)) {
                System.out.println("Successfully created account");
            } else {
                System.out.println("Failed to create account. Please try again later.");
            }
        } else {
            System.out.println("Passwords do not match. Please try again.");
        }
    }
}
