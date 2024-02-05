package ui;

import java.util.Scanner;

public class AdminMenuUI {

    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "*********************Admin Menu*******************" );
        int choix = -1;
        while (choix != 0){
            System.out.println( "To whitelist e-mails, press 1" );
            System.out.println( "To update or delete an employee's account, press 2" );
            System.out.println( "To create a new store press 3" );
            System.out.println( "To create and delete a new item in the inventory press 4" );
            choix = scanner.nextInt();
            if(choix == 1) ;
            if(choix == 2) ;
        }
    }
}
