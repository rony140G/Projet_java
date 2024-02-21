package ui;


import infrastructure.ManageUserDao;
import usecase.PasswordHashing;
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
            System.out.println("4. Gérer le stock du magasin");
            System.out.println("0. Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 0:
                    running = false;
                    break;
                case 1:
                    new ManageUserDao().lireUtilisateur();
                    break;
                case 2:
                    mettreAJourUtilisateur();
                    break;
                case 3:
                    supprimerUtilisateur();
                    return;
                case 4:
                    GestionDuMagasin();
                    break;
                default:
                    System.out.println("ERREUR DE SAISIE ...");
                    break;
            }
        }

    }

    private void GestionDuMagasin() {
        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        while (choix != 0) {
            System.out.println("********************* Gestion du Magasin *******************");
            System.out.println("1. Gérer le stock du magasin");
            System.out.println("2. Voir les employés");
            System.out.println("0. Retour au menu précédent");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    // Appeler la méthode pour gérer le stock du magasin
                    // Exemple : gestionStockMagasin();
                    break;
                case 2:
                    // pour voire mes co emplyées
                    break;
                case 0:
                    System.out.println("Retour au menu précédent...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
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