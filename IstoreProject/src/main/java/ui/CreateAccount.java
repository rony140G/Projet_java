package ui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount {
    private final Scanner scanner;

    public CreateAccount() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        System.out.println("***********Create an account*********");

        String email = "";
        boolean isValidEmail = false;

        while (!isValidEmail) {

            System.out.print("Email: ");
            email = scanner.next();


            isValidEmail = isValidEmail(email);

            if (!isValidEmail) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }

        System.out.print("Enter Password: ");
        String password = scanner.next();

        System.out.print("Enter password again: ");
        String password2 = scanner.next();

        if (password.equals(password2)) {
            System.out.println("Successfully created account");
        } else {
            System.out.println("Passwords do not match. Please try again.");
        }
    }


    private boolean isValidEmail(String email) {
        // Pattern pour la validation de l'email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // Compilation de la regex en Pattern
        Pattern pattern = Pattern.compile(regex);
        // Création d'un objet Matcher
        Matcher matcher = pattern.matcher(email);
        // Vérification si le format de l'email correspond à la regex
        return matcher.matches();
    }
}
