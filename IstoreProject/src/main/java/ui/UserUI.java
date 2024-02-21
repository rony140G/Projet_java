package ui;

import infrastructure.ManageUserDao;
import usecase.PasswordHashing;
import infrastructure.LireUtilisateur;
import java.util.Scanner;
public class UserUI {
    Scanner scanner = new Scanner(System.in);
    int choix = -1;

    public void ShowUserMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Menu des utilisateurs:");
            System.out.println("1. Lire les utilisateurs");
            System.out.println("2. Se Mettre à jour");
            System.out.println("3. Se Supprimer");
            System.out.println("0. Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 0:
                    running = false;
                    break;
                case 1:
                    new LireUtilisateur().lireUtilisateur();
                    break;
                case 2:
                    mettreAJourUtilisateur();
                    break;
                case 3:
                    supprimerUtilisateur();
                    return;
                default:
                    break;
            }
        }

    }
    private void mettreAJourUtilisateur() {
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

        ManageUserDao manageUserDao = new ManageUserDao();
        manageUserDao.UpdateUser(oldEmail, newPassword, newEmail, newRole, newPseudo);
    }
    private void supprimerUtilisateur() {
        System.out.println("Entrez votre email pour confirmer la suppression de votre compte :");
        String email = scanner.next();

        ManageUserDao manageUserDao = new ManageUserDao();
        manageUserDao.DeleteUser(email);
    }
}