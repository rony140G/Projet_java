package infrastructure;

import infrastructure.DatabaseCo;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean insertUser(String email, String password) {
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
    public static boolean login(String email, String password) {
        String sql = "SELECT password FROM User WHERE Email = ?";
        String hashedPassword;

        try (Connection connexion = DatabaseCo.getConnection();
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

    public static boolean loginAdmin(String email, String password) {
        String sql = "SELECT password, role FROM User WHERE Email = ?";
        String hashedPassword;
        String role;

        try (Connection connexion = DatabaseCo.getConnection();
             PreparedStatement statement = connexion.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hashedPassword = resultSet.getString("password");
                role = resultSet.getString("role");
                return BCrypt.checkpw(password, hashedPassword) && "Admin".equals(role);
            }
            return false; // Aucun utilisateur trouvé avec cet email ou mauvais mot de passe ou non admin
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
            return false;
        }
    }
}
