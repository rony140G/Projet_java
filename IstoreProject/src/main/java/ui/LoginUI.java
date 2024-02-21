package ui;

import infrastructure.*;
import java.util.Scanner;

public class LoginUI {
    private final Scanner scanner;
    private int userId;

    public LoginUI() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        System.out.println("********************  Page de connexion ********************");
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Mot de passe: ");
        String password = scanner.next();

        userId = ManageStoreDao.getUserID(email);

        if (UserDAO.loginAdmin(email, password)) {
            new AdminMenuUI().show();
        } else if (UserDAO.login(email, password)){

            StoreAccessResult storeAccessResult = StoreAccessResult.verifyIfIsInStore(userId);
            if (storeAccessResult.isInStore()) {
                new UserUI().ShowUserMenu(); // Afficher le menu utilisateur s'il est dans une boutique
            } else {
                System.out.println("Vous n'étes associez à aucune boutique , vous ne pouvez pas vous connecter."); // L'utilisateur n'est pas associé à une boutique
            }
        } else {
            System.out.println("Mail ou mots de passe incorect. Essayez plus tard.");
        }
    }
}