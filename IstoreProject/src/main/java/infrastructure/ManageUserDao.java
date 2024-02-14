package infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageUserDao {
    public void ViewUser() {
        String sql = "SELECT * FROM User";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println( "Id : " + resultSet.getString("Id") +" ;Email: " + resultSet.getString("Email")+ " ;Role : " + resultSet.getString("role")
                + " ;pseudo : " + resultSet.getString("pseudo"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving whitelist: " + e.getMessage());
        }
    }

    public void DeleteUser(String email) {
        String sql = "DELETE FROM User WHERE Email = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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

    public boolean VerifyUserExiste(String email) {
        String sql = "SELECT * FROM User WHERE Email = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error verifying user existence: " + e.getMessage());
            return false;
        }
    }

    public void UpdateUser(String oldEmail, String newEmail, String newRole, String newPseudo) {
        String sql = "UPDATE User SET Email = ?, role = ?, pseudo = ? WHERE Email = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newEmail);
            statement.setString(2, newRole);
            statement.setString(3, newPseudo);
            statement.setString(4, oldEmail);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("Failed to update user information.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating user information: " + e.getMessage());
        }
    }
}