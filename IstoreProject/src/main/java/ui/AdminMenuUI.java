package ui;

import java.util.Scanner;

import static ui.Main.clearConsole;


public class AdminMenuUI {

    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "*********************Admin Menu*******************" );
        int choix = -1;
        while (choix != 0){
            System.out.println( "To manage whitelist e-mails, press 1" );
            System.out.println( "To manage User, press 2" );
            System.out.println( "To Manage store press 3" );
            System.out.println( "Log out press 9");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    new WhiteListUI().show();
                    break;
                case 2:
                    new ManageUserUi().show();
                    break;
                case 3:
                    new ManageStoreUi().show();
                case 9:
                    clearConsole();
                    new Main();
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
