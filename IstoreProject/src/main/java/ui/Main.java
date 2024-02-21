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
            System.out.println("********************************* BOUTIQUE I-STOR *********************************");
            System.out.println("BIENVENUE DANS LA BOUTIQUE ISTORE !");
            System.out.println("1. POUR VOUS CONNECTER");
            System.out.println("2. POUR CREER UN COMPTE");
            System.out.println("0. POUR QUITTER");
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
                default:
                    System.out.println("OPTION INVALIDE. ESSAYEZ ENCORE.");
                    break;
            }
        }
        scanner.close();
    }
}
