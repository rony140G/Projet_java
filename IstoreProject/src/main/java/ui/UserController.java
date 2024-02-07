package ui;

import entity.User;
import infrastructure.UserRepository;

import java.util.List;

public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public User readUser(int id) {
        return userRepository.readUser(id);
    }

    public List<User> readAllUsers() {
        return userRepository.readAllUsers();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }
}