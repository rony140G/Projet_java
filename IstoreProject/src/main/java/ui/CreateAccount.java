package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount {
    private final Scanner scanner;
    private final String url = "jdbc:mysql://localhost/Istore?";
    private final String utilisateur = "root";
    private final String motDePasse = "";
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
            isValidEmail = isValidEmail(email);

            if (!isValidEmail) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }

        System.out.print("Enter Password: ");
        String password = scanner.next();
      //  PasswordHack = PasswordHashing(password);
        System.out.print("Enter password again: ");
        String password2 = scanner.next();

        System.out.print("Enter Pseudo ");
        String pseudo = scanner.next();


        if (password.equals(password2)) {
            if (insertUser(email, password, pseudo)) {
                System.out.println("Successfully created account");
            } else {
                System.out.println("Failed to create account. Please try again later.");
            }
        } else {
            System.out.println("Passwords do not match. Please try again.");
        }
    }


    private boolean isValidEmail(String email) {
        // Pattern pour la validation de l'email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // Compilation de la regex en Pattern
        Pattern pattern = Pattern.compile(regex);
        // Création d'un objet Matcher
        Matcher matcher = pattern.matcher(email);
        // Vérification si le format de l'email correspond à la regex
        return matcher.matches();
    }
    private boolean insertUser(String email, String password, String pseudo) {
        String sql = "INSERT INTO User (Email, Password, Pseudo) VALUES (?, ?, ?)";

        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             PreparedStatement statement = connexion.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, pseudo);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            return false;
        }
    }
}
