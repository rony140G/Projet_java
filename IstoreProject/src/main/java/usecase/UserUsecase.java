package usecase;

import entity.User;
import ui.UserController;

public class UserUsecase {
    private UserController userController;

    public UserUsecase(UserController userController) {
        this.userController = userController;
    }

    public void createNewUser(String email, String pseudo, String password, String role) {
        User user = new User();
        user.setEmail(email);
        user.setPseudo(pseudo);
        user.setPassword(password);
        user.setRole(role);

        userController.createUser(user);
    }

    public User getUser(int id) {
        return userController.readUser(id);
    }

    // Autres méthodes pour mettre à jour, lire et supprimer un utilisateur
}