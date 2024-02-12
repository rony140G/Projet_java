package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import infrastructure.DatabaseCo;
import usecase.EmailValidator;
import static infrastructure.PasswordHashing.hashPassword;

public class CreateAccount {
    private final Scanner scanner;
    public CreateAccount() {
        this.scanner = new Scanner(System.in);
    }
    public void show() {
        System.out.println("***********Create an account*********");

        String email = "";
        boolean isValidEmail = false;

        while (!isValidEmail) {

            System.out.print("Email: ");
            email = scanner.next();
            isValidEmail = EmailValidator.isValidEmail(email);

            if (!isValidEmail) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }

        System.out.print("Enter Password: ");
        String password = scanner.next();
        System.out.print("Enter password again: ");
        String password2 = scanner.next();
        String HashPwd = hashPassword(password);
      // System.out.print("Enter Pseudo ");//  String pseudo = scanner.next();


        if (password.equals(password2)) {
            if (insertUser(email, HashPwd)) {
                System.out.println("Successfully created account");
            } else {
                System.out.println("Failed to create account. Please try again later.");
            }
        } else {
            System.out.println("Passwords do not match. Please try again.");
        }
    }



    private boolean insertUser(String email, String password) {
        String sql = "INSERT INTO User (Email, Password) VALUES (?, ?)";

        try (Connection connexion = DatabaseCo.getConnection();
             PreparedStatement statement = connexion.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            return false;
        }
    }
}
