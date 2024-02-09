package infrastructure;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "password123";

        // Hacher le mot de passe
        String hashedPassword = hashPassword(password);
        System.out.println("Mot de passe haché : " + hashedPassword);

        // Vérifier le mot de passe
        boolean isValid = verifyPassword(password, hashedPassword);
        System.out.println("Mot de passe valide : " + isValid);
    }
}
