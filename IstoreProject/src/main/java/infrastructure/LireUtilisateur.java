package infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LireUtilisateur {
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