package ui;

import infrastructure.*;
import usecase.EmailValidator;
import java.util.Scanner;

public class WhiteListUI {
    private final Scanner scanner;
    private final WhiteListDao whiteListDao;

    public WhiteListUI() {
        this.scanner = new Scanner(System.in);
        this.whiteListDao = new WhiteListDao();
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("********************* LISTE BLANCHE DES COURRIELS *******************");
            System.out.println("Pour revenir au menu administrateur, appuyez sur 0");
            System.out.println("Pour ajouter des e-mails à la liste blanche, appuyez sur 1");
            System.out.println("Pour supprimer des e-mails de la liste blanche, appuyez sur 2");
            System.out.println("Pour voir la liste blanche, appuyez sur 3");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    insertMailToWhiteList();
                    break;
                case 2:
                    deleteMailFromWhiteList();
                    break;
                case 3:
                    whiteListDao.viewWhiteList();
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    private void deleteMailFromWhiteList() {
        System.out.print("Entrez l'email à supprimer : ");
        String email = scanner.next();
        boolean isValidEmail = EmailValidator.isValidEmail(email);

        if (!isValidEmail) {
            System.out.println("Format d'email invalide. Veuillez entrer une adresse email valide.");
        } else {
            whiteListDao.deleteFromWhiteList(email);
        }
    }

    private void insertMailToWhiteList() {
        System.out.print("Entrez l'email à ajouter : ");
        String email = scanner.next();
        boolean isValidEmail = EmailValidator.isValidEmail(email);

        if (!isValidEmail) {
            System.out.println("Format d'email invalide. Veuillez entrer une adresse email valide.");
        } else {
            whiteListDao.insertIntoWhiteList(email);
        }
    }
}
