package ui;

import infrastructure.ManageUserDao;
import infrastructure.PasswordHashing;
import infrastructure.lireUtilisateur;

import java.util.Scanner;

public class UserUI {
    Scanner scanner = new Scanner(System.in);
    int choix = -1;

    public void ShowUserMenu() {
        while (true) { // Boucle pour rester dans le menu jusqu'à ce que l'utilisateur choisisse de sortir
            System.out.println("Menu de gestion des utilisateurs:");
            System.out.println("1. Lire un utilisateur");
            System.out.println("2. Mettre à jour un utilisateur");
            System.out.println("3. Supprimer un utilisateur");
            System.out.println("0. Quitter");

            choix = scanner.nextInt();
            switch (choix) {
                case 0:
                    break;
                case 1:
                    new lireUtilisateur().lireUtilisateur();
                    break;
                case 2:
                    mettreAJourUtilisateur();
                    break;
                case 3:
                    supprimerUtilisateur();
                    break;
                default:
                    break;
            }
            // Logique pour afficher le menu de gestion des utilisateurs
            // Ajoutez d'autres options de menu selon les besoins
        }

    }
    private void mettreAJourUtilisateur() {
        // Demandez à l'utilisateur les informations de mise à jour
        System.out.println("Entrez votre email actuel :");
        String oldEmail = scanner.next();
        System.out.println("Entrez votre nouveau mot de passe :");
        String Password = scanner.next();
        String newPassword = PasswordHashing.hashPassword(Password);
        System.out.println("Entrez votre nouvel email :");
        String newEmail = scanner.next();
        System.out.println("Entrez votre nouveau rôle :");
        String newRole = scanner.next();
        System.out.println("Entrez votre nouveau pseudo :");
        String newPseudo = scanner.next();

        // Appeler la méthode UpdateUser pour mettre à jour l'utilisateur
        ManageUserDao manageUserDao = new ManageUserDao();
        manageUserDao.UpdateUser(oldEmail, newPassword, newEmail, newRole, newPseudo);
    }

    private void supprimerUtilisateur() {
        // Demandez à l'utilisateur son email pour confirmer la suppression du compte
        System.out.println("Entrez votre email pour confirmer la suppression de votre compte :");
        String email = scanner.next();

        // Appeler la méthode DeleteUser pour supprimer l'utilisateur
        ManageUserDao manageUserDao = new ManageUserDao();
        manageUserDao.DeleteUser(email);
    }
}

