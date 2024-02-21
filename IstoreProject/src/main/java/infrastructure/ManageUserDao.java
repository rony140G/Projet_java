package infrastructure;

import java.sql.*;
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
                System.out.println("User deleted  successfully.");
            } else {
                System.out.println("Failed to delete User.");
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
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error verifying user existence: " + e.getMessage());
            return false;
        }
    }
    public void UpdateUser(String oldEmail,String newPassword, String newEmail, String newRole, String newPseudo) {
        String sql = "UPDATE User SET Email = ?,Password =?, role = ?, pseudo = ? WHERE Email = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newEmail);
            statement.setString(2, newPassword);
            statement.setString(3, newRole);
            statement.setString(4, newPseudo);
            statement.setString(5, oldEmail);

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
    public void lireUtilisateur() {
        try (Connection connection = DatabaseCo.getConnection())  {
            String query = "SELECT Email, Pseudo, Role FROM user WHERE Role != 'Admin'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("Utilisateurs enregistrés :");
                    while (resultSet.next()) {
                        String email = resultSet.getString("Email");
                        String pseudo = resultSet.getString("Pseudo");
                        String role = resultSet.getString("Role");
                        System.out.println("Email : " + email);
                        System.out.println("Pseudo : " + pseudo);
                        System.out.println("Role : " + role);
                        System.out.println();
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + ex.getMessage());
        }
    }
}