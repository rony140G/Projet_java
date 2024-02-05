package ui;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "*********************iStore*******************" );
        int choix = -1;
        while (choix != 0){
            System.out.println( "Pour créer un compte 1" );
            System.out.println( "Pour se connecter 2" );
            choix = scanner.nextInt();
            if(choix == 1) new CreateAccount().show();
            if(choix == 2) new LoginUI().show();
        }

    }
}

