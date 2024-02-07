package ui;

import java.util.Scanner;

public class Main {
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choix = -1;
        while (choix != 0) {
            System.out.println("*********************iStore*******************");
            System.out.println("To log in, press 1");
            System.out.println("To create an account, press 2");
            System.out.println("To manage users, press 3"); // Nouveau choix
            System.out.println("To quit, press 0");

            choix = scanner.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Exiting program...");
                    break;
                case 1:
                    new LoginUI().show();
                    break;
                case 2:
                    new CreateAccount().show();
                    break;
                case 3:
                    manageUsers(); // Appel de la méthode pour gérer les utilisateurs
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Méthode pour gérer les utilisateurs
    private static void manageUsers() {
        // Instanciation de la classe concrète UserUIImpl
        UserUI userUI = new UserUIImpl();

        // Appel de la méthode pour afficher le menu de gestion des utilisateurs
        userUI.showUserManagementMenu();
    }

    // Interface utilisateur pour la gestion des utilisateurs
    public interface UserUI {
        void showUserManagementMenu();
    }

    // Implémentation concrète de l'interface utilisateur pour la gestion des utilisateurs
    public static class UserUIImpl implements UserUI {
        @Override
        public void showUserManagementMenu() {
            // Logique pour afficher le menu de gestion des utilisateurs
            System.out.println("Menu de gestion des utilisateurs:");
            System.out.println("1. Créer un utilisateur");
            System.out.println("2. Lire un utilisateur");
            System.out.println("3. Mettre à jour un utilisateur");
            System.out.println("4. Supprimer un utilisateur");
            // Ajoutez d'autres options de menu selon les besoins
        }
    }
}