package ui;

import infrastructure.DatabaseCo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionUtilisateurs {
    // Méthode pour mettre à jour un utilisateur
    public static void mettreAJourUtilisateur(String utilisateurConnecte, String email, String nouveauPseudo, String nouveauMotDePasse, String nouveauRole) {
        // Vérifier si l'utilisateur connecté peut mettre à jour le compte spécifié
        if (!peutMettreAJour(utilisateurConnecte, email)) {
            System.out.println("Vous n'avez pas les autorisations nécessaires pour mettre à jour ce compte.");
            return;
        }

        try (Connection connection = DatabaseCo.getConnection()) {
            // Vérifiez si l'utilisateur existe
            String selectQuery = "SELECT * FROM utilisateurs WHERE Email = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, email);
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Mettez à jour les informations de l'utilisateur
                        String updateQuery = "UPDATE utilisateurs SET Pseudo = ?, Password = ?, Role = ? WHERE Email = ?";
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                            updateStatement.setString(1, nouveauPseudo != null ? nouveauPseudo : resultSet.getString("Pseudo"));
                            updateStatement.setString(2, nouveauMotDePasse);
                            updateStatement.setString(3, nouveauRole);
                            updateStatement.setString(4, email);
                            updateStatement.executeUpdate();
                            System.out.println("Utilisateur mis à jour avec succès !");
                        }
                    } else {
                        System.out.println("L'utilisateur spécifié n'existe pas.");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + ex.getMessage());
        }
    }

    // Méthode pour vérifier si l'utilisateur connecté peut mettre à jour le compte spécifié
    private static boolean peutMettreAJour(String utilisateurConnecte, String email) {
        // Vérifiez si l'utilisateur connecté est administrateur
        boolean estAdmin = estAdministrateur(utilisateurConnecte);

        // L'utilisateur connecté peut mettre à jour son propre compte ou il est administrateur
        return utilisateurConnecte.equals(email) || estAdmin;
    }

    // Méthode pour vérifier si l'utilisateur connecté est administrateur
    private static boolean estAdministrateur(String utilisateurConnecte) {
        // Logique pour vérifier si l'utilisateur connecté est administrateur
        // Cette méthode pourrait consulter la base de données pour vérifier le rôle de l'utilisateur
        // Pour cet exemple, nous supposerons simplement que l'utilisateur connecté est administrateur
        return true;
    }
}
