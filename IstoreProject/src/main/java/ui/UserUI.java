package ui;

import java.util.Scanner;

public class UserUI {
    Scanner scanner = new Scanner(System.in);
    int choix = -1;
    public void ShowUserManagementMenu() {
        System.out.println("Menu de gestion des utilisateurs:");
        System.out.println("1. Créer un utilisateur");
        System.out.println("2. Lire un utilisateur");
        System.out.println("3. Mettre à jour un utilisateur");
        System.out.println("4. Supprimer un utilisateur");

        choix = scanner.nextInt();
        switch (choix) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        // Logique pour afficher le menu de gestion des utilisateurs
          // Ajoutez d'autres options de menu selon les besoins
    }

}

