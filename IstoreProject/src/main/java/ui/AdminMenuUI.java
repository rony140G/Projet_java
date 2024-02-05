package ui;

import java.util.Scanner;

public class AdminMenuUI {

    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "*********************Menu Administrateur*******************" );
        int choix = -1;
        while (choix != 0){
            System.out.println( "Pour créer une saison entrez 1" );
            System.out.println( "Pour créer une journée entrez 2" );
            System.out.println( "Pour quitter l'application entrez 0" );
            choix = scanner.nextInt();
            if(choix == 1) ;
            if(choix == 2) ;
        }
    }
}
