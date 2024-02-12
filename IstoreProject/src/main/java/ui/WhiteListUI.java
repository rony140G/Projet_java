package ui;

import infrastructure.DatabaseCo;
import usecase.EmailValidator;
import java.sql.*;
import java.util.Scanner;

public class WhiteListUI {
    private final Scanner scanner;

    public WhiteListUI() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("********************* WHITELIST E-MAILS *******************");
            System.out.println("Back to admin menu, press 0");
            System.out.println("Add e-mails to whitelist, press 1");
            System.out.println("Delete e-mails from whitelist, press 2");
            System.out.println("View whitelist, press 3");

            int choix = scanner.nextInt();

            switch (choix) {
                case 0:
                    running = false; // Quitte la boucle et revient au menu principal
                    break;
                case 1:
                    InsertMailToWhiteList();
                    break;
                case 2:
                    DeleteMailFromWhiteList();
                    break;
                case 3:
                    ViewWitelist();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void ViewWitelist() {
        String sql = "SELECT * FROM listeblanche";
        try (Connection connexion = DatabaseCo.getConnection();
             PreparedStatement statement = connexion.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Email: " + resultSet.getString("Email"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving whitelist: " + e.getMessage());
        }
    }

    private void DeleteMailFromWhiteList() {
        String email = "";
        boolean isValidEmail = false;

        while (!isValidEmail) {
            System.out.print("Enter the email to delete: ");
            email = scanner.next();
            isValidEmail = EmailValidator.isValidEmail(email);

            if (!isValidEmail) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }

        String sql = "DELETE FROM listeblanche WHERE Email = ?";
        try (Connection connexion = DatabaseCo.getConnection();
             PreparedStatement statement = connexion.prepareStatement(sql)) {
            statement.setString(1, email);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Email deleted from whitelist successfully.");
            } else {
                System.out.println("Failed to delete email from whitelist.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting email: " + e.getMessage());
        }
    }

    private void InsertMailToWhiteList() {
        String email = "";
        boolean isValidEmail = false;

        while (!isValidEmail) {
            System.out.print("Enter the email to add: ");
            email = scanner.next();
            isValidEmail = EmailValidator.isValidEmail(email);

            if (!isValidEmail) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }

        String sql = "INSERT INTO listeblanche (Email) VALUES (?)";
        try (Connection connexion = DatabaseCo.getConnection();
             PreparedStatement statement = connexion.prepareStatement(sql)) {
            statement.setString(1, email);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Email inserted into whitelist successfully.");
            } else {
                System.out.println("Failed to insert email into whitelist.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting email: " + e.getMessage());
        }
    }
}
