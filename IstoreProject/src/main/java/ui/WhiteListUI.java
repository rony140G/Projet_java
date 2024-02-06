package ui;

import java.util.Scanner;

public class WhiteListUI {
    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "*********************WHITELIST E-MAILS*******************" );
        int choix = -1;
        System.out.println( "Add e-mails to whitelist , press 1" );
        System.out.println( "Delete e-mails to whitelist , press 2" );
        System.out.println( "To create and delete a new item in the inventory press 9" );
        choix = scanner.nextInt();
        if(choix == 1) new WhiteListUI().show();
        if(choix == 2) new WhiteListUI().show();
        if(choix == 9) new AdminMenuUI().show();
    }
}
