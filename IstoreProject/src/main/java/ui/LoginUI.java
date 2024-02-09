package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class LoginUI {
    private final Scanner scanner;
    private final String url = "jdbc:mysql://localhost/Istore";
    private final String utilisateur = "root";
    private final String motDePasseDB = ""; // Mettez votre mot de passe MySQL ici

    public LoginUI() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        System.out.println("***********LOGIN*********");
        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        if (login(email, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    private boolean login(String email, String password) {
        String sql = "SELECT password FROM User WHERE Email = ?";
        String hashedPassword = null;

        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasseDB);
             PreparedStatement statement = connexion.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hashedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            } else {
                return false; // Aucun utilisateur trouvé avec cet email
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.show();
    }
}
