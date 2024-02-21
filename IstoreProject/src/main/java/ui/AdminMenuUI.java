package ui;

import java.util.Scanner;
import static ui.Main.clearConsole;
public class  AdminMenuUI {
    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "*********************Menu administrateur*******************" );
        int choix = -1;
        while (choix != 0){
            System.out.println( "1. Pour gérer la liste blacnche." );
            System.out.println( "2. Pour gérer les utilisateurs" );
            System.out.println( "3. Pour gérer les boutiques" );
            System.out.println( "9. Pour ce déconnecter");
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
                    break;
                case 9:
                    clearConsole();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}