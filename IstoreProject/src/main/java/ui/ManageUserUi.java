package ui;

import infrastructure.*;
import usecase.*;

import java.util.Scanner;

public class ManageUserUi {
    private final Scanner scanner;
    private final infrastructure.ManageUserDao ManageUserDao;

    public ManageUserUi(){
        this.scanner = new Scanner(System.in);
        this.ManageUserDao = new ManageUserDao();
    }

    public void show() {
        boolean running = true;
        while(running) {
            System.out.println("********************* Outils de gestion des utilisateurs *******************");

            while (running) {
                System.out.println("Pour revenir, appuyez sur 0");
                System.out.println("Pour créer un utilisateur, appuyez sur 1");
                System.out.println("Pour voir tous les utilisateurs, appuyez sur 2");
                System.out.println("Pour supprimer un utilisateur, appuyez sur 3");
                System.out.println("Pour mettre à jour un utilisateur, appuyez sur 4");

                int choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        new CreateAccount().show();
                        break;
                    case 2:
                        new ManageUserDao().ViewUser();
                        break;
                    case 3:
                        deleteUser();
                        break;
                    case 4:
                        updateUser();
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                        break;
                }
            }
        }
    }

    private void deleteUser() {
        System.out.print("Entrez l'email à supprimer : ");
        String email = scanner.next();
        boolean isValidEmail = EmailValidator.isValidEmail(email);

        if (!isValidEmail) {
            System.out.println("Format d'email invalide. Veuillez entrer une adresse email valide.");
        } else {
            ManageUserDao.DeleteUser(email);
        }
    }

    private void updateUser(){
        System.out.println("Entrez l'ancien email : ");
        String oldEmail = scanner.next();
        boolean UserExiste = ManageUserDao.VerifyUserExiste(oldEmail);
        if (!UserExiste){
            System.out.println("Cet utilisateur ne fait pas partie de la base de données");
        } else {
            System.out.println("Entrez le nouveau courriel : ");
            String newEmail = scanner.next();

            System.out.println("Entrez le nouveau mot de passe : ");
            String Password = scanner.next();
            String newPassword = PasswordHashing.hashPassword(Password);

            System.out.println("Entrez le nouveau rôle : ");
            String newRole = scanner.next();

            System.out.println("Entrez le nouveau pseudo : ");
            String newPseudo = scanner.next();

            ManageUserDao.UpdateUser(oldEmail,newPassword, newEmail, newRole,newPseudo);
        }
    }
}
