package ui;
import infrastructure.DatabaseCo;
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
            System.out.println("Welcome to Istore Market !");
            System.out.println("To log in, press 1");
            System.out.println("To create an account, press 2");
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
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}