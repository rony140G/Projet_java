package ui;

import java.util.Scanner;
import usecase.EmailValidator;
import infrastructure.WhiteListDao;
import usecase.PasswordHashing;
import infrastructure.UserDAO;

public class CreateAccount {
    private final Scanner scanner;
    public CreateAccount() {
        this.scanner = new Scanner(System.in);
    }
    public void show() {
        System.out.println("***************** Créer un compte ***************");
        String email = "";
        boolean isValidEmail = false;
        boolean whitelisted = false;
        while (!(isValidEmail && whitelisted)) {
            System.out.print("Email: ");
            email = scanner.next();
            isValidEmail = EmailValidator.isValidEmail(email);
            whitelisted = WhiteListDao.isWhitelisted(email);
            if (!isValidEmail) {
                System.out.println("Format de l'adrress mail invalid. Entrez un mail valide s'il vous plait .");
            } else if (!whitelisted) {
                System.out.println("Cette adresse mail n'est pas sur la liste blache vous ne pouvez pas créer de compte.");
                return;
            }
        }
        System.out.print("Mot de passe : ");
        String password = scanner.next();
        System.out.print("Entrez une deuxiemme fois votre mot de passe : ");
        String password2 = scanner.next();
        String hashedPassword = PasswordHashing.hashPassword(password);
        if (password.equals(password2)) {
            if (UserDAO.insertUser(email, hashedPassword)) {
                System.out.println("Compte créer avec succèes");
            } else {
                System.out.println("Echec lors de la création du compte. Réesaayez plus tard.");
            }
        } else {
            System.out.println("Les mots de passe ne concordent pas.");
        }
    }
}