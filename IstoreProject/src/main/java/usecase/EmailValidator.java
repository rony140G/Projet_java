package usecase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
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
